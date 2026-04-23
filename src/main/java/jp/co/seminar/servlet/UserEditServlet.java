package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.beans.UserBean;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEdit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//遷移先ページ格納変数
		String nextPage2 = "login.jsp";
		//リダイレクトする
		response.sendRedirect(nextPage2);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// セッションから「今の（古い）情報」を取り出す
		HttpSession session = request.getSession();
		MeetingRoom MR = (MeetingRoom)session.getAttribute("MR");

		String id = MR.getUser().getId(); // セッションに保存されている確実なID
		boolean isAdmin = MR.getUser().getIsAdmin();

		// JSPの入力欄から「新しい情報」をパラメータで受け取る
		String newUserPw = request.getParameter("userPw");
		String newUserName = request.getParameter("userName");
		String newUserAddress = request.getParameter("useraddress");

		
		String nextPage = "";

		try {// DAOで更新をする

			// サーブレット内の変数を「引数」として、DAOに渡す。updateが成功したらboolean型の変数に代入
			UserBean isSuccess = MR.update(id, newUserPw, newUserName, newUserAddress,isAdmin);
			// 成功して、isSuccessがあれば
			if (isSuccess != null) {
				request.setAttribute("msg", "更新に成功しました。");
				MR.setUser(isSuccess);
				session.setAttribute("MR", MR);
				nextPage = "userEdit.jsp";
			} else {
				// 更新されなかった時（IDが消えていた等）の行き先
				request.setAttribute("msg", "更新に失敗しました。対象が見つかりません。");
				nextPage = "userEdit.jsp";
			}
		} catch (RuntimeException e) {
			// DAOで throw new RuntimeException されたらここに飛んでくる
			e.printStackTrace();
			request.setAttribute("msg", "システムエラーが発生しました。");
			nextPage = "userEdit.jsp";
		}

		// フォワード
		request.getRequestDispatcher(nextPage).forward(request, response);

	}

}
