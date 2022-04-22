package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		(컴퓨터의 숫자는 난수를 이용하여 구한다)
 * 		(스트라이크는 S, 볼은 B로 나타낸다)
 */
public class BaseBallTest {
	ArrayList<Integer> numList;
	ArrayList<Integer> num;
	int s;
	int b;

	public static void main(String[] args) {
		new BaseBallTest().startGame();
	}

	public void getNum() {
		HashSet<Integer> numSet = new HashSet<>();
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 + 1));
		}
		System.out.println(numSet);
		numList = new ArrayList<>(numSet);
	}

	public void inputNum() {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자입력 -> ");
		num = new ArrayList<>();
		for (int i = 0; i < numList.size(); i++) {
			num.add(sc.nextInt());
		}
	}

	public void ballCount() {
		s = 0;
		b = 0;

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < num.size(); j++) {
				if (numList.get(i) == num.get(j)) {
					if (i == j) { // 위치가 같으면
						s++;
					} else {
						b++;
					}
				}
			}
		}
		System.out.println(num.get(0) + " " + num.get(1) + " " + num.get(2) + " : " + s + "S " + b + "B");
	}

	public void startGame() {
		int ag = 0;
		getNum();
		do {
			ag++;
			inputNum();
			ballCount();
		} while (s != 3);
		System.out.println(ag + "번 만에 맞추셨습니다!");
	}
}
