package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;
import dto.Staff;

@WebServlet("/forgotpassword")
public class Forgotpassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		Date dob=Date.valueOf(req.getParameter("dob"));

		HospitalDao dao=new HospitalDao();
		Staff staff= dao.fetchStaff(id);
        Doctor doctor=dao.fetchDoctor(id);
		
        if(staff==null && doctor==null){

     		resp.getWriter().print("<h1 style='color:red'>Incorrect Id</h1>");
    	    req.getRequestDispatcher("forgetpassword.html").include(req, resp);
         }
    else{
    	
       if(staff!=null)
        	 {
        		 if(mobile==staff.getMobile() && dob.equals(staff.getDob()) && name.equals(staff.getName()) )
        		 {
        				staff.setPassword(req.getParameter("password"));
                        dao.updateStaff(staff);  
        				resp.getWriter().print("<h1 style='color:green'>Password Updated succesful</h1>");
        			    req.getRequestDispatcher("login1.html").include(req, resp);
        		     }
        		 else{
        			 resp.getWriter().print("<h1 style='color:red'>Invalid Details Can not set new Password</h1>");
     			    req.getRequestDispatcher("forgetpassword.html").include(req, resp);
        		     }
        	 }
        if(doctor!=null)
             {
        	 if(mobile==doctor.getMobile() && dob.equals(doctor.getDob()) &&  name.equals(doctor.getName()) )
        	 {
        		 doctor.setPassword(req.getParameter("password"));
                 dao.updatedoctor(doctor);  
 				resp.getWriter().print("<h1 style='color:green'>Password Updated succesful</h1>");
 			    req.getRequestDispatcher("login1.html").include(req, resp);
 		     }
 		 else{
 			 resp.getWriter().print("<h1 style='color:red'>Invalid Details Can not set new Password</h1>");
			    req.getRequestDispatcher("forgetpassword.html").include(req, resp);
 		     }
            }
    }
	}
}
