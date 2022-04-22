package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
/**
 * Service객체는 DAO에 만들어진 메서드를 원하는 작업에 맞게 호출하여 
 * 그 결과를 받아서 Controller에게 보내주는 역할을 한다
 * @author PC-09
 *
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memvo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1이상의 정수, insert작업 실패 : 0
	 */
	public int insertMember(MemberVO memvo);
	
	/**
	 * 회원 ID를 인수값으로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원 ID
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO 자료를 이용하여 회원 정보를  update하는 메서드
	 * 
	 * @param memvo update할 회원 정보가 저장된 MemberVO객체
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	public int updateMember(MemberVO memvo);
	
	/**
	 * 수정할 정보를 갖는 Map을 인수값으로 받아서 회원정보를 수정하는 메서드
	 * 
	 * @param paramMap 수정할 정보를 갖는 Map객체
	 *  ( key : field(수정할 컬럼명), data(수정할값), memid(수정할회원ID) )
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	// update mymember set 수정할 컬럼명 = 수정할값 where mem_id = 수정할회원ID
	public int updateMember2(Map<String, String> paramMap);
	
	/**
	 * DB의 회원 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고있는 List
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 인수값으로 받아서 해당 회원데이터의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원ID 개수
	 */
	public int getMemberCount(String memId);
}
