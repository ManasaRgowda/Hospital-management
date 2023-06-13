<%@page import="java.util.Base64"%>
<%@page import="dto.Patient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient list</title>
</head>
<body>
<% List<Patient> patient=(List<Patient>) request.getAttribute("patient"); %>  <!-- getAttribute is a object type -->
<table border="">
<tr>
<th>Id</th>
<th>Name</th>
<th>Mobile</th>
<th>Age</th>
<th>Picture</th>
<th>Edit</th>
<th>Book Appointment</th>
</tr> 
    
<%for(Patient pt1:patient) {%>
<tr>
<th><%=pt1.getId()%></th>
<th><%=pt1.getName() %></th>
<th><%=pt1.getMobile()%></th>
<th><%=pt1.getAge()%></th>
<th><%
String base64=Base64.getEncoder().encodeToString(pt1.getPicture());
%>
<img alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></th>
<th><a href="edit.jsp?id=<%=pt1.getId()%>"><button>Edit</button></a></th>  
<th><button>Book</button></th>

</tr>
<%} %>
</table>
</body>
</html>