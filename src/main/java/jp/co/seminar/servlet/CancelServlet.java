package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.beans.ReservationBean;

/**
 * Servlet implementation class CancelServlet
 */
@WebServlet("/Cancel")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MeetingRoom meetingRoom = (MeetingRoom) session.getAttribute("meetingRoom");
		ReservationBean reservation = (ReservationBean) session.getAttribute("reservation");
		String nextPage;
		try {
			meetingRoom.cancel(reservation);
			nextPage = "/canceld.jsp";
			request.getRequestDispatcher(nextPage)
					.forward(request, response);
		} catch (Exception e) {
			nextPage = "/cancelError.jsp";
			request.setAttribute("errorReason", e.getMessage());
			request.getRequestDispatcher(nextPage)
					.forward(request, response);
		}
		
	}

}
