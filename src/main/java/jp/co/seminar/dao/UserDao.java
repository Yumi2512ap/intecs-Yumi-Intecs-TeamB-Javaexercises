package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.seminar.beans.UserBean;
import jp.co.seminar.util.MRConnectionProvider;

public class UserDao {
	
	public UserDao() {
	}

	public static UserBean certificate​(String id, String password) {
		
		UserBean user = null;
		
		String sql = "SELECT id, password, name, address  FROM user WHERE id = ? AND password = ?";		

		try (Connection conn = MRConnectionProvider.getConnection();

				PreparedStatement pstmt = conn.prepareStatement(sql)) {			

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {	

				if (rs.next()) {
				    String name = rs.getString("name");       // 追加
				    String address = rs.getString("address"); // 追加
  
				user = new UserBean(id, password, name, address);
            }
        }							
		}catch (Exception e) {
		
		}
		return user;
	}
	
}
