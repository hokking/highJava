package kr.or.ddit.basic;

public class Test {

	public static void main(String[] args) {
		
		Value v = new Value();
		
		v.num1 = 200; // 직접변수입력
//		v.num2 = 200; // 캡슐화 : 외부에서 인위적으로 데이터를 변경하지 못하게 감춰놓는다
		v.setNum2(200); // 간접변수입력
		
		System.out.println("num1 : " + v.num1);
		System.out.println("num2 : " + v.getNum2());
	}

}
class Value{
	public int num1;
	private int num2; // 데이터를 보호하기 위해 private 사용

	public int getNum2() { // 메서드는 public
		return num2;
	}
	public void setNum2(int num2) {
		if(num2<0 || num2>100){
			System.out.println("잘못된 점수 입력");
		}else{
			this.num2 = num2;
		}
	}
}