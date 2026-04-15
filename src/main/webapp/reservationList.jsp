<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約履歴</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>予約履歴</h1>
	<hr>

	<table>
		<tr>
			<th>予約日</th>
			<th>時間</th>
			<th>会議室</th>
			<th>予約者</th>
		</tr>
		<%
			MeetingRoom MR =new MeetingRoom();
			out.print(MR.getReservationList());
		%>
		${ ReservationList }
	</table>

	<hr>
	<form action="adminMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>