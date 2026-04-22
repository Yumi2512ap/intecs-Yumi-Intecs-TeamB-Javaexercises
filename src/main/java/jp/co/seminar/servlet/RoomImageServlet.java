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
 * Servlet implementation class RoomImageServlet
 */
@WebServlet("/RoomImage")
public class RoomImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//セッションから取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		String roomId = request.getParameter("roomId");
		
		// ルームIDなし
		if (roomId == null || roomId.isEmpty()) {
			request.setAttribute("err", "roomIdが不明です");
		} else {
			// イメージ画像のバイナリ
			String imageSrc = MR.getImageSrc(roomId);
			request.setAttribute("imageSrc", imageSrc);
			request.setAttribute("roomName", MR.getRoom(roomId).getName());
			// イメージ取得不可
			if (imageSrc == null) {
				request.setAttribute("err", "該当する画像が見つかりません。");
			}
		}

		request.getRequestDispatcher("/room.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
