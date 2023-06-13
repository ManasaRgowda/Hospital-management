package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Doctor;
import dto.Staff;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String password=req.getParameter("password");
        
         HospitalDao dao=new HospitalDao();
         Staff staff= dao.fetchStaff(id);
         Doctor doctor=dao.fetchDoctor(id);
         
   if(staff==null && doctor==null && id!=999999)
   {
     		resp.getWriter().print("<h1 style='color:red'>Incorrect Id</h1>");
    	    req.getRequestDispatcher("login1.html").include(req, resp);
         }
   else{
       if(staff!=null)
        	 {
    	   if(staff.getPassword().equals(password))
    	   {
               if(staff.isStatus()) 
               {   
  				        req.getSession().setAttribute("staff", staff);
        				resp.getWriter().print("<h1 style='color:green'>Login succesful</h1>");
        			    req.getRequestDispatcher("staffHome.html").include(req, resp);
        		     }
        		 else{
        			 resp.getWriter().print("<h1 style='color:red'>Wait for Admin approval</h1>");
     			    req.getRequestDispatcher("login1.html").include(req, resp);
        		     }
        	 }
    	   else {

        		resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
       	        req.getRequestDispatcher("login1.html").include(req, resp);
    	   }
        }
        if(doctor!=null)
             {
        		 if(doctor.getPassword().equals(password))
        		 {
        			 if(doctor.isStatus())
        			 {
        				 req.getSession().setAttribute("doctor", doctor);
     				     resp.getWriter().print("<h1 style='color:green'>Login succesful</h1>");
     			         req.getRequestDispatcher("doctorHome.html").include(req, resp);
     		         }
     		       else{
     			    resp.getWriter().print("<h1 style='color:red'>Wait for Admin approval</h1>");
  			        req.getRequestDispatcher("login1.html").include(req, resp);
     		       }
               }
               else {
             		resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
            	        req.getRequestDispatcher("login1.html").include(req, resp);
                   }
            } 		 
        if(id==999999)
             {
        	     if("999999".equals(password))
        	     {
        	      req.getSession().setAttribute("admin", "admin");
        		  resp.getWriter().print("<h1 style='color:green'>Login succesful</h1>");
 			      req.getRequestDispatcher("adminHome.html").include(req, resp);
        	      }
                else{
			      resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
			      req.getRequestDispatcher("login1.html").include(req, resp);
		         }
             }
	    }
   
	  }
   }
	
