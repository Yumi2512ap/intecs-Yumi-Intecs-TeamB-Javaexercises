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
import jp.co.seminar.dao.UserDao;

/**
 * ログイン処理を行うサーブレット
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. リクエストの文字エンコーディング設定（日本語入力対応）
        request.setCharacterEncoding("UTF-8");

        // 2. 画面から入力されたIDとパスワードを取得
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");

        // 3. DAOを使ってデータベース照合
        UserBean user = UserDao.certificate​(id, pass);

        if (user != null) {
            // 4-A. ログイン成功：セッションにユーザー情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);

            // メニュー画面（menu.jsp）へ遷移
            response.sendRedirect("menu.jsp");
        } else {
            // 4-B. ログイン失敗：エラーメッセージをセットしてログイン画面（login.jsp）へ戻す
            request.setAttribute("error", "IDまたはパスワードが正しくありません");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    // GETリクエスト（直接URLを叩かれた場合など）はログイン画面へ飛ばす
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
