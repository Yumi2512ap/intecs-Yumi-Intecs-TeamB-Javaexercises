package jp.co.seminar.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;

@WebServlet("/ChangeDate")
public class ChangeDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeDateServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String date = request.getParameter("date");
		String nextPage = request.getParameter("page");
		
		HttpSession session = request.getSession();
		
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		if (MR == null) {
		    response.sendRedirect("login.jsp");
		    return; 
		}
			
		if (date != null && !date.isEmpty()) {
		    session.setAttribute("reservedDate", date);
		}

		request.getRequestDispatcher(nextPage).forward(request, response);	

	}
}
