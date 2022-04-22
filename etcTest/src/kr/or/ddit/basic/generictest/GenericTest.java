package kr.or.ddit.basic.generictest;

/*
 * Generic class를 만드는 방법
 * 형식) 
 * 	class 클래스명<제네릭타입글자>{
 * 		제네릭타입글자 변수명; // 변수 선언에 제네릭을 사용한 경우
 * 		...
 * 		제네릭타입글자 메서드명(메개변수들...){ // 메서드의 반환값에 제네릭을 사용한 경우
 * 			...
 * 			return 값;
 * 		}
 * 
 * 		반환값타입 메서드명(제네릭타입글자 변수명){ // 메서드의 매개변수에 제네릭을 사용한 경우
 * 			...
 * 			return 값;
 * 		}
 * 	}
 */

class MyGeneric<T>{ // MyGeneric<V,N,W> 여러개의 타입을 사용할 수 있음 초기화 할때도 세개 써줘야 함
	private T val;
	
	public void setVal(T val){
		this.val = val;
	}
	
	public T getVal(){
		return val;
	}
}

class NonGeneric{
	private Object val;
	
	public void setVal(Object val){
		this.val = val;
	}
	
	public Object getVal(){
		return val;
	}
}

public class GenericTest { // collection객체를 생성할 때 지정

	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setVal("가나다라마바사");
		
		NonGeneric ng2 = new NonGeneric();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 : " + rtnNg1);
		
//		Integer rtnNg2 = (Integer)ng2.getVal();
		Integer rtnNg2 = (int)ng2.getVal(); // Wrapper클래스를 자동으로 변환 int->Integer
		System.out.println("정수형 반환값 : " + rtnNg2);
		
		NonGeneric ng3 = new NonGeneric();
		ng3.setVal("홍길동");
//		Integer rtnNg3 = (Integer)ng3.getVal();
//		System.out.println("ng3 -> " + rtnNg3); // 다른 데이터의 문자로 형변환 해서 가져오는 것을 check를 하지 못한다
		
		System.out.println("-----------------------------------");
		MyGeneric<String> mg1 = new MyGeneric<>();
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		
		mg1.setVal("우리나라");
		mg2.setVal(200);
		
		String rtnMg1 = mg1.getVal();
		System.out.println("제네릭 문자열 반환값 : " + rtnMg1);
		
		int rtnMg2 = mg2.getVal();
		System.out.println("제네릭 정수형 반환값 : " + rtnMg2);
		
//		int test = mg1.getVal(); // 오류발생
	}

}
