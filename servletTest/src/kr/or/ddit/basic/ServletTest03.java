package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * - 서블릿의 동작 과정(Life Cycle)
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 Servlet Container로 전송(요청)
 * 2. 컨테이너는  web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해서 처리해야 할 지 검색한다.
 * 	  (이 때 해당 서블릿이 로딩이 안된 상태이면 로딩 작업을 수행한다. 
 *     그리고 해당 서블릿이 처음 로딩시 init()메서드를 자동으로 호출해준다.)
 *    (Servlet 3.0이상에서는 애노테이션(@WebServlet)으로 설정할 수 있다.)
 * 3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성하여 
 * 	    실행할 Servlet의 service()메서드를 호출해준다.
 * 	  (이 때 HttpServletRequest 및 HttpServletRequest객체를 생성하여 파라미터의 인수값으로 넘겨준다.)
 * 4. service()메서드는 메서드(method) 타입을 체크하여 적절한 메서드를 호출한다.
 *    (doGet(), doPost(), doPut(), doDelete()등...)
 * 5. 요청 및 응답 처리가 완료되면 HttpServletRequest 및 HttpServletResponse객체는 소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다. - servlet객체 수정하고 저장(재부팅)
 */

// 서블릿의 LifeCycle 예제
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Servlet : " + this.getServletName() 
										+ "에서 init()메서드 호출...");
	}

	public void destroy() {
		System.out.println("Servlet : " + this.getServletName() 
										+ "에서 destroy()메서드 호출...");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메서드 시작...");
		
		// GET방식과 POST방식에 맞는 메서드 호출하기
		
		// 방법1 : HttpServlet의 service()메서드로 위임해서 처리한다.
//		super.service(request, response);
		
		// 방법2 : 클라이언트의 전송방식(GET, POST등)을 구분해서 직접 메서드 호출하기
		String method = request.getMethod(); // 전송방식을 반환하는 메서드
		System.out.println("method = " + method);
		if("GET".equals(method)) {
			doGet(request, response);
		} else if("POST".equals(method)) {
			doPost(request, response);
		} else {
			System.out.println("알 수 없는 전송방식입니다.");
			return;
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작...");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h1>doGet()메서드를 처리한 결과입니다.</h1></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작...");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h1>doPost()메서드를 처리한 결과입니다^^*^*^*</h1></body></html>");
	}

}
