<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>予約確認</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>会議室予約</h1>
	<hr>
	<h2>予約確認</h2>
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
			<td>${MR.user.name}</td>
		</tr>
	</table>
	<hr>
	<div class="flex">
		<form action="reserveInput.jsp" method="post">
			<input type="submit" value="戻る">
		</form>
		<form action="Reserve" method="post">
			<input type="submit" value="決定">
		</form>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>