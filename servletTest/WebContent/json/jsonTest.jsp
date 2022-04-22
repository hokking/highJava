<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax, json 연습</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 문자열
		$("#strBtn").on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonDataTest.do",
				type : "post",
				data : "choice=string",
				success : function(data) {
					$('#result').html(data);
				},
				dataType : "json" // 응답데이터의 종류(text/html/...)
			})
		})
		
		// 배열
		$("#arrayBtn").on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonDataTest.do",
				type : "post",
				data : "choice=array",
				success : function(data) {
					let htmlCode = "";
					$.each(data, function(i, v) {
						htmlCode += i + "번째 데이터 : " + v + "<br>";
					})
					$('#result').html(htmlCode);
				},
				dataType : "json"
			})
		})
		
		// 객체
		$("#objBtn").on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonDataTest.do",
				type : "post",
				data : "choice=object",
				success : function(data) {
					// data = {"mem_id:"test001", "mem_name":"홍길동동",...}
					let htmlCode = "회원ID : " + data.mem_id + "<br>";
					htmlCode += "회원이름 : " + data.mem_name + "<br>";
					htmlCode += "비밀번호 : " + data.mem_pass + "<br>";
					htmlCode += "전화번호 : " + data.mem_tel + "<br>";
					htmlCode += "회원주소 : " + data.mem_addr + "<br>";
					
					$('#result').html(htmlCode);
				},
				dataType : "json"
			})
		})
		
		// List
		$("#listBtn").on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonDataTest.do",
				type : "post",
				data : "choice=list",
				success : function(data) {
					let htmlCode = "";
					$.each(data, function(i, v) {
						htmlCode += "<h4>" + i + "번째 자료</h4>";
						htmlCode += "회원ID : " + v.mem_id + "<br>";
						htmlCode += "회원이름 : " + v.mem_name + "<br>";
						htmlCode += "비밀번호 : " + v.mem_pass + "<br>";
						htmlCode += "전화번호 : " + v.mem_tel + "<br>";
						htmlCode += "회원주소 : " + v.mem_addr + "<br>";
						htmlCode += "<hr color = 'lightblue'>";
					})
					$('#result').html(htmlCode);
				},
				dataType : "json"
			})
		})

		// Map
		$("#mapBtn").on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonDataTest.do",
				type : "post",
				data : "choice=map",
				success : function(data) {
					let htmlCode = "name : " + data.name + "<br>";
					htmlCode += "age : " + data.age + "<br>";
					htmlCode += "tel : " + data.tel + "<br>";
					htmlCode += "addr : " + data.addr + "<br>";
					
					$('#result').html(htmlCode);
				},
				dataType : "json"
			})
		})
	})
</script>
</head>
<body>
	<form>
		<input type="button" id="strBtn" value="문자열 데이터 가져오기">
		<input type="button" id="arrayBtn" value="배열 데이터 가져오기">
		<input type="button" id="objBtn" value="객체 데이터 가져오기">
		<input type="button" id="listBtn" value="List 데이터 가져오기">
		<input type="button" id="mapBtn" value="Map 데이터 가져오기">
	</form>
	<h3>응답 데이터 출력하기</h3>
	<div id="result"></div>
</body>
</html>