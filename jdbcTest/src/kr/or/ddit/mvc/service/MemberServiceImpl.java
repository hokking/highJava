package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao; // DAO객체의 참조값이 저장될 변수 선언
	
	private static MemberServiceImpl service;
	
//	public MemberServiceImpl() {
	private MemberServiceImpl() {
//		dao = new MemberDaoImpl(); // DAO객체 생성
		dao = MemberDaoImpl.getInstance(); // DAO객체 생성
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memvo) {
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {
		return dao.updateMember(memvo);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}


}
