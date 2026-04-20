<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom"%>
<%
MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
String[] roomsName = MR.getRoomsName();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約履歴</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>予約履歴</h1>
	<form action="/ResListSort">
		並び順：
		<select name="order">
			<option value="ASC">古い順</option>
			<option value="DESC">新しい順</option>
		</select>
		<br>日付：
		<input type="date" name="date1" value="">～
		<input type="date" name="date2" value="">
		<br>会議室：
		<select name="room">
			<option value="all">全て</option>
			<% for(int i = 0; i < roomsName.length; i++){ %>
				<option value="<%= i %>"><%= roomsName[i] %></option>
			<% } %>
		</select>
		<br>ユーザー：
		<input type="text" name="user" placeholder="ユーザー名を入力"><br>
		<input type="submit" value="検索">
	</form>
	<hr>

	<table>
		<tr>
			<th>予約日</th>
			<th>時間</th>
			<th>会議室</th>
			<th>予約者</th>
		</tr>
		<%
		out.print(MR.getReservationList());
		%>

	</table>

	<hr>
	<form action="adminMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>