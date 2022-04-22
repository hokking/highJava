package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * UDP방식 : 비연결 지향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없다. 그렇지만 TCP보다는 속도가 빠르다.
 * 
 * DatagramSocket객체와 DatagramPacket객체를 이용해서 통신한다.
 * - DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다(우체부)
 * - DatagramPacket : 주고받는 데이터와 관련된 작업을 수행한다(소포,편지)
 * 					 수신을 위한 생성자와 송신을 위한 생성자를 따로 제공한다
 * 
 */
public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다
			DatagramSocket socket = new DatagramSocket(8888); 
			// 포트번호는 내맘대로 작성(중복이 안될만한 걸로 설정)
			
			// 수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행중...");
			
			while(true){
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷객체 생성
				// - 데이터가 저장될 byte형 배열, 배열의 길이를 이용하여 생성한다
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다
				// 이 메서드는 데이터가 올때까지 기다린다
				// 수신 된 데이터의 패킷 정보는 지정한 패킷변수에 저장된다
				socket.receive(inpacket);
				
				// 수신받은 패킷에서 상대방의 IP주소, 포트번호등을 알 수 있다
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP정보 : " + address);
				System.out.println("상대방의 Port번호 : " + port);
				
				// 상대방이 보낸 메시지 화면에 출력하기
				// inpacket.getLength() - 실제 읽어온 길이
				// inpacket.getData() - 실제 읽어온 데이터를 byte배열로 반환한다
				// 실제 데이터는 수신용 패킷객체에 지정한 byte형 배열에도 저장된다
				// 방법1)
//				String msg = new String(bMsg, 0, inpacket.getLength(), "utf-8");
				// bMsg가 가진 문자중에 0번째부터 inpacket이 가진 길이만큼 문자열로 만들어라
				
				// 방법2)
				String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				
				if("/end".equals(msg)){
					break;
				}
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();
				
				// ----------------------------------------------------------------------------
				// 상대방에게 메시지 보내기(수신받은 메시지 그대로 송신한다)
				
				// 송신할 메시지를 byte형 배열로 변환한다
				byte[] sendMsg = msg.getBytes("utf-8");
				
				// 송신용 패킷 객체 생성 
				// - 전송할 데이터가 저장된 byte형 배열, 전송할 자료의 길이(배열길이), 상대방 주소 정보, 상대방 포트번호
				//	  위의 4가지를 지정하여 생성한다
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기 - send()메서드 이용
				socket.send(outpacket);
				
				System.out.println("송신 완료");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
