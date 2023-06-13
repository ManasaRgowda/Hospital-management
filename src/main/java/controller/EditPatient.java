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

@WebServlet("/edit")
@MultipartConfig    	
public class EditPatient extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	Patient dto=new Patient();
	dto.setName(req.getParameter("name"));
	dto.setId(Integer.parseInt(req.getParameter("id")));
	dto.setMobile(Long.parseLong(req.getParameter("mobile")));
	Date dob=Date.valueOf(req.getParameter("dob"));
	int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	dto.setAge(age);
	dto.setDob(dob);
	
	Part picture= req.getPart("picture");
	byte[] image=new byte[picture.getInputStream().available()];
    picture.getInputStream().read(image);
	dto.setPicture(image);

	HospitalDao dao=new HospitalDao();
	dao.update(dto);
	
	resp.getWriter().print("<h1>data updated successfully</h1>");
    req.getRequestDispatcher("home1.html").include(req, resp);
}
}