<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#btnAdd").on("click", function(){
		location.href="<%=request.getContextPath()%>/member/memberForm.do";
	});
});
</script>
</head>
<body>
<%
	List<MemberVO> memList = 
		(List<MemberVO>)request.getAttribute("memberList");
%>
<h2>회원 목록 보기</h2>
<table border="1">
<tr>
	<td colspan="5" style="text-align:right;"><input type="button" value="회원추가" id="btnAdd"></td>
</tr>
<tr>
	<th>ID</th><th>비밀번호</th><th>이 름</th><th>전 화</th><th>주 소</th>
</tr>
<%
	for(MemberVO memVo : memList){
%>
	<tr>
		<td><a href="<%=request.getContextPath()%>/member/memberView.do?mem_id=<%=memVo.getMem_id()%>"><%=memVo.getMem_id() %></a></td>
		<td><%=memVo.getMem_pass() %></td>
		<td><%=memVo.getMem_name() %></td>
		<td><%=memVo.getMem_tel() %></td>
		<td><%=memVo.getMem_addr() %></td>
	</tr>
<%
	}
%>
</table>
</body>
</html>









