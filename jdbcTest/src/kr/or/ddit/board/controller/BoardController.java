package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
	private Scanner scan;
	private IBoardService service;
	
	public BoardController() {
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardController().boardStart();
	}
	
	public void boardStart() {
		String title = null;
		while(true) {	
			int input = displayMenu(title);
			title = null;
			switch(input){
			case 1:
				insertBoard();
				break;
			case 2:
				selectBoard();
				break;
			case 3:
				title = searchBoard(title);
				break;
			case 0:
				System.out.println("게시판 프로그램 종료...");
				System.exit(0);
			default:
				System.out.println("잘못 입력하였습니다");
				System.out.println("다시 입력해주세요");
			}
		}
		
	}
	
	public void detailStart(int boardNo) {
		int input;
		do {
			input = detailMenu();
			switch(input){
			case 1:
				updateBoard(boardNo);
				return;
			case 2:
				deleteBoard(boardNo);
				return;
			case 3:
				return;
			default:
				System.out.println("잘못입력하였습니다");
				System.out.println("다시 입력해 주세요");
				break;
			}
		} while (input != 3);
	}
	
	public int displayMenu(String title) {
		listBoard(title);
		System.out.println("메뉴 : 1.새글작성 2.게시물보기 3.검색 0.작업끝");
		System.out.print("작업선택 >> ");
		return scan.nextInt();
	}
	
	public int detailMenu() {
		System.out.println("메뉴 : 1.수정 2.삭제 3.리스트로 가기");
		System.out.print("작업선택 >> ");
		return scan.nextInt();
	}
	
	public void listBoard(String title) {
		
		List<BoardVO> boardList = null;
		
		if(title == null){
			boardList = service.getAllList();
		} else {
			boardList = service.searchBoard(title);
		}
		
		System.out.println();
		System.out.println("===============================================");
		System.out.println(" No\t제목\t\t작성자\t\t조회수");
		System.out.println("===============================================");
		
		if(boardList.size() == 0){
			System.out.println("출력할 게시물이 없습니다");
		} else {
			for(BoardVO b : boardList){
				System.out.print(b.getBoard_no() + "\t");
				System.out.print(b.getBoard_title() + "\t");
				System.out.print(b.getBoard_writer() + "\t\t");
				System.out.println(b.getBoard_cnt() + "\t");
			}
		}
		System.out.println("===============================================");
	}
	
	public void insertBoard() {
		
		scan.nextLine();
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		
		System.out.print("- 제 목 : ");
		String boardTitle = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String boardWriter = scan.next();
		
		scan.nextLine();
		System.out.print("- 내 용 : ");
		String boardContent = scan.nextLine();
		
		// 입력한 데이터들을 VO 객체에 저장한다
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_writer(boardWriter);
		boardVo.setBoard_content(boardContent);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("새 글이 추가되었습니다");
		} else {
			System.out.println("새 글이 추가되지 않았습니다");
		}
		System.out.println();
	}
	
	public void selectBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		List<BoardVO> boardList = service.selectBoard(boardNo);
		
		System.out.println();
		if(boardList == null){
			System.out.println("해당 게시물이 존재하지 않습니다");
			System.out.println();
		} else {
			System.out.println(boardNo + "번 글 내용");
			System.out.println("-----------------------------------------------");
			for(BoardVO b : boardList){
				System.out.println("- 제 목 : " + b.getBoard_title());
				System.out.println("- 작성자 : " + b.getBoard_writer());
				System.out.println("- 내 용 : " + b.getBoard_content());
				System.out.println("- 작성일 : " + b.getBoard_date());
				System.out.println("- 조회수 : " + b.getBoard_cnt());
			}
			System.out.println("-----------------------------------------------");
		}
		
		
		detailStart(boardNo);
	}
	
	public String searchBoard(String title) {
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("-----------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		scan.nextLine();
		title = scan.nextLine();
		System.out.println("-----------------------------------------------");
		
		return title;

	}
	
	public void updateBoard(int boardNo) {
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------------------");
		
		scan.nextLine();
		System.out.print("- 제 목 : ");
		String boardTitle = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String boardContent = scan.nextLine();
		System.out.println("-----------------------------------------------");
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_content(boardContent);
		boardVo.setBoard_no(boardNo);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번글이 수정되었습니다");
		} else {
			System.out.println(boardNo + "번글이 수정에 실패했습니다");
		}
	}
	
	public void deleteBoard(int boardNo){
		System.out.println();
		
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번글이 삭제되었습니다");
		} else {
			System.out.println(boardNo + "번글이 삭제되지 않았습니다");
		}
	}
}
