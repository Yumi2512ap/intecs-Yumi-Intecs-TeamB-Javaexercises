package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.UserBean;
import jp.co.seminar.dao.UserDao;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
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
		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(nextPage2);
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
		UserBean UB = (UserBean) session.getAttribute("UB");

		// ログインチェック（念のため）
		if (UB == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String id = UB.getId(); // セッションに保存されている確実なID

		// JSPの入力欄から「新しい情報」をパラメータで受け取る
		String newUserPw = request.getParameter("userPw");
		String newUserName = request.getParameter("userName");
		String newUserAddress = request.getParameter("useraddress");

		UserDao dao = new UserDao();
		String nextPage = "";

		try {// DAOで更新をする

			// サーブレット内の変数を「引数」として、DAOに渡す。updateが成功したらboolean型の変数に代入
			boolean isSuccess = dao.update(id, newUserPw, newUserName, newUserAddress);
			// 成功して、isSuccessがあれば
			if (isSuccess) {
				request.setAttribute("msg", "更新に成功しました。");
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
