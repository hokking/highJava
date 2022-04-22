package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File객체 만들기 연습
		
		// 1. new File(String 파일이름 또는 경로)
		// - 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 
		//   구분 문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
//		File file1 = new File("d:/d_other/test.txt"); // 구분문자로 '/'를 사용한 경우
		File file1 = new File("D:\\d_other\\test.txt"); // 구분문자로 '\'를 사용한 경우(2개씩 작성)
		System.out.println("파일명 : " + file1.getName());
		System.out.println("디렉토리 여부 : " + file1.isDirectory());
		System.out.println("파일 여부 : " + file1.isFile());
		System.out.println();
		
		
		File file2 = new File("d:/D_Other");
		System.out.println("폴더명 : " + file2.getName());
		System.out.println("디렉토리 여부 : " + file2.isDirectory());
		System.out.println("파일 여부 : "  + file2.isFile());
		System.out.println();
		
		
		// 2-1. new File(File parent, String child)
		// 2-2. new File(String parent, String child)
		// - 'parent'폴더(디렉토리) 안에 있는 'child'파일을 나타낸다.
//		File file3 = new File(file2, "test.txt");
		File file3 = new File("d:/D_Other", "test.txt");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("디렉토리 여부 : " + file3.isDirectory());
		System.out.println("파일 여부 : " + file3.isFile());
		System.out.println();
		
		
		// 디렉토리(폴더) 만들기
		// mkdir() : File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		// - 반환값 : 만들기 성공 true, 만들기 실패 false
		// - 중간 부분의 경로가 모두 만들어져 있어야 마지막 위치의 디렉토리를 만들 수 있다.
		File file4 = new File("D:/d_other/연습용");
		System.out.println(file4.getName() + "의 존재 여부 : " + file4.exists());
		if(!file4.exists()){
			if(file4.mkdir()){
				System.out.println(file4.getName() + " 만들기 성공");
			}else{
				System.out.println(file4.getName() + " 만들기 실패");
			}
		}else {
			System.out.println(file4.getName() + " 은 이미 존재합니다.");
		}
		System.out.println();
		
		
		// mkdirs() : 중간부분의 경로가 없으면 중간 부분의 경로도 같이 만들어준다.
		File file5 = new File("d:/d_other/test/java/src"); 
		// 전체 경로 중 src만 만드는 것인데 test나 java가 만들어지지 않았으므로 오류가 난다
		if(!file5.exists()){
			if(file5.mkdirs()){
				System.out.println(file5.getName() + " 만들기 성공");
			}else{
				System.out.println(file5.getName() + " 만들기 실패");
			}
		}else{
			System.out.println(file5.getName() + " 은 이미 존재합니다.");
		}
		
	}

}
