package calculation;//p164 p42 データベースdate型　

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import jp.co.seminar.beans.ReservationBean;
import jp.co.seminar.util.ReservationConnectionProvider;
import jp.co.seminar.util.ReservationList;

public class ReservationDAO {
	//コンストラクタ
	private ReservationDAO() {

	}

	//---データベーステーブルから日付データ検索するメソッド
	public List<ReservationBean> findByDate(String date){
		//////利用日を指定し、該当日の予約情報を取得する
		//DB取得結果を格納 
		List<ReservationBean> List=new ReservationList();
		//データベース接続
		String sql = "SELECT * FROM meetingroom WHERE date = ?";
	
		//try-with-resources構文
		try(
		Connection conn=MRConnectionProvider.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql)){
		}
		//プレスホルダーに値を設定
		pstmt.setString(1, date);
		//SQL文を実行して結果を取得
		try(ResultSet rs=pstmt.executeQuery()){
			
		}


		
		//結果セットをviewへ送るための準備
		while(rs.next()) {
		//結果セットから取得
		}
		
		
		//書式設定
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String dateStr=sdf.format(date);
		
	}

	//--予約情報を格納する
	public boolean insert(reservation:Reservation) {
		String sql = "INSERT FROM meetingroom WHERE date = ?";
		try(Connection conn=MRConnectionProvider.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql)){
		
		}
		//-1が帰ってきたら格納できていない P37参考
		int ret=-1;
		//プレスホルダーに値を設定
		pstmt.setString（1,keyword);
		//SQL文を実行して結果を取得
		try(ResultSet rs=pstmt.executeQuery()){
			
		}
		
	}
	//--予約情報を削除する    
	public boolean delete(reservation:Reservation) {
		
		String sql = "DELETE FROM meetingroom WHERE date = ?";
		try(
				Connection conn=MRConnectionProvider.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql){
		
				}
	}
}