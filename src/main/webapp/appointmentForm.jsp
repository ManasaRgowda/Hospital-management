<%@page import="dto.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="dto.Staff"%>
<%@page import="dto.Patient"%>
<%@page import="dao.HospitalDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>appointment form</title>
</head>
<body>
	<% Staff staff=(Staff)session.getAttribute("staff");

int pid=Integer.parseInt(request.getParameter("id"));

HospitalDao dao=new HospitalDao();
Patient patient=dao.fetchPatientById(pid);

if(patient==null){
	response.getWriter().print("<h1 style='color:red'>Enter proper Patient Id</h1>");
	request.getRequestDispatcher("fetchById.html").include(request, response);
}
else{
	List<Doctor> doctor=dao.fetchAvailableDoctor();
	if(doctor.isEmpty())
	{
		response.getWriter().print("<h1 style='color:red'>No Doctors are Available</h1>");
		request.getRequestDispatcher("staffHome.html").include(request, response);
	}
	else{
%>
	<form>
		Patient ID:<input type="text" name="pid" value="<%=pid%>" readonly><br>
		Patient Name:<input type="text" name="pname"
			value="<%=patient.getName()%>" readonly><br> Staff Name:<input
			type="text" name="staffname" value="<%=staff.getName()%>" readonly><br>
		Problem:<input type="text" name="problem"><br> Select
		Doctor: <select name="doctor">
		<%for(Doctor dtr:doctor){ %>
		<option value="<%=dtr.getId()%>"><%=dtr.getName()%>(<%=dtr.getSpecialization()%>)</option>
		<%} %>
		</select>
	<%}} %>
		
	</form>
</body>
</html>