package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Staff;

@WebServlet("/staffsignup")
public class StaffSignup extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

				
		Staff staff1=new Staff();
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String gender=req.getParameter("gender");
		Date dob=Date.valueOf(req.getParameter("dob"));
		
		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		
		HospitalDao dao=new HospitalDao();
		
		if(dao.fetchStaff(mobile)==null && dao.fetchStaff(email)==null && dao.fetchDoctor(mobile)==null && dao.fetchDoctor(email)==null)
		{	
		staff1.setName(name);
		staff1.setMobile(mobile);
		staff1.setDob(dob);
		staff1.setEmail(email);
		staff1.setGender(gender);
		staff1.setPassword(password);
		staff1.setAge(age);
		
		dao.saveStaff(staff1);
		
		resp.getWriter().print("<h1 style='color:green'><center>Staff account created succesfully, wait for Admin approval </center></h1>");
		resp.getWriter().print("<h1><center>your staff id is:"+staff1.getId()+"<center></h1>");
	    req.getRequestDispatcher("login1.html").include(req, resp);
	}
		else{
			resp.getWriter().print("<h1 style='color:Red'><center>Mobile/Email already exists</center></h1>");
		    req.getRequestDispatcher("staffSignup.html").include(req, resp);
		}
}
}
