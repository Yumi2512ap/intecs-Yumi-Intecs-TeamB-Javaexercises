package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		String newDate = request.getParameter("date");
		String from = request.getParameter("from");

		if (newDate != null && !newDate.isEmpty()) {

			HttpSession session = request.getSession();
			session.setAttribute("reservedDate", newDate);

			if ("cancel".equals(from)) {
				response.sendRedirect("cancelInput.jsp");
			} else {
				response.sendRedirect("reserveInput.jsp");
			}

			String newDate = request.getParameter("date");

			if (newDate != null && !newDate.isEmpty()) {

				HttpSession session = request.getSession();
				session.setAttribute("reservedDate", newDate);

				response.sendRedirect("reserved.jsp");

				response.sendRedirect("reserveInput.jsp");
			} else {

				response.sendRedirect("reserveRoom.jsp?error=1");
			}
		}
	}
}
