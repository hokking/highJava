package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt"); // - 파일
		System.out.println(f1.getName() + " 의 크기 : " + f1.length() + " bytes");
		
		System.out.println("path : " + f1.getPath()); // 파일 객체 만들때 지정한 경로
		System.out.println("absolutePath : " + f1.getAbsolutePath()); // 절대 경로
		System.out.println();
		
		File f2 = new File("."); // 현재 위치 - 디렉토리
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		if(f2.isDirectory()){
			System.out.println(f2.getName() + " 은 디렉토리 입니다.");
		}else if(f2.isFile()){
			System.out.println(f2.getName() + " 은 파일입니다.");
		}else{
			System.out.println(f2.getName() + " 은 ?");
		}
		
		File f3 = new File("d:/d_other/sample.exe");
		if(f3.isDirectory()){
			System.out.println(f3.getName() + " 은 디렉토리 입니다.");
		}else if(f3.isFile()){
			System.out.println(f3.getName() + " 은 파일입니다.");
		}else{ // 현재위치에 존재하지 않기 때문에 디렉토리인지 파일인지 모른다
			System.out.println(f3.getName() + " 은 ?");
		}
	}

}
