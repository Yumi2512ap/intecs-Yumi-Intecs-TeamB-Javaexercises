package jp.co.seminar.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.ImageBean;
import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.dao.ImageDao;

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
		//キャストしながらセッションから取得
		MeetingRoom meetingRoom = (MeetingRoom) session.getAttribute("MR");
		String roomId = request.getParameter("roomId");
		// ルームIDなし
		if (roomId == null || roomId.isEmpty()) {
			request.setAttribute("err", "roomIdが不明です");
			request.getRequestDispatcher("/room.jsp").forward(request, response);
			return;
		}
		
		try {
			ImageBean image = new ImageDao().findById(Integer.parseInt(roomId));
			// 検索結果なし
			if (image == null) {
				request.setAttribute("err", "該当する画像が見つかりません。");
				request.getRequestDispatcher("/room.jsp").forward(request, response);
				return;
			}

			String imageSrc = null;
			// コンテンツと画像タイプがあるか？
			if (image.getImageContent() != null && image.getImageType() != null) {
				String base64 = Base64.getEncoder().encodeToString(image.getImageContent());
				imageSrc = "data:" + image.getImageType() + ";base64," + base64;
			}

			request.setAttribute("image", image);
			request.setAttribute("imageSrc", imageSrc);

			request.getRequestDispatcher("/room.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リダイレクト
	}

}
