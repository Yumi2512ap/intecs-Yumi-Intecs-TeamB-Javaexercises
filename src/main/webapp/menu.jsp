<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>会議室予約システム</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>会議室予約</h1>
	<hr>
	<h2>メニュー</h2>
	<form action="<%=request.getContextPath()%>/reserveInput.jsp"
		method="get">
		<input type="submit" value="会議室予約">
	</form>
	<form action="<%=request.getContextPath()%>/cancelInput.jsp"
		method="get">
		<input type="submit" value="予約キャンセル">
	</form>
	<form action="<%= request.getContextPath() %>/adminMenu.jsp">
		<input type="submit" value="管理者メニュー">
	</form>
	<form action="<%=request.getContextPath()%>/Logout.java"
		method="post">
		<input type="submit" value="ログアウト">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>
