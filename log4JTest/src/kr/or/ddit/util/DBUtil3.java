package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
// (dbinfo.properties 파일의 내용으로 설정하는 방법)

// 방법1) ResourceBundle 객체 이용하기

public class DBUtil3 {
	// 로그 기록을 남기려면 클래스 처음에 선언해놓는다
	static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	private static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	// static 초기화 블럭
	static {
		
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		// .properties(확장자)를 적지 않는다
		logger.info("ResourceBundle객체 생성 - dbinfo.properties파일 읽기");
		
		try {
			
			Class.forName(bundle.getString("driver"));
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("DB드라이버 로딩 성공!!!");
			
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
			logger.error("DB드라이버 로딩 실패!!!", e);
//			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PJE0118", "java");
			Connection conn = DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("pass"));
			logger.info("DB연결 성공!!");
			
			return conn;
			
		} catch (SQLException e) {
			logger.error("DB연결 실패!!", e);
			return null;
		}
	}
}
