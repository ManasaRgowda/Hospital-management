package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Staff;

@WebServlet("/fetchallstaff")
public class FetchAllStaff extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("admin")!=null) 
        {    	
	     	HospitalDao dao=new HospitalDao();
            List<Staff> list= dao.fetchAllStaff();
         	
        	if(list.isEmpty()) {
        		resp.getWriter().print("<h1 style='color:red'>No staff has signed up</h1>");
            	req.getRequestDispatcher("login1.html").include(req, resp);

        	     }
        	else {
        		req.setAttribute("staff", dao.fetchAllStaff());
            	req.getRequestDispatcher("approveStaff.jsp").include(req, resp);
        	   }
	  }
        else {
    		resp.getWriter().print("<h1 style='color:red'>Session Expaired,please login</h1>");
        	req.getRequestDispatcher("login1.html").include(req, resp);
        }
}
}