package jp.co.seminar.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import jp.co.seminar.util.MRConnectionProvider;

public class ImageDao {

	public void insertImage(String roomId, String imageName, String imageType,
			InputStream imageContent, int imageSize, Timestamp createdAt) throws Exception {

		String sql = "INSERT INTO image (room_id, image_name, image_type, image_content, image_size, created_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, roomId);
			pstmt.setString(2, imageName);
			pstmt.setString(3, imageType);
			pstmt.setBlob(4, imageContent);
			pstmt.setInt(5, imageSize);
			pstmt.setTimestamp(6, createdAt);

			pstmt.executeUpdate();
		}
	}
}