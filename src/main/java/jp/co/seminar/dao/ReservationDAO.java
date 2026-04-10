package jp.co.seminar.dao;//p164 p42 データベースdate型　

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.seminar.beans.ReservationBean;
import jp.co.seminar.util.MRConnectionProvider;

public class ReservationDAO {
	//コンストラクタ
	private ReservationDAO() {
	}

	//利用日による予約情報取得 

	public List<ReservationBean> findByDate(String date){
		//////利用日を指定し、該当日の予約情報を取得する
		//DB取得結果を格納 
		List<ReservationBean> List = new ArrayList<ReservationBean>();
		//データベース接続
		String sql = "SELECT * FROM meetingroom WHERE date = ?";
	
		//try-with-resources構文
		try(
		Connection conn = MRConnectionProvider.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)){
		}catch (Exception e) {
			// TODO: handle exception
		}
		//プレスホルダーに値を設定
		pstmt.setString(1, date);
		//beanのString型を取得 
		String dateStr=ReservationBean.getString(date);
		

		//SQL文を実行して結果を取得
		try(ResultSet rs=pstmt.executeQuery()){
			
		}catch(Exception e){}
		//P56 date型を文字列変換
			
		//見つからない場合は、空リスト
		//エラーの場合は、nullを出力する

		
		//結果セットをviewへ送るための準備
		while(rs.next()) {
		//結果セットから取得
		
		}
		
	}

	//--予約情報を格納する　String→date
	public boolean insert(reservation:Reservation) {
		//-1が帰ってきたら格納できていない P31参考
		int ret=-1;
		
		String sql = "INSERT INTO meetingroom WHERE date = ?";
		try(Connection conn=MRConnectionProvider.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql)){
		   //SQL文を実行
			ret=pstmt.executeUpdate();
			System.out.println(ret+"件、挿入しました");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです");
		}
	} //try-with-resourcesによりconnとpstmtは自動的にクローズされる

	//--予約情報を削除する  P37
	public boolean delete(reservation:Reservation) {
		
		String sql = "DELETE FROM meetingroom WHERE date = ?";
		try(Connection conn=MRConnectionProvider.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql)){
			//SQL文を実行
			ret=pstmt.executeUpdate();
			System.out.println(ret+"件、削除しました");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("SQLに関するエラーです");
		}
	}
}