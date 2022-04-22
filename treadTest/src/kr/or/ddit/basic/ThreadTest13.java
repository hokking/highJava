package kr.or.ddit.basic;

import java.util.Arrays;

/*
 * 10마리의 말들이 경주하는 프로그램을 작성하시오.
 * 
 * - 말은 Horse라는 이름의 thread 클래스로 작성하는데
 * - 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 * - 그리고 이 클래스에는 등수를 오름차순으로 정렬할 수 있는 내부 정렬 기준이 있다(Comparable인터페이스 구현) 
 * - 경기 구간은 1 ~ 50구간으로 되어있다.
 * - 경기가 끝나면 등수 순으로 출력한다.
 * - 경기 중에는 중간중간에 각 말들의 위치를 아래와 같이 나타내 준다.
 * 	ex) 말이름1 : ------->-------------------------------------(50개)
 * 		말이름2 : ---------------------->----------------------
 * 		...
 * 		말이름10 : --------------->-----------------------------
 * 
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horse = new Horse[] { new Horse("A"), new Horse("B"),
				new Horse("C"), new Horse("D"), new Horse("E"), new Horse("F"),
				new Horse("G"), new Horse("H"), new Horse("I"), new Horse("J") };

		GameState gs = new GameState(horse);
		for(Horse h : horse){
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horse){
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 종료");
		System.out.println();
		
		System.out.println("경기 결과");
		Arrays.sort(horse);
		
		for(Horse h : horse){
			System.out.println(h.toString());
		}
	
	}

}

class Horse extends Thread implements Comparable<Horse> {
	// 경기가 끝난 말의 등수를 정하기 위한 변수
	public static int curRank = 0;

	private String horseName;
	private int rank;
	private int position;

	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return horseName + " : " + rank;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			position = i; // 현재 말의 위치를 저장한다

			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		// 한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다.
		curRank++;
		rank = curRank;

	}

	// 등수의 오름차순 내부 정렬 기준
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.getRank(), h.getRank());
	}

}

class GameState extends Thread {
	private Horse[] horse;

	public GameState(Horse[] horse) {
		super();
		this.horse = horse;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말들의 경기가 종료되었는지 여부 검사
			if (Horse.curRank == horse.length) {
				break;
			}
			
			for(int i = 0; i<15; i++){
				System.out.println();
			}

			for (int i = 0; i < horse.length; i++) {
				System.out.print(horse[i].getHorseName() + " : ");
				for (int j = 0; j < 50; j++) {
					System.out.print("-");
					if (horse[i].getPosition() == j) { // 말의 현재 위치 표시
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println(); // 줄바꿈
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		
		}

	}
	
}