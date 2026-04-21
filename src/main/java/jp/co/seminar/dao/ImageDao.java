package jp.co.seminar.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jp.co.seminar.beans.ImageBean;
import jp.co.seminar.util.MRConnectionProvider;

public class ImageDao {

	public ImageBean findById(int roomId) throws Exception {
		String sql = "SELECT image_id, room_id, image_name, image_type, image_content, image_size, created_at "
				+ "FROM image WHERE image_id = ?";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, roomId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new ImageBean(
							rs.getInt("image_id"),
							rs.getString("room_id"),
							rs.getString("image_name"),
							rs.getString("image_type"),
							rs.getBytes("image_content"),
							rs.getInt("image_size"),
							rs.getTimestamp("created_at"));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです");
		}
		return null;
	}

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです");
		}
	}
}