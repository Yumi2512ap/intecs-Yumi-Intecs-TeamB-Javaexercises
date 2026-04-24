package jp.co.seminar.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */

public class LoginFilter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public LoginFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	    // 【追加】ここを通ったことをコンソールに表示
	    System.out.println("DEBUG: Filterを通りました - " + ((HttpServletRequest)request).getRequestURI());
		
		
		// TODO Auto-generated method stub
		// place your code here
		// パスを取得
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String rURI = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();

		boolean isLoginJsp = rURI.equals(contextPath + "/login.jsp");
		boolean isLoginServlet = rURI.equals(contextPath + "/Login");
		boolean isCss = rURI.startsWith(contextPath + "/stylesheet.css");
		boolean isJs = rURI.startsWith(contextPath + "/sample.js");
		boolean isAddUserServlet = rURI.startsWith(contextPath + "/AddUser");
		boolean isAddUser = rURI.startsWith(contextPath + "/addUser.jsp");

		// ログインJSP,サーブレット,CSS,JSは処理してよい
		if (isLoginJsp || isLoginServlet || isCss || isJs || isAddUser || isAddUserServlet) {
			chain.doFilter(request, response);
			return;
		}

	    // それ以外のページはセッションにMRがあれば処理してよい
	    HttpSession session = ((HttpServletRequest) request).getSession();
	    if (session.getAttribute("MR") != null) {
	        jp.co.seminar.beans.MeetingRoom mr = (jp.co.seminar.beans.MeetingRoom) session.getAttribute("MR");
	        String userId = mr.getUser().getId();
	        
	        // 二重ログインチェック
	        String latestSessionId = (String) getServletContext().getAttribute(userId);
	        String currentSessionId = session.getId();

	        if (latestSessionId != null && !currentSessionId.equals(latestSessionId)) {
	            session.invalidate();
	            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=double");
	            return; 
	        }

	        // DB側の最新状態をチェック
	        jp.co.seminar.dao.UserDao dao = new jp.co.seminar.dao.UserDao();
	        if (dao.isActiveUser(userId)) {
	            chain.doFilter(request, response);
	        } else {
	            session.invalidate();
	            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login.jsp");
	        }
	    } else {
	        // MRがないならログインページへ
	        ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login.jsp");
	    }
	} // doFilterの終わり (足りなかったカッコ1)
} // クラスの終わり (足りなかったカッコ2)
