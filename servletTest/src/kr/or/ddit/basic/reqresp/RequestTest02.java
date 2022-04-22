package kr.or.ddit.basic.reqresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식으로 전달되는 문자 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		
		String snum1 = request.getParameter("num1");
		String op = request.getParameter("op");
		String snum2 = request.getParameter("num2");
		
		int num1 = Integer.parseInt(snum1);
		int num2 = Integer.parseInt(snum2);
		
		double sum = 0.0; // 계산 결과가 저장될 변수
		boolean calcOk = true; // 계산 성공 여부를 나타내는 변수
		
		switch (op) {
		case "+":
			sum = num1 + num2;
			break;
		case "-":
			sum = num1 - num2;
			break;
		case "*":
			sum = num1 * num2;
			break;
		case "/":
			if(num2 == 0) {
				calcOk = false;
			} else {
				sum = (double)num1 / num2;
			}
			break;
		case "%":
			if(num2 == 0) {
				calcOk = false;
			} else {
				sum = num1 % num2;
			}
			break;
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request 연습 form</title></head>");
		out.println("<body>");
		out.println("<h3>계산결과</h3>");
		out.println("<hr>");
		out.println(num1 + op + num2 + " = ");
		
		if(calcOk == true) {
			out.println(sum);
		} else {
			out.println("계산이 불가능 합니다.");
		}
		out.println("</body></html>");
		
	}

}
