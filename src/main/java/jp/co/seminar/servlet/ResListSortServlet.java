package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.beans.SearchBean;

/**
 * Servlet implementation class ResListSortServlet
 */
@WebServlet("/ResListSort")
public class ResListSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResListSortServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//セッションから取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		String order = request.getParameter("order");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		String room = request.getParameter("room");
		String user = request.getParameter("user");
		String page = request.getParameter("page");

		request.setAttribute("reservations", MR.getReservationList(order, date1, date2, room, user, request));
		request.setAttribute("searchBean", new SearchBean(order, date1, date2, room, user));
		String nextPage;
		if(page == null) {
			nextPage = "reservationList.jsp";
		}else {
			nextPage = "userReservations.jsp";
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}
