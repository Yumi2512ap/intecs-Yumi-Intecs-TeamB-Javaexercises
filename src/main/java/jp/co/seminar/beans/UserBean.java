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
	private Boolean isAdmin;

	// コンストラクタ
	public UserBean() {
		// デフォルトコンストラクタ
	}

	public UserBean(String id, String name, String address, String password, Boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public UserBean(String id, String name, String address, String password) {
		this(id, name, address, password, false);
	}

	// Getter / Setter
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}
}
