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

    // コンストラクタ（全フィールド初期化用）
    public UserBean(String id, String password, String name, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    // Getter / Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    // 文字列表現（デバッグ用）
    @Override
    public String toString() {
        return "UserBean [id=" + id + ", password=" + password +
                ", name=" + name + ", address=" + address + "]";
    }
}
