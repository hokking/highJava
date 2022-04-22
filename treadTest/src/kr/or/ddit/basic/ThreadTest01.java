package kr.or.ddit.basic;

public class ThreadTest01 {

	public static void main(String[] args) { // main thread
		
		// single thread program
		for(int i=0; i<200; i++){
			System.out.print("*");
		}
		
		System.out.println();
		System.out.println();
		
		for(int j=0; j<200; j++){
			System.out.print("$");
		}
		
	}

}
