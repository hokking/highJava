package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC(Java DataBase Connectivity)라이브러리를 이용한 DB자료 처리하기

public class JdbcTest01 {

	/*
	 * - JDBC 처리 순서
	 * 1. 드라이버 로딩 : 라이브러리를 사용할 수 있게 메모리로 읽어 들이는 작업
	 * 	Class.forName("oracle.jdbc.driver.OracleDriver");
	 * 
	 * 2. DB시스템에 접속하기 : 접속이 완료되면 Connection객체가 반환된다
	 * 	DriverManager.getConnection()메서드를 이용
	 * 
	 * 3. 질의 : SQL문장을 DB서버로 보내서 결과를 얻어온다
	 * 	(Statement객체 또는 PreparedStatement객체를 이용하여 작업한다)
	 * 
	 * 4. 결과처리 : 질의 결과를 받아서 원하는 작업을 수행한다
	 * 	1) SQL문이 select문일 경우 : select한 결과가 ResultSet객체에 저장되어 반환된다
	 * 	2) SQL문이 insert, update, delete문일 경우
	 * 		: 정수값이 반환된다(이 정수값은 실행에 성공한 레코드 수를 의미한다)
	 * 
	 * 5. 사용한 자원을 반납한다 : close() 메서드 이용
	 */
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결 : Connection객체 생성
			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@오라클서버IP주소:포트번호:SID값", 
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"PJE0118", "java");
			
			// 3. 질의
			// 	1) 실행할 SQL문 작성
			String sql = "select * from lprod";
			// 	2) Statement객체 또는 PreparedStatement객체를 생성한다
			//		(Connection객체를 이용하여 생성)
			stmt = conn.createStatement();
			// 	3) SQL문을 DB서버로 보내서 실행한 결과를 얻어온다
			// 	(지금은 샐행한 SQL문이 select문이기 때문에 select한 결과가 ResultSet객체에 저장되어 반환된다)
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리
			// rs.next() : ResultSet객체의 데이터를 가리키는 포인터를 다음 위치로 이동하고 
			// 그 곳에 데이터가 있으면 true를 반환한다
			while(rs.next()){
				// 포인터가 가리키는 곳의 자료 가져오기
				// 형식1) rs.get자료형이름("컬럼명")
				// 형식2) rs.get자료형이름(컬럼번호) : 컬럼번호는 1부터 시작
				// 형식3) rs.get쟈료형이름("컬럼의 alias명")
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-----------------------------------------");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5. 자원 반납
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {}
			}
			if(stmt!=null){
				try {stmt.close();} catch (SQLException e) {}
			}
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {}
			}
		}
	}

}
