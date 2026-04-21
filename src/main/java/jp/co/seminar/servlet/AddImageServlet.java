package jp.co.seminar.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jp.co.seminar.dao.ImageDao;

@WebServlet("/AddImage")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, 
		maxFileSize = 5 * 1024 * 1024, 
		maxRequestSize = 10 * 1024 * 1024)
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String roomId = request.getParameter("roomId");
		Part filePart = request.getPart("picture");

		if (filePart == null || filePart.getSize() == 0) {
			request.setAttribute("message", "画像ファイルが選択されていません。");
			request.getRequestDispatcher("/addImage.jsp").forward(request, response);
			return;
		}

		String fileName = getFileName(filePart);
		String contentType = filePart.getContentType();
		long fileSize = filePart.getSize();

		if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
			request.setAttribute("message", "JPEGまたはPNGのみアップロード可能です。");
			request.getRequestDispatcher("/addImage.jsp").forward(request, response);
			return;
		}

		try (InputStream inputStream = filePart.getInputStream()) {
			ImageDao imageDao = new ImageDao();
			imageDao.insertImage(
					roomId,
					fileName,
					contentType,
					inputStream,
					(int) fileSize,
					Timestamp.valueOf(LocalDateTime.now()));

			request.setAttribute("message", "画像を登録しました。");
			request.getRequestDispatcher("/addImage.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "画像登録に失敗しました: " + e.getMessage());
			request.getRequestDispatcher("/addImage.jsp").forward(request, response);
		}
	}

	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		if (contentDisposition != null) {
			for (String token : contentDisposition.split(";")) {
				if (token.trim().startsWith("filename")) {
					return token.substring(token.indexOf("=") + 2, token.length() - 1);
				}
			}
		}
		return null;
	}
}