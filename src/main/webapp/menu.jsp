<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<h1>会議室予約</h1>
	<hr>
	<h2>メニュー</h2>
	<form action= reserveInput.jsp method="post">

		<input type="submit" value="会議室予約"> <br>
	</form>
	<form action=cancelInput.jsp method="post">
		<input type="submit" value="予約キャンセル"> <br>
	</form>
	<form action="<%=request.getContextPath()%>/LogoutServlet"
		method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>