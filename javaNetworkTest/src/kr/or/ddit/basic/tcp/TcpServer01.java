package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 위해서 ServerSocket객체를 생성한다
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 접속을 기다립니다 ...");
		
		/*
		  accept()메서드 
		- 클라이언트에서 연결 요청이 올 때까지 기다린다.
		- 연결 요청이 오면 클라이언트와 연결된 Socket객체를 생성해서 반환한다.*/
		Socket socket = server.accept();
		
		// accept()메서드 이후의 내용은 연결이 완료되어야만 실행된다.
		System.out.println("클라이언트와 연결이 완료되었습니다.");
		System.out.println();
		
		System.out.println("소켓으로 연결된 상대방에 대한 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("소켓으로 연결된 로컬 컴퓨터에 대한 정보"); // 내 컴퓨터에 대한 정보
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		// 클라이언트에게 메시지 보내기
		// - Socket의 OutputStream객체를 이용하여 전송한다.
		//	(socket.getOutputStream()메서드를 이용하여 출력용 스트림 객체를 구할 수 있다)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// 클라이언트에게 메시지를 보낸다(클라이언트로 출력하는 방식으로 처리한다)
		// = 클라이언트로 출력한다
		dos.writeUTF("환영합니다. 어서오세요.");
		
		System.out.println("클라이언트에게 메시지를 보냈습니다");
		
		// 스트림과 소켓 닫기
		dos.close();
		socket.close();
		server.close();
	}

}
