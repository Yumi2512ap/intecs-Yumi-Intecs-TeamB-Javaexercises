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
			<td>会議室ID</td>
			<td>会議室名</td>
		</tr>
		<% for(int i = 0; i < roomsName.length; i++){ %>
	 		<form action="<%= request.getContextPath() %>/DeleteRoom" method="post">
				<tr>
					<td><%= roomsId[i] %></td> 
					<td><%= roomsName[i] %></td>
					<td><input type="submit" value="削除"></td>
					<td><input type="hidden" name="roomId" value="<%= roomsId[i] %>"></td>
					<td><input type="hidden" name="roomName" value="<%= roomsName[i] %>"></td>
				</tr>
			 </form>
		<% } %>      
	  </table>
  
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