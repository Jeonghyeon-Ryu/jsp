package common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	private String jdbc_driver;
	private String oracle_url;
	private String id;
	private String pw;

	public DAO() {
//		dbConfig();
	}

	private void dbConfig() {
		String resource = "/WEB-INF/db.properties";
		Properties properties = new Properties();

		try {
//			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath();
			properties.load(new FileInputStream(resource));
		} catch (Exception e) {
			System.out.println("DB Config 실패 : " + e.toString());
			e.printStackTrace();
		}
		jdbc_driver = properties.getProperty("driver");
		oracle_url = properties.getProperty("url");
		id = properties.getProperty("id");
		pw = properties.getProperty("pw");
	}

	protected void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "me", "me");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver 로딩실패 : " + e.toString());
		} catch (SQLException e) {
			System.out.println("Oracle Driver 접속실패 : " + e.toString());
		}
	}

	protected void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("Disconnect ���� : " + e.toString());
		}
	}
}
