package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/*
 * 이 예제는 애노테이션을 이용하여 서블릿을 등록하는 예제입니다.
 * 애노테이션(@WebServlet)은 Servlet버전 3.0이상에서 사용할 수 있습니다.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @WebService 애노테이션의 속성
 * 1) name : 서블릿 이름을 설정 - 기본값 : 빈문자열("")
 * 2) urlPatterns : 서블릿의 요청  URL패턴의 목록을 설정 - 기본값 : 빈배열({})
 *    예) urlPatterns="/url1" 또는 urlPattern={"/url1"} : 패턴이 1개일 경우
 *    예) urlPatterns={"/url1", "/url2", ... } : 패턴이 2개 이상일 경우
 * 3) value : urlPattern속성과 동일한 기능을 한다
 * 4) description : 주석(설명글)을 설정한다
 */
@WebServlet(urlPatterns="/servletTest02.do", 
			description="애노테이션을 이용한 서블릿 설정")
public class ServletTest02 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//처리한 내용을 PrintWriter객체를 이용하여 출력한다.
        //(이 작업이 곧 응답 데이터를 클라이언트로 보내는 작업이 된다)
        
        //방법2: print()메서드 또는 println()메서드 이용하기
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 서블릿 예제</title></head>");
		out.println("<body>");
		out.println("<h1 style='text-align:center;'>");
		out.println("안녕하세요 두번째 서블릿 예제입니다.<br>");
		out.println("애너테이션을 이용하여 설정했습니다<br><br>");
		out.println("실행 ContextPath : " + request.getContextPath() + "</h1>");
		out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
