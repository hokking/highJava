package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		
		// InetAddress 클래스 : IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println("toString : " + naverIp.toString());
		
		System.out.println();
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("Local Host Name : " + localIp.getHostName());
		System.out.println("Local Host Address : " + localIp.getHostAddress());
		
		System.out.println();
		
		// IP주소가 여러개인 호스트이 정보 가져오기
		InetAddress[] ips = InetAddress.getAllByName("www.daum.net");
		for(InetAddress ip : ips){
			System.out.println(ip.toString());
		}
	}

}
