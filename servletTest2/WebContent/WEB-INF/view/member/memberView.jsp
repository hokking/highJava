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
	$("#btnList").on("click", function(){
		location.href="<%=request.getContextPath()%>/member/memberList.do";
	});
	
	$("#btnUpdate").on("click", function(){
		var form = $("#memberForm").get(0);
		form.action = "<%=request.getContextPath()%>/member/memberUpdateForm.do";
		form.submit();
	});
	
	$("#btnDelete").on("click", function(){
		var form = $("#memberForm").get(0);
		form.action = "<%=request.getContextPath()%>/member/memberDelete.do";
		form.submit();
	});
	
});
</script>
</head>
<body>
<%
	MemberVO memVo = (MemberVO)request.getAttribute("memberVo");
%>

<h2>회원 정보 상세보기</h2>

<form id="memberForm">
	<input type="hidden" id="mem_id" name="mem_id" value="<%=memVo.getMem_id()%>">
</form>

<table border="1">
<tr>
	<td>회원ID</td>
	<td><%=memVo.getMem_id() %></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><%=memVo.getMem_pass() %></td>
</tr>

<tr>
	<td>회원이름</td>
	<td><%=memVo.getMem_name() %></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><%=memVo.getMem_tel() %></td>
</tr>
<tr>
	<td>회원주소</td>
	<td><%=memVo.getMem_addr() %></td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;">
		<input type="button" id="btnUpdate" value="수정">
		<input type="button" id="btnDelete" value="삭제">
		<input type="button" id="btnList" value="회원목록">
	</td>
</tr>

</table>



</body>
</html>