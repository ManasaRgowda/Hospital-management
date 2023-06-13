package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HospitalDao;
import dto.Staff;

@WebServlet("/changeStaffStatus")
public class ChangeStaffStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		HospitalDao dao = new HospitalDao();
		Staff stf = dao.fetchStaff(id);

		if (stf.isStatus()) {
			stf.setStatus(false);
		} else {
			stf.setStatus(true);
		}

		dao.updateStaff(stf);

		resp.getWriter().print("<h1 style='color:green'>Status updated succesfully</h1>");
		req.setAttribute("staff", dao.fetchAllStaff());
		req.getRequestDispatcher("approveStaff.jsp").include(req, resp);
	}
}
