package kr.or.ddit.basic.argtes;

public class ArgsTest {
	
	// 매개변수로 여러개의 정수들을 받아서 이 정수들의 합계를 구하는 메서드
	// (이 정수들의 개수는 상황에 따라 다르다.)
	public int sumArr(int[] data){
		int sum = 0;
		for(int i : data){
			sum+=i;
		}
		return sum;
	}
	
	// 가변형 인수 : 메서드의 인수값의 개수가 메서드를 호출할 때마다 다를 때 사용한다.
	//	- 가변형 인수를 갖는 변수는 메서드 안에서는 배열로 처리된다.
	//  - 가변형 변수는 한가지 자료형만 사용할 수 있다.
	
	// 가변형 인수를 이용한 메서드 : 개수가 정해지지 않은 인수값
	public int sumArg(int...data){
		int sum = 0;
		for(int i : data){
			sum+=i;
		}
		return sum;
	}
	
	// 가변형 변수와 일반적인 매개변수를 혼합해서 사용할 수 있다
	// 단, 이때는 가변형 변수를 제일 뒤쪽에 배치한다
	public String sumArg2(String name, int...data){
		int sum = 0;
		for(int i : data){
			sum+=i;
		}
		return name + " 씨 총점 : " + sum;
	}

//	public void test(int a, int b){	}

	public static void main(String[] args) {
		ArgsTest a = new ArgsTest();
//		a.test(100, 200);
		
		int[] nums = {100, 200, 300};
		System.out.println("합계 : " + a.sumArr(nums));
		System.out.println("합계 : " + a.sumArr(new int[]{1,2,3,4,5}));
		System.out.println();
		
		System.out.println("가변형 합계 : " + a.sumArg(100, 200, 300));
		System.out.println("가변형 합계 : " + a.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(a.sumArg2("홍길동", 1,2,3,4,5,6,7,8));
		
	}

}
