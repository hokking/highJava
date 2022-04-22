package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// Thread 가 수행되는 시간 체크하기

		Thread th = new Thread(new MyRunner2());
		
		// 1970년 1월 1일 0시 0분 0초(표준시간)로 부터 경과한 시간을 
		// millisecond단위(1/1000초)로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start(); // thread 환경 구축, thread 실행 
		// thread의 일처리는 run()으로 실행
		
		try {
			th.join(); 	// 현재 실행중인 thread에서 대상이 되는 
						//thread(여기서는 변수 th)가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
		}
		
		long endTime = System.currentTimeMillis();

		System.out.println("경과시간 : " + (endTime - startTime));
	}

}

class MyRunner2 implements Runnable {

	@Override
	public void run() {
		long sum = 0L;
		for (long i = 1L; i <= 1_000_000_000L; i++) { // 자릿수를 나타내는 기호로 '_' 사용 가능
			sum += i;
		}

		System.out.println("합계 : " + sum);
	}

}
