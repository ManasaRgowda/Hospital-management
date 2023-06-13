<%@page import="java.util.Base64"%>
<%@page import="dto.Patient"%>
<%@page import="dao.HospitalDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit patient details</title>
</head>
<body>
<%int id=Integer.parseInt(request.getParameter("id"));%>

<%
HospitalDao dao=new HospitalDao();
Patient dto=dao.fetch1(id);
%>
<form action="edit" method="post" enctype="multipart/form-data">

Name:<input type="text" name="name" value="<%=dto.getName()%>"><br>
Id:<input type="number" name="id" value="<%=dto.getId()%>" readonly="readonly"><br>
Mobile:<input type="" name="mobile" value="<%=dto.getMobile()%>"><br>
DOB:<input type="date" name="dob" value="<%=dto.getDob()%>"><br>
Picture:<input type="file" name="picture" value="<%=dto.getPicture()%>"><br>
 
<%--  <%
 String base64=Base64.getEncoder().encodeToString(dto.getPicture());
%>
Picture:<input type="file" name="picture" value="<img alt="unknown" src="data:image/jpeg;base64,<%=base64%>">">
<br>
 --%><button>cancel</button>
<button>Save</button>

</form>
</body>
</html>