package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 파일에 데이터 출력하기

		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/out.txt");
			// 없으면 새로 만들어지지만 있으면 덮어쓰게 된다.

			for (char ch = 'A'; ch < 'Z'; ch++) {
				fout.write(ch); // ch변수의 데이터를 파일로 출력한다
			}

			System.out.println("출력 작업 완료");
			fout.close(); // 쓰기 작업 완료 후 스트림 닫기
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
