package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションの取得
		HttpSession session = request.getSession(false);
		// もしセッション情報がnullじゃなければ、セッション情報を無効化する。
		if (session != null) {
			session.invalidate();
		}
		// ログイン画面にリダイレクト
		response.sendRedirect("login.jsp");

	}

}
// menuとloginの橋渡し役　loginにリダイレクト、そして、セッションを全て破棄する