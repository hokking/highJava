package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelManageTest {

	Scanner sc = new Scanner(System.in);
	HashMap<Integer, Room> map = new HashMap<>();

	public HotelManageTest() {

		for (int i = 2; i < 5; i++) {
			String roomType = null;
			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트";
				break;
			}
			for (int j = 1; j < 10; j++) {
				int roomNum = i * 100 + j;
				map.put(roomNum, new Room(roomNum, roomType));
			}

		}

	}

	public static void main(String[] args) {
		new HotelManageTest().start();
	}

	public void start() {
		while (true) {
			int input = menu();
			switch (input) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				stateRoom();
				break;
			case 4:
				System.out.println("호텔이 문을 닫았습니다");
				System.exit(0);
			default:
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

	public int menu() {
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.print("선택 > ");
		return sc.nextInt();
	}

	private void checkIn() {
		System.out.println("=== 체크인 ===");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.print("방 번호 입력 > ");
		int roomNum = sc.nextInt();
		if (!map.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		if (map.get(roomNum).getUserName() != null) {
			System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			return;
		}

		System.out.print("이름 입력 > ");
		sc.nextLine();
		String name = sc.nextLine();
		map.get(roomNum).setUserName(name);
	}

	private void checkOut() {
		System.out.println("=== 체크아웃작업 ===");
		System.out.print("체크아웃 할 방번호를 입력하세요 > ");
		int roomNum = sc.nextInt();
		if (!map.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		if (map.get(roomNum).getUserName() == null) {
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		map.get(roomNum).setUserName(null);
	}

	private void stateRoom() {
		System.out.println("=== 현재 객실 상태 ===");
		System.out.println("방번호\t방종류\t투숙객이름");
		ArrayList<Integer> list = new ArrayList<>(map.keySet());
		Collections.sort(list);

		for (Integer i : list) {
			Room r = map.get(i);
			System.out.print(i + "\t" + r.getRoomType() + "\t");
			if(r.getUserName()!=null){
				System.out.println(r.getUserName());
			}else{
				System.out.println("-");
			}
		}
	}

}

class Room {
	private int roomNum;
	private String roomType;
	private String userName;

	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}