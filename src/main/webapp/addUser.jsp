<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録ページ</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>ユーザー登録</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/AddUser" method="post">
	
		<table class="result">
			<tr>
				<th>利用者ID：</th>
				<td><input type="text" name="userId" value=""></td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><input type="password" name="userPw" value=""></td>
			</tr>
			<tr>
				<th>表示ユーザー名：</th>
				<td><input type="text" name="userName" value=""></td>
			</tr>
			<tr>
				<th>居住地：</th>
				<td><input type="text" name="address" value="" placeholder="都道府県を入力"></td>
			</tr>
		</table>
	
		<input type="hidden" name="admin" value="false"> 
			
		<input type="submit" value="アカウント作成">
	</form>
	${ msg }
	<hr>
	<form action="login.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>