package kr.or.ddit.basic.session;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import kr.or.ddit.basic.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDao {
	private static MemberDao dao;
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(dao == null) dao = new MemberDao();
		return dao;
	}
	
	public MemberVO getLoginMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO memberVo = null; // 로그인한 결과가 저장될 변수
		
		try {
			 conn = DBUtil3.getConnection();
			 
			 String sql = "select * from mymember"
			 		+ "		where mem_id = ? and mem_pass = ?";
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, memVo.getMem_id());
			 pstmt.setString(2, memVo.getMem_pass());
			 
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 memberVo = new MemberVO();
				 memberVo.setMem_id(rs.getString("mem_id"));
				 memberVo.setMem_pass(rs.getString("mem_pass"));
				 memberVo.setMem_name(rs.getString("mem_name"));
				 memberVo.setMem_tel(rs.getString("mem_tel"));
				 memberVo.setMem_addr(rs.getString("mem_addr"));
			 }
		} catch (SQLException e) {
			memberVo = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return memberVo;
		
	}
}
