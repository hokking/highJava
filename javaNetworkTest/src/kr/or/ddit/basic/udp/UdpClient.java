package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 송신용, 수신용 패킷객체 변수선언
		DatagramPacket inpacket, outpacket;

		// 수신 받은 데이터가 저장될 byte형 배열
		byte[] bMsg = new byte[512];

		try {
			DatagramSocket socket = new DatagramSocket();

			// 접속할 곳의 주소 생성
			InetAddress address = InetAddress.getByName("localhost");

			while (true) {
				// 전송할 메시지 입력
				System.out.print("전송할 메시지 입력 : ");
				String msg = scan.nextLine();

				if ("/end".equals(msg)) {
					break;
				}

				// 전송할 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"),
						msg.getBytes("utf-8").length, address, 8888);
				
				// 전송
				socket.send(outpacket);
				
				//---------------------------------------------------------------------------
				
				// 서버에서 보내온 메시지 받아서 출력하기
				
				// 수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 수신
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 데이터 : " + new String(bMsg, 0, inpacket.getLength(), "utf-8"));
				System.out.println();
			} // while문 끝
			
			System.out.println("통신 끝");
		
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
