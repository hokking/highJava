package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다
 * 
 * 		이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
 * 		이 Student객체는 List에 저장하여 관리한다
 * 		List에 저장된 데이터(Student)는 학번의 오름차순으로 정렬할 수 있는 내부 정렬기준을 구현한다
 * 		그리고, 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 될 수 있는 외부 정렬기준 클래스를 작성한다
 * 		
 * 		위 정렬 기준에 맞춰 정렬되는 프로그램을 작성하시오
 * 		(단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다)
 */
public class StudentTest {

	public static void main(String[] args) {

		ArrayList<Student> stuList = new ArrayList<>();

		stuList.add(new Student(5, "김영훈", 23, 9, 11));
		stuList.add(new Student(9, "박채린", 51, 64, 18));
		stuList.add(new Student(1, "손영태", 85, 65, 74));
		stuList.add(new Student(3, "곽지훈", 62, 15, 23));
		stuList.add(new Student(2, "박슬기", 62, 85, 45));
		stuList.add(new Student(10, "강나영", 62, 85, 45));
		stuList.add(new Student(7, "김서윤", 60, 79, 7));
		stuList.add(new Student(6, "김진석", 22, 82, 59));
		stuList.add(new Student(8, "강태영", 68, 68, 75));
		stuList.add(new Student(4, "김충신", 93, 62, 37));

		System.out.println("정렬 전");
		for(Student st : stuList){
			System.out.println(st);
		}
		
		for (Student std1 : stuList) {
			int rank = 1;
			for (Student std2 : stuList) {
				if (std1.getSum() < std2.getSum()) {
					rank++;
				}
			}
			std1.setRank(rank);
		}

		System.out.println();
		Collections.sort(stuList, new SortSumDesc());
		System.out.println("정렬 후");
		for (Student sd : stuList) {
			System.out.println(sd);
		}

	}

}

class Student implements Comparable<Student> {

	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		sum = kor + eng + math;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", math=" + math + ", sum=" + sum
				+ ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student num) {
		return Integer.compare(this.getNum(), num.getNum());
	}
}

class SortSumDesc implements Comparator<Student> {

	@Override
	public int compare(Student sum1, Student sum2) {
		if (Integer.compare(sum1.getSum(), sum2.getSum()) * -1 == 0) {
			return sum1.getName().compareTo(sum2.getName());
		} else {
			return Integer.compare(sum1.getSum(), sum2.getSum()) * -1;
		}
	}

}
