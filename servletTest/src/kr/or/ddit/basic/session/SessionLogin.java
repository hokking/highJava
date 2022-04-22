package kr.or.ddit.basic.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.basic.vo.MemberVO;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDao dao = MemberDao.getInstance();
		
		// 입력받은 데이터를  MemberVO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		
		HttpSession session = request.getSession();
		
		// DB에서 id와 password를 이용하여 해당 회원 정보를 가져온다.
		MemberVO returnMemVo = dao.getLoginMember(memVo);
		
		if(returnMemVo == null) { // 로그인 실패
			response.sendRedirect(request.getContextPath() + "/session/sessionLogin.jsp");
		} else { // 로그인 성공
			session.setAttribute("loginMember", returnMemVo);
			response.sendRedirect(request.getContextPath() + "/session/sessionLogin.jsp");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
