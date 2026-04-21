package jp.co.seminar.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;//javascript

import jp.co.seminar.beans.MeetingRoom;


public class UserExit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//リクエストで受信したUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");

		//JSPからのリクエストパラメーター
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		//インスタンス
		MeetingRoom MR = new MeetingRoom();
		boolean result = MR.login(userId, userPw);
		
		if() {
			//「はい」を選択
			
			//ログインページへ
			String nextPage="Login.jsp";
		}else { 
			//「いいえ」を選択
			String nextPage2 = "menu.jsp";
			
			break;
		}
		//nextPageに遷移するためのディスパッチャを作成する
		RequestDispatcher rd = request.getRequestDispatcher(LogoutServlet.java);
		//フォワードする
		rd.forward(request, response);
		//session情報の無効化はLogoutServletが行う
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}



//「はい」の場合退会しましたのメッセージを出力後ログイン画面へ

//いいえの場合　セッションを無効化後即ログイン画面へ

//userIdを受け取る

//「はい/いいえ」の判定や、セッションの無効化、画面遷移



