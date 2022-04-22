package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
		// 컬렉션 객체에 저장되는 데이터는 객체만 저장할 수 있다
		// 객체 생성
		Vector v1 = new Vector(); // 모든객체 저장 가능
		
		System.out.println("처음 크기 : " + v1.size());
		
		// 데이터 추가 1 : add(추가할 데이터)
		// 반환값 : 성공(true), 실패(false)
		v1.add("AAAA"); // string 객체
		v1.add(new Integer(123)); // 숫자를 넣으려면 정수형 데이터를 객체화 시켜줘야 한다
		v1.add(111); // 객체가 아닌 데이터가 들어갈 때 자동으로 객체화 시켜서 넣어준다 : 오토 박싱으로 자동으로 객체화 된다
		v1.add('A');
		v1.add(true); // 논리형 데이터도 오토 박싱작업이 수행된다
		boolean r = v1.add(3.14);
		
		System.out.println("v1의 크기 : " + v1.size());
		System.out.println("반환값 : " + r);
		System.out.println("v1 -> " + v1); // v1 = v1.toString()
		
		// 데이터 추가 2 : addElement(추가할 데이터) - add와 기능이 거의 같음
		// 이전 버전의 Vector에서 사용하던 메서드
		v1.addElement("KKK");
		
		System.out.println("v1 -> " + v1);
		
		// 데이터 추가 3 : add(index, 데이터)
		// -> index번째에 '데이터'를 끼워 넣는다
		// -> index는 0부터 시작한다
		// -> 반환값은 없다
		v1.add(1, "ZZZ");
		
		System.out.println("v1 -> " + v1);
		
		// 데이터 수정 : set(index, 새로운데이터)
		// -> 'index'번째 자료를 '새로운 데이터'로 변경한다
		// 반환값 : 원래의 데이터
		String temp = (String) v1.set(0, "YYYY"); // v1.set(0, "YYYY") -> Object로 변환된 상태
		
		System.out.println("v1 -> " + v1);
		System.out.println("원래의 데이터(반환값) ->" + temp);
		
		// 데이터 삭제 1 : remove(index)
		// -> 'index'번째의 데이터를 삭제한다
		// -> 반환값 : 삭제된 데이터
		temp = (String) v1.remove(0);
		
		System.out.println("삭제 후 : " + v1);
		System.out.println("삭제된 데이터(반환값) -> " + temp);

		// 데이터 삭제 2 : remove(삭제할 데이터)
		// -> '삭제할 데이터'를 찾아서 삭제한다
		// -> '삭제할 데이터'가 여러개이면 앞에서부터 삭제된다
		// -> 반환값 : 삭제성공(true), 삭제실패(false)
		// => '삭제할 데이터'가 '정수형'이거나 'char형'일 경우 '삭제할 데이터'를 객체로 변환해서 사용해야 한다
		r = v1.remove("KKK");
		System.out.println("삭제 후 -> " + v1);
		System.out.println("반환값 : " + r);
		
//		v1.remove(111); // index는 숫자이기 때문에 정수형 데이터가 오면 index로 인식을 한다
		v1.remove(new Integer(111));
		System.out.println("삭제 후 : " + v1);
		
//		v1.remove('A'); // 작은 따옴표로 감싼 문자는 문자의 코드값(숫자)으로 바뀐다
		v1.remove(new Character('A'));
		System.out.println("삭제 후 : " + v1);
	
		v1.remove(true); // 정수형과 char형 이외의 타입은 오류없이 삭제 가능
		System.out.println("삭제 후 : " + v1);

		v1.remove(3.14);
		System.out.println("삭제 후 : " + v1);
		
		// 데이터 꺼내오기 : get(index)
		// -> 'index'번째의 데이터를 반환한다
		int data = (int) v1.get(1);
		System.out.println("0번째 자료 : " + data);
		
		//=======================================================================
	/*
	 
	  제네릭 타입(Generic Type) : 클래스 내부에서 사용할 데이터의 타입을 외부에서 지정하는 기법
	 -> 객체를 선언할 때 <>안에 그 객체가 사용할 데이터의 타입을 정해준다
	 -> 이런식으로 선언하게 되면 그 데이터 타입 이외의 데이터를 저장할 수 없다. 
	         단, 제네릭으로 선언될 수 있는 데이터 타입은 클래스형이어야 한다.
	 ex) int -> Integer, boolean -> Boolean, char -> Character 등으로 대체해서 사용해야 한다.
	 -> 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
	 
	 */
		
		Vector<String> v2 = new Vector<String>();
		Vector<Integer> v3 = new Vector<>();
		
		v2.add("안녕하세요");
//		v2.add(123); // 오류 : 제네릭 타입과 맞지 않은 자료형 추가
		
		String temp2 = v2.get(0); // 형변환이 필요없다
		
		Vector<Vector> vv = new Vector<>(); // 2차원 배열과 비슷한 형태
		Vector<Vector<Vector>> vvv = new Vector<>();
		
		//=======================================================================

		System.out.println("-----------------------------");
		
		v2.clear(); // 전체 데이터 삭제
		System.out.println("v2의 크기 : " + v2.size());
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");

		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v2 -> " + v2);
		System.out.println("v4 -> " + v4);
		
		// 데이터 삭제 3 : removeAll(Collection 객체) // 삭제할 데이터가 들어있는 객체를 삭제
		// -> 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다
		// -> 반환값 : 성공(true), 실패(false)
		v2.removeAll(v4); // v2에서 v4가 가지고 있는 것들을 삭제해라
		
		System.out.println("v2 -> " + v2);
	
		System.out.println("----------------------------------------------------------");
		
		v2.clear();
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		// Vector의 데이터를 처음부터 차례로 읽어와 처리하고 싶으면 반복문을 사용하면 된다
		for(int i=0; i<v2.size(); i++){
			System.out.println(i + " 번째 자료 : " + v2.get(i));
		}
		System.out.println("----------------------------------------------------------");
		
		// 향상된 for문
		for(String s : v2){ // 벡터 개수만큼 반복 벡터의 데이터를 꺼내서 s에 저장
			System.out.println(s);
		}
		
		
		
	}

}
