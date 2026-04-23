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

@WebServlet("/UserEdit")
public class UserExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String nextPage = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//遷移先ページ格納変数
		String nextPage2 = "login.jsp";
		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(nextPage2);
		//リダイレクトする
		response.sendRedirect(nextPage2);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//リクエストで受信したUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");

		//userEdit.jspから情報を取得
		String action = request.getParameter("action");
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		
		// 管理者自身の削除を無効
		if(MR.getUser().getIsAdmin()) {
			request.setAttribute("err", "管理者自身の削除はできません");
			request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
		}
		// UserDaoの削除メソッドを呼び出す。
		MR.deleteUser(MR.getUser().getId());
		//ログインページへ
		String nextPage = request.getContextPath()+"/Logout";

		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		//フォワードする
		rd.forward(request, response);
		//session情報の無効化はLogoutServletが行う

	}

}

//「はい」の場合退会しましたのメッセージを出力後ログイン画面へ

//いいえの場合　セッションを無効化後即ログイン画面へ

//userIdを受け取る

//「はい/いいえ」の判定や、セッションの無効化、画面遷移
