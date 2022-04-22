package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		try {
			// 파일 내용을 읽기 위해서는 FileInputStream객체가 필요하다.

			// 스트림 객체 생성
			// 방법1) 읽어올 파일 정보를 분자열로 직접 기술하는 방법
			// FileInputStream fin = new FileInputStream("d:/d_other/test.txt");

			// 방법2) 읽어올 파일을 File 객체로 만들어서 사용하는 방법
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);

			int data; // 읽어온 데이터가 저장될 변수

			while ((data = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)data); 
				// enter도 줄바꿈 문자로 들어가 있기 때문에 줄이 자동으로 바뀐다
			}
			
			fin.close(); // 작업 완료 후 스트림 닫기

		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
