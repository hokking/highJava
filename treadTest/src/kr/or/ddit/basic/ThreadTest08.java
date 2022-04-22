package kr.or.ddit.basic;

import java.io.IOException;

import javax.swing.JOptionPane;

public class ThreadTest08 {

	public static void main(String[] args) {
		DataInput th1 = new DataInput();
		CountDown th2 = new CountDown();
		
		th1.start();
		th2.start();
	}
}

class DataInput extends Thread{
	public static boolean checkInput = false;
	
	@Override
	public void run() {
		String st = JOptionPane.showInputDialog("아무거나 입력하세요");
		checkInput = true;
		System.out.println(st);
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		for(int i = 10; i>0; i--){
			System.out.println(i);
			if(DataInput.checkInput == true){
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("십초가 지났습니다 프로그램을 종료합니다.");
		System.exit(0);
	}
}