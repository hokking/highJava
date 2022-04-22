package kr.or.ddit.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	private SqlMapClient smc = null;
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		
		int cnt = 0;
		Object obj = null; // 반환값이 저장될 변수
		try {
			obj = smc.insert("mymember.insertMember", memvo);
			
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
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("mymember.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memvo) {
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember", memvo);
		
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
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
	public List<MemberVO> getAllMember() {
		List<MemberVO> memberList = null;
		try {
			memberList = smc.queryForList("mymember.getAllMember");
		} catch (SQLException e) {
			memberList = null;
			e.printStackTrace();
		} 
		return memberList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int count = 0; // 반환값 저장 변수
		
		try {
			count = (int) smc.queryForObject("mymember.getMemberCount", memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}

}
