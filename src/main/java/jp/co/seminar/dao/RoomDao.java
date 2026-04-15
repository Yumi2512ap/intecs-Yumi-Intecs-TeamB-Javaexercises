package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.seminar.beans.RoomBean;
import jp.co.seminar.util.MRConnectionProvider;

public class RoomDao {
	// インスタンス化を防ぐため、コンストラクタをprivateで定義
	public RoomDao() {
	}

	public RoomBean[] findAll() {
		String id;
		String name;
		// SELECTの結果格納用の配列を用意
		// 拡張可能なリストの作成
		List<RoomBean> nameDataList = new ArrayList<RoomBean>();
		// パラメータを含む動的SQL文を用意
		String sql = "SELECT * FROM room where id";
		// try-with-resourcesでリソースを自動的にクローズ
		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// SQL文を実行して結果を取得
			ResultSet rs = pstmt.executeQuery();
			// 結果を表示
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				nameDataList.add(new RoomBean(id, name));
			}
			// List型の配列を、固定の長さの配列にする。（nameDataListのデータ数でカウント）
			RoomBean[] roB = new RoomBean[nameDataList.size()];

			for (int i = 0; i < roB.length; i++) {
				roB[i] = nameDataList.get(i);
			}
			return roB;//  会議室の配列（見つからない場合は、nullを返却）

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLに関するエラーです。");
		} catch (ClassNotFoundException e) {
			System.err.println("ドライバ");
		}
		return null;
	}

	//会議室の追加
	public Boolean addRoom(String id, String name) {

		String sql = "INSERT INTO room(id,name,deleteFlg) VALUES(?,?,?)";
		int deleteFlg = 0;
		try (Connection conn = MRConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, deleteFlg);
			return pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("会議室の追加に失敗しました");
			return false;
		}
	}
}
