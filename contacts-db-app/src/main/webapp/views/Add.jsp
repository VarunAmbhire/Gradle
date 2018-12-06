<%@page import="java.util.UUID"%>
<%@page import="service.StateService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

if(request.getParameter("logged") != null){
	session.invalidate(); 
	response.sendRedirect("/contacts-db-app/Contacts"); 
}

%>
	<div>
		<span class="nav-item"><a href="/contacts-db-app/">Contacts</a></span>&nbsp;&nbsp;
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
	UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
	String id=uid.randomUUID().toString();
	
	String[] stateList=new StateService().getState();
	
	%>

	<div>
		<form class="form-group" action="Add" method="post">
		Id : <label class="form-control" ><%=id%></label>
		<input name="contactId" value="<%=id %>" hidden> <br>
		Name :<input class="form-control" type="text" name="contactName" ><br>
		Email:<input class="form-control" type="email" name="contactEmail"><br>
		
		State:<select name="contactState" class="form-control" size="1">
				<%for(int i=0;i<stateList.length;i++){
					%><option value="<%=stateList[i]%>"><%=stateList[i]%></option><%	
					}
				%>			  
			  </select><br>		
		Gender:&nbsp;&nbsp;<input type="radio" name="contactGender" value="male">Male&nbsp;&nbsp; 
		<input type="radio" name="contactGender" value="female">Female<br><br>
		
		<input type="submit" class="btn btn-default" value="Add">
	</form>
	</div>
</body>
</html>