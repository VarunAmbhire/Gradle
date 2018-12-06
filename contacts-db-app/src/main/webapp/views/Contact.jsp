<%@page import="listeners.ActiveUserListener"%>
<%@page import="service.ContactService"%>
<%@page import="Model.Contacts"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contacts</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>

<body class="container">

	<script type="text/javascript">
		function deleteMsg() {
			if (confirm("Do u really want to Delete???")) {
				return true;
			} else {
				return false;
			}
		}

		$(document).ready(
				function() {
					$("#btnsearch").click(
							function() {
								var text = $("#search").val()
								$("#tableDisplay").html("<h1>loading...</h1>");
								$.post("/contacts-db-app/Search?text=" + text,
										function(data, statusCode) {
											console.log(statusCode)
											$("#tableDisplay").html(data);
										})
							})

					$("#logOut").click(function() {
						$("#logOutForm").submit();
					})
				})
	</script>
	<%
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date();

		String user = (String) session.getAttribute("user");
		if (user == null) {
			user = "";
		}

		if (request.getParameter("logged") != null) {
			session.invalidate();
			response.sendRedirect("/contacts-db-app/Contacts");
		}
	%>
	<div>
		<span class="nav-item"><a href="/contacts-db-app/Contacts">Contacts</a></span>&nbsp;&nbsp;
		<span class="nav-item"><a href="/contacts-db-app/Add">Add</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="nav-item"><a><%=new Timestamp(date.getTime())%></a></span>&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="nav-item"><a><%=user%></a></span> &nbsp;&nbsp; <span>Active
			User : <%=request.getAttribute("activeUser")%></span>
		<%
			if (!user.equals("")) {
		%>
		<form id="logOutForm" method="post">
			<input type="hidden" name="logged"> <input type="button"
				value="Log Out" id="logOut">
		</form>
		<%
			}
		%>

	</div>

	<div>
		Search : <input type="text" id="search"> &nbsp;
		<button id="btnsearch" class="btn btn-primary">Search</button>
	</div>
	<br>
	<br>

	<div id="tableDisplay">
		<table class="table table-striped">
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Email</td>
				<td>State</td>
				<td>Gender</td>
			</tr>

			<%
				List<Contacts> list = new ArrayList<>();
				list = (List<Contacts>) request.getAttribute("list");
				Iterator<Contacts> it = list.iterator();

				while (it.hasNext()) {
					Contacts cont = (Contacts) it.next();
			%>
			<tr>
				<td><%=cont.getId()%></td>
				<td><%=cont.getName()%></td>
				<td><%=cont.getEmail()%></td>
				<td><%=cont.getState()%></td>
				<td><%=cont.getGender()%></td>
				<td>
					<form action="Edit" method="get">
						<input name="id" value=<%=cont.getId()%> hidden> <input
							type="submit" class="btn btn-primary" value="Edit">
					</form>
				</td>
				<td>
					<form action="Delete" method="get" onSubmit="return deleteMsg()">
						<input name="id" value=<%=cont.getId()%> hidden> <input
							class="btn btn-primary" type="submit" value="Delete">
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>