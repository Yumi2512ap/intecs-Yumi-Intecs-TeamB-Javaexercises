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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		// セッションから「今の（古い）情報」を取り出す
				HttpSession session = request.getSession();
				UserBean UB = (UserBean) session.getAttribute("UB");
				
		String id = UB.getId(); // セッションに保存されている確実なID	
		
		// JSPの入力欄から「新しい情報」をパラメータで受け取る
		String newUserPw = request.getParameter("userPw");
		String newUserName = request.getParameter("userName");
		String newUserAddress = request.getParameter("useraddress");
		
		if (UB == null) {
		    response.sendRedirect("login.jsp");
		    return; 
		}
			
		String nextPage;
		request.getRequestDispatcher(nextPage).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//遷移先ページ格納変数
				String nextPage2 = "login.jsp";
				//nextPageに遷移するためのディスパッチャを作成する
				RequestDispatcher rd = request.getRequestDispatcher(nextPage2);
				//リダイレクトする
				response.sendRedirect(nextPage2);
				return;
			}

	}


