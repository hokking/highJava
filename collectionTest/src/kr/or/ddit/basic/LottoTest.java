package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LottoTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("================================");
			System.out.println("  Lotto 프로그램");
			System.out.println("--------------------------------");
			System.out.println("  1. Lotto 구입");
			System.out.println("  2. 프로그램 종료");
			System.out.println("================================");
			System.out.print("  메뉴선택 : ");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("  Lotto 구입 시작");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int money = sc.nextInt();
				int num = 0;
				if (money >= 1000 && money <= 100000) {
					num = money / 1000;
					HashSet<Integer> lottoSet = new HashSet<>();
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					for (int i = 0; i < num; i++) {
						lottoSet = new HashSet<>();
						while (lottoSet.size() < 6) {
							lottoSet.add((int) (Math.random() * 45) + 1);
						}
						ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
						System.out
								.println("당첨번호" + (i + 1) + " : " + lottoList);

					}
					
					System.out.println("받은 금액은 : " + money + "이고 거스름돈 : "
							+ (money - (num * 1000)));
				} else if (money < 1000) {
					System.out.println("입력 금액이 너무 적습니다. 로또 번호 구입 실패!!!");
				} else {
					System.out.println("입력 금액이 너무 많습니다. 로또 번호 구입 실패!!!");
				}

				break;

			case 2:
				System.out.println("감사합니다");
				System.exit(0);
			}
		}
	}

}
