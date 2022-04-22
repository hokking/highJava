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
	
	// 중복확인
	$("#idCheck").on("click", function(){
		var memId = $("#mem_id").val();
		if(memId==""){
			alert("ID를 입력하세요.");
			return;
		}
		
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberIdCheck.do",
			data : { "mem_id" : memId },
			dataType : "json",
			success : function(result){
				if(result=="OK"){
					$("#idChkResult").html("사용가능");
				}else{
					$("#idChkResult").html("ID 중복 - 사용불가");
				}
			}, 
			error : function(xhr){
				alert("status : " + xhr.status);
			}
		});
	});
	
	// 회원정보 추가하기 - submit
	$("#memberForm").on("submit", function(){
		var idChk = $("#idChkResult").html();
		if(idChk!="사용가능"){
			alert("ID가 중복되거나 중복체크를 하지 않았습니다.");
			return false;   // 서버로 전송하지 않는다.
		}
		
		if($("#mem_pass").val() != $("#mem_pass2").val()){
			alert("비밀번호와 비밀번호 확인이 다릅니다. 다시 확인하세요.");
			return false;
		}
		
		// 각 데이터의 빈값 여부 확인하기  -- 각자 추가 요망
		
		return true; // 서버로 전송한다.
	});
	
	
});
</script>

</head>
<body>
<h2>회원 정보 입력 폼</h2>
<form id="memberForm" action="<%=request.getContextPath()%>/member/memberInsert.do">
<table border="1">
<tr>
	<td>회원ID</td>
	<td>
		<input type="text" name="mem_id" id="mem_id">
		<input type="button" id="idCheck" value="중복확인"><br>
		<span id="idChkResult"></span>
	</td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="mem_pass" id="mem_pass"></td>
</tr>
<tr>
	<td>비밀번호 확인</td>
	<td><input type="password" name="mem_pass2" id="mem_pass2"></td>
</tr>
<tr>
	<td>회원이름</td>
	<td><input type="text" name="mem_name" id="mem_name"></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><input type="text" name="mem_tel" id="mem_tel"></td>
</tr>
<tr>
	<td>회원주소</td>
	<td><input type="text" name="mem_addr" id="mem_addr"></td>
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