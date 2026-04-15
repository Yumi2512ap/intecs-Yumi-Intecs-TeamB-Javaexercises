<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<% String room[] = {"大会議室","5A会議室","5B会議室","5C会議室（テスト）","5D会議室（テスト）"};  %>
		<% String roomId[] = {"0501","0502","0503","0504","0505"}; %>
		<tr>
			<td>会議室ID</td>
			<td>会議室名</td>
		</tr>
		<% for(int i = 0; i < room.length; i++){ %>
		<tr>
			<td><%= roomId[i] %></td> 
			<td><%= room[i] %></td>
			<td><input type="submit" name="update" value="修正"></td>
			<td><input type="submit" name="delete" value="削除"></td>
		</tr>
		<% } %>      
	  </table>
  </form>
  
<hr>
<h2>会議室追加</h2>
  <form action="<%= request.getContextPath() %>/AddRoom">
		<input type="text" placeholder="追加したい部屋名" required="required">
		<input type="number" placeholder="会議室ID (数字4桁)" required="required">
		<input type="submit" value="追加">
  </form>
  
  <hr>
  
  <form action="<%= request.getContextPath() %>/adminMenu.jsp" method="post">
  	<input type="submit" value="戻る">
  </form>	

<%@ include file="footer.jsp"%>
</body>
</html>