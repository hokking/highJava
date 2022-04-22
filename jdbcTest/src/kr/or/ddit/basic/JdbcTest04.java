package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "PJE0118", "java");
			
			System.out.println("계좌번호 정보 추가하기");
			String bankNo = null;
			int count = 0;
			do{
				System.out.print("계좌번호 : ");
				bankNo = sc.next();
				String sql2 = "SELECT COUNT(*) "
						+ "		  FROM BANKINFO "
						+ "		 WHERE BANK_NO = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, bankNo);
				rs = pstmt2.executeQuery();
				if (rs.next()){ // select문의 결과가 한개의 레코드일때 if문 사용 가능
					count = rs.getInt(1);
				}
				if (count > 0){
					System.out.println("존재하는 계좌번호 입니다");
					System.out.println("다시 입력해주세요");
				}
			} while(count > 0);
			
			
			
			System.out.print("은행명 : ");
			String bankName = sc.next();


			System.out.print("예금주명 : ");
			String bankUser = sc.next();
			
			// Statement -----------------------------------------------------------------------------
//			String insql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
//					+ "   VALUES ('" + bankNo + "', '" + bankName + "', '" + bankUser + "', SYSDATE)";
//			
//			stmt = conn.createStatement();
//			
//			// select문을 실행할 때는 executeQuery()메서드를 사용하고,
//			// insert, update, delete문과 같이 select문이 아닌 쿼리문을 실행할때는 
//			// executeUpdate()메서드를 사용한다.
//			// executeUpdate()메서드의 반환값은 작없에 성공한 레코드 수를 반환한다.
//			int cnt = stmt.executeUpdate(insql);
			
			
			// PreparedStatement ---------------------------------------------------------------------
			// PreparedStatement객체를 이용하는 방법
			// - SQL문을 작성할 때 데이터가 들어갈 자리를 물음표(?)로 표시한다

			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					+ "   VALUES (?, ?, ?, SYSDATE)";
			
			// PreparedStatement객체를 생성한다
			// - 이 때 실행할 SQL문을 매개변수에 넘겨준다
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 세팅한다
			// 형식) pstmt.set자료형이름(물음표번호, 세팅할데이터)
			// 		물음표 번호는 1번부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// 데이터의 세팅이 완료되면 쿼리문을 실행한다
			int cnt = pstmt.executeUpdate();
			// ---------------------------------------------------------------------------------------
			
			System.out.println("반환값 출력 : " + cnt);
			if(cnt > 0){
				System.out.println("INSERT 성공");
			}else{
				System.out.println("INSERT 실패");
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(stmt!=null) try{stmt.close();}catch(SQLException e){}
			if(pstmt2!=null) try{pstmt2.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
			if(conn!=null) try{conn.close();}catch(SQLException e){}
		}

	}

}
