package kr.or.ddit.basic;

/*
 * wait(), notify()를 이용해서 두 thread가 번갈아 한번씩 실행하는 예제
 * 
 * wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
 */

public class ThreadTest19 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}

}

// 공통으로 사용할 class
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 앞부분 실행 중...");

		notify(); // wait()를 먼저 쓰게 되면 두 thread 모두 waiting pool에 들어가므로
		// 깨워줄 thread가 없기 때문에 notity()먼저 작성

		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodA() 뒷부분 실행 중...");
	}

	public synchronized void methodB() {
		System.out.println("methodB() 앞부분 작업 실행 중...");

		notify();

		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodB() 뒷부분 작업 실행 중...");
	}

}

// WorkObject의 methodA()메서드를 호출하는 thread
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.methodA();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

// WorkObject의 methodB()메서드를 호출하는 thread
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

