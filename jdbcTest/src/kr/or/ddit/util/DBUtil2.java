package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
// (dbinfo.properties 파일의 내용으로 설정하는 방법)

// 방법1) properties객체 이용하기

public class DBUtil2 {
	
	private static Properties prop; // Properties객체 변수 선언
	// static 초기화 블럭
	static {
		
		prop = new Properties(); // Properties객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f); // 입력 스트림 객체 생성
			prop.load(fin);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("설정 파일이 없습니다");
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류입니다");
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} finally {
			if(fin != null) try { fin.close(); } catch (IOException e) {}
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PJE0118", "java");
			return DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}
}
