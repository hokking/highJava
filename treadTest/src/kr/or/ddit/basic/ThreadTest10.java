package kr.or.ddit.basic;

/*
 * thread의 상태를 출력하는 예제
 */

public class ThreadTest10 {

	public static void main(String[] args) {

		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();
	}

}

// thread 상태의 검사 대상이 되는 thread
class TargetThread extends Thread{
	@Override
	public void run() {
		for (long i = 1L; i <= 20_000_000_000L; i++){} // 시간 지연용
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for (long i = 1L; i <= 20_000_000_000L; i++){} // 시간 지연용
	}
}

// TargetThread의 상태를 검사하고 출력하는 thread
class StatePrintThread extends Thread{
	private TargetThread target;
	
	// 생성자 : TargetThread 초기화
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true){
			// thread의 현재 상태 값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 현재 상태 값 : " + state);
			
			if(state == Thread.State.NEW){ // thread가 NEW 상태이면
				target.start();
			}
			
			if(state == Thread.State.TERMINATED){ // thread가 종료 상태이면
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}