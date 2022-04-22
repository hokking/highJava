package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao {
	private SqlMapClient smc = null;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		return dao;
	}
	
	@Override
	public List<BoardVO> getAllList() {
		List<BoardVO> boardList = null;
		try {
			boardList = smc.queryForList("board.getAllList");
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}
	
	@Override
	public BoardVO selectBoard(int boardNo) {
		BoardVO board = null;
		try {
			smc.update("board.boardCnt", boardNo);
			
			board = (BoardVO)smc.queryForObject("board.selectBoard", boardNo);
			
		} catch (SQLException e) {
			board = null;
			e.printStackTrace();
		}
		return board;
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		int cnt = 0; 
		
		try {
			Object obj = smc.insert("board.insertBoard", boardvo);
			
			if(obj == null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardvo) {
		
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard");
		
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(String searchBoard) {
		List<BoardVO> boardList = null;
		
		try {
			if(searchBoard == null){
				searchBoard = "";
			}
			boardList = smc.queryForList("board.searchBoard", searchBoard);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}

}
