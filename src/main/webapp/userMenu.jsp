<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザーメニュー</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<h2>ユーザー情報</h2>
	<hr>
	<h2>メニュー</h2>
	<div class="menu">
		<form action="userEdit.jsp">
			<input type="submit" value="ユーザー情報の編集">
		</form>
		<form action="<%= request.getContextPath()%>/UserReservations" method="post">
			<input type="hidden" name="user" value="${MR.user.name}">
			<input type="submit" value="自身の予約一覧">
		</form>
	</div>
	
	<hr>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="get">
		<input type="submit" value="戻る">
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>