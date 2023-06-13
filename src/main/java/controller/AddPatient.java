package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.HospitalDao;
import dto.Patient;

@WebServlet("/addPatient")
@MultipartConfig    //to add pictures
public class AddPatient extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		Date dob=Date.valueOf(req.getParameter("dob"));
		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		
		Part picture= req.getPart("picture");
		byte[] image=new byte[picture.getInputStream().available()];
        picture.getInputStream().read(image);
        
		HospitalDao dao=new HospitalDao();
		
		Patient patient= new Patient();
		
		Patient patient1= dao.fetchpatient(mobile);
		
		if(patient1==null) 
	  {
		patient.setName(name);
		patient.setAge(age);
		patient.setDob(dob);
		patient.setMobile(mobile);
		patient.setPicture(image);

        dao.savePatient(patient);

		resp.getWriter().print("<h1 style='color:green'><center>Added Patient</center></h1>");
        req.getRequestDispatcher("staffHome.html").include(req, resp);
	  }
	else {
		resp.getWriter().print("<h1 style='color:red'><center>mobile number already exists</center></h1>");
        req.getRequestDispatcher("addPatient.html").include(req, resp);

	}
}
}