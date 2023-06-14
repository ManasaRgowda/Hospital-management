<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book appointment</title>
</head>
<body>
<% if(session.getAttribute("staff")==null)
	{
	response.getWriter().print("<h1 style='color:red'>Session expaired</h1>");
	request.getRequestDispatcher("login1.html").include(request,response);
	}
	else{
	%>
	<a href="fetchAllPatient"><button>View All Patient</button></a><br><br>
	<a href="fetchById.html"><button>Fetch Patient By Id</button></a><br><br>
	<%} %>
	<br>
	<br>
	<a herf="staffHome.html"><button>Back</button></a>
</body>
</html>