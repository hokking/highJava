package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {

		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("1번", sObj);
		TestThread th2 = new TestThread("2번", sObj);
		
		th1.start();
		th2.start();
		
	}

}

// 공통으로 사용할 클래스
class ShareObject {
	private int sum = 0;

	// 동기화 처리하기
	
	// 방법1) 메서드에 동기화 설정하기
//	public synchronized void add() {
	
	// 방법2) 동기화 블럭으로 설정하기(부분적 동기화 처리)
	public void add() {
		synchronized (this) { // this - add()
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum); // 현재 이 객체를 사용하는 thread : currentThread()
		}
	}
}

class TestThread extends Thread{
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj) {
		super(name);	//	thread의 이름 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			sObj.add(); // add() 메서드를 각각 10번 호출
		}
	}
}
