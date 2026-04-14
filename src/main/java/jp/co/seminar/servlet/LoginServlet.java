package jp.co.seminar.servlet; //P73

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.util.MRConnectionProvider;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//フィールド
	private static String userId;
	private static String userPw;
	

	//コンストラクタ
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストで受信したUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		
		//JSPからのリクエストパラメーター
		String userId=request.getParameter(userId);
		String userPw=request.getParameter(userPw);
		//データベース接続
				String sql = "SELECT * FROM meetingroom WHERE userId = ?";

		//遷移先ページ格納変数
		String nextPage;
		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			HttpSession session = request.getSession();
			//判定
			if(userId.equlas()&&userPw()) {
				//ログイン成功時
				nextPage="menu.jsp";
			}else {
				nextPage="login.jsp";
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです。");
			
		}
			

		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd=request.getRequestDispatcher(nextPage);
		
		//リダイレクトする
		response.sendRedirect(nextPage);
		return;
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
