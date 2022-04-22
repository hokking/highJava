package kr.or.ddit.basic;

/*
 * 1~20억까지의 합계를 구하는 프로그램 작성하기
 *  - 이 작업을 하나의 thread가 단독으로 처리하는 경우와
 *  - 여러개의 thread가 협력해서 처리할 때의 경과시간을 비교해본다.
 */

public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리할 thread 생성
		SumThread st = new SumThread(1L, 2_000_000_000L);
		
		// 협력해서 처리할 thread 생성(4개의 thread 객체 생성)
		SumThread[] stArr = new SumThread[]{
			new SumThread(1L, 500_000_000L),	
			new SumThread(500_000_000L, 1_000_000_000L),	
			new SumThread(1_000_000_000L, 1_500_000_000L),	
			new SumThread(1_500_000_000L, 2_000_000_000L),	
		};
		
		// 단독으로 처리하기
		long startTime = System.currentTimeMillis();
		st.start();
		try { st.join(); } catch (InterruptedException e) {}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때의 경과시간 : " + (endTime - startTime));
		
		System.out.println();
		
		// 협력해서 처리하기
		startTime = System.currentTimeMillis();
		for(SumThread s : stArr){
			s.start(); // 각각 시작을 해줘야 하기 때문에 for문 사용
		}
		for (int i = 0; i < stArr.length; i++) {
			try { stArr[i].join(); } catch (InterruptedException e) {}
			// 각 배열이 끝날때까지 기다려준다
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리할 때의 경과시간 : " + (endTime - startTime));
	}

}

class SumThread extends Thread {
	// 합계를 구할 영역의 사작값과 종료값이 저장될 변수 선언
	private long min, max;

	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		long sum = 0L;

		for (long i = min; i <= max; i++) {
			sum += i;
		}
		
		System.out.println("합계 : " + sum);
	}

}