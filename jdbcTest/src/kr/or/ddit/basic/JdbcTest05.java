package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * LPROD 테이블에 새로운 데이터를 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고,
 * lprod_id는 현재의 lprod_id 값 중에서 제일 큰 값보다 1 크게한다
 * 
 * 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다
 */

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PJE0118", "java");
			conn = DBUtil.getConnection();
			
			String sql = "SELECT NVL(MAX(LPROD_ID), 0) + 1 AS MAX FROM LPROD";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int max = 0;
			if(rs.next()){
				max = rs.getInt("MAX");
			}

			String gu = null;
			int count = 0;
			do{
				System.out.print("lprod_gu 입력 : ");
				gu = sc.next();
				String sql2 = "SELECT COUNT(*)"
						+ "		 FROM LPROD"
						+ "		WHERE LPROD_GU = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt(1);
				}
				if (count > 0){
					System.out.println("이미 존재하는 lprod_gu입니다");
					System.out.println("다시 입력해주세요");
				}
			} while(count > 0);
			
			System.out.print("lprod_nm 입력 : ");
			String nm = sc.next();
			
			String sql3 = "INSERT INTO LPROD(LPROD_ID, LPROD_GU, LPROD_NM)"
					+ "	  VALUES (?, UPPER(?), ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, max);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println("INSERT 성공");
			} else {
				System.out.println("INSERT 실패");
			}
		
		} catch (SQLException e) {
			// TODO: handle exception
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e){}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e){}
			if(conn != null) try {conn.close();} catch(SQLException e){}
		}
	}

}
