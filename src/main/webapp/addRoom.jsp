<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom" %>
<% 
	MeetingRoom MR = (MeetingRoom)session.getAttribute("MR");
	String[] roomsName = MR.getRoomsName();
	String[] roomsId = MR.getRoomsId();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<h1>管理者用ページ</h1>
	<hr>
	<h2>会議室管理</h2>
		<font color=red>${deleteErrorMessage}</font>
		${deleteSuccessMessage}
		<table class="list">
		<tr>
			<th>会議室ID</th>
			<th>会議室名</th>
		</tr>
		<% for(int i = 0; i < roomsName.length; i++){ %>
	 		<form action="<%= request.getContextPath() %>/DeleteRoom" method="post">
				<tr>
					<td><%= roomsId[i] %></td> 
					<td><a href="<%=request.getContextPath() %>/RoomImage?roomId=<%= roomsId[i] %>"><%= roomsName[i] %></a></td>
					<td class="cell"><input type="submit" value="削除"></td>
					<td><input type="hidden" name="roomId" value="<%= roomsId[i] %>"></td>
				</tr>
			 </form>
		<% } %>      
	  </table>
			<form action="<%= request.getContextPath() %>/addImage.jsp" method="get">
				<td><input type="submit" value="画像追加"></td>
			</form>
	<hr>
	<h2>会議室追加</h2>
		<font color=red>${errorMessage}</font>
		${successMessage}
	<form action="<%= request.getContextPath() %>/AddRoom" method="post">
		<input type="number" name="roomId" placeholder="会議室ID (数字4桁)" required="required">
		<input type="text" name="roomname" placeholder="追加したい部屋名" required="required">
		<input type="submit" value="追加"><br>
	</form>
	<hr>
	<form action="<%= request.getContextPath() %>/adminMenu.jsp" method="post">
		<input type="submit" value="戻る">
	</form>	
<%@ include file="footer.jsp"%>
</body>
</html>