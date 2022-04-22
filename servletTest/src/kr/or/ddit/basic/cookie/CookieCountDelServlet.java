package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset=utf-8>");
		out.println("<title>Cookie 삭제 연습</title></head>");
		out.println("<body>");
		
		if(cookieArr != null) {
			// 반복문을 사용해서 삭제할 쿠키를 찾는다.
			// (예: city 쿠키변수에 저장된 쿠키 삭제하기)
			for(Cookie c : cookieArr) {
				String name = c.getName(); // '쿠키변수'값 구하기
				
				if("count".equals(name)) { // 삭제할 쿠키 찾기
					c.setMaxAge(0); // 유지시간을 0으로 설정한다.
					response.addCookie(c); // 변경된 쿠키를 저장한다.
					break;
				}
			}
		}
		
		out.println("<h2>Count가 초기화 되었습니다.</h2>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'>시작문서로 가기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
