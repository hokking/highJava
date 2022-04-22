package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieReadTest.do")
public class CookieReadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 저장된 Cookie 정보 읽어오기
		
		// 1. 전체 Cookie정보를 Request객체를 이용해서 가져온다.
		// : 가져온 Cookie 정보들은 배열에 저장된다.
		// 형식) Cookie[] 쿠키배열변수 = request.getCookie();
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset=utf-8>");
		out.println("<title>Cookie 읽기 연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie 데이터 확인하기</h2>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h2>저장된 쿠키가 하나도 없습니다.</h2>");
		} else {
			// 2. 쿠키 배열에서 해당 쿠키 정보를 구해온다.
			for(Cookie c : cookieArr) {
				String name = c.getName(); // '쿠키변수' 구하기
//				String value = c.getValue(); // '쿠키값' 구하기
				
				// '쿠키값'으로 저장된 데이터가 한글일 경우
				String value = URLDecoder.decode(c.getValue(), "utf-8");
				out.println("쿠키변수 : " + name + "<br>");
				out.println("쿠키값 : " + value + "<br><hr>");
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
