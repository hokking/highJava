package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;  // 1번
	private SqlMapClient smc;		// SqlMapClient객체 변수 선언
	
	// 2번  생성자
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient(); // SqlMapClient객체 생성  
	}	
	
	// 3번
	public static MemberDaoImpl getInstance(){
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj==null){
				cnt = 1;
			}
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			
			cnt = smc.delete("mymember.deleteMember", memId);
			
		}catch(SQLException e){
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("mymember.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = null;  // 가져온 데이터가 저장될 List객체 변수 선언
		
		try {
			memList = smc.queryForList("mymember.getAllMember");
		} catch (SQLException e) {
			memList = null;
		} 
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int count = 0;
		
		try {
			
			count = (int) smc.queryForObject("mymember.getMemberCount", memId);
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} 
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 정보  ==> 회원ID(memId), 수정할 컬럼명(field), 수정할데이터값(data)
		int cnt = 0;
		try {
			
			cnt = smc.update("mymember.updateMember2", paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO memVo = null;
		try {
			memVo = (MemberVO) smc.queryForObject("mymember.getMember", memId);
		} catch (Exception e) {
			memVo = null;
			e.printStackTrace();
		}
		return memVo;
	}

}






