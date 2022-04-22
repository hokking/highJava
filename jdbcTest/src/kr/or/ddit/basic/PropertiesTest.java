package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		try {
			// 파일 내용을 읽어올 입력용 스트림 객체 생성
			fin = new FileInputStream(f);
			
			// 입력용 스트림 객체를 이용하여 파일 내용을 읽어와 Properties객체에 저장하기
			
			// 파일 내용을 읽어와 'key값'과 'value값'을 분류한 후 그 내용을 Properties객체에 저장한다
			prop.load(fin);
			
			// 읽어온 정보를 출력해 보기
			System.out.println("driver : " + prop.getProperty("driver"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다");
			e.printStackTrace();
		} finally {
			if(fin != null) try { fin.close(); } catch (IOException e) {}
		}
		
	}

}
