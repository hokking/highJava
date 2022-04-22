<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 연습</title>
</head>
<body>
	<h2>Request 연습 form(숫자 입력은 정수형으로 입력하세요.)</h2>
	<form method="post" action="<%= request.getContextPath() %>/requestTest02.do">
	<table>
		<tr>
			<td><input type="text" size="10" name="num1"></td>
			<td>
				<select name="op">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
					<option value="%">%</option>
				</select>
			</td>
			<td><input type="text" size="10" name="num2"></td>
			<td><input type="submit" value="확인"></td>
		</tr>
	</table>
	</form>
</body>
</html>