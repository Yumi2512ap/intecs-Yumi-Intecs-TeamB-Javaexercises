<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アクセスログ</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>アクセスログ</h1>
	<hr>
	<table>
		<tr>
			<th>アクセスログID</th>
			<th>使用したユーザーID</th>
			<th>ログイン結果</th>
			<th>アクセス時間</th>
			<th>IPアドレス</th>
			<th>デバイス等の情報</th>
		</tr>
		${ MR.accessLog }

	</table>

	<hr>
	<form action="adminMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>