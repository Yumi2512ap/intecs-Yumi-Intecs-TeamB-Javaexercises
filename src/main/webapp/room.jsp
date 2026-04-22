<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		String err = (String) request.getAttribute("err");
		String imageSrc = (String) request.getAttribute("imageSrc");
	%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会議室詳細</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>詳細ページ</h1>
	<hr>
	<h2>${ roomName }イメージ</h2>
	${ err }
	
	
		<img src="${ imageSrc }" alt="部屋画像" class="img">
	
			
	
	<%@ include file="footer.jsp"%>
</body>
</html>