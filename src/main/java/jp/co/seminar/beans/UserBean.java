package jp.co.seminar.beans;

public class UserBean {
	//フィールド-----------------------------------------
	private String id;
	private String password;
	private String name;
	private String address;
	private static final long serialVersionUID = 1L;

	//コンストラクタ------------------------------------
	public UserBean() {
	}

	public UserBean(String id, String password, String name, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
	}

	//メソッド-------------------------------------------
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	//文字列表現
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", password=" + password +
				", name=" + name + ",address=" + address + "]";
	}
}
