<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// 스크립트릿 영역 : java코드를 작성하는 영역
	
	// 쿠키값 구하기
	Cookie[] cookies = request.getCookies();

	String cookieUserId = "";
	String chk = "";

	if(cookies != null) {
		for(Cookie c : cookies) {
			if("userId".equals(c.getName())) {
				cookieUserId = c.getValue();
				chk = "checked";
			}
		}
	}
	
%>
	<form method="post" action="<%=request.getContextPath()%>/cookieLoginServlet.do">
		<table>
			<tr>
				<td>ID : </td>
				<td>
					<input type="text" placeholder="ID 입력하세요" name="id" value="<%= cookieUserId %>">
				</td>
			</tr>
			
			<tr>
				<td>PASS : </td>
				<td>
					<input type="password" placeholder="PassWord 입력하세요" name="pass">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="checkbox" <%= chk %> name="check"> id 기억하기
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>