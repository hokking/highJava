package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() {}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "INSERT INTO MYMEMBER "
					+ "(MEM_ID, MEM_NAME, MEM_PASS, MEM_TEL, MEM_ADDR) "
					+ "VALUES(?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getMem_id());
			pstmt.setString(2, memvo.getMem_name());
			pstmt.setString(3, memvo.getMem_pass());
			pstmt.setString(4, memvo.getMem_tel());
			pstmt.setString(5, memvo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "UPDATE MYMEMBER "
					+ " 	 SET MEM_NAME = ?,"
					+ "			 MEM_PASS = ?,"
					+ "          MEM_TEL = ?,"
					+ "          MEM_ADDR = ?"
					+ "    WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getMem_name());
			pstmt.setString(2, memvo.getMem_pass());
			pstmt.setString(3, memvo.getMem_tel());
			pstmt.setString(4, memvo.getMem_addr());
			pstmt.setString(5, memvo.getMem_id());
			
			cnt = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			// update mymember set 수정할컬럼명 = 수정할값 where mem_id=수정할회원ID
			String sql = "UPDATE MYMEMBER "
					+ " SET " + paramMap.get("field") + " = ? "
					+ " WHERE MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));

			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close();   }catch(SQLException e){}
			if(conn!=null) try{ conn.close();   }catch(SQLException e){}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memList = new ArrayList<>(); // 반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				// 반복 처리 부분에서는 한 레코드의 데이터를 VO에 담고 
				// 데이터가 저장된 VO를 List에 추가하는 작업을 진행한다
				
				// 1개의 레코드 값들을 VO에 저장하기
				MemberVO memVo = new MemberVO();
				
				// 한 레코드의 각 컬럼값들을 VO에 저장한다
				memVo.setMem_id(rs.getString(1));
				memVo.setMem_name(rs.getString(2));
				memVo.setMem_pass(rs.getString(3));
				memVo.setMem_tel(rs.getString(4));
				memVo.setMem_addr(rs.getString(5));
				
				memList.add(memVo); // VO객체를 List에 추가한다
			}
		
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0; // 반환값 저장 변수
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return count;
	}


}
