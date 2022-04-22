package kr.or.ddit.mvc.vo;

/*
 * DB테이블에 있는 각 컬럼들을 기준으로 데이터를 객체화할 클래스이다
 * 
 * DB테이블의 '컬럼명'이 이 VO클래스의 '멤버변수'가 된다
 * 
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑해서 사용한다
 * 
 */
public class MemberVO {
	// number만 int형으로
	private String mem_id;
	private String mem_name;
	private String mem_pass;
	private String mem_tel;
	private String mem_addr;
	
	// VO클래스에 별도의 생성자를 만들었을 때는 반드시 기본 생성자도 같이 만들어야 한다
//	public MemberVO(String mem_id, String mem_name, String mem_pass,
//			String mem_tel, String mem_addr) {
//		super();
//		this.mem_id = mem_id;
//		this.mem_name = mem_name;
//		this.mem_pass = mem_pass;
//		this.mem_tel = mem_tel;
//		this.mem_addr = mem_addr;
//	}
	// 객체를 생성해줘야 하기 때문에 기본생성자도 필요
//	public MemberVO() {
//		
//	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

}
