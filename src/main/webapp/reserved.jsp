<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>予約確定</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>会議室予約</h1>
	<hr>
	<h2>予約完了</h2>
	<table>
		<tr>
			<th>予約ID</th>
			<td>${reservation.id}</td>
		</tr>
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
	<form action="menu.jsp">
		<input type="submit" value="完了">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>