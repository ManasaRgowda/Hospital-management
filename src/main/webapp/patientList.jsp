<%@page import="org.apache.commons.codec.binary.Base64"%>
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
<th>Book Appointment</th>
<th>Edit</th>
</tr> 
    
<%for(Patient pt1:patient) {%>
<tr>
<th><%=pt1.getId()%></th>
<th><%=pt1.getName() %></th>
<th><%=pt1.getMobile()%></th>
<th><%=pt1.getAge()%></th>
<th><%
String base64=Base64.encodeBase64String(pt1.getPicture());
%>
<img height=100px; width=100px; alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></th>
<th><a href="appointmentForm.jsp?id=<%=pt1.getId()%>"><button>Book Appointment</button></a></th>
<th><a href="edit.jsp?id=<%=pt1.getId()%>"><button>Edit details</button></a></th>
</tr>
<%} %>
</table>
<br>
<a href="login1.html"><button>Back</button></a>
</body>
</html>