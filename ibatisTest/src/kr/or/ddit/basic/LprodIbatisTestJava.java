package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.sun.org.apache.bcel.internal.generic.NEW;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodIbatisTestJava {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SqlMapClient smc = null; // iBatis 실행용 객체 변수 선언

//		try {
//			Charset charset = Charset.forName("utf-8");
//			Resources.setCharset(charset);
//
//			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
//
//			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//
//			rd.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		smc = SqlMapClientFactory.getSqlMapClient();

		try {
			int lprodID = (int) smc.queryForObject("lprodj.getLprodj") + 1;

			int count = 0;
			String lprodGu = null;
			
			do {
				System.out.print("Lprod_gu 입력 : ");
				lprodGu = scan.next();
				count = (int) smc.queryForObject("lprodj.countLprodj", lprodGu);

				if (count > 0) {
					System.out.println("이미 존재하는 lprod_gu입니다.");
					System.out.println("다시 입력해주세요.");
				}

			} while (count > 0);

			System.out.print("Lprod_nm 입력 : ");
			String lprodNm = scan.next();

			LprodVO lvo1 = new LprodVO();
			lvo1.setLprod_id(lprodID);
			lvo1.setLprod_gu(lprodGu);
			lvo1.setLprod_nm(lprodNm);

			Object obj = smc.insert("lprodj.insertLprodj", lvo1);

			if (obj == null) {
				System.out.println("insert 작업 성공");
			} else {
				System.out.println("insert 작업 실패");
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

}
