package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		// 문자 기반의 Buffered 스트림 사용 예제

		try {

			// 이클립스에서 자바 프로그램이 실행되는 현재 위치는 해당 프로젝트 폴더가 현재 위치가 된다.
			FileReader fr = new FileReader(
					"./src/kr/or/ddit/basic/FileTest01.java");

			BufferedReader br = new BufferedReader(fr);

			String temp = "";

			for (int i = 1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp); 
				// 출력할 데이터의 길이가 정해져 있을 때 printf를 사용하는게 자원을 낭비하지 않음
				

			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
