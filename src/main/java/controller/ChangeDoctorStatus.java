package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;

@WebServlet("/changeDoctorStatus")
public class ChangeDoctorStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	          int id=Integer.parseInt(req.getParameter("id"));
	          HospitalDao dao= new HospitalDao();
	          Doctor dtr=dao.fetchDoctor(id);
	          
	          if(dtr.isStatus()) {
	        	  dtr.setStatus(false);
	          }
	          else {
	        	  dtr.setStatus(true);
	          }
	          
	          dao.updatedoctor(dtr);
	        
	        resp.getWriter().print("<h1 style='color:green'>Status updated succesfully</h1>");
	        req.setAttribute("doctor", dao.fetchAllDoctor());
	      	req.getRequestDispatcher("approveDoctor.jsp").include(req, resp);      
	}
}
