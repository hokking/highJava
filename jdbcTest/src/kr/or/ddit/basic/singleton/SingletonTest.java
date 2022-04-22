package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		// 외부에서 new 명령을로 생성 불가
//		MySingleton test1 = new MySingleton();
		
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 : " + test2);
		System.out.println("test3 : " + test3);
		// 객체가 하나만 만들어졌다
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		test2.displayTest();
	}

}
