package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.basic.vo.MemberVO;

@WebServlet("/jsonDataTest.do")
public class JsonDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8"); // 응답데이터가 html일 경우
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		
		String choice = request.getParameter("choice");
		
		String jsonData = null; // 처리한 데이터를 JSON 문자열 데이터로 변환한 후 저장할 변수
		
		switch (choice) {
		case "string": 
			String str = "안녕하세요"; // 처리된 결과 데이터
			jsonData = gson.toJson(str); // 자바의 객체나 데이터를 JSON문자열로 변환한다.
			break;
		case "array": 
			int[] arr = new int[] {100, 200, 300, 400};
			jsonData = gson.toJson(arr);
			break;
		case "object": 
			MemberVO memVo = new MemberVO();
			memVo.setMem_id("test001");
			memVo.setMem_name("홍길동동");
			memVo.setMem_pass("1234");
			memVo.setMem_tel("010-0000-1111");
			memVo.setMem_addr("대전 중구 오류동");
			jsonData = gson.toJson(memVo);
			break;
		case "list":
			ArrayList<MemberVO> memList = new ArrayList<MemberVO>();
			MemberVO memVo1 = new MemberVO();
			memVo1.setMem_id("test001");
			memVo1.setMem_name("홍길동동");
			memVo1.setMem_pass("1234");
			memVo1.setMem_tel("010-0000-1111");
			memVo1.setMem_addr("대전 중구 오류동");
			memList.add(memVo1);
			
			MemberVO memVo2 = new MemberVO();
			memVo2.setMem_id("test002");
			memVo2.setMem_name("이몽룡룡");
			memVo2.setMem_pass("0000");
			memVo2.setMem_tel("010-1234-1111");
			memVo2.setMem_addr("대전 동구 용전동");
			memList.add(memVo2);
			
			MemberVO memVo3 = new MemberVO();
			memVo3.setMem_id("test003");
			memVo3.setMem_name("강강감찬");
			memVo3.setMem_pass("4567");
			memVo3.setMem_tel("010-4567-1111");
			memVo3.setMem_addr("대전 서구");
			memList.add(memVo3);
			
			jsonData = gson.toJson(memList); // list는 배열로 바뀜
			break;
			
		case "map": 
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "변학도");
			map.put("age", "30");
			map.put("tel", "02-134-1234");
			map.put("addr", "서울 어딘가");
			jsonData = gson.toJson(map);
			break;
		}
		
		out.write(jsonData); // = print : 출력한다는 것이 보낸다는 의미
		response.flushBuffer(); // 버퍼에 남은 데이터 강제로 출력하기
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
