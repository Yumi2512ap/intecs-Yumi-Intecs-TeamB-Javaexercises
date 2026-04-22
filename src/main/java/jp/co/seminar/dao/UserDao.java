package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.seminar.beans.UserBean;
import jp.co.seminar.util.MRConnectionProvider;

public class UserDao {

	public UserDao() {
	}

	public UserBean certificate(String id, String password) {

		UserBean user = null;

		String sql = "SELECT id, password, name, address, is_admin FROM user WHERE id = ? AND password = ?";

		try (Connection conn = MRConnectionProvider.getConnection();

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					String name = rs.getString("name");
					String address = rs.getString("address");
					int is_admin = rs.getInt("is_admin");
					if (is_admin == 1) {
						user = new UserBean(id, name, address, password, true);
						return user;
					}
					user = new UserBean(id, name, address, password);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 何が起きたかコンソールに表示する
			System.err.println(e);

		}
		return user;
	}

	// idで存在チェック
	public boolean existsByUserId(String userId) {

		String sql = "SELECT 1 FROM user WHERE id = ?";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, userId);
			// id一致がするものが存在する？
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("userIdの存在確認に失敗しました", e);
		}
	}

	//Bean引数で登録
	public boolean addUser(UserBean user) {

		String sql = "INSERT INTO user (id, password, name, address, is_admin) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAddress());
			pstmt.setBoolean(5, user.getIsAdmin());

			// 更新が一行だけある？
			return pstmt.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ユーザー登録に失敗しました", e);
		}
	}
	
	// 削除処理メソッド SQLの実行と削除フラグの更新 
	public boolean deleteUser(String userId) {
		//データベースに削除のフラグを出す
		String sql="UPDATE MeetingRoom SET delete_flg = TRUE WHERE userId = ?";
	//データベースに接続
			try (Connection conn = MRConnectionProvider.getConnection();
				     PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// プレースホルダに値をセット
		        pstmt.setString(1, userId);
		     // SQLで更新された行数を取得
		        int affectedRows = pstmt.executeUpdate();
		     // 1行以上更新されていればtrue
		        return affectedRows > 0;
			
			}catch(SQLException e){
				System.err.println("SQLに関するエラーが発生しました");
				return false;
				
			}catch(ClassNotFoundException e){
				System.err.println("ドライバーが見つかりません。");
				return false;
				
			}
	}
	
}
