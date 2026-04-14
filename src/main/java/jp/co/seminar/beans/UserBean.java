package jp.co.seminar.beans;

import java.io.Serializable;

/**
 * ユーザー情報を保持するBeanクラス
 */
public class UserBean implements Serializable {

    // フィールド
    private String id;
    private String password;
    private String name;
    private String address;
    private static final long serialVersionUID = 1L;

    // コンストラクタ（引数なし）
    public UserBean() {
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
  
      // 文字列表現（デバッグ用）
    @Override
    public String toString() {
        return "UserBean [id=" + id + ", password=" + password +
                ", name=" + name + ", address=" + address + "]";
    }
}
