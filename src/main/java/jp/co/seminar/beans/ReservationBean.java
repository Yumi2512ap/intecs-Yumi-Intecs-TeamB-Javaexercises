package calculation;

import java.io.Serializable;

public class ReservationBean implements Serializable {

	private int id;//予約番号
	private String roomId;//会議室ID
	private String date;//利用日
	private String start;//利用開始時刻
	private String end;//利用終了時刻
	private long serialVersionUID;//直下列用バージョン番号
	private String userId;//利用者ID

	//コンストラクタ　javabean仕様
	public ReservationBean() {

	}

	//コンストラクタ　会議室予約情報を元に初期化する
	public ReservationBean(int id, String roomId, String date, String start, String end, String userId) {
		this.id = id;
		this.roomId = roomId;
		this.date = date;
		this.start = start;
		this.end = end;
		this.userId = userId;
	}

	//コンストラクタ　予約ID未定で予約情報を初期化する
	public ReservationBean(String roomId, String date, String start, String end, String userId) {
		this.roomId = roomId;
		this.date = date;
		this.start = start;
		this.end = end;
		this.userId = userId;
	}

	//予約番号取得　予約番号設定
	public int getid() {
		return id;
	}

	//予約番号設定　予約番号設定
	public void setid(int id) {
		this.id = id;
	}

	//会議室ID取得　住所を出力
	public String getRoomId() {
		return roomId;
	}

	//利用日取得　利用日出力
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	//開始時刻取得　開始時刻出力
	public String start() {
		return start;
	}

	//終了時刻取得　終了時刻取得
	public String end() {
		return end;
	}

	//利用者ID取得　利用者ID出力
	public String userId() {
		return userId;
	}

	//文字列表現
	// デバッグ用出力メソッド
	public void printDebugInfo() {
		System.out.println("予約番号: " + id);
		System.out.println("会議室ID: " + roomId);
		System.out.println("利用日: " + date);
		System.out.println("開始時刻: " + start);
		System.out.println("終了時刻: " + end);
		System.out.println("利用者ID: " + userId);
	}

}
