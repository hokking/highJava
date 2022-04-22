package kr.or.ddit.basic;

// Thread에섯 객체를 공통으로 사용하는 예제

/*
 * 원주율(PI)를 계산하는 thread와
 * 계산된 원주율을 출력하는 thread가 있다.
 * 
 * 원주율을 저장하는 객체가 필요하다.
 * 이 객체를 두 thread가 공통으로 사용해서 처리한다.
 */
public class ThreadTest15 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		// thread 객체를 생성하고 공통으로 사용할 객체를  thread에 주입한다.
		CalcPIThread ct = new CalcPIThread();
		ct.setSd(sd);
		
		PrintPIThread pt = new PrintPIThread(sd);
		
		ct.start();
		pt.start();
		
	}

}

// 원주율을 관리하는 클래스 작성(공통으로 사용할 클래스)
class ShareData {
	public double result; // 계산된 원주율이 저장될 변수 선언

	public boolean isOk = false; // 계산이 완료되었는지를 나타내는 변수

}

// 원주율을 계산하는 thread
class CalcPIThread extends Thread {
	private ShareData sd;

	// setter
	public void setSd(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
		 * 원주율 = (1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 ...) * 4;
		 * 1 - 3 + 5 - 7 + 9 ...
		 * 0 - 1 + 2 - 3 + 4 ... (2로 나눈 몫)
		 */

		double sum = 0.0;
		for (int i = 1; i <= 900000000; i += 2) {
			if ((i / 2) % 2 == 0) { // 몫이 짝수인 경우
				sum += (1.0 / i);
			} else {
				sum -= (1.0 / i);
			}
		}

		sd.result = sum * 4; // 계산이 완료된 값을 공통객체에 저장한다.
		sd.isOk = true;
	}
}

// 계산이 완료되면 계산된 원주율을 출력하는 thread
class PrintPIThread extends Thread{
	private ShareData sd;
	
	// 생성자
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true){
			// 계산 먼저
			if(sd.isOk==true){
				break;
			}
			yield(); // isOk가 true가 아니면 양보 
			// 실행대기(-일시정지)로 안들어가기 위해서 사용 : 프로그램이 끝나지 않음
		}
		
		System.out.println();
		System.out.println("결과   : " + sd.result);
		System.out.println("PI  : " + Math.PI);
	}
}