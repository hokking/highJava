package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.basic.vo.MemberVO;

@WebServlet("/sessionAddTest.do")
public class SessionAddTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 정보 저장하기
		
		// 1. Session 객체를 생성하거나 현재 Session 정보 가져오기
		// 형식1) request객체.getSession() 또는 request객체.getSession(true)
		//		: 현재 Session이 존재하면 현재 Session을 반환하고, 존재하지 않으면 새로운 Session을 생성한다.
		// 형식2) request객체.getSession(false);
		//		: 현재 Session이 존재하면 현재 Session을 반환하고, 존재하지 않으면 null을 반환한다.
		HttpSession session = request.getSession();
		
		// 2. setAttribute()메서드를 이용하여 Session값을 저장한다.
		// 형식) session객체.setAttribute("key값", session값);
		//		: 'key값'은 문자열, 'session값'은 모든 종류의 데이터
		MemberVO memVo = new MemberVO();
		memVo.setMem_id("a001");
		memVo.setMem_name("홍길동");
		memVo.setMem_pass("1234");
		memVo.setMem_tel("010-1234-5678");
		memVo.setMem_addr("대전시 중구 대흥동");
		
		session.setAttribute("memberVo", memVo);
		session.setAttribute("userName", "이순신");
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 저장</title></head>");
		out.println("<body>");
		out.println("<h2>Session 데이터가 저장되었습니다.</h2><br><br>");
		out.println("<a href='" + request.getContextPath() 
								+ "/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
