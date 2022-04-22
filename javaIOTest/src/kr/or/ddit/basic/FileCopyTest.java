package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 문제) 'D:D_Other'폴더에 저장되어 있는 '펭귄.jpg'파일을
 * 	    'D:D_Other/연습용'폴더에 '복사본_펭귄.jpg'파일로 복사하는 프로그램을 작성하시오.
 */

public class FileCopyTest {

	public static void main(String[] args) {

		String fileName = "펭귄.jpg";
		File file = new File("d:/d_other/" + fileName);

		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다");
			System.out.println("작업을 중단합니다");
			return;
		}
		
		String targetFileName = "복사본_펭귄.jpg";
		File targetFile = new File("d:/d_other/연습용");
		
		if(!targetFile.exists()){ // '연습용'폴더가 없으면
			targetFile.mkdir(); // '연습용'폴더를 만든다
		}

		try {// 복사할 원본 파일에 사용할 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);

			BufferedInputStream bin = new BufferedInputStream(fin);
			
			FileOutputStream fout = 
					new FileOutputStream(targetFile.getPath() + File.separator + targetFileName);
//					new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);

			System.out.println("복사시작");

			int c;

			// 기반이 되는 스트림을 작업
//			while ((c = fin.read()) != -1) {
//				fout.write((char) c);
//			}
//
//			fin.close();
//			fout.close();
			
			// 버퍼스트림으로 작업
			while ((c = bin.read()) != -1) {
				bout.write((char) c);
			}

			bout.flush();
			
			bout.close();
			
			System.out.println("복사 완료");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
