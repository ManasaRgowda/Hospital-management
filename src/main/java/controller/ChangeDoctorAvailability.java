package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;

@WebServlet("/changeDoctorAvailability")
public class ChangeDoctorAvailability extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
   Doctor doctor=(Doctor) req.getSession().getAttribute("doctor");
   
	HospitalDao dao= new HospitalDao();

   if(doctor==null) {
	   resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
		req.getRequestDispatcher("login1.html").include(req, resp);      
   }
   else {
    
    if(doctor.isAvailabile()) {
  	  doctor.setAvailabile(false);
      dao.updatedoctor(doctor);
      req.getSession().setAttribute("doctor", doctor);
      resp.getWriter().print("<h1 style='color:white'>Change to not Available</h1>");
	  req.getRequestDispatcher("login1.html").include(req, resp);      


    }
    else {
  	  doctor.setAvailabile(true);
  	dao.updatedoctor(doctor);
    req.getSession().setAttribute("doctor", doctor);
    resp.getWriter().print("<h1 style='color:white'>Change to Available</h1>");
	req.getRequestDispatcher("login1.html").include(req, resp);      
    }
   }

	}
}