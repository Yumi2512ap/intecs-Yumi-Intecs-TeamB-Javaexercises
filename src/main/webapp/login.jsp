<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<h1>会議室予約</h1>
	<hr>
	<h2>ログイン</h2>
	<form action="<%=request.getContextPath()%>/Login" method="post">
		<table>
			<tr>
				<th>ユーザーID:</th><td><input type="text" name="userId" value=""></td>
			</tr>
			<tr>
				<th>パスワード:</th><td><input type="password" name="userPw" value=""></td> 
			</tr>
		</table>
		<input type="submit" value="ログイン">
		<div class="msg">${ msg }</div>
	</form><br>
	<a href="addUser.jsp" class="no">新規ユーザー登録</a>
	
	<%@ include file="footer.jsp"%>
</body>
</html>