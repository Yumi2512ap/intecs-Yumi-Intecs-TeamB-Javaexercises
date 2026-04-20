package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jp.co.seminar.util.MRConnectionProvider;

public class AccessLogDao {
	public AccessLogDao() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void addLog(String try_user_id,
			String result,
			String ip_address,
			String user_agent) {
		String sql = "INSERT INTO access_log (try_user_id, result, ip_address, user_agent) "
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, try_user_id);
			pstmt.setString(2, result);;
			pstmt.setString(3, ip_address);
			pstmt.setString(4, user_agent);

			pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
