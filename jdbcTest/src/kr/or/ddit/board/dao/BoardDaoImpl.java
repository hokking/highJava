package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements IBoardDao {
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		return dao;
	}
	
	@Override
	public List<BoardVO> getAllList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM JDBC_BOARD"
					+ "	   ORDER BY BOARD_NO DESC";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_cnt(rs.getInt(5));
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardList;
	}
	
	@Override
	public List<BoardVO> selectBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql1 = "UPDATE JDBC_BOARD "
					+ " 	  SET BOARD_CNT = BOARD_CNT + 1"
					+ "		WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, boardNo);
			pstmt.executeQuery();
			
			String sql = "SELECT BOARD_TITLE, "
					+ "			 BOARD_WRITER, "
					+ "			 TO_CHAR(BOARD_DATE,'yyyy-MM-dd') as board_date, "
					+ "			 BOARD_CNT, "
					+ "			 BOARD_CONTENT "
					+ "     FROM JDBC_BOARD "
					+ "    WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				boardList = new ArrayList<>();
				boardVo.setBoard_title(rs.getString(1));
				boardVo.setBoard_writer(rs.getString(2));
				boardVo.setBoard_date(rs.getString(3));
				boardVo.setBoard_cnt(rs.getInt(4));
				boardVo.setBoard_content(rs.getString(5));
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "INSERT INTO JDBC_BOARD "
					+ "(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT)"
					+ "VALUES(BOARD_SEQ.nextVal, ?, ?, SYSDATE, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_title());
			pstmt.setString(2, boardvo.getBoard_writer());
			pstmt.setString(3, boardvo.getBoard_content());
			
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
	public int updateBoard(BoardVO boardvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "UPDATE JDBC_BOARD "
					+ " 	 SET BOARD_TITLE = ?,"
					+ "			 BOARD_CONTENT = ?,"
					+ "			 BOARD_DATE = SYSDATE"
					+ "    WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_title());
			pstmt.setString(2, boardvo.getBoard_content());
			pstmt.setInt(3, boardvo.getBoard_no());
			
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
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
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
	public List<BoardVO> searchBoard(String searchBoard) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			if(searchBoard == null){
				searchBoard = "";
			}
			String sql = "SELECT * "
					+ "     FROM JDBC_BOARD "
					+ "    WHERE BOARD_TITLE LIKE '%' || ? || '%'"
//			        + "    WHERE BOARD_TITLE LIKE '%?%'"; // 사용 불가
					+ "	   ORDER BY BOARD_NO DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchBoard);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_cnt(rs.getInt(5));
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardList;
	}

}
