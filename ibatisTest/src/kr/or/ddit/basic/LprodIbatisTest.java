package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {

	// iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		SqlMapClient smc = null; // iBatis 실행용 객체 변수 선언
		
		// 1. iBatis의 환경 설정 파일을 읽어와 실행한다.
		// 	  (sqlMapConfig.xml)
		try {
			// 1-1. 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정 파일(sqlMapConfig.xml)을 읽어온다.
			Reader rd = 
					Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후 
			//    SQL문을 호출해서 실행할 객체를 생성한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ----------------------------------------------------------------------------------
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업 수행하기
		try {
			/*
			// 2-1. insert 연습
			System.out.println("insert 작업 시작");
			System.out.print("Lprod_id 입력 : ");
			int lprodID = scan.nextInt();
			
			System.out.print("Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			System.out.print("Lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			// 1) 입력한 값들을 VO객체에 담는다.
			LprodVO lvo1 = new LprodVO();
			lvo1.setLprod_id(lprodID);
			lvo1.setLprod_gu(lprodGu);
			lvo1.setLprod_nm(lprodNm);
			
			// 2) SqlMapClient 객체변수(smc)를 이용해서 처리할 쿼리문을 호출해서 실행한다.
			//	    형식 : smc.insert("namespace속성값.id속성값", 파라미터클래스);
			// 		      반환값 : insert 성공 = null, insert 실패 = 오류객체
			Object obj = smc.insert("lprod.insertLprod", lvo1);
			if(obj == null) {
				System.out.println("insert 작업 성공");
			} else {
				System.out.println("insert 작업 실패");
			}
			*/
			/*
			// 2-2. update 연습
			System.out.println("update 작업 시작");
			System.out.print("수정할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			System.out.print("새로운 Lprod_id 입력 : ");
			int lprodID = scan.nextInt();
			
			System.out.print("수정할 Lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			// 1) 수정할 데이터를 VO에 담는다.
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(lprodID);
			lvo2.setLprod_gu(lprodGu);
			lvo2.setLprod_nm(lprodNm);
			
			// 2) smc.update("namespace속성값.id속성값", 파라미터클래스);
			// 		반환값 : 작업에 성공한 레코드 수
			int cnt = smc.update("lprod.updateLprod", lvo2);
			
			if(cnt > 0) {
				System.out.println("update 작업 성공");
			} else {
				System.out.println("update 작업 실패");
			}
			*/
			
			// 2-3. delete 연습
			System.out.println("delete 작업 시작");
			System.out.print("삭제할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			// 1) smc.delete("namespace속성값.id속성값", 파라미터 클래스);
			// 		반환값 : 작업에 성공한 레코드 수
			int cnt = smc.delete("lprod.deleteLprod", lprodGu);
			if(cnt > 0) {
				System.out.println("delete 작업 성공");
			} else {
				System.out.println("delete 작업 실패");
			}
			
			/*
			// 2-4 select 작업
			// 1) select의 응답 결과가 여러개의 레코드일 경우
			System.out.println("select 작업 시작 (결과가 어러개일 경우");
			
			// 응답의 결과가 여러개일 경우에는 queryForList()메서드를 사용하는데
			// 이 메서드는 여러개의 레코드 각각을 VO에 담은 후 
			// 이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다.
			// 형식) smc.queryForList("namespace속성값.id속성값", 파라미터클래스);
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			
			for(LprodVO lvo3 : lprodList) {
				System.out.println("ID : " + lvo3.getLprod_id());
				System.out.println("GU : " + lvo3.getLprod_gu());
				System.out.println("NM : " + lvo3.getLprod_nm());
				System.out.println("------------------");
			}
			System.out.println("출력 끝");
			
			// 2) 응답 결과가 1개일 경우
			//	     응답 결과가 1개가 확실한 경우에는 queryForObject()메서드를 사용한다.
			// 형식) smc.queryForObject("namespace속성값.id속성값", 파라미터클래스);
			 * 반환값 
			 * 1) SQL문의 처리결과가 여러개일 경우 : Exception리턴
			 * 2) SQL문의 처리 결과가 1개일 경우 : 해당 객체 리턴(정상)
			 * 3) SQL문의 처리 결과가 없을경우 : null리턴

			System.out.println("select 작업 시작 (결과가 한개일 경우)");
			System.out.print("검색할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			LprodVO lvo4 = (LprodVO)smc.queryForObject("lprod.getLprod", lprodGu);
			
			if(lvo4 == null) {
				System.out.println("검색한 데이터가 하나도 없습니다.");
			} else {
				System.out.println("검색 결과");
				System.out.println("ID : " + lvo4.getLprod_id());
				System.out.println("GU : " + lvo4.getLprod_gu());
				System.out.println("NM : " + lvo4.getLprod_nm());
			}
			*/
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
