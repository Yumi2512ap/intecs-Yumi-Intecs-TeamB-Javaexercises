<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>キャンセルエラー</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>キャンセルエラー</h2>
	<label>${errorReason}既にキャンセルされています</label>
	<table class="result">
		<tr>
			<th>予約日</th>
			<td>${reservation.date}</td>
		</tr>
		<tr>
			<th>会議室</th>
			<td>${room.name}</td>
		</tr>
		<tr>
			<th>予約時刻</th>
			<td>${reservation.start}～${reservation.end}</td>
		</tr>
		<tr>
			<th>予約者</th>
			<td>${meetingRoom.user.name}</td>
		</tr>
	</table>
	<hr>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="post">
		<input type="submit" value="確認">
	</form>
	
	<%@ include file="footer.jsp"%>
</body>
</html>