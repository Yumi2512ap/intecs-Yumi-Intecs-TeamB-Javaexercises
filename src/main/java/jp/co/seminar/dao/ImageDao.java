package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jp.co.seminar.beans.ImageBean;
import jp.co.seminar.util.MRConnectionProvider;

public class ImageDao {

	public void insertImage(ImageBean image) throws Exception {
		String sql = "INSERT INTO image (room_id, image_name, image_type, image_content, image_size, created_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, image.getRoomId());
			pstmt.setString(2, image.getImageName());
			pstmt.setString(3, image.getImageType());
			pstmt.setBytes(4, image.getImageContent());
			pstmt.setInt(5, image.getImageSize());
			pstmt.setTimestamp(6, image.getCreatedAt());

			pstmt.executeUpdate();
		}
	}
}