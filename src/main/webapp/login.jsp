<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>会議室予約</h1>
	<hr>
	<h2>ログイン</h2>
	<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
		利用者ID：<input type="text" name="userId" value=""> <br>
		パスワード：<input type="password" name="userPw" value=""> <br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>