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
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoom")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddRoomServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//セッションから取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("meetingRoom");
		String roomName = request.getParameter("roomname");
		String roomId = request.getParameter("roomId");
		String message;
		if (roomName.length() <= 25 && roomId.matches(".*[0-9]{4}.*")) {
			MR.addRoom(roomId, roomName);
			message = "会議室の追加に成功しました";
		} else {
			if (roomName.length() >= 25) {
				message = "会議室の追加に失敗しました 会議室名は２５文字以下にしてください";
			} else {
				message = "会議室の追加に失敗しました";
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("addRoom.jsp").forward(request, response);
		

	}

}
