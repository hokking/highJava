package kr.or.ddit.basic.reqresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTest01
 */
@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * forward : 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
		 * 			 (파라미터도 같이 넘겨줄 수 있다.)
		 * 		   : URL 주소는 처음 요청할 때의 값이 바뀌지 않으며, 서버 내부에서만 접근이 가능하다.	
		 * 			 (Request객체와 Response객체를 공유해서 사용한다.)
		 */
		// 이동되는 페이지로 값을 넘기려면 Request객체의 setAttribute()메서드로 데이터를 셋팅하여 보내고, 
		// 받는 쪽에서는 Request객체의 getAttribute()메서드로 데이터를 읽어온다.
		// 형식) request.setAttribute("키값", 데이터);
		// 형식) request.getAttribute("키값");
		request.setAttribute("tel", "010-1234-5678");
		
		// request.getRequestDispatcher("이동할URL주소");
		// '이동할URL주소'는 Context Path이후의 경로를 기술한다.
		// '/servletTest/myurl.do' : '/myurl.do'
		RequestDispatcher rd = request.getRequestDispatcher("/responseForwardTest.do");
		
		rd.forward(request, response);
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// 여기서get방식을 호출해주면 post방식에서 get방식을 사용할 수 있다
	}

}
