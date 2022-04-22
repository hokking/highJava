package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.vo.FileVO;

/*
 * - Servlet 3.0이상에서 파일 업로드를 처리하려면 서블릿에 
 *   @MultipartConfig라는 애노테이션을 설정해야 한다.
 * - @MultipartConfig 애노테이션 설정 방법(사용되는 변수 및 역할)
 *   1) location 
 *   	: 업로드한 파일이 임시적으로 저장될 경로를 지정한다
 *   	(기본값 : "")
 *   2) fileSizeThreshold 
 *   	: 이 속성에서 지정한 크기보다 큰 파일은 
 *   	위의 location에서 지정한 디스크의 임시 디렉토리에 저장한다.
 *   	(단위 : byte, 기본값 : 0 (무조건 임시 디렉토리를 사용한다.))
 *   3) maxFileSize 
 *   	: 1개의 파일의 최대 크기 (단위 : byte, 기본값 : -1L(무제한))
 *   4) maxRequestSize 
 *   	: 서버로 전송되는 Request데이터의 최대 크기
 *   	(단위 : byte, 기본값 : -1L(무제한))
 */
//파일 전송시 post방식만 사용가능
@WebServlet("/fileUploadTest.do")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 10, // 10MB
	maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 50 // 전체 용량
)
public class FileUploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// upload한 파일이 저장될 폴더 설정
		String uploadPath = "D:/D_Other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 생성한다.
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터 데이터(username) : " + userName);
		
		// --------------------------------------------------------------------------------------
		
		String fileName = ""; // 파일명이 저장될 변수 선언
		
		// 업로드한 파일 정보가 저장될 List객체 생성
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		/*
		 * //파일을 파트로 데이터를 구분해서 전송
		 * 
		 * - Servlet 3.0이상에서 새롭게 추가된 Upload용 메서드
		 * 1) request.getParts(); : 전체 Part객체가 저장된 컬렉션을 반환한다.
		 * 2) request.getPart("part이름"); : 지정된 이름을 가진 part객체를 반환한다. // 특정한 파트 하나를 찾을때
		 */
		
		for(Part part : request.getParts()) {
			// 파일명 구하기
			fileName = extractFileName(part);
			 // 파일명이 공백("")이면 이것은 파일이 아닌 일반 파라미터
			if(!"".equals(fileName)) {
				// 1개의 업로드 파일에 대한 정보를 저장할 객체 생성
				FileVO fileVo = new FileVO();
				
				fileVo.setFileName(fileName);
				fileVo.setFileSize((int)Math.ceil(part.getSize() / 1024.0));
				
				try {
					// 파일 저장 : Part객체의 write()메서드 이용
					part.write(uploadPath + File.separator + fileName);
					fileVo.setUploadStatus("Success");
				} catch (IOException e) {
					fileVo.setUploadStatus("Fail : " + e.getMessage());
				}
				
				fileList.add(fileVo); // 처리된 파일 정보를 저장한 객체 List에 추가
			}
		} // for
		
		request.setAttribute("userName", userName);
		request.setAttribute("uploadFileList", fileList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/fileUpload/uploadFiles.jsp");
		rd.forward(request, response);
		
	} // doPost
	
	/*
	 * - Part 구조
	 * 1) 파일이 아닐 때(즉, 일반 파라미터 데이터일 경우)
	 * --------very1785addf5445412 						-> Part를 구분하는 구분선
	 * content-disposition: form-data; name="username" 	-> 파라미터명 (content-disposition : 헤더값)
	 * 													-> 빈 줄
	 * tester											-> 파라미터 값
	 * 
	 * 2) 파일일 경우
	 * --------very1785addf5445412 													-> Part를 구분하는 구분선
	 * content-disposition: form-data; name="uploadFile1"; filename="test1.txt" 	-> 파일 정보
	 * content-Type: text/plain														-> 파일 종류
	 * 																				-> 빈 줄
	 * abcde12345!@#$																-> 파일의 내용
	 */
	
	// Part영역에서 읽어올 '파일명'을 찾아 반환하는 메서드
	private String extractFileName(Part part) {
		String fileName = "";
		
		String contentDisposition = part.getHeader("content-disposition");
		// form-data; name="username" 							-> 파일이 아닐때
		// form-data; name="uploadFile1"; filename="test1.txt" 	-> 파일일때
		
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				// filename="test1.txt" -> item변수값
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		
		return fileName;
	}

}




















