package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.seminar.beans.AccessLogBean;
import jp.co.seminar.util.MRConnectionProvider;

public class AccessLogDao {
	public AccessLogDao() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// ログイン時のログ生成
	public void addLog(String try_user_id,
			String result,
			String ip_address,
			String user_agent) {
		String sql = "INSERT INTO access_log (try_user_id, result, ip_address, user_agent) "
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, try_user_id);
			pstmt.setString(2, result);
			pstmt.setString(3, ip_address);
			pstmt.setString(4, user_agent);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AccessLogBean> findAll() {

		List<AccessLogBean> List = new ArrayList<AccessLogBean>();
		//データベース接続
		String sql = "SELECT * FROM access_log";

		//try-with-resources構文
		try (
				Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			//SQL文を実行して結果を取得
			try (ResultSet rs = pstmt.executeQuery()) {
				//結果セットをviewへ送るための準備
				while (rs.next()) {
					//結果セットから取得
					String id = rs.getString("id");
					String userId = rs.getString("try_user_id");
					String result = rs.getString("result");
					String ip = rs.getString("ip_address");
					String agent = rs.getString("user_agent");
					String whenTime = rs.getString("when_access");
					//ReservationBeanオブジェクトを生成し、リストに追加
					AccessLogBean ALB = new AccessLogBean(id, userId, result, ip, agent, whenTime);

					List.add(ALB);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ドライバが見つかりません。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです");
		}
		//try-with-resourcesによりconn,pstmtは自動的にクローズされる
		return List;//画面に返す
	}
}
