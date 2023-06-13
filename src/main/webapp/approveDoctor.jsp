<%@page import="dto.Doctor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List<Doctor> dto1=(List<Doctor>) request.getAttribute("doctor"); %>
<table border="">
<tr>
<th>Id</th>
<th>Name</th>
<th>Mobile</th>
<th>Age</th>
<th>Qualification</th>
<th>Specialization</th>
<th>Status</th>
<th>Change status</th>
</tr> 
    
<%for(Doctor dtr:dto1) {%>
<tr>
<th><%=dtr.getId()%></th>
<th><%=dtr.getName() %></th>
<th><%=dtr.getMobile()%></th>
<th><%=dtr.getAge()%></th>
<th><%=dtr.getQualification()%></th>
<th><%=dtr.getSpecialization()%></th>
<th><%=dtr.isStatus()%></th>
<th><a href="changeDoctorStatus?id=<%=dtr.getId()%>"><button>Change</button></a></th>

</tr>
<%} %>
</table>
</body>
</html>