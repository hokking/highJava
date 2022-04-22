package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		int data; // 읽어온 데이터가 저장될 변수

		// read()메서드 : 더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		while ((data = input.read()) != -1) {
			/* 데이터 동작 */
			output.write(data); // 읽어온 데이터를 그대로 출력한다.
		}

		// 출력된 스트림 값들을 배열로 변환해서 저장하기
		outSrc = output.toByteArray();

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("inSrc : " + Arrays.toString(inSrc));
		System.out.println("outSrc : " + Arrays.toString(outSrc));
	}

}
