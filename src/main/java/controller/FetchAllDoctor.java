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

@WebServlet("/fetchalldoctor")
public class FetchAllDoctor extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 if(req.getSession().getAttribute("admin")!=null) 
	        {    	
		     HospitalDao dao=new HospitalDao();
             List<Doctor> list= dao.fetchAllDoctor();
          	
         	if(list.isEmpty()) {
         		resp.getWriter().print("<h1 style='color:red'>No Doctor has signed up</h1>");
             	req.getRequestDispatcher("login1.html").include(req, resp);

         	}
         	else {         		
         	req.setAttribute("doctor", dao.fetchAllDoctor());
        	req.getRequestDispatcher("approveDoctor.jsp").include(req, resp);
         	}
	   }
		  else {
	    		resp.getWriter().print("<h1 style='color:red'>Session Expaired,please login</h1>");
	        	req.getRequestDispatcher("login1.html").include(req, resp);
	        }
	}
}

