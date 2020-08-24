package com.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
//	public static String driver = "com.mysql.cj.jdbc.Driver";
//	public static String url = "jdbc:mysql://localhost:3306/userDB?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:asdf:@localhost:1521:xe";
	public static String id = "asdf";
	public static String pw = "asdf";
	static {
		try {
			Class.forName(driver); // oracle로 바꾸고
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 로딩 실패");
		}	
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,id,pw);
	}
	public static void close(Connection con) {
		if(con != null) {
			try { con.close();} catch(Exception e) {}
		}
	}
	public static void close(PreparedStatement stmt) {
		if(stmt != null) {
			try { stmt.close();} catch(Exception e) {}
		}
	}
	public static void close(ResultSet rs) {
		if(rs != null) {
			try { rs.close();} catch(Exception e) {}
		}
	}
	public static void rollback(Connection con) {
		if(con != null) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
