<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet File Upload</title>
</head>
<body>
	<h2>File Upload 연습</h2>
	<!-- enctype="multipart/form-data" : 파일 전송 시 반드시 필요한 속성 -->
	<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileUploadTest.do">
		이름 : <input type="text" name="username"><br><br>
		파일1개 선택 : <input type="file" name="uploadFile1"><br><br>
		다중파일 선택 : <input type="file" name="uploadFile2" multiple><br><br>
		<button type="submit">자료 전송</button>
	</form>
	<br><hr><br>
	<a href="<%=request.getContextPath()%>/uploadFileList.do">업로드된 전체 파일 목록 가져오기</a>
</body>
</html>