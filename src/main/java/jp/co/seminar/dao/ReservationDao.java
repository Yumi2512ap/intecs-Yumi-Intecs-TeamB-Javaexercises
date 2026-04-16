package jp.co.seminar.dao;//p164 p42 データベースdate型　

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.seminar.beans.ReservationBean;
import jp.co.seminar.util.MRConnectionProvider;

public class ReservationDao {
	//コンストラクタ
	public ReservationDao() {

	}

	//利用日による予約情報取得 

	public List<ReservationBean> findByDate(String date) {

		//////利用日を指定し、該当日の予約情報を取得する

		//DB取得結果を格納 
		List<ReservationBean> List = new ArrayList<ReservationBean>();
		//データベース接続
		String sql = "SELECT * FROM reservation WHERE date = ?";

		//try-with-resources構文
		try (
				Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			//プレスホルダー(?)に値を設定
			pstmt.setString(1, date);
			//SQL文を実行して結果を取得
			try (ResultSet rs = pstmt.executeQuery()) {
				//結果セットをviewへ送るための準備
				while (rs.next()) {
					//結果セットから取得
					int intid = rs.getInt("id");
					String StringroomId = rs.getString("roomId");
					String strdate = rs.getString("date");
					String strstart = rs.getString("start");
					String strend = rs.getString("end");
					String struserId = rs.getString("userId");
					//ReservationBeanオブジェクトを生成し、リストに追加
					ReservationBean rese = new ReservationBean(intid, StringroomId, strdate, strstart, strend,
							struserId);

					List.add(rese);
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

	//--予約情報を格納する　
	public boolean insert(ReservationBean reservation) {

		int ret = -1;

		String sql = "INSERT INTO reservation (roomid, date, start, end, userid) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, reservation.getRoomId());

			pstmt.setString(2, reservation.getDate());
			pstmt.setString(3, reservation.getStart());
			pstmt.setString(4, reservation.getEnd());

			pstmt.setString(5, reservation.getUserId());

			ret = pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				reservation.setId(rs.getInt(1));
			}

			return ret != 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//	public boolean insert(ReservationBean reservation) {
	//
	//		//-1が帰ってきたら格納できていない P31参考
	//		int ret = -1;
	//
	//		String sql = "INSERT INTO reservation WHERE date = ?";
	//		try (Connection conn = MRConnectionProvider.getConnection();
	//				PreparedStatement pstmt = conn.prepareStatement(sql)) {
	//			//SQL文を実行
	//
	//			int autoIncrementKey = 0;
	//			ret = pstmt.executeUpdate(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	//			ResultSet rs = pstmt.getGeneratedKeys();
	//			return ret != 0;
	//
	//		} catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//			System.err.println("ドライバが見つかりません。");
	//			return false;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			System.err.println("SQLに関するエラーです");
	//			return false;
	//		}
	//	}

	//--予約情報を削除する  P37
	public boolean delete(ReservationBean reservation) {
		int ret = -1;
		String sql = "DELETE FROM reservation WHERE id = ?";
		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, reservation.getId());

			ret = pstmt.executeUpdate();

			return ret != 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//	public boolean delete(ReservationBean reservation) {
	//		int ret = -1;
	//		//SQLでデータベース削除
	//		String sql = "DELETE FROM reservation WHERE date = ?";
	//		//try-with-resources構文でリソースを自動的にクローズ
	//		try (Connection conn = MRConnectionProvider.getConnection();
	//				PreparedStatement pstmt = conn.prepareStatement(sql)) {
	//			//SQL文を実行
	//			ret = pstmt.executeUpdate();
	//
	//			return ret != 0;
	//
	//		} catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//			System.err.println("ドライバが見つかりません。");
	//			return false;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			System.err.println("SQLに関するエラーです");
	//			return false;
	//		} //try-with-resourcesによりconnとpstmtは自動的にクローズされる
	// }

	//　追加要件
	// 予約の全件取得
	public List<String[]> findAll() {

		//////利用日を指定し、該当日の予約情報を取得する

		//DB取得結果を格納 
		List<String[]> List = new ArrayList<String[]>();
		//データベース接続 ※テーブル名
		String sql = "SELECT date, start, end, room.name AS room_name, user.name AS user_name "
				+ "FROM reservation AS r "
				+ "INNER JOIN room ON r.roomid = room.id "
				+ "INNER JOIN user ON r.userid = user.id";

		//try-with-resources構文
		try (
				Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			//SQL文を実行して結果を取得
			try (ResultSet rs = pstmt.executeQuery()) {
				//結果セットをviewへ送るための準備
				while (rs.next()) {
					//結果セットから取得　※カラム名
					String[] res = new String[5];
					res[0] = rs.getString("date");
					res[1] = rs.getString("start").substring(0, 5);
					res[2] = rs.getString("end").substring(0, 5);
					res[3] = rs.getString("room_name");
					res[4] = rs.getString("user_name");

					List.add(res);
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

	//利用日と時間による予約情報取得 

	public ReservationBean findCancel(String date, String time, String roomID) {

		//////利用日を指定し、該当日の予約情報を取得する

		//DB取得結果を格納 
		//データベース接続
		String sql = "SELECT * FROM reservation WHERE date = ? AND start = ? AND roomId = ?";

		//try-with-resources構文
		try (
				Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			//プレスホルダー(?)に値を設定
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			pstmt.setString(3, roomID);
			//SQL文を実行して結果を取得
			try (ResultSet rs = pstmt.executeQuery()) {
				//結果セットをviewへ送るための準備
				if (rs.next()) {
					//結果セットから取得
					int intid = rs.getInt("id");
					String StringroomId = rs.getString("roomId");
					String strdate = rs.getString("date");
					String strstart = rs.getString("start").substring(0, 5);
					String strend = rs.getString("end").substring(0, 5);
					String struserId = rs.getString("userId");
					//ReservationBeanオブジェクトを生成
					ReservationBean rese = new ReservationBean(intid, StringroomId, strdate, strstart, strend,
							struserId);
					return rese;
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
}