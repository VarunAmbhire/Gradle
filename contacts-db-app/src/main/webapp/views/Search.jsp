<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Contacts"%>
<%@page import="java.util.List"%>
<table class="table table-striped">
	<tr>
		<td>Id</td>
		<td>Name</td>
		<td>Email</td>
		<td>State</td>
		<td>Gender</td>
	</tr>
	<%		
		List<Contacts>list=new ArrayList<>();
		list=(List<Contacts>)request.getAttribute("searchList");
		
		Iterator<Contacts>it=list.iterator();
		
		while(it.hasNext()){
			Contacts cont=(Contacts)it.next();
			%>
			<tr>
				<td><%=cont.getId() %></td>
				<td><%=cont.getName() %></td>
				<td><%=cont.getEmail() %></td>
				<td><%=cont.getState() %></td>
				<td><%=cont.getGender() %></td>
			</tr>
			<% } %>
</table>