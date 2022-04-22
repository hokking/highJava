package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashcodeTest {

	public static void main(String[] args) {

		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1);
		p2.setName(new String("홍길동"));
		
		Person p3 = p1; // p1에 있는 값을 p3에 저장 = p1의 주소를 p3에 저장해라
		
		System.out.println(p1==p2); // 참조값이 다르다 = 주소가 다르다
		System.out.println(p1.equals(p2));
		
//		String str1 = "이순신";
//		String str2 = "이순신";
//		String str3 = new String("이순신");
//		String str4 = new String("이순신");
		
		HashSet<Person> testSet = new HashSet<>();
		
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("set의 크기 : " + testSet.size()); // equals외의 hashcod도 비교한다
		
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode()); // 값 비교 메서드에서 hashcode도 재정의 해야한다
		
		/*
		 * - equals() 메소드 -> 두 객체의 내용이 같은지 검사하는 연산자
		 * - hashCode() 메소드 -> 두 객체의 동일성을 검사하는 연산자
		 * 
		 * HashSet, Hashtable, HashMap과 같이 Hash로 시작하는 컬렉션 객체들은 
		 * 객체의 의미상의 동일성을 비교하기 위해서  hashCode()메서드를 호출하여 비교한다.
		 * 그러므로, 객체가 같은지 여부를 결정하려면  hashCode()메소드를 재정의 해야 한다.
		 * 
		 * hashCode()메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서 같은 hashcode가 발생할 수 있다.
		 */
	}

}

/*
 * equals() 메소드
 * public boolean equlas(Object obj){
 * 	return this == obj;
 * }
 */
class Person {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/*
	// 객체의 id값과 name값이 같으면 true를 반환하도록 재정의 한다.
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		// 같은 유형의 클래스인지 검사
		if(this.getClass() != obj.getClass()) return false; // 클래스 유형을 알아내는 메서드
		
		// 참조값이 같은지 검사
		if(this == obj) return true;
		
		// 매개변수에 저장된 객체를 현재 객체 유형으로 형변환 한다.
		Person that = (Person)obj; // 형변환을 하지 않으면 Person 객체에 접근할 수가 없다
		
		if(this.name == null && that.name != null) 
			return false;
		if(this.id == that.id && this.name == that.name) // new String 객체를 만들때 false가 나옴
			return true;
		if(this.id == that.id && this.name.equals(that.name)) 
			return true;
		
		return false;
	}
	*/
}
