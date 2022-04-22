package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기

		try {
			// System.in : 콘솔(포준입출력장치)의 입력장치용 스트림 객체
			// InputStreamReader : 입력용 바이트기반의 스트림을 문자기반의 스트림으로 변환하는 보조스트림이다.
			InputStreamReader isr = new InputStreamReader(System.in);

			// 파일 출력용 문자기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");

			System.out.println("아무 내용이나 입력하세요.(입력의 끝은 Ctrl+Z 입니다.)");

			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl + Z'키를 누르면 된다.
			while ((c = isr.read()) != -1) {
				fw.write(c); // 콘솔로 입력한 데이터를 파일로 출력한다.
			}
			// 스트림 닫기
			isr.close();
			fw.close();

		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
