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
import jp.co.seminar.beans.UserBean;

/**
 * Servlet implementation class addUserServlet
 */
@WebServlet("/addUser")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addUserServlet() {
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
		// セッションからミーティングルームを取得
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom) session.getAttribute("meetingRoom");

		// JSPからデータを受け取る
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		String address = request.getParameter("address");
		String admin = request.getParameter("admin");

		Boolean isAdmin = false;
		String nextPage;
		if (admin.equals("true")) {
			isAdmin = true;
			nextPage = "addAdmin.jsp";
		} else {
			nextPage = "addUser.jsp";
		}
		String msg;

		UserBean user = new UserBean(userId, userName, address, userPw, isAdmin);

		// ユーザーチェック(ID被りなし?
		if (!MR.existsByUserId(userId)) {
			//ユーザー登録
			try {
				MR.addUser(user);
				msg = "ユーザー登録に成功しました";

			} catch (Exception e) {
				msg = "登録に失敗しました";
			}
		} else {
			msg = "登録に失敗しました";
		}

		// フォワード
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
