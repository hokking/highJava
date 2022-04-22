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

@WebServlet("/jsonTest03.do")
public class JsonTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		LprodDao dao = LprodDao.getInstance();
		
		List<LprodVO> list = dao.getLprod();
	
		// 처리된 결과 데이터를 jsp문서로 forward방식으로 보낸다.
		request.setAttribute("lpList", list);
		request.getRequestDispatcher("/json/lprodList2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
