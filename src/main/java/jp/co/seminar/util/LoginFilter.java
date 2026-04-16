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
		String contextPath = httpRequest.getContextPath();

		boolean isLoginJsp = rURI.equals(contextPath + "/login.jsp");
		boolean isLoginServlet = rURI.equals(contextPath + "/Login");
		boolean isCss = rURI.startsWith(contextPath + "/stylesheet.css");
		boolean isJs = rURI.startsWith(contextPath + "/sample.js");

		// ログインJSP,サーブレット,CSS,JSは処理してよい
		if (isLoginJsp || isLoginServlet || isCss || isJs) {
			chain.doFilter(request, response);
			return;
		}

		// それ以外のページはセッションにMRがあれば処理してよい
		HttpSession session = ((HttpServletRequest) request).getSession();

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
