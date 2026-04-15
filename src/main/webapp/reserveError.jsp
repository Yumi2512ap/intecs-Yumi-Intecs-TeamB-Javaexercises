<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会議室予約</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<h1>会議室予約</h1>
	<hr>
	<h2>予約エラー</h2>
	<label>${errorReason}時刻が過ぎているため予約できません</label>
	<table class="result">
		<tr>
			<th>予約日</th>
			<td>${reservation.date}2023-02-02</td>
		</tr>
		<tr>
			<th>会議室</th>
			<td>${room.name}大会議室</td>
		</tr>
		<tr>
			<th>予約時刻</th>
			<td>${reservation.start}14:00～${reservation.end}15:00</td>
		</tr>
		<tr>
			<th>予約者</th>
			<td>${meetingRoom.user.name}情報太郎</td>
		</tr>
	</table>
	<hr>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="get">
		<input type="submit" value="確認">
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>