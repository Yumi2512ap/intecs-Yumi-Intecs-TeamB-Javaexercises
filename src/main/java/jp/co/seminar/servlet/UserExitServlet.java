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

@WebServlet("/UserExit")
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
		MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
		
		// 管理者自身の削除を無効
		if(MR.getUser().getIsAdmin()) {
			request.setAttribute("msg", "管理者自身の削除はできません");
			request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
			return;
		}
		// UserDaoの削除メソッドを呼び出す。
		MR.deleteUser(MR.getUser().getId());
		//ログアウト
		String nextPage = "/Logout";
		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		//フォワードする
		rd.forward(request, response);
		//session情報の無効化はLogoutServletが行う

	}

}


