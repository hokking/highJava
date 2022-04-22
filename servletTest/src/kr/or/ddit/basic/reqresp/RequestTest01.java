package kr.or.ddit.basic.reqresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 보내온 데이터 가져오기
		
		// POST방식으로 전송되어 온 데이터의 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		
		// request.getParameter("파라미터명");
		// : 해당 '파라미터명'에 설정된 '값'을 문자열로 가져온다.
		
		// http://localhost/servletTest/서블릿URL패턴?파라미터명=값&파라미터명=값
		// 파라미터 이름을 이용해서 값을 가져오는 것 - get
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// request.getParameterValues("파라미터명");
		// : 파라미터명이 같은 것이 여러개일 경우에 사용한다.
		//   가져온 '값'의 자료형은 'String[]'이다.
		String[] hobbies = request.getParameterValues("hobby");
		
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request객체로 전송값 구하기</title></head>");
		out.println("<body>");
		out.println("<h2>클라이언트가 보내온 값</h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");
		
		out.println("<tr><td>취미</td>");
		out.println("<td>");
		if(hobbies != null) {
			// 배열 크기만큼 반복 처리
			// 향상된 for문
			for(String h : hobbies) {
				out.println(h + "<br>");
			}
		} else {
			out.println("취미가 하나도 없습니다.");
		}
		out.println("</td></tr>");
		
		out.println("</table>");
		out.println("<hr>");
		
		out.println("<h2>request객체의 메서드</h2>");
		out.println("<ul>");
		out.println("<li>클라이언트의 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li>Context Path : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL 정보 : " + request.getRequestURL() + "</li>");
		out.println("<li>URI 정보 : " + request.getRequestURI() + "</li>");
		out.println("</ul>");
		
		
		out.println("</body></html>");
	}

}
