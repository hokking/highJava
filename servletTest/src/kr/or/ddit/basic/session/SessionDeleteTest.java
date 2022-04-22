package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDeleteTest.do")
public class SessionDeleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Session 삭제하기
		
		// 1. Session 객체 생성 또는 현재 Session 가져오기
		HttpSession session = request.getSession();
		
		// 2. removeAttribute()메서드로 개별적인 Session값 삭제하기
		// 형식) session객체.removeAttribute("key값");
		//		: Session객체는 삭제되지 않고 해당 'key값'에 설정된 'session값'만 삭제된다.
		
//		session.removeAttribute("userName"); // 개별삭제
		
		// 3. invalidate()메서드로 삭제하기 : Session객체 자체가 삭제된다.
		// 형식) session객체.invalidate();
		session.invalidate(); // 전체 삭제
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 삭제</title></head>");
		out.println("<body>");
		out.println("<h2>Session정보 삭제하기</h2>");
		
		
		
		out.println("<a href='" + request.getContextPath() 
								+ "/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
