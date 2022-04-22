package kr.or.ddit.basic;

/*
 * Daemon Thread 연습 : 자동 저장하는 thread
 */

public class ThreadTest06 {

	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// Daemon Thread로 설정하기 : 반드시 start() 메서드 호출 전에 설정
		autoSave.setDaemon(true); // 일반 thread는 독립적으로 종료를 설정하는데 
		//데몬 thread는 일반 thread가 종료되면 자동으로 종료된다.
		
		autoSave.start();
		
		try {
			for(int i = 1; i<=20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("main thread 작업 끝");
	}

}

// 자동 저장하는 thread 작성 (3초에 한번씩 자동 저장하기)
class AutoSaveThread extends Thread {
	// 작업 내용을 저장하는 메서드
	public void save() {
		System.out.println("작업 내용을 저장합니다");
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			save();
		}
	}
}