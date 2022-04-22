package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.vo.LprodVO;

@WebServlet("/jsonTest02.do")
public class JsonTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		LprodDao dao = LprodDao.getInstance();
		// 입력받은 데이터를  MemberVO객체에 담는다.
		List<LprodVO> list = dao.getLprod();
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(list);
		
		out.write(jsonData); // = print : 출력한다는 것이 보낸다는 의미
		response.flushBuffer(); 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
