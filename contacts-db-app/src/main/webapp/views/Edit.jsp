<%@page import="Model.Contacts"%>
<%@page import="service.ContactService"%>
<%@page import="service.StateService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
<link rel="stylesheet" href="/contacts-db-app/views/bootstrap.css">
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function(){	
			$("#logOut").click(function(){
				$("#logOutForm").submit();
				})	
			})	
	</script>

<%
String user=(String)session.getAttribute("user");
if(user==null){
	user="";
}

if(request.getParameter("logged") != null){
	session.invalidate(); 
	response.sendRedirect("/contacts-db-app/Contacts"); 
}
%>
	<div>
		<span class="nav-item"><a href="/contacts-db-app/Contacts">Contacts</a></span>&nbsp;&nbsp;
		<span class="nav-item"><a href="/contacts-db-app/Add">Add</a></span>
		
		<span class="nav-item"><a><%=user %></a></span>	&nbsp;&nbsp;
		<%
		if(!user.equals("")){
						
			%>
			<form id="logOutForm" method="post">
				<input type="hidden" name="logged">
				<input type="button" value="Log Out" id="logOut">
			</form>			
			<%
		}		
		%>	
	</div>
	


	<%	
		String id=request.getParameter("id");
	
	ContactService contactService = new ContactService();

		Contacts contact = contactService.select(id);

		String stateList[]=new StateService().getState();
		String name=contact.getName();
		String email=contact.getEmail();
		String gender=contact.getGender();
		String state=contact.getState();
		String check1="",check2="";
		if(gender.equals("male")){
			check1="checked";
		}else{
			check2="checked";
		}
		
		
		 if(request.getParameter("logged") != null){
		    	session.invalidate(); 
		    	response.sendRedirect("/contacts-db-app/Contacts"); 
		    }

	%>
	
	<form class="form-group" action="Edit" method="post">
		Id : <label class="form-control" ><%=id%></label>
		<input name="editId" value="<%=id %>" hidden> <br>
		Name :<input class="form-control" type="text" name="editName" value="<%=name%>"><br>
		Email:<input class="form-control" type="email" name="editEmail" value="<%=email%>"><br>
		State:<select name="editState" class="form-control" size="1" >
				<%for(int i=0;i<stateList.length;i++){
					String select[]=new String[stateList.length];
					if(state.equals(stateList[i])){
						select[i]="selected";
					}
				%>
				<option value="<%=stateList[i]%>" <%=select[i]%>><%=stateList[i]%></option>
				<%	
					}
				%>			  
			  </select><br>
		
		Gender:&nbsp;&nbsp;<input type="radio" name="editGender" value="male" <%=check1 %>>Male&nbsp;&nbsp; 
		<input type="radio" name="editGender" value="female" <%=check2 %>>Female<br><br>
		
		<input type="submit" class="btn btn-default" value="Update">
	</form>

</body>
</html>