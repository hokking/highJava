package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.basic.vo.MemberVO;

@WebServlet("/sessionReadTest.do")
public class SessionReadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 저장된 Session정보 읽어오기
		
		// 1. Session 객체 생성 또는 현재 Session 가져오기
		HttpSession session = request.getSession();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 읽기</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Session 데이터 확인하기</h2><hr>");
		out.println("<h3>Session 데이터 1개 확인하기</h3>");
		
		// 2. getAttribute()메서드로 Session값을 읽어온다.
		// 형식) session객체.getAttribute("key값");
		String userName = (String)session.getAttribute("userName");
		if(userName == null) { // session에 해당 'key값'으로 저장된 데이터가 없을 때
			out.println("<h3>userName의 Session값이 없습니다.</h3>");
		} else {
			out.println("<h3>userName의 세션값 : " + userName + "</h3>");
		}
		out.println("<hr>");
		
		out.println("<h3>전체 Session 데이터 확인하기</h3>");
		out.println("<dl>");
		
		// session의 모든 'key값'을 가져온다.
		Enumeration<String> sessionNames = session.getAttributeNames();
		
		while(sessionNames.hasMoreElements()) { // 다음 데이터가 있는지 검사
			// session의 'key값' 1개 가져오기
			String sessionKey = sessionNames.nextElement();
			Object data = session.getAttribute(sessionKey);
			
			out.println("<dt>" + sessionKey + "</dt>");
			if(data instanceof MemberVO) {
				MemberVO memvo = (MemberVO)data;
				out.println("<dd>" + memvo.getMem_id() + "</dd>");
				out.println("<dd>" + memvo.getMem_name() + "</dd>");
				out.println("<dd>" + memvo.getMem_pass() + "</dd>");
				out.println("<dd>" + memvo.getMem_tel() + "</dd>");
				out.println("<dd>" + memvo.getMem_addr() + "</dd>");
			} else {
				out.println("<dd>" + data + "</dd>");
			}
			
		}
		
		out.println("</dl>");
		out.println("<hr>");
		
		// 세션 ID : 세션을 구분하기 위한 고유한 값
		out.println("세션 ID : " + session.getId() + "<br>");
		
		// 생성 시간: 1970년 1월 1일부터 경과한 시간(millisecond 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>");

		// 최근 접근 시간 : 1970년 1월 1일부터 경과한 시간(millisecond 단위)
		out.println("세션 최근 접근 시간 : " + session.getLastAccessedTime() + "<br>");
		
		// 세션 유효 시간 : 세션이 생성된 후 유지되는 시간(초 단위)
		// - 유효시간 설정 : session객체.setMaxInactiveInterval(시간)
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		out.println("<hr><br>");
		out.println("<a href='" + request.getContextPath() 
								+ "/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
