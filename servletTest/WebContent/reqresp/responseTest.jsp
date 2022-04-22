<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 이동 연습</title>
</head>
<body>
	<h2>forward, redirect 연습</h2><hr>
	<form method="post" action="<%=request.getContextPath()%>/responseTest01.do">
		forward 이동 : <input type="text" name="username">
		<input type="submit" value="확인">	
	</form>
	<hr>
	
	<form method="post" action="<%=request.getContextPath()%>/responseTest02.do">
		response.sendRedirect : <input type="text" name="username">
		<input type="submit" value="확인">
	</form>
</body>
</html>