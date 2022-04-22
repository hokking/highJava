package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	문제) 5명의 별명을 입력받아 ArrayList에 저장한 후
 		이들 중 별명의 길이가 제일 긴 별명을 출력하시오
 		(입력은 Scanner 이용)
 */
public class ArrayListTest03 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("5명의 별명을 입력해주세요");
		for (int i = 0; i < 5; i++) {
			String name = sc.next();
			list.add(name);
		}
		 // 같은 길이의 사람이 나올 수 있다
		int len = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).length() > list.get(j).length()) {
					len = list.get(i).length();
				}
			}
		}
		System.out.println("가장 긴 별명");
		for(int i=0; i<list.size(); i++){
			if(list.get(i).length() == len){
				System.out.println(list.get(i));
			}
		}
		
	}

}
