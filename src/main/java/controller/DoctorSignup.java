package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.OneToMany;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;

@WebServlet("/doctorsignup")
public class DoctorSignup extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		Doctor dtr=new Doctor();

		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String gender=req.getParameter("gender");
		Date dob=Date.valueOf(req.getParameter("dob"));
		String specialization= req.getParameter("specialization");
		String qualification= req.getParameter("qualification");

		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		
		HospitalDao dao=new HospitalDao();

		
	if(dao.fetchDoctor(mobile)==null && dao.fetchDoctor(email)==null && dao.fetchStaff(mobile)==null && dao.fetchStaff(email)==null)
	  {
		dtr.setName(name);
		dtr.setMobile(mobile);
		dtr.setDob(dob);
		dtr.setEmail(email);
		dtr.setGender(gender);
		dtr.setPassword(password);
		dtr.setAge(age);
		dtr.setSpecialization(specialization);
		dtr.setQualification(qualification);
		
		dao.saveDoctor(dtr);
		
		resp.getWriter().print("<h1 style='color:green'><center>Doctor account created succesfully wait for admin approval</center></h1>");
		resp.getWriter().print("<h1><center>your Doctor id is:"+dtr.getId()+"<center></h1>");
	    req.getRequestDispatcher("login1.html").include(req, resp);
	}
	else{
		resp.getWriter().print("<h1 style='color:red'><center>Mobile/Email already exists</center></h1>");
	    req.getRequestDispatcher("doctorSignup.html").include(req, resp);
	}

}
}