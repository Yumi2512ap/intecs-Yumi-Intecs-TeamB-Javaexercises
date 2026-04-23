package jp.co.seminar.servlet; //P73

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//リクエストで受信したUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");

		//JSPからのリクエストパラメーター
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		//インスタンス
		MeetingRoom MR = new MeetingRoom();

		// ipアドレスとユーザーのデバイス等の情報
		String ip = request.getRemoteAddr();
		String agent = request.getHeader("User-Agent");
		boolean result = MR.login(userId, userPw, ip, agent);

		String nextPage;

		if (result) {
			  // 【これを追加！】最新のセッションIDをサーバーに保存する
		    getServletContext().setAttribute(userId, session.getId());	

			// MRを送る（setattribute)
			session.setAttribute("MR", MR);
			nextPage = "/menu.jsp";
		
		} else {
			nextPage = "/login.jsp";
		}

		// nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		// フォワードする
		rd.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//遷移先ページ格納変数
		String nextPage2 = "login.jsp";
		//リダイレクトする
		response.sendRedirect(nextPage2);
		return;
	}

}
