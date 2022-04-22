package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03 {

	public static void main(String[] args) {

		FileTest03 test = new FileTest03();
		
		File file = new File("d:/d_other");
		
		test.displayFileList(file);
	}
	
	// 디렉토리를 갖는 File객체를 인수 값으로 받아서 
	// 해당 디렉토리에 있는 모든 파일과 디렉토리 목록을 출력하는 메서드
	public void displayFileList(File dir){
		if(!dir.isDirectory()){
			System.out.println("대상 디렉토리(폴더)가 없거나, 디렉토리(폴더)만 가능합니다.");
			return;
		}
		
		System.out.println("[ " + dir.getAbsolutePath() + " ] 디렉토리 내용");
		System.out.println();
		
		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구해온다.
		File[] files = dir.listFiles();
//		String[] files = dir.list();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복처리
		
		for(File f : files){
			String fileName = f.getName();
			String attr = ""; // 파일 속성(읽기, 쓰기, 히든, 디렉토리 여부)
			String size = ""; // 파일 크기
			
			if(f.isDirectory()){
				attr = "<DIR>";
			}else{
				size = f.length() + ""; // String.valueOf(f.length);
				attr = f.canRead() ? "R" : "";
				attr += f.canWrite() ? "W" : "";
				attr += f.isHidden() ? "H" : "";
			}
			
			System.out.printf("%s %5s %12s %s\n", df.format(new Date(f.lastModified())), attr, size, fileName);
		}
		
	}

}
