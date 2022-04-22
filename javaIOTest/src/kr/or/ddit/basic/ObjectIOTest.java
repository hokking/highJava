package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ObjectIOTest {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "남해");
		Member mem4 = new Member("홍길북", 50, "강릉");
		
		Map<String, Member> m = new HashMap<>();
		m.put(mem1.getName(), mem1);
		m.put(mem2.getName(), mem2);
		m.put(mem3.getName(), mem3);
		m.put(mem4.getName(), mem4);

		try {
			// 객체를 파일에 저장하기
			FileOutputStream fout = new FileOutputStream(
					"d:/d_other/memObj.bin");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			

			// 쓰기 작업 시작
			System.out.println("객체 저장하기 시작");
			oout.writeObject(m.get(mem1));
			oout.writeObject(m.get(mem2));
			oout.writeObject(m.get(mem3));
			oout.writeObject(m.get(mem4));
			System.out.println("객체 저장 작업 끝!");

			oout.close(); // 스트림 닫기

		} catch (IOException e) {
			// TODO: handle exception
		}
		// ==================================================================

		// 저장된 객체를 읽어와 그 내용을 화면에 출력하기
		try {
			// 입력용 스트림 객체 생성
			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(
							"d:/d_other/memObj.bin")));

			Object obj; // 읽어온 객체를 저장할 변수

			System.out.println("객체 읽기 작업 시작");

			// readObject()메서드가 데이터를 끝까지 다 읽어오면 EOFException이 발생한다.

			while ((obj = oin.readObject()) != null) {
				// 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다
				Map<String, Member> mem = (Map<String, Member>) obj;
				for(String key : mem.keySet()){
				System.out.println("이름 : " + mem.get(key).getName());
				System.out.println("나이 : " + mem.get(key).getAge());
				System.out.println("주소 : " + mem.get(key).getAddr());
				System.out.println("----------------------------");
				}
			}
		} catch (EOFException e) {
			System.out.println("객체 읽기 작업 끝!");
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Member implements Serializable {

	private static final long serialVersionUID = 562285606573703211L;
	// transient : 직렬화가 되지 않을 멤버변수에 지정한다.
	// 직렬화가 되지 않은 멤버변수는 기본값으로 저장된다.(참조변수 - null, 숫자유형변수 - 0)
	private String name;
	private transient int age;
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}