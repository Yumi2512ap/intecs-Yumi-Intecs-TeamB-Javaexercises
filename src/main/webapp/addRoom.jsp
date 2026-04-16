<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom" %>
<% 
	MeetingRoom MR = (MeetingRoom)session.getAttribute("MR");
	String[] roomsName = MR.getRoomsName();
	String[] roomsId = MR.getRoomsId();
	String[] times = MR.getPeriod();
	session.setAttribute("meetingRoom", MR);
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
	 <form action="<%= request.getContextPath() %>/AdminPage" method="post">
		<table class="list">
		<tr>
			<td>会議室ID</td>
			<td>会議室名</td>
		</tr>
		<% for(int i = 0; i < roomsName.length; i++){ %>
		<tr>
			<td><%= roomsId[i] %></td> 
			<td><%= roomsName[i] %></td>
			<td><button type="submit" name="delete" value="<%= roomsId[i] %>">削除</button></td>
		</tr>
		<% } %>      
	  </table>
  </form>
  
<hr>
<h2>会議室追加</h2>
  <form action="<%= request.getContextPath() %>/AddRoom" method="post">
		<input type="number" name="roomId" placeholder="会議室ID (数字4桁)" required="required">
		<input type="text" name="roomname" placeholder="追加したい部屋名" required="required">
		<input type="submit" value="追加">
  </form>
  
  <hr>
  
  <form action="<%= request.getContextPath() %>/adminMenu.jsp" method="post">
  	<input type="submit" value="戻る">
  </form>	

<%@ include file="footer.jsp"%>
</body>
</html>