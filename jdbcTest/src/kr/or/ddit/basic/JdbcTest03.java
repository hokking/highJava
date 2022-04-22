package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료를 출력하시오

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.print("lprod_id1 : ");
		int num1 = sc.nextInt();
		System.out.print("lprod_id2 : ");
		int num2 = sc.nextInt();
		int max = Math.max(num1, num2);
		int min = Math.min(num1, num2);
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "PJE0118", "java");
			
			String sql = "select * "
					+ "		from lprod "
					+ "	   where lprod_id between " + min + " and " + max;
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println();
			System.out.println("======= 결과 출력  =======");
			while (rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt(1));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString(3));
				System.out.println("---------------------");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch (SQLException e) {}
			if(stmt!=null) try{stmt.close();}catch (SQLException e) {}
			if(conn!=null) try{conn.close();}catch (SQLException e) {}
		}
	}

}
