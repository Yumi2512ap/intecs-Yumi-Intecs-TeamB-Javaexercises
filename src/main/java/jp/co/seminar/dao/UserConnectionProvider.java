package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.UserConfig; // UserConfigがあるパッケージ名を指定


public class UserConnectionProvider {
    /**
     * データベースへの接続を確立して返却する
     */
    public static Connection getConnection() throws SQLException {
        try {
            // JDBCドライバのロード（環境により省略可）
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
        
        // Configの定数を使って接続を返す
        return DriverManager.getConnection(
            UserConfig.URL, 
            UserConfig.USER, 
            UserConfig.PASS
        );
    }
    
}
