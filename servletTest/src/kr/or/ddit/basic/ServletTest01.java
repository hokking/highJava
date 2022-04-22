package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet이란? 컨테이너(서블릿엔진)에 의해 관리되는 자바 기반의 웹컴포넌트.
 *                동적인 웹 컨텐츠 생성을 가능하게 한다.
 */

/*
 * 이 서블릿을 호출할 URL주소 만들기
 * 예) http://localhost:80/servletTest/servletTest01.do
 *   - http              ==> 프로토콜
 *   - localhost         ==> 컴퓨터이름(도메인명) 또는 IP주소
 *   - 80                ==> PORT번호(80번은 생략 가능)
 *   - /servletTest      ==> 컨텍스트패스(보통 프로젝트 이름으로 지정한다)
 *   - /servletTest01.do ==> 서블릿 요청 URL패턴
 */

//서블릿을 등록하는 방법이 2가지가 있는데
//첫번째는 배포서술자(web.xml)를 이용하는 방법
//두번째는 에노테이션을 이용하는 방법이 있다.

//이 예제는 배포서술자를 이용하는 방법을 사용하는 예제이다
public class ServletTest01 extends HttpServlet { //클래스로 그냥 만들면 서블릿이 아님.. 그래서 extends 해줘야함
   private static final long serialVersionUID = 1L;

   /*
    * 이 곳에서는 대부분 service()메서드나 doGet()메서드 또는 doPost()메서드를 재정의해서 작성한다.
    * 
    * doGet()메서드나 doPost()메서드의 매개변수로 다음과 같은 2개의 객체가 전달된다.
    * 1) HttpServletRequest객체
    *      ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
    * 2) HttpServletResponse객체
    *      ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
    */
   
   //service()메서드 ==> 모든 요청시 실행되는 메서드
   //             ==> 이 메서드에서 전송방식에 맞는 메서드를 자동 호출해준다
   @Override
      protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         super.service(req, resp);
      }
   
   //doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
   @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setCharacterEncoding("UTF-8"); //응답 문서의 인코딩 방식 지정
         response.setContentType("text/html; charset=utf-8"); //응답문서의 ContentType지정
         
         //처리한 내용을 응답으로 보내기 위한 PrintWriter객체 생성
         PrintWriter out = response.getWriter();
         
         //처리한 내용을 PrintWriter객체를 이용하여 출력한다.
         //(이 작업이 곧 응답 데이터를 클라이언트로 보내는 작업이 된다)
         
         //방법1: append()메서드 이용하기
         out.append("<html>")
            .append("<head>")
            .append("<meta charset='utf-8'>")
            .append("<title>첫번째 servlet 연습</title>")
            .append("</head>")
            .append("<body>")
            .append("<h1 style='text-align:center;'>")
            .append("안녕하세요. 첫번째 Servlet 프로그램입니다 <br>")
            .append(" 실행 Servlet 경로 : " + request.getContextPath())
            .append("</h1></body></html>");
            
      }
   
   //doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
   @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request, response);
      }
}