<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body class="container">
	<script>
		function chkPassword() {
			var pass1 = document.getElementById("password").value
			var pass2 = document.getElementById("confirmPassword").value
			console.log(pass1 + " " + pass2)
			if (pass1 != null && pass2 != null && pass1 == pass2) {
				return true
			}
			document.getElementById("errMsg").value="Password doesn't match"
			return false
		}
	</script>

	<%
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String id = uid.randomUUID().toString();
		Date d = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(d);
	%>
	<div>
		<form class="form-group" action="/Banking-App/Register" method="post"
			onsubmit="return chkPassword()">
			Customer Id :<input type="text" class="form-control"
				name="customerId" value="<%=id%>"><br> Name :<input
				class="form-control" type="text" name="name"><br>
			Balance :<input class="form-control" type="number" name="balance"><br>
			Date :<input type="text" class="form-control" name="date"
				value="<%=date%>"><br> Password :<input
				class="form-control" type="password" name="password" id="password"><br>
			Confirm Password :<input class="form-control" type="password"
				name="confirmPassword" id="confirmPassword"><br> <br>
				<p id="errMsg"></p>
			<input class="form-control btn btn-primary" type="submit"
				value="Register">

		</form>
	</div>


</body>
</html>