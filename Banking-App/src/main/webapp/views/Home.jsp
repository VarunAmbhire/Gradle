<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.*"%>
<%@page import="com.opencsv.CSVWriter"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="model.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="services.DatabaseService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body class="container">
	<script type="text/javascript">
		function init() {
			var transactionDiv = document.getElementById("displayTrans");
			var passBookDiv = document.getElementById("passbook");
			transactionDiv.style.display = "none";
			passBookDiv.style.display = "none";
		}

		window.onload = init;

		function doTransaction() {
			var transactionDiv = document.getElementById("displayTrans");
			var passBookDiv = document.getElementById("passbook");
			transactionDiv.style.display = "block";
			passBookDiv.style.display = "none";
		}

		function getPassbook() {
			var transactionDiv = document.getElementById("displayTrans");
			var passBookDiv = document.getElementById("passbook");
			transactionDiv.style.display = "none";
			passBookDiv.style.display = "block";
		}

		function logOut() {
			document.getElementById("logOutForm").submit()
		}
	</script>
	<%
		String customerID = session.getAttribute("id").toString();

		if (request.getParameter("logged") != null) {
			session.invalidate();
			response.sendRedirect("Login");
		}
	%>
	<div class="nav justify-content-end">
		<p class="nav-item">
			Welcome User :
			<%=customerID%></p>

		<%
			if (!customerID.equals("")) {
		%>
		<form class="nav-item" id="logOutForm" method="get">
			<input type="hidden" name="logged"> <input type="button"
				class="btn btn-link" value="Log Out" onclick="logOut()">
		</form>
		<%
			}
		%>
	</div>
	<br>
	<br>
	<div class="justify-content-center">
		<input class="btn btn-primary" type="button" onclick="getPassbook()"
			value="Passbook">&nbsp;&nbsp;&nbsp; 
		<input type="button" class="btn btn-primary" onclick="doTransaction()"
			value="Do Transaction">	
	</div>
	<br>
	<br>

	<div class="card">
		<div id="passbook">
			<table class="table">
				<tr>
					<td>Transaction Id</td>
					<td>Name</td>
					<td>Amount</td>
					<td>Type</td>
					<td>Date</td>
				</tr>
				<c:forEach items="${requestScope.list}" var="transaction">
					<tr>
						<td><c:out value="${transaction.getTransactionId()}"></c:out></td>
						<td><c:out value="${transaction.getName()}"></c:out></td>
						<td><c:out value="${transaction.getAmount()}"></c:out></td>
						<td><c:out value="${transaction.getType()}"></c:out></td>
						<td><c:out value="${transaction.getDate()}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			
			<a href="Download">Download</a>
		</div>

		<div id="displayTrans">
			<form class="form-group" action="Home" method="post">
				Add Amount :<input class="form-control" type="number" name="amount">
				Operation :<select class="form-control" name="transactionMethod">
					<option>Deposit</option>
					<option>Withdraw</option>
				</select> <input type="hidden" name="id" value="<%=customerID%>"> <input
					type="hidden" name="name"
					value="${requestScope.list.get(0).getName()}"> <input
					class="form-control btn btn-primary" type="submit"
					value="Transaction">
			</form>
		</div>
	</div>
</body>
</html>