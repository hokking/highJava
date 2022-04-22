<%@page import="kr.or.ddit.basic.vo.MemberVO"%>
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
	MemberVO memVo = (MemberVO)session.getAttribute("loginMember");

	if(memVo == null) { // Session값이 없을 때 (로그인이 되지 않았을 때)
%>
	<form method="post" action="<%=request.getContextPath()%>/sessionLogin.do">
		<table>
			<tr>
				<td>ID : </td>
				<td>
					<input type="text" placeholder="ID를 입력하세요" name="id">
				</td>
			</tr>
			<tr>
				<td>PASS : </td>
				<td>
					<input type="password" placeholder="PASSWORD를 입력하세요" name="pass">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
<%
	} else {
%>
	<h3><%=memVo.getMem_name()%>님 반갑습니다.</h3>
	<br>
	<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
<%		
	}
	
%>
</body>
</html>