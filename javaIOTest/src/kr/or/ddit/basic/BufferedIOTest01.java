package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다.
		try {
				FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
				
				// 버퍼의 크기가 5인 버퍼스트림 객체 생성
				// 버퍼의 크기를 정하지 않으면 8KB(8192bytes)의 크기로 설정된다.
				BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
				
				for(char ch='1'; ch<='9'; ch++){
					bout.write(ch);
				}
				// 출력작업이 마무리 되면 flush 명령을 써서 작업을 하면 된다.
				bout.flush(); // 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력 시킨다.
				
				System.out.println("출력 작업 끝");
				
				bout.close(); // 스트림 닫기 : 보조 스트림을 닫으면 보조스트림이 사용하는 기반이 되는 스트림도 같이 닫힌다.
				
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
