package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection : 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
//		URL url = new URL("https://www.naver.com/index.html");
		URL url = new URL("https://www.naver.com");

		// URLConnection 객체 구하기
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		for(String headerKey : headerMap.keySet()){
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		
		System.out.println();
		
		// 해당 문서의 내용을 가져와 화면에 출력하기
		// (index.html문서 내용 가져오기)
		
		// 방법 1 : URLConnection객체를 이용하는 방법
		
		// 파일을 읽어오기 위한 스트림 객체 생성
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 자료를 읽어와 출력하기
//		while(true){
//			String str = br.readLine(); // 한줄씩 읽기
//			if(str==null){ // 더 이상 읽어올 데이터가 없다.
//				break;
//			}
//			
//			System.out.println(str);
//		}
//		br.close();
		
		// 방법 2 : URL객체를 이용해서 스트림 객체를 구한다
		// URL객체의 openStream()메서드 이용
		InputStream is2 = url.openStream();
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(is2, "utf-8") );
		
		while(true){
			String str = br2.readLine(); // 한줄씩 읽기
			if(str==null){ // 더 이상 읽어올 데이터가 없다.
				break;
			}
			
			System.out.println(str);
		}
		br2.close();
		
	}

}
