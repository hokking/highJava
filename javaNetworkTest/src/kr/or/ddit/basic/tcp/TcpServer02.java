package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) {
		
		try {
			// ServerSocket객체를 만들고, 클라이언트가 접속해 오면 Socket을 만들어서 
			// 메시지를 보내는 클래스와 메시지를 받는 클래스에 이 Socket을 넘겨준다
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비중입니다...");
			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
