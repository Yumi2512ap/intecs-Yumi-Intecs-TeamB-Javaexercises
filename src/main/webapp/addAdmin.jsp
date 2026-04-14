<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者追加ページ</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>管理者追加</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/addUser" method="post">
		利用者ID：<input type="text" name="userId" value=""> <br>
		パスワード：<input type="password" name="userPw" value=""> <br>
		表示ユーザー名：<input type="text" name="userName" value=""> <br>
		居住地：<input type="text" name="address" value="" placeholder="都道府県を入力"> <br>
			<input type="hidden" name="admin" value="true"> 
			
		<input type="submit" value="アカウント作成">
	</form>
	${ msg }
	<hr>
	<form action="adminMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>