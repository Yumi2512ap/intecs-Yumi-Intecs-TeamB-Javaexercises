package jp.co.seminar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DictionaryConnectionProvider implements DatabaseConfig{
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}