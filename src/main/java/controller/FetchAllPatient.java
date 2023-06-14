package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;
import dto.Patient;

@WebServlet("/fetchAllPatient")
public class FetchAllPatient extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 if(req.getSession().getAttribute("staff")==null) 
        {    	
		 resp.getWriter().print("<h1 style='color:red'>Session Expired,please login</h1>");
	     	req.getRequestDispatcher("login1.html").include(req, resp);
         }
	 else {         
	     HospitalDao dao=new HospitalDao();
         List<Patient> list= dao.fetchAllPatient();
   	
  	     if(list.isEmpty())
  	     {
  		    resp.getWriter().print("<h1 style='color:red'>No patient data found</h1>");
      	    req.getRequestDispatcher("bookAppointment.jsp").include(req, resp);

  	       }
  	    else {		
         	req.setAttribute("patient", dao.fetchAllPatient());
 	        req.getRequestDispatcher("patientList.jsp").include(req, resp);
          	}
         }	
	
  }
}
