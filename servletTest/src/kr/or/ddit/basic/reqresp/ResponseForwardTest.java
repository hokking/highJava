package kr.or.ddit.basic.reqresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseForwardTest.do")
public class ResponseForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 데이터 받기
		String userName = request.getParameter("username");
		// form에서 넘겨주는 데이터
		
		// setAttribute()로 세팅한 데이터 받기
		String tel = (String)request.getAttribute("tel");
		// servlet에서 넘겨준 데이터
		// setAttribute로 보낸것은 getAttribute로 받아야 한다
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'");
		out.println("<title>Forward방식 연습</title></head>");
		out.println("<body>");
		out.println("<h2>Forward 결과</h2><hr>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td><td>" + userName + "</td></tr>");
		out.println("<tr><td>전화</td><td>" + tel + "</td></tr>");
		out.println("</table>");
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
