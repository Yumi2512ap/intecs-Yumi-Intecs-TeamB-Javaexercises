package jp.co.seminar.beans;

import java.io.Serializable;

public class RoomBean implements Serializable {
	// フィールド
	private static final long serialVersionUID = 1L;// バージョン番号
	private String id;// 会議室ID
	private String name;// 会議室名

	// コンストラクタ
	// 直列化復元時に使用
	public RoomBean() {
	}

	// 会議室情報で初期化
	public RoomBean(String id, String name) {
		this.id = id;
		this.name = name;
	}

	// メソッド
	// 会議室IDを返します
	public String getId() {
		return id;
	}

	// 会議室名を返します
	public String getName() {
		return name;
	}

	@Override
	// このオブジェクトの文字列表現を返します
	public String toString() {
		return  ""; // 戻り値はString型
	}
}
