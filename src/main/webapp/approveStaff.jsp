<%@page import="java.util.List"%>
<%@page import="dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approve Staff</title>
</head>
<body>
<% List<Staff> dto=(List<Staff>) request.getAttribute("staff"); %>

<table border="">
<tr>
<th>Id</th>
<th>Name</th>
<th>Mobile</th>
<th>Age</th>
<th>Status</th>
<th>Change Status</th>
</tr> 
    
<%for(Staff stf:dto) {%>
<tr>
<th><%=stf.getId()%></th>
<th><%=stf.getName() %></th>
<th><%=stf.getMobile()%></th>
<th><%=stf.getAge()%></th>
<th><button><%=stf.isStatus()%></button></th>
<th><a href="changeStaffStatus?id=<%=stf.getId()%>"><button>Change</button></a></th>

</tr>
<%} %>
</table>

</body>
</html>