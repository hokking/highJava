package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 
 문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에 
 	  저장된 ArrayList의 데이터 중 '김'씨 성의 이름을 모두 출력하시오
 	 (단, 입력은 Scanner객체를 이용한다.)
 	  
 */
public class ArrayListTest02 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			System.out.print("이름입력 > ");
			String name = sc.nextLine();
			list.add(name);
		}
		
		for(int i=0; i<list.size(); i++){
			
		}
		
		System.out.print("김씨성을 가진 이름 : ");
		for(int i=0; i<list.size(); i++){
			if(list.get(i).indexOf("김") == 0){
				System.out.print(list.get(i) + " ");
			}
		}
		System.out.println();
		System.out.print("김씨성을 가진 이름 : ");
		for(int i=0; i<list.size(); i++){
			if(list.get(i).startsWith("김")){
				System.out.print(list.get(i) + " ");
			}
		}

		System.out.println();
		System.out.print("김씨성을 가진 이름 : ");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).substring(0, 1) == ("김")) {
				System.out.print(list.get(i) + " ");
			}
		}
		System.out.println();
		System.out.print("김씨성을 가진 이름 : ");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).charAt(0) == ('김')) {
				System.out.print(list.get(i) + " ");
			}
		}
		
		
	}

}
