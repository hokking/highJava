package kr.or.ddit.lprod.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.util.DBUtil3;

public class LprodDao {
	
		private static LprodDao dao;
		
		private LprodDao() {}
		
		public static LprodDao getInstance() {
			if(dao == null) dao = new LprodDao();
			return dao;
		}
		
		public List<LprodVO> getLprod() {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			List<LprodVO> list = new ArrayList<LprodVO>();
			
			try {
				 conn = DBUtil3.getConnection();
				 
				 String sql = "select * from lprod";
				 stmt = conn.createStatement();
				 
				 rs = stmt.executeQuery(sql);
				 while(rs.next()) {
					 LprodVO lprodVo = new LprodVO();
					 lprodVo.setLprod_id(rs.getInt("lprod_id"));
					 lprodVo.setLprod_gu(rs.getString("lprod_gu"));
					 lprodVo.setLprod_nm(rs.getString("lprod_nm"));
					 list.add(lprodVo);
				 }
			} catch (SQLException e) {
				list = null;
				e.printStackTrace();
			} finally {
				if(rs != null) try {rs.close();} catch(SQLException e) {}
				if(stmt != null) try {stmt.close();} catch(SQLException e) {}
				if(conn != null) try {conn.close();} catch(SQLException e) {}
			}
			
			return list;
			
		}
	

}
