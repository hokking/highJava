package kr.or.ddit.basic;

/*
 * 은생의 입출금 thread로 처리하는 예제(동기화 처리 예제)
 */

public class ThreadTest17 {

	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금하는 메서드(출금 성공 : true, 출금 샐패 : false 반환)
	public synchronized boolean withdraw(int money) {
//		synchronized (this) {
			if (balance >= money) {
				for (int i = 0; i < 100_000_000; i++) {
				} // 다른 thread로 넘어갈 수 있도록 하기 위한 시간 지연용 
				balance -= money;
				System.out.println("메서드 안에서 balance = " + balance);
				return true;
			} else
				return false;
//		}
	}

	public static void main(String[] args) {
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000); // 잔액을 10000원으로 설정한다.

		// 익명 구현체로 thread 만들기
		Runnable test = new Runnable() {

			@Override
			public void run() {
				boolean result = acount.withdraw(6000); // 6000원 출금하기

				System.out.println("thread에서 result = " + result
						+ ", balance : " + acount.getBalance());
			}
		};
		//---------------------------------------------------------------------------
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
	}

}
