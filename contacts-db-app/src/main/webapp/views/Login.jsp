<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logging Page</title>
<link rel="stylesheet" href="/contacts-db-app/views/bootstrap.css">
</head>
<body>


	<%
	String user=(String)session.getAttribute("user");
	String loc=request.getParameter("backTo");
	String id=request.getParameter("id");

	if(user==null){
		user="";
	}
	%>
	
	<div>
		<span class="nav-item"><a href="/contacts-db-app/Contacts">Contacts</a></span>&nbsp;&nbsp;
			
	</div>
	<form class="form-group" action="/contacts-db-app/Login" method="post">
		User Name : <input type="text" name="userName" class="form-control">
		Password  : <input type="password" name="password" class="form-control"><br>
		<input hidden=true name="loc" value="<%=loc+"?id="+id %>">
		<input type="submit" class="btn btn-primary" value="Log In">
		<input type="button" class="btn btn-primary" onclick="" value="cancel">	 
	</form>
</body>
</html>