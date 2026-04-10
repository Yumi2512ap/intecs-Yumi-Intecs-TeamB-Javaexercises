package jp.co.seminar.beans;

import java.io.Serializable; // これを追加

public class UserBean implements Serializable { // abstractを消して、implements Serializableを追加

	// 直列化用バージョン番号
	private static final long serialVersionUID = 1L;

	// フィールド
	private String id; // 利用者ID
	private String name; // 氏名
	private String address; // アドレス
	private String password; // パスワード

	// コンストラクタ
	public UserBean() {
		// デフォルトコンストラクタ
	}

	// Getter / Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
