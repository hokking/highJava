<%@page import="kr.or.ddit.vo.MemberVO"%>
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
	// 회원 목록 버튼 처리
	$("#btnList").on("click", function(){
		location.href="<%=request.getContextPath()%>/member/memberList.do";
	});
	
});
</script>
</head>
<body>
<%
	MemberVO memVo = (MemberVO)request.getAttribute("memberVo");
%>

<h2>회원 정보 상세보기</h2>

<form id="memberForm" action="<%=request.getContextPath()%>/member/memberUpdate.do">
	<input type="hidden" id="mem_id" name="mem_id" value="<%=memVo.getMem_id()%>">

<table border="1">
<tr>
	<td>회원ID</td>
	<td><%=memVo.getMem_id() %></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="mem_pass" id="mem_pass"></td>
</tr>

<tr>
	<td>회원이름</td>
	<td><input type="text" name="mem_name" id="mem_name" value="<%=memVo.getMem_name() %>"></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><input type="text" name="mem_tel" id="mem_tel" value="<%=memVo.getMem_tel() %>"></td>
</tr>
<tr>
	<td>회원주소</td>
	<td><input type="text" name="mem_addr" id="mem_addr" value="<%=memVo.getMem_addr() %>"></td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;">
		<input type="submit" value="저장">
		<input type="reset" value="취소">
		<input type="button" id="btnList" value="회원목록">
	</td>
</tr>

</table>
</form>

</body>
</html>