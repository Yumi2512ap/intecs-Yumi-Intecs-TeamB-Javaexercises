<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会議室予約キャンセル</title>
</head>
<body>
<%@ include file="header.jsp"%>
<%  %>
	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>利用日</h2>
	<form action="<%= request.getContextPath() %>/ChangeDate" method="post">
		<input type="date" name="date" value="${meetingRoom.date}">
		<input type="hidden" name="page" value="<%= request.getContextPath() %>/reserveInput.jsp">
		<input type="submit" value="日付変更">
	</form>
	<h2>キャンセル可能時間帯${meetingRoom.user.name}(ダミーネーム)</h2>
	
		
	 <form action="<%= request.getContextPath() %>/CancelCreate" method="post">
	 <table>
	 
	 <tr>
		<th>会議室名＼時間帯</th>
		<%  for(int i = 9;i <=16; i++) { %>
		<th><%= String.format("%02d", i) %>:00 </th> 
		<% } %>
	</tr>

	
	<% String room[] = {"大会議室","5A会議室","5B会議室","5C会議室（テスト）","5D会議室（テスト）"};  %>
	<% String roomId[] = {"501","502","503","504","505"}; %>
	<% for(int i = 0; i < room.length; i++){ %>
	<label></label>
	<tr>
		<td><%= room[i] %></td>
	<% for(int j = 9;j <= 16; j++) { %>
		<td class="cell">
			<input type="submit" name="time" value=<%= String.format("%02d", j) %>:00>
			<input type="hidden" name="roomId" value="<%= roomId[i] %>">
		</td> 
	<% } %>
	
	</tr>
	<% } %>      
  </table>
  </form>
  
  <hr>
  
  <form action="<%= request.getContextPath() %>/menu.jsp" method="post">
  	<input type="submit" value="戻る">
  </form>	
  
<%@ include file="footer.jsp"%>
</body>
</html>