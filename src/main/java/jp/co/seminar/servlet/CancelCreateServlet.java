
package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.beans.ReservationBean;
import jp.co.seminar.beans.RoomBean;

/**
 * Servlet implementation class CancelCreateServlet
 */
@WebServlet("/CancelCreate")
public class CancelCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// セッションからミーティングルームを取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		
		// JSPからデータを受け取る
		String roomId = request.getParameter("roomId");
		String time = request.getParameter("time");
		
		// ミーティングルームを使って必要な情報を取得
		RoomBean room = MR.getRoom(roomId);
		ReservationBean reservation = MR.createReservation(roomId, time);
		
		// 取得した情報をセット
		session.setAttribute("room", room);
		session.setAttribute("reservation", reservation);
		
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher("cancelConfirm.jsp");
		rd.forward(request, response);

	}

}

