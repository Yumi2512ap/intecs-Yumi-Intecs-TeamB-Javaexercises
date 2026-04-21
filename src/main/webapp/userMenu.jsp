<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーメニュー</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<h2>ユーザーメニュー</h2>
	<hr>
	<h2>メニュー</h2>

	<form action="userEdit.jsp">
		<input type="submit" value="ユーザー情報の編集">
	</form>

	<form action="<%=request.getContextPath()%>/menu.jsp" method="get">
		<input type="submit" value="戻る">
	</form>

	<form action="<%=request.getContextPath()%>/Logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>