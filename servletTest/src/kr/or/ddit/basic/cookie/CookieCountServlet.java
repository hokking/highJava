package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookie = request.getCookies();
		
		int count = 0; // 카운트 값 저장할 변수 선언 및 초기화
		if(cookie != null) {
			for(Cookie c : cookie) {
				String scount =c.getName();
				if("count".equals(scount)) {
					count = Integer.parseInt(c.getValue());
					break;
				}
			}
		}
		count++;
		
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		
		response.addCookie(countCookie);
		
		out.println("<html><head><meta charset=utf-8>");
		out.println("<title>Cookie 예제</title></head>");
		out.println("<body>");		
		out.println("<h2>어서오세요. 당신은 " + count + "번째 방문입니다.</h2>");
		out.println("<br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'>시작 문서로 이동하기</a><br>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
