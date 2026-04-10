package jp.co.seminar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

@WebServlet("/UserServlet") // ブラウザでアクセスする時のURLになります
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Beanにデータをセットする
		UserBean user = new UserBean();
		user.setId("U001");
		user.setName("山田 太郎");
		user.setAddress("nagoya@example.com");
		user.setPassword("pass123");

		// 2. リクエストスコープにBeanを保存（JSPに渡す準備）
		request.setAttribute("user", user);

		// 3. 表示用のJSPファイルへ移動する
		request.getRequestDispatcher("/WEB-INF/jsp/userView.jsp").forward(request, response);
	}
}
