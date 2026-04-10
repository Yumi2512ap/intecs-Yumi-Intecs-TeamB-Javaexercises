package jp.co.seminar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.seminar.beans.UserBean;

//利用者テーブル用DAO
public class UserDao {

	// データベース接続情報
    private final String URL = "jdbc:mysql://localhost:3306/_db";
    private final String USER = "root";
    private final String PASSWORD = "password";
    
    
    
	
	    // メソッド----------------------------------
	    public static  getList (String keyword) {
	    	
	    // DB取得結果を格納するリスト
	    	DictionaryList dList = new DictionaryList();
	    	
	    // データベース接続
	        String sql = "SELECT  engWord  AS  english,  jpWord  AS  Japanese  FROM  dictionary WHERE  engWord  =  ?";
	        
	     // try-with-resources構文でリソースを自動的にクローズ
	        try (
	                Connection conn = DictionaryConnectionProvider.getConnection();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	
	      // プレースホルダーに値を設定
	            pstmt.setString(1, keyword);
	            
	      // SQL文を実行して結果を取得
	            try (ResultSet rs = pstmt.executeQuery()) {
	            	
	      // 結果セットをViewへ送るための準備
	                while(rs.next()) {
	                	// 結果セットから取得
	                	String english = rs.getString("english");
	                	String japanese = rs.getString("japanese");
	                    // DictionaryBeanオブジェクト生成し、リストに追加
	                	DictionaryBean dict = new DictionaryBean(english, japanese);
	                	dList.add(dict);
	                }
	            }
	            
	            // try-with-resourcesによりrsは自動的にクローズされる
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	            System.err.println("ドライバが見つかりません。");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("SQLに関するエラーです。");
	        }
	        // try-with-resourcesによりconn, pstmtは自動的にクローズされる
	        
	        return dList;
	    }

	    public static boolean addDictionary(DictionaryBean dict) {
	    	
	        // データベース接続
	        String sql = "INSERT INTO dictionary (engword, jpword) VALUES(?, ?)";
	        
	        // try-with-resources構文でリソースを自動的にクローズ
	        try (Connection conn = DictionaryConnectionProvider.getConnection();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	
	            // プレースホルダーに値を設定
	            pstmt.setString(1, dict.getEnglish());
	            pstmt.setString(2, dict.getJapanese());
	            
	            //更新クエリの実行
	            int ret = pstmt.executeUpdate();
	            return ret != 0; 
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	            System.err.println("ドライバが見つかりません。");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("SQLに関するエラーです。");
	        }
	        // try-with-resourcesによりconnとpstmtは自動的にクローズされる
	        return false;
	    }
	}
// TODO 自動生成されたコンストラクター・スタブ
}

}
