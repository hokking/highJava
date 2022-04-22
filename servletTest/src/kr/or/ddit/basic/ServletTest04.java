package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletTest04
 */
@WebServlet("/servletTest04.do")
public class ServletTest04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet 환경 정보 알아보기
		// : Servlet클래스나 JSP 페이지의 환경에 관련된 정보는 
		//	 javax.servlet.ServletContext 인터페이스 타입의 객체를 이용해서 얻을 수 있다.
		
		// ServletContext객체 구하기
		ServletContext context = this.getServletContext();
		
		String serverInfo = context.getServerInfo();
		int majorVersion = context.getMajorVersion();
		int minorVersion = context.getMinorVersion();
		
		String servletName = this.getServletName();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>웹 서버 정보 보기</title></head>");
		out.println("<body>");
		out.println("웹 서버 종류(ServerInfo) :" + serverInfo + "<br>");
		out.println("지원하는 Servlet버전 : " + majorVersion + "." + minorVersion + "<br>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
