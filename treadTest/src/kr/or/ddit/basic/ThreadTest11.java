package kr.or.ddit.basic;

// yield() 메서드 연습
public class ThreadTest11 {

	public static void main(String[] args) {

		YieldThread yt1 = new YieldThread("1번 thread");
		YieldThread yt2 = new YieldThread("2번  thread");
		
		yt1.start();
		yt2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("11111111111=========111111111111");
		
		yt1.work = false;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("222222222=========2222222222");
		
		yt1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		// 2에서 일을 하지 못한 1은 여기서 몰아서 일함
		System.out.println("3333333333=========33333333333");
		
		yt1.stop = true;
		yt2.stop = true;
		
	}

}

// yield() 메서드 연습용 thread
class YieldThread extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name){
		super(name); // thread의 이름 설정하기
		// 부모 thread의 생성자
	}
	
	@Override
	public void run() {
		while(!stop){ // stop값이 true이면 반복문 탈출
			if(work){
				// getName() 메서드 : thread 이름(name속성값) 반환
				System.out.println(getName() + " 작업중");
			}else{
				System.out.println(getName() + " 양보");
				Thread.yield();
			}
		}
	}
}