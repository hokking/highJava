package kr.or.ddit.basic;

import java.io.File;

public class CurrentPathTest {

	public static void main(String[] args) {
		// 자바 프로그램이 실행되는 실행 위치 구하기
		
		// 방법1) File객체 이용
		File file = new File("");
		System.out.println("실행 위치 : " + file.getAbsolutePath());
		System.out.println();
		
		// 방법2) System.getProperty("user.dir")명령 이용하기
		String path1 = System.getProperty("user.dir");
		System.out.println("실행 위치 : " + path1);

	}

}
