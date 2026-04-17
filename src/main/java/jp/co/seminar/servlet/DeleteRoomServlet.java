package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;

/**
 * Servlet implementation class DeleteRoomServlet
 */
@WebServlet("/DeleteRoom")
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//セッションから取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		String roomId = request.getParameter("roomId");
		String roomName = request.getParameter("roomName");
		String deleteSuccessMessage = null;
		String deleteErrorMessage = null;
		try {
			MR.deleteRoom(roomId);
			session.setAttribute("MR", MR);
			deleteSuccessMessage = "会議室を削除しました<br>会議室id:" + roomId + "会議室名:" + roomName;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DeleteRoomServlet 会議室の削除に失敗しました");
			deleteErrorMessage = "会議室の削除に失敗しました";
		}

		request.setAttribute("deleteErrorMessage", deleteErrorMessage);
		request.setAttribute("deleteSuccessMessage", deleteSuccessMessage);
		request.getRequestDispatcher("addRoom.jsp").forward(request, response);
	}

}
