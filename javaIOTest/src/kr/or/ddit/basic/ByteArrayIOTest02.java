package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4]; // 4개짜리 배열 생성

		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			// 익어올 데이터가 있는지 확인
			while (input.available() > 0) { // 데이터가 몇개 남아 있는지 확인하는 메서드
//				input.read(temp); // 데이터가 temp 배열보다 적으면 4,5가 있던 자리에 8,9가 들어간다
//				output.write(temp);
				
				// 실제 읽어온 byte수를 반환한다.
				int len = input.read(temp);
				
				// temp 배열의 내용 중에서 0번째부터 len개수만큼 출력한다.
				output.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp : " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			System.out.println("inSrc : " + Arrays.toString(inSrc));
			System.out.println("outSrc : " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
