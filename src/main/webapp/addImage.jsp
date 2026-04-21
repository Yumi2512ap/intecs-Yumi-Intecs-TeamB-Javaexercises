<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom" %>
<% 
	MeetingRoom MR = (MeetingRoom)session.getAttribute("MR");
	String[] roomsName = MR.getRoomsName();
	String[] roomsId = MR.getRoomsId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会議室用画像追加用ページ</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>会議室画像追加</h1>
<hr>
	${message}
	<form action="AddImage" method="post" enctype="multipart/form-data">
		<select name="roomId">
			<% for(int i = 0 ; i < roomsName.length; i++){ %>
				<option value="<%= roomsId[i] %>"><%= roomsName[i] %></option>
			<% } %>
		</select>
		<input type="file" name="picture" accept="image/png, image/jpeg" 
			required="required"><br><br>
		<input type="submit" value="登録">
	</form><br>
<hr>
<form action="addRoom.jsp" method="get">
	<input type="submit" value="戻る">
</form>
<%@ include file="footer.jsp"%>
</body>
</html>