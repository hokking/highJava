package kr.or.ddit.basic.reqresp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * redirect
		 * - 다른 페이지로 제어가 넘어가도록 한다.(이 때 직접 파라미터를 넘길 수 없다.)
		 * - 응답 시 브라우저에게 '이동할URL'을 전송하여 브라우저가 해당 URL로 이동하는 방식이다.
		 * 	 (이 때는 무조건 GET방식으로만 이동한다.)
		 * - redirect방식은 Request객체를 유지하지 못한다.
		 *   : 브라우저에게 새로운 요청을 하기 때문
		 * 
		 * 명령 - response.sendRedirect("이동할URL");
		 * 	  : '이동할URL'은 전체 URL주소를 기술한다.
		 */
		/*
		/. request객체 공유 불가
		request.setAttribute("tel", "010-5678-1234");
		
		response.sendRedirect(request.getContextPath() + "/responseRedirectTest.do");
		*/
		
		// 데이터를 보내려면  GET방식으로 보낼 수 있다.
		String userName = request.getParameter("username");
		response.sendRedirect(request.getContextPath() 
				+ "/responseRedirectTest.do?username=" + userName
				+ "&tel=010-9999-5555");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
