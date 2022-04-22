package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {

		// ArrayList는 기본적인 사용법이 Vector와 같다.
		
		ArrayList list1 = new ArrayList<>();
		
		// add()메서드를 이용해서 추가한다
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(123.45);
		
		System.out.println("list -> " + list1);
		System.out.println("size -> " + list1.size());
		
		// get()메서드로 데이터를 꺼내온다
		System.out.println("2번째 데이터 : " + list1.get(1));
		
		// 데이터 끼워넣기
		list1.add(3, "zzz");
		System.out.println("list -> " + list1);

		// 데이터 수정하기
		String temp = (String) list1.set(3, "yyy");
		System.out.println("list1 -> " + list1);
		System.out.println("temp -> " + temp);
		
		// 데이터 삭제
		list1.remove(3);
		System.out.println("list1 -> " + list1);
		
		list1.remove("bbb");
		System.out.println("list1 -> " + list1);
		System.out.println("------------------------------------------------------------");
		
		// 제네릭을 사용할 수 있다
		ArrayList<String> list2 = new ArrayList<>();
		
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " -> " + list2.get(i));
		}
		System.out.println("------------------------------------------------------------");
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("------------------------------------------------------------");
		
		// contains(비교객체)
		// -> List에 '비교객체'가 있으면 true, 없으면 false 반환
		System.out.println("DDD 값 존재 여부 : " + list2.contains("DDD"));
		System.out.println("YYY 값 존재 여부 : " + list2.contains("YYY"));
		System.out.println();
		// indexOf(비교객체)
		// -> List에 '비교객체'가 있으면 '비교객체'가 있는 위치의 index값을 반환한다
		// -> List에 '비교객체'가 없으면 -1을 반환한다
		System.out.println("DDD 의 위치값 : " + list2.indexOf("DDD"));
		System.out.println("YYY 의 위치값 : " + list2.indexOf("YYY"));
		
		System.out.println("------------------------------------------------------------");

		// toArray() : List 안의 데이터를 배열로 변환하여 반환한다.
		// -> 기본적으로 Object형 배열로 변환한다
		Object[] strArr = list2.toArray(); // 배열로 바꾸면 다시 object형으로 변환된다

		System.out.println("배열의 개수 : " + strArr.length);
		for (int i = 0; i < strArr.length; i++) {
			String str = (String) strArr[i];
			System.out.println(i + " -> " + str);
		}
		System.out.println();
		// 지정한 제네릭 타입에 맞는 배열로 변환하기
		// -> toArray(new 제네릭타입[0])
		String[] strArr2 = list2.toArray(new String[0]);
		for (int i = 0; i < strArr.length; i++) {
			String str = strArr2[i];
			System.out.println(i + " -> " + str);
		}
		
		
	}

}
