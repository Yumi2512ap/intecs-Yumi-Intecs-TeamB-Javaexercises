package jp.co.seminar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.seminar.beans.MeetingRoom; // またはUser情報を保持しているクラス

@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        MeetingRoom mr = (MeetingRoom) session.getAttribute("MR");
        
        // 1. セッションから「自分のID」を取得
        String loginUserId = mr.getUser().getId(); 

        // 2. 画面（JSP）から送られてきた「削除したいID」を取得
        String targetUserId = request.getParameter("userId");

        // 3. 自分自身なら処理を中断
        if (loginUserId.equals(targetUserId)) {
         // エラーメッセージをセット
         request.setAttribute("errorMessage", "自分自身（管理者）を削除することはできません。");
         
         // adminMenuではなく、入力画面(deleteUser.jsp)に戻す
         request.getRequestDispatcher("/deleteUser.jsp").forward(request, response);
         return; 
     }

        // 4. 自分以外の場合のみ、DAOを呼び出して削除を実行
        jp.co.seminar.dao.UserDao dao = new jp.co.seminar.dao.UserDao();
        dao.deleteUser(targetUserId);

        // 完了したら管理者メニューへ
        response.sendRedirect("adminMenu.jsp");
    }

}
