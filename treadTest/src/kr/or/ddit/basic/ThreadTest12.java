package kr.or.ddit.basic;

/*
 * 3개의 thread가 각각 알파벳 A ~ Z까지 출력하는데
 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
 */

public class ThreadTest12 {

	public static void main(String[] args) {
		DisplayCharacter[] ths = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"), 
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬") };

		for (DisplayCharacter dc : ths) {
			dc.start();
		}

		// 모든 경기가 끝날때까지 기다린다
		for (DisplayCharacter dc : ths) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.setRank);
	}

}

// A ~ Z까지 출력하는 thread
class DisplayCharacter extends Thread {
	public static String setRank = "";
	private String name;

	public DisplayCharacter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + " 의 출력문자  : " + ch);
			try {
				// 0 ~ 499 사이의 난수 값으로 일시정지 시킨다.
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + " 출력 끝");

		// 출력을 끝낸 순서대로 이름을 배치한다
		DisplayCharacter.setRank += name + " ";
	}
}