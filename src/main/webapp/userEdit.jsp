<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2>ユーザーメニュー</h2>
	<hr>
	<h2>ユーザー情報編集</h2>
	<form action="<%=request.getContextPath()%>/UserEditServlet"
		method="post">

		<table class="result">
			<tr>
				<th>利用者ID：</th>
				<td><input type="text" name="userId" value="${user.userId}"></td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><input type="password" name="userPw" value="${user.userPw}"></td>
			</tr>
			<tr>
				<th>表示ユーザー名：</th>
				<td><input type="text" name="userName" value="${user.userName}"></td>
			</tr>
			<tr>
				<th>居住地：</th>
				<td><input type="text" name="address"
					value="${user.userAddress}" placeholder="都道府県を入力"></td>
			</tr>
		</table>

		<input type="submit" name="action" value="変更を保存する">
	</form>

	<form action="<%=request.getContextPath()%>/UserEditServlet"
		method="post">
		<input type="submit" name="action" value="退会する">
	</form>

	${ msg }
	<form action="login.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>