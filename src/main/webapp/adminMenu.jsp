<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>管理者メニュー</h1>
	<hr>
	<h2>メニュー</h2>
	<form action="<%=request.getContextPath()%>/addAdmin.jsp"
		method="get">
		<input type="submit" value="管理者アカウント追加">
	</form>
	<form action="<%=request.getContextPath()%>/addRoom.jsp"
		method="get">
		<input type="submit" value="会議室管理">
	</form>
	<form action="<%= request.getContextPath() %>/menu.jsp" method="get">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>