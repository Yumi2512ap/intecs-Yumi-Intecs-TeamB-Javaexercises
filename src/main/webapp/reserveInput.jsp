<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会議室予約</title>
</head>
<body>
<%@ include file="header.jsp"%>


	<h1>会議室予約</h1>
	<hr>
	<h2>利用日</h2>
	<form action="<%= request.getContextPath() %>/reserveInput.jsp" method="post">
		<input type="date" name="date" value="${meetingRoom.date}">
		<input type="submit" value="日付変更">
	</form>
	<h2>予約可能時間帯${meetingRoom.user.name}(ダミーネーム)</h2>
	
	<!-- ここの書き方はあんましよくない気が
		配列に会議室名を入れてforループ1つで済ませたい-->
	 <form action="<%= request.getContextPath() %>/ReserveCreateServlet" method="get">
	 <table class="list">
	 
	 <tr>
		<th>会議室名＼時間帯</th>
		<%  for(int i = 9;i <=16; i++) { %>
		<th><%= String.format("%02d", i) %>:00 </th> 
		<% } %>
	</tr>
	
    <tr>

		<td>大会議室</td><!-- 本来なら配列から会議室名取得 -->
		<%  for(int i = 9;i <=16; i++) { %>
		<td class="cell"><input type="submit"  name="time" value="<%= String.format("%02d", i) %>:00"> </td> 
		<% } %>
	</tr>
    
	<tr>
		<td>3A会議室</td>
		<%  for(int i = 9;i <=16; i++) { %>
		<td class="cell"><input type="submit" name="time" value="<%= String.format("%02d", i) %>:00"> </td> 
		<% } %>
	</tr>
      
	<tr>
		<td>3B会議室</td>
		<%  for(int i = 9;i <=16; i++) { %>
		<td class="cell"><input type="submit" name="time" value="<%= String.format("%02d", i) %>:00"> </td> 
		<% } %>
	</tr>
      
  </table>
  </form>
  
  <hr>
  
  <form action="<%= request.getContextPath() %>/menu.jsp" method="get">
  	<input type="submit" value="戻る">
  </form>	
  
 <%@ include file="footer.jsp"%>
</body>
</html>