package jp.co.seminar.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
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
		// TODO Auto-generated method stub
		// place your code here
		// パスを取得
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String rURI = httpRequest.getRequestURI();

		// ログインJSPとサーブレットは処理してよい
		if (rURI.equals("/MeetingRoom/login.jsp") || rURI.equals("/MeetingRoom/Login")) {
			chain.doFilter(request, response);
			return;
		}

		// それ以外のページはセッションにMRがあれば処理してよい
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (session.getAttribute("MR") != null) {
			chain.doFilter(request, response);
		} else {
			//　MRがないならログインページにリダイレクト
			String nextPage = ((HttpServletRequest) request).getContextPath() + "/login.jsp";
			((HttpServletResponse) response).sendRedirect(nextPage);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
