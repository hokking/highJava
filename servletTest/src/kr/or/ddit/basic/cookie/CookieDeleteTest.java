package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieDeleteTest.do")
public class CookieDeleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장된 Cookie 삭제하기
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 1. 쿠키 데이터의 삭제는 쿠키의 유지시간을 0으로 설정하는 방법을 사용한다.
		//    먼저 삭제 할 쿠키를 구한 후 이 쿠키의 유지시간을 0으로 설정한 후  addCookie()메서드로 다시 저장하면 된다.
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset=utf-8>");
		out.println("<title>Cookie 삭제 연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie 데이터 삭제하기</h2>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>삭제할 쿠키가 하나도 없습니다.</h3>");
		} else {
			// 반복문을 사용해서 삭제할 쿠키를 찾는다.
			// (예: city 쿠키변수에 저장된 쿠키 삭제하기)
			for(Cookie c : cookieArr) {
				String name = c.getName(); // '쿠키변수'값 구하기
				
				if("city".equals(name)) { // 삭제할 쿠키 찾기
					c.setMaxAge(0); // 유지시간을 0으로 설정한다.
					response.addCookie(c); // 변경된 쿠키를 저장한다.
				}
			}
		}
		
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest.jsp'>시작문서로 가기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
