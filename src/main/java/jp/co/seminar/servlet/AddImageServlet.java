package jp.co.seminar.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jp.co.seminar.beans.MeetingRoom;

@WebServlet("/AddImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String nextPage = "/addImage.jsp";

		try {
			String roomId = request.getParameter("roomId");
			//Part型（ファイル形式やら名前やら取得できる便利な型）
			Part filePart = request.getPart("picture");

			if (roomId == null || roomId.isEmpty()) {
				request.setAttribute("message", "会議室IDが取得できませんでした。");
				request.getRequestDispatcher(nextPage).forward(request, response);
				return;
			}
			//ファイルがちゃんと送信されているかのバリデーション
			if (filePart == null || filePart.getSize() == 0) {
				request.setAttribute("message", "画像ファイルが選択されていません。");
				request.getRequestDispatcher(nextPage).forward(request, response);
				return;
			}
			String imageName = filePart.getSubmittedFileName();
			String imageType = filePart.getContentType();
			int imageSize = (int) filePart.getSize();
			byte[] imageContent = filePart.getInputStream().readAllBytes();//バイナリデータをすべて呼び出す,readAllBytes
			Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
			//ファイル形式が正しいかどうかのバリデーション
			if (!"image/jpeg".equals(imageType) && !"image/png".equals(imageType)) {
				request.setAttribute("message", "JPEGまたはPNG形式の画像のみ登録できます。");
				request.getRequestDispatcher(nextPage).forward(request, response);
				return;
			}
			//処理はMeetingRoomで行う
			MeetingRoom MR = new MeetingRoom();
			MR.addImage(imageSize, roomId, imageName, imageType, imageContent, createdAt);
			request.setAttribute("message", "画像を登録しました。");
			request.getRequestDispatcher(nextPage).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "画像の登録に失敗しました。");
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}
}