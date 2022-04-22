package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB서버에 연결해서 SQL문을 수행하고 결과를 작성해서 Service에 전달하는 DAO의 인터페이스
 * @author PC-09
 *
 */
public interface IBoardDao {
	// 전체 목록 출력, 새글작성, 수정, 삭제, 검색 기능을
	
	/**
	 * DB의 게시물 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return BoardVO객체를 담고있는 List
	 */
	public List<BoardVO> getAllList();
	
	/**
	 * 게시물 번호를 이용하여 게시물 조회하는 메서드
	 * 
	 * @param boardNo 조회할 게시물 번호
	 * @return 조회할 객체를 담고있는 BoardVO
	 */
	public BoardVO selectBoard(int boardNo);
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardvo DB에 insert할 자료가 저장된 BoardVO객체
	 * @return 글 작성 성공 : 1, 글 작성 실패 : 0
	 */
	public int insertBoard(BoardVO boardvo);
	
	/**
	 * BoardVO 자료를 이용하여 게시물을 수정하는 메서드
	 * 
	 * @param boardvo 수정할 정보가 저장된 BoardVO객체
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	public int updateBoard(BoardVO boardvo);
	
	/**
	 * 게시물 번호를 인수값으로 받아서 해당 게시물을 삭제하는 메서드
	 * @param boardNo 삭제할 게시물 번호
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * BoardVO 자료를 이용하여 게시물을 검색하여 List에 담아서 반환하는 메서드
	 * 
	 * @param searchBoard DB에 select할 문자가 저장
	 * @return 검색된 BoardVO객체를 담고있는 List
	 */
	public List<BoardVO> searchBoard(String searchBoard);
}
