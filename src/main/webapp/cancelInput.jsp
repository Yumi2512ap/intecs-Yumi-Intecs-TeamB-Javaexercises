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
	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>利用日</h2>
	<form action="<%= request.getContextPath() %>/reserveInput.jsp" method="post">
		<!-- 本来であれば今日の日付を入れる場所
			とりあえず仮置き、サーブレットかなにかから日付を取得して
			動的に変更されるように-->
		<input type="date" name="date" value="${meetingRoom.date}">
		<input type="submit" value="日付変更">
	</form>
	<h2>キャンセル可能時間帯${meetingRoom.user.name}(ダミーネーム)</h2>
	
	<!-- ここの書き方はあんましよくない気が
		配列に会議室名を入れてforループ1つで済ませたい-->
	 <form action="<%= request.getContextPath() %><!-- ここにURL -->>"method="get">
	 <table border="1">
    <tr>
      <th>会議室名＼時間帯</th>
      <%  for(int i = 9;i <=16; i++) { %>
      <th><%= i %>:00 </th> 
	  <% } %>
     
    </tr>
    <tr>
      <td>大会議室</td>
       <%  for(int i = 9;i <=16; i++) { %>
    	  <td><input type="submit" name="time" value="<%= i %>:00"> </td> 
    	  <% } %>
    </tr>
    <tr>
      <td>3A会議室</td>
      <%  for(int i = 9;i <=16; i++) { %>
	  <td><input type="submit" name="time" value="<%= i %>:00"> </td>      
	  <% } %>
    </tr>
      
      <tr>
      <td>3B会議室</td>
      <%  for(int i = 9;i <=16; i++) { %>
      <td><input type="submit" name="time" value="<%= i %>:00"> </td> 
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