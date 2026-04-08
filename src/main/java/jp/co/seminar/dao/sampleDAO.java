package jp.co.seminar.dao;

import java.sql.Connection;

import jp.co.seminar.util.MRConnectionProvider;

public class sampleDAO {
	public static void main(String[] args) {
		// データベース接続
		try(Connection conn = MRConnectionProvider.getConnection()) {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
