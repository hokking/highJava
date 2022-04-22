package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {

		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-2155-1452"));
		memList.add(new Member(5, "이순신", "010-0123-1452"));
		memList.add(new Member(3, "성춘향", "010-3587-1452"));
		memList.add(new Member(7, "변학도", "010-4581-1452"));
		memList.add(new Member(4, "일지매", "010-5698-1452"));
		memList.add(new Member(9, "강감찬", "010-8442-1452"));
		
		System.out.println("정렬 전");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-------------------------------------------------");
		
		Collections.sort(memList); // 내부정렬 기준이 없어서 정렬을 하지 못한다
		
		System.out.println("정렬 후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-------------------------------------------------");
		
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("정렬 후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-------------------------------------------------");
		
	}

}

// Member 클래스의 회원이름을 기준으로 오름차순 정렬이 되도록 내부 정렬기준 추가하기 -> Comparable인터페이스 구현
class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 내부 정렬기준을 설정하는 메서드 구현하기
	// (회원이름의 오름차순 기준 설정)
	@Override
	public int compareTo(Member mem) { // 현재 this와 mem을 비교
		// if문을 쓸 필요가 없다 - String.compareTo 의 반환값을 사용하면 된다
		return this.getName().compareTo(mem.getName());
	}

}

// Member 클래스의 회원번호(num)를 기준으로 내림차순되는 외부정렬기준 class를 작성하시오

class SortNumDesc implements Comparator<Member> {
	
	 @Override
	public int compare(Member mem1, Member mem2) {
//		if (mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if (mem1.getNum() < mem2.getNum()) {
//			return 1;
//		} else
//			return 0;
		 
		 // Wrapper클래스를 이용하는 방법 1
//		 return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1; // 내림차순으로 만들기 위해서 부호를 반대로 바꿔줌
		 
		 // Wrapper클래스를 이용하는 방법 2
		 return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}	
	
}