package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service == null) service = new BoardServiceImpl();
		return service;
	}
	
	@Override
	public List<BoardVO> getAllList() {
		return dao.getAllList();
	}

	@Override
	public List<BoardVO> selectBoard(int boardNo) {
		return dao.selectBoard(boardNo);
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		return dao.insertBoard(boardvo);
	}

	@Override
	public int updateBoard(BoardVO boardvo) {
		return dao.updateBoard(boardvo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(String searchBoard) {
		return dao.searchBoard(searchBoard);
	}

}
