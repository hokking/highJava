package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.vo.FileVO;

@WebServlet("/uploadFileList.do")
public class UploadFileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Upload된 전체 파일 목록을 구성하는 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// upload한 파일이 저장될 폴더 설정
		String uploadPath = "D:/D_Other/uploadFiles";
				
		// 저장될 폴더가 없으면 새로 생성한다.
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		// 파일이 저장된 폴더에서 전체 파일 목록을 구해와서 List에 담아준다.
		File[] allFiles = fileUploadDir.listFiles();
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		for(File file : allFiles) {
			if(file.isFile()) {
				FileVO fvo = new FileVO();
				fvo.setFileName(file.getName());
				fvo.setFileSize((int)Math.ceil(file.length() / 1024.0));
				fvo.setUploadStatus("Success");
				fileList.add(fvo);
			}
		}
		
		request.setAttribute("uploadFileList", fileList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/fileUpload/uploadFiles.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
