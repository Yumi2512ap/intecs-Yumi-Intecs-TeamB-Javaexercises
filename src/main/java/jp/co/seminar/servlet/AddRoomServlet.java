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
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		String roomName = request.getParameter("roomname");
		String roomId = request.getParameter("roomId");
		String successMessage = null;
		String errorMessage = null;
		if (roomName.length() <= 25 && roomId.matches("[0-9]{4}") && MR.getRoom(roomName) == null) {
			MR.addRoom(roomId, roomName);
			session.setAttribute("MR", MR);
			successMessage = "会議室の追加に成功しました <br>会議室ID:"+roomId+"会議室名:"+roomName;
		} else if (roomName.length() > 25) {
			errorMessage = "会議室の追加に失敗しました 会議室名は２５文字以下にしてください";
		} else if(MR.getRoom(roomName) != null){
			errorMessage = "会議室の追加に失敗しました 登録済みの会議室を追加することはできません";
		}
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("successMessage", successMessage);
		request.getRequestDispatcher("addRoom.jsp").forward(request, response);
	}
}
