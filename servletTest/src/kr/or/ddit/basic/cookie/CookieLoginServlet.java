package kr.or.ddit.basic.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String check = request.getParameter("check");
		
		Cookie idCookie = new Cookie("userId", id);
		
		if(check == null) { // 체크 해제
			// 쿠키 삭제
			idCookie.setMaxAge(0);
			response.addCookie(idCookie);
		} else { // 체크
			// 쿠키 저장
			response.addCookie(idCookie);
		}
		
		if("test".equals(id) && "1234".equals(pass)) {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieMain.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieLogin.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
