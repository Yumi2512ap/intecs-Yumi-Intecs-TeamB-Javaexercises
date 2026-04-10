<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンセル確認</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>キャンセル確認</h2>
	<table>
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
	<form action="cancelConfirm.jsp">
		<input type="submit" value="戻る">
	</form>
	<form action="Reserve">
		<input type="submit" value="決定">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>