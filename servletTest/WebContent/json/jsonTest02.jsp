<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script>
	$(function() {
		// 비동기 방식 처리
		$('#btn1').on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/jsonTest02.do",
				type : "post",
				success : function(res) {
					let htmlCode = "<table border='1'>";
					htmlCode += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>";
					$.each(res, function(i, v) {
						htmlCode += "<tr><td>" + v.lprod_id + "</td>";
						htmlCode +=	"<td>" + v.lprod_gu + "</td>";
						htmlCode +=	"<td>" + v.lprod_nm + "</td></tr>";
					})
					htmlCode += "</table>";
					$('#result').html(htmlCode);
				},
				dataType : 'json'
			})
		})
		
		// 동기 방식 처리
		$('#btn2').on('click', function() {
			// 직접 요청
			location.href = "<%=request.getContextPath()%>/jsonTest03.do";
		})
	})
</script>
</head>
<body>
	<input type="button" value="Lprod자료 가져오기(Ajax)" id="btn1">
	<input type="button" value="Lprod자료 가져오기(non Ajax)" id="btn2">
	<h3>Lprod 자료 목록</h3>
	<div id="result"></div>
</body>
</html>