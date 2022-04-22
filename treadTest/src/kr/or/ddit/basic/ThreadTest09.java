package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 컴퓨터의 가위바위보는 난수를 이용해서 구하고, 
 * 사용자의 가위바위보는 showInputDialog()메서드를 이용해서 입력받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트 다운을 한다.
 * 5초안에 입력이 없으면 게임에 진것으로 처리한후 끝낸다.
 * 5초안에 입력이 있으면 승패를 구해서 출력한다.
 */

public class ThreadTest09 {

	public static void main(String[] args) {
		dataCreate dc = new dataCreate();
		data di = new data();
		countTime cd = new countTime(); // 같은 thread이름을 쓸 수 없다.

		dc.start();
		di.start();
		cd.start();
	}
}

class dataCreate extends Thread {
	public static String rs;
	@Override
	public void run() {
		String[] cdata = { "가위", "바위", "보" };
		int data = (int) (Math.random() * 3);
		rs = cdata[data];

	}
}

class data extends Thread {
	public static boolean inputCheck = false;
	@Override
	public void run() {
		String input = JOptionPane.showInputDialog("가위 바위 보");
		inputCheck = true;
		String result = "";
		System.out.println("컴퓨터 : " + dataCreate.rs);
		System.out.println("사용자 : " + input);
		if (input.equals(dataCreate.rs)) {
			result = "무승부입니다.";
		} else if (input.equals("가위") && dataCreate.rs.equals("바위")
				|| input.equals("바위") && dataCreate.rs.equals("보")
				|| input.equals("보") && dataCreate.rs.equals("가위")) {
			result = "당신이 졌습니다";
		} else {
			result = "당신이 이겼습니다";
		}
		System.out.println("결과 : " + result);
	}
}

class countTime extends Thread {
	@Override
	public void run() {
		for (int i = 5; i > 0; i--) {
			if (data.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("시간초과로 당신이 졌습니다.");
		System.exit(0);
	}
}
