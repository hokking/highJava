<%@page import="kr.or.ddit.basic.vo.FileVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet File Upload</title>
</head>
<body>
<%
	String userName = (String)request.getAttribute("userName");

	List<FileVO> fileList = (List<FileVO>)request.getAttribute("uploadFileList");
%>

<%
	if(userName != null) {
%>
	<h2><%= userName %>님이 방금 업로드 한 파일 목록</h2>
<%
	} else {
%>
	<h2>전체 업로드 파일 목록</h2>
<%
	}
%>
	<table border="1">
		<tr>
			<td>파일이름</td>
			<td>파일크기</td>
			<td>업로드상태</td>
			<td>비고</td>
		</tr>
<%
	if(fileList != null) {
		for(FileVO fileVo : fileList) {
%>
		<tr>
			<td><%= fileVo.getFileName() %></td>
			<td><%= fileVo.getFileSize() %></td>
			<td><%= fileVo.getUploadStatus() %></td>
			<td><a href="<%=request.getContextPath()%>/fileDownLoad.do?filename=<%=fileVo.getFileName()%>">DownLoad</a></td>
		</tr>
<%
		}
	}
%>
	</table>
	<br><hr><br>
	<a href="<%=request.getContextPath()%>/fileUpload/fileUploadTest.jsp">파일 업로드 시작 문서로 가기</a>
</body>
</html>