package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom;
import jp.co.seminar.beans.UserBean;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEdit")
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//遷移先ページ格納変数
		String nextPage2 = "login.jsp";
		//リダイレクトする
		response.sendRedirect(nextPage2);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		// 1. セッションチェック
		HttpSession session = request.getSession();
		UserBean UB = (UserBean) session.getAttribute("UB");

		if (UB == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// 2. パラメータの受け取り
		String id = UB.getId(); 
		String newUserPw = request.getParameter("userPw");
		String newUserName = request.getParameter("userName");
		String newUserAddress = request.getParameter("useraddress");
		// isAdminが必要な場合は追加してください（例: UBから取得など）
		boolean isAdmin = UB.getIsAdmin();

		String nextPage = "userEdit.jsp"; // デフォルトの遷移先

		try {
		    // MRを使えるように定義（実体化）する
		    MeetingRoom MR = new MeetingRoom(); // ★ここでDAO（MeetingRoomなど）のインスタンス化が必要です
			// 例: MeetingRoom MR = new MeetingRoom(); 
			
			// DAOのupdateを実行
			UserBean isSuccess = MR.update(id, newUserPw, newUserName, newUserAddress, isAdmin);

			if (isSuccess != null) {
				request.setAttribute("msg", "更新に成功しました。");
				// セッション情報を最新に更新
				session.setAttribute("UB", isSuccess); 
			} else {
				request.setAttribute("msg", "更新に失敗しました。対象が見つかりません。");
			}
		} catch (Exception e) { // RuntimeException以外もキャッチできるようExceptionに
			e.printStackTrace();
			request.setAttribute("msg", "システムエラーが発生しました。");
		}

		// 3. 最後に一回だけフォワードする
		request.getRequestDispatcher(nextPage).forward(request, response);
	} 
}// ← ここでdoPostを閉じる
