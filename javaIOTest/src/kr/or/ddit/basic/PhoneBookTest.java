package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.server.LoaderHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고, 
 * 		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오
 * 
 * 		이 프로그램에는 아래와 같은 메뉴가 있는데 그 기능을 모두 구현하시오
 * 메뉴예시)
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		0. 프로그램 종료
 * 
 * 		Map의 구조는 key값으로 저장되는 사람의 이름을 사용하고, value값으로는 'Phone클래스의 인스턴스'로 한다.
 * 
 * 		- 추가 조건)
 * 		1. '6. 전화번호 저장'메뉴를 추가하고 구현한다.
 * 		   (이 때 저장파일명은 'phoneData.dat'로 한다.)
 * 		2. 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 세팅한다.
 * 		3. 프로그램을 종료할 때 Map의 데이터가 수정되거나, 추가 또는 삭제되면 저장 후 종료되도록 한다.
 * 
 * 
 * 실행예시)
 * 		--------------------------
 * 		다음 메뉴를 선택하세요.
 * 		--------------------------
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		6. 전화번호 저장
 * 		0. 프로그램 종료
 * 		--------------------------
 * 		번호입력 >
 */
public class PhoneBookTest {
	private HashMap<String, Phone> map;
	private Scanner sc;
	private String fileName = "d:/d_other/phoneData.dat";

	// 데이터가 변경되었는지 여부를 나타내는 변수 선언
	// 데이터가 변경되면 이 변수값이 true가 된다
	private boolean dataChange;

	public PhoneBookTest() {
		map = load();

		if (map == null) { // 파일이 없거나 입출력 오류일때
			map = new HashMap<>();
		}

		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new PhoneBookTest().startPhone();
	}

	public void startPhone() {

		while (true) {

			int input = viewMenu();
			switch (input) {
			case 1:
				insertPh();
				break;
			case 2:
				updatePh();
				break;
			case 3:
				deletePh();
				break;
			case 4:
				searchPh();
				break;
			case 5:
				displayAllPh();
				break;
			case 6:
				saveAll();
				break;
			case 0:
				if (dataChange == true) { // 데이터가 변경되었으면
					System.out.println("변경된 데이터를 저장합니다");
					saveAll();
				}
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
			default:
				System.out.println("다시 입력해주세요");

			}
		}
	}

	// 저장된 전화번호 정보를 읽어와서 반환하는 메서드
	private HashMap<String, Phone> load() {
		// 읽어온 데이터가 저장될 변수 선언
		HashMap<String, Phone> pMap = null;

		File file = new File(fileName);
		if (!file.exists()) { // 저장될 파일이 없으면
			return null;
		}

		// 저장될 파일을 읽어오기 위한 스트림 개체 변수 선언
		ObjectInputStream ois = null;
		try {
			// 파일입력용 스트림 객체 생성
			ois = new ObjectInputStream(new FileInputStream(file));
			
			// 파일 내용을 읽어와 Map객체 변수에 저장
			pMap = (HashMap<String, Phone>) ois.readObject();
		
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) { // 형변환 할때 예외
			return null;
		} finally {
			if (ois != null) {try {ois.close();} catch (IOException e) {}}
		}

		return pMap;
	}

	public int viewMenu() {

		System.out.println("----------------------------");
		System.out.println("  다음 메뉴를 선택하세요");
		System.out.println("----------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------------------");
		System.out.print(" 번호입력 > ");
		return Integer.parseInt(sc.nextLine());
	}

	public void insertPh() {
		System.out.print("이름 > ");
		String name = sc.nextLine();
		if (map.containsKey(name)) {
			System.out.println("동일한 이름이 존재합니다");
		} else {

			System.out.print("주소 > ");
			String addr = sc.nextLine();
			System.out.print("번호 > ");
			String tel = sc.nextLine();
			// 등록방법1
			// Phone p = new Phone(name, addr, tel);
			// map.put(name, p);
			// 등록방법2
			map.put(name, new Phone(name, addr, tel));

			dataChange = true;
		}

		/*
		 * Scanner 메서드 특징 nextLine()이 아닌 메서드 -> next(), nextInt(), nextDouble();
		 * 자료형마다 입력할 수 있는 메서드가 존재 : 사이띄기, Tab키, Enter키를 구분문자로 분리해서 분리된 자료만 읽어간다.
		 * Enter키의 찌꺼기가 남는다? nextLine() : 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면
		 * Enter키까지 읽어간다
		 */

	}

	public void updatePh() {

		System.out.print("수정할 사람의 이름 > ");
		String input = sc.nextLine();

		// if (map.containsKey(input)) {
		// System.out.print("주소 > ");
		// String addr = sc.nextLine();
		// System.out.print("번호 > ");
		// String tel = sc.nextLine();
		// map.put(input, new Phone(input, addr, tel));
		// } else {
		// System.out.println("존재하지 않는 이름입니다.");
		// }

		if (!map.containsKey(input)) {
			System.out.println("존재하지 않는 이름입니다.");
			return;
		}
		System.out.print("주소 > ");
		String addr = sc.nextLine();
		System.out.print("번호 > ");
		String tel = sc.nextLine();

		// 정보수정방법1 -> 같은 key값에 새로운 정보를 저장한다
		map.put(input, new Phone(input, addr, tel));

		dataChange = true;
		// 정보수정방법2 -> name을 이용해서 Phone객체를 구한 후 Phone객체의 내용 변경
		// Phone p = map.get(input);
		// p.setAddr(addr);
		// p.setTel(tel);

	}

	public void deletePh() {
		System.out.print("삭제할 사람의 이름 > ");
		String name = sc.nextLine();
		if (map.containsKey(name)) {
			map.remove(name);
			System.out.println("삭제되었습니다");
			dataChange = true;
		} else {
			System.out.println("삭제할 정보가 없습니다.");
		}
	}

	public void searchPh() {
		System.out.print("검색할 사람의 이름 > ");
		String name = sc.nextLine();
		if (!map.containsKey(name)) {
			System.out.println("검색할 정보가 없습니다.");
			return;
		}
		Phone p = map.get(name);
		System.out.println("이름 : " + p.getName());
		System.out.println("주소 : " + p.getAddr());
		System.out.println("전화번호 : " + p.getTel());

	}

	public void displayAllPh() {
		int num = 1;
		System.out.println("번호\t이름\t주소\t전화번호");
		if (map.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
		} else {
			for (String key : map.keySet()) {
				Phone p = map.get(key);
				System.out.println(num++ + "\t" + key + "\t" + p.getAddr()
						+ "\t" + p.getTel());
			}
		}

	}

	private void saveAll() {
		ObjectOutputStream oos = null;
		try {
			// 객체 저장용 출력 스트림 객체 생성
			oos = new ObjectOutputStream(new FileOutputStream(fileName));

			oos.writeObject(map);

			System.out.println("저장이 완료되었습니다");

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			if (oos != null) {try {oos.close();} catch (IOException e) {}
				// 다른 작업을 하다가 예외가 발생하면 닫지못할 수 있기 때문에 finally에
				// 넣어줌
			}
		}
		dataChange = false; // 저장하고 나서 프로그램을 마칠때 또 저장할 필요가 없다
	}
}

class Phone implements Serializable {
	private static final long serialVersionUID = 562285606573703213L;

	private String name;
	private String addr;
	private String tel;

	public Phone(String name, String addr, String tel) {
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
