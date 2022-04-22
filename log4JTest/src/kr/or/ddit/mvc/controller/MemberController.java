package kr.or.ddit.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.CryptoUtil;

public class MemberController {
	private Scanner scan;
	private IMemberService service; // service객체 변수 선언
	private String key;
	
	// 생성자
	public MemberController() {
		scan = new Scanner(System.in);
//		service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
		key = "a1b2c3d4e5f6g7h8";
	}
	
	public static void main(String[] args) throws Exception {
		new MemberController().memberStart();

	}
	
	public void memberStart() throws Exception{
	
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 :	// 추가 
					insertMember(); break;
				case 2 : 	// 삭제
					deleteMember(); break;
				case 3 : 	// 수정  ==> 전체 항목 수정
					updateMember(); break;
				case 4 : 	// 전체 자료 출력
					displayMember(); break;
				case 5 : 	// 수정	==> 원하는 항목만 수정
					updateMember2(); break;
				case 0 : 	// 종료
					System.out.println("작업을 마칩니다.");
					return;
				default : 
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	
	private void insertMember() throws Exception {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId = null;  // 회원ID가 저장될 변수
		
		do{
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			count = service.getMemberCount(memId);
			
			if(count > 0){
				System.out.println(memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
			
		} while(count > 0);
		memId = CryptoUtil.encryptAES256(memId, key);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("비밀번호 >> ");
		String memPass = scan.next();
		memPass = CryptoUtil.sha512(memPass);
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력한 데이터들을 VO 객체에 저장한다
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_pass(memPass);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVo);
		
		if(cnt > 0) {
			System.out.println("- 회원 정보 추가 성공 -");
		} else {
			System.out.println("- 회원 정보 추가 실패 -");
		}
	}
	
	private void deleteMember() throws Exception {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		memId = CryptoUtil.encryptAES256(memId, key);
		
		int cnt = service.deleteMember(memId);
		if(cnt > 0) {
			System.out.println("회원ID가 " + CryptoUtil.decryptAES256(memId, key) + "인 회원 삭제 성공!!");
		} else {
			System.out.println(CryptoUtil.decryptAES256(memId, key) + "은(는) 없는 회원ID이거나 "
					+ "삭제에 실패했습니다.");
		}
	}
	
	private void updateMember() throws Exception {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		memId = CryptoUtil.encryptAES256(memId, key);
		
		int count = service.getMemberCount(memId);
		if(count == 0){ // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 중단합니다.");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 회원이름 >> ");
		String newMemName = scan.next();
		
		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = scan.next();
		newMemPass = CryptoUtil.sha512(newMemPass);
		
		System.out.print("새로운 전화번호 >> ");
		String newMemTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(newMemName);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt > 0){
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 회원 정보 수정 완료");
		} else {
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 회원 정보 수정 실패");
		}
		
	}
	
	private void updateMember2() throws Exception {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		memId = CryptoUtil.encryptAES256(memId, key);
		
		int count = service.getMemberCount(memId);
		if(count==0){ // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 중단합니다.");
			return;
		}
		
		int num; // 수정할 컬럼에 대한 선택 값이 저장될 변수
		String updateField = null;
		String updateTitle = null;
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println(" 1.회원이름  2.비밀번호  3.전화번호  4.회원주소");
			System.out.println("----------------------------------------------");
			System.out.print("수정할 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num){
				case 1 : updateField = "mem_name";
						 updateTitle = "회원이름"; break;
				case 2 : updateField = "mem_pass";
						 updateTitle = "비밀번호"; break;
				case 3 : updateField = "mem_tel";
						 updateTitle = "전화번호"; break;
				case 4 : updateField = "mem_addr";
						 updateTitle = "회원주소"; break;
				default : 
					System.out.println("수정할 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
		}while(num<1 || num>4);
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		if(updateField.equals("mem_pass")) {
			updateData = CryptoUtil.sha512(updateData);
		}
		
		// 수정 작업에 필요한 데이터들을 Map에 저장
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		paramMap.put("memid", memId);
		int cnt = service.updateMember2(paramMap);
		
		if(cnt > 0){
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 개별 정보 수정 완료!!!");
		} else {
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 개별 정보 수정 실패~~~");
		}
	}
	
	private void displayMember() throws Exception {
		
		List<MemberVO> memList = service.getAllMember();
		System.out.println();
		System.out.println("===============================================");
		System.out.println(" 회원ID\t회원이름\t전화번호\t주 소");
		System.out.println("===============================================");
		List<String> list = new ArrayList<>();
		
		if(memList == null){
			System.out.println("출력할 회원 정보가 없습니다");
		} else {
			for(String s : list) {
				System.out.print(s + "\t");
			}
			for(MemberVO m : memList){
				System.out.print(CryptoUtil.decryptAES256(m.getMem_id(), key) + "\t");
				System.out.print(m.getMem_name() + "\t");
				System.out.print(m.getMem_tel() + "\t");
				System.out.println(m.getMem_addr() + "\t");
			}
		}
		System.out.println("===============================================");
		System.out.println("출력 작업 끝...");
	}
	
	// 메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("====== 작업 선택 ======");
		System.out.println("1. 자료 추가 ");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정2");
		System.out.println("0. 작업 끝.");
		System.out.println("===================");
		System.out.print("원하는 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}

}
