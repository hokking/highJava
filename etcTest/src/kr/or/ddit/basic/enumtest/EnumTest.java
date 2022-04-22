package kr.or.ddit.basic.enumtest;

/*
 * enum(열거형) : 서로 관련있는 상수들의 집합을 나타낸다.
 * 	- 클래스처럼 보이게 하는 상수
 *  - 열거형은 class처럼 독립된 java파일에 만들 수 있고,
 *    하나의 java파일에 클래스와 같이 만들 수 있고,
 *    class안에 내부 클래스처럼 만들 수 있다.
 *    
 *  - 열거형의 속성 및 메서드
 *  1) name() : 열거형 상수의 이름을 문자열로 반환한다.
 *  2) ordinal() : 열거형 상수가 정의된 순서값(index값)을 반환한다.(0부터 시작)
 *  3) valueOf("열거형상수명") : 지정된 열거형에서 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
 *  4) 열거형이름.상수명 : valueOf("상수명")메서드와 같다.
 *  
 *  
 *  - 열거형 선언하기
 *  1) enum 열거형이름 {상수명1,상수명2,상수명3,...}
 *  2) enum 열거형이름 {
 *     	  상수명1(값들...), // 값을 여러개 지정할 경우 모든 상수의 자료형은 같아야 한다
 *     	  상수명2(값들...), // ex) int, String, int
 *     	  상수명3(값들...),		  int, String, int
 *     	 ...
 *     	  상수명n(값들...);
 *     }
 *     - '값들'이 저장될 변수를 선언한다.
 *     - '값들'의 개수만큼 변수를 선언한다.
 *     private 자료형이름 변수명1;
 *     ...
 *     
 *     - 열거형의 생성자를 만든다.
 *     - 열거형의 생성자는 '열거형상수'에 '값들'을 세팅하는 역할을 수행한다.
 *     - 열거형의 생성자는 묵시적으로 'private'이다.
 *     
 *     // '변수명'은 '값들'과 개수가 같고, 각각의 '값들'과 자료형이 맞아야 한다.
 *     private 열거형이름(자료형이름 변수명,...){//자료형 이름의 변수는 값들의 개수랑 맞춰야 한다
 *     	  위에 선언된 변수들을 매개변수를 이용해서 초기화 한다.
 *     	 ...
 *     }
 *     // 구성된 '값들'을 외부에서 불러올 수 있도록 getter메서드를 작성한다.
 *     public 자료형이름 get변수명1(){
 *        return 변수명1;
 *     }
 *     
 *     
 *     
 */

public class EnumTest {
	public enum Color {RED, GREEN, BLUE}
	public enum Count {ONE, TWO, THREE}
	
	public enum Season{
		봄("3월부터 5월까지"),	// 상수명(값들...) 형식의 선언
		여름("6월부터 8월까지"),
		가을("9월부터 11월까지"),
		겨울("12월부터 2월까지");
		
		// 값이 저장될 변수 선언
		private String span;
		
		// 생성자
		Season(String months) { // private Season(String monts){ 와 같다. 
			span = months; // 변수에 값을 초기화하는 작업을 수행한다.
		}
		
		// getter 생성
		public String getSpan(){
			return span;
		}
	}

	public static void main(String[] args) {

//		System.out.println("Red : " + ConstTest.RED);
//		System.out.println("Three : " + ConstTest.THREE);
//		
//		if(ConstTest.RED == ConstTest.ONE){ // 물리적으로는 맞지만 논리적으로 맞지 않다
//			System.out.println("yes");
//		}else{
//			System.out.println("no");
//		}
		
		Color mycol = Color.valueOf("GREEN"); // Color.GREEN 과 같다.
		Count mycnt = Count.ONE; // Count.valueOf("ONE") 과 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		
		System.out.println("mycol ordinal : " + mycol.ordinal());
		System.out.println("mycnt ordinal : " + mycnt.ordinal());
		
		// 서로 다른 열거형끼리의 비교는 불가능하다.
//		if(Color.RED == Count.TWO){ // 열거형 이름이 다르면 서로 비교를 할 수 없다
//			System.out.println();
//		}
		
		if(mycol == Color.BLUE){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
		System.out.println();
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println();
		
		// 열거형명.values() : 모든 상수들을 가져와 배열로 반환한다.
		for(Season time : Season.values()){
			System.out.println(time.name() + " : " + time + " -> " + time.getSpan());
		}
		System.out.println();
		
		for(Color col : Color.values()){
			System.out.println(col + " -> " + col.ordinal());
		}
		System.out.println();
		
		
		
		
	}

}
