<%@page import="kr.or.ddit.lprod.vo.LprodVO"%>
<%@page import="java.util.List"%>
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
	// 서블릿이 보낸 데이터를 받는다.
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lpList");
%>
	<h2>Lprod 자료 목록</h2>
	<table>
		<tr>
			<th>LPROD_ID</th>
			<th>LPROD_GU</th>
			<th>LPROD_NM</th>
		</tr>
<%
	for(LprodVO l : lprodList) {
%>
	<tr>
		<td><%= l.getLprod_id() %></td>
		<td><%= l.getLprod_gu() %></td>
		<td><%= l.getLprod_nm() %></td>
	</tr>
<%
	}
%>
	</table>
</body>
</html>