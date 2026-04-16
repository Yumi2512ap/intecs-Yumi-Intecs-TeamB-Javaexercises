<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom" %>
<% 
	MeetingRoom MR = (MeetingRoom)session.getAttribute("MR");
	String[] roomsName = MR.getRoomsName();
	String[] roomsId = MR.getRoomsId();
	String[] times = MR.getPeriod();
	String[][] disabled = MR.getDisabled();
%>
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
	<form action="<%= request.getContextPath() %>/ChangeDate" method="post">
		<input type="date" name="date" value="${MR.date}">
		<input type="hidden" name="page" value="reserveInput.jsp">
		<input type="submit" value="日付変更">
	</form>
	
	<h2>予約可能時間帯(${MR.user.name})</h2>
	
		<table class="list">
			<tr>
				<th>会議室名＼時間帯</th>
				<%  for(int j = 0;j < times.length; j++) { %>
					<th><%= times[j] %></th> 
				<% } %>
			</tr>
	
			<% for(int i = 0; i < roomsName.length; i++){ %>
				<tr>
					<td><%= roomsName[i] %></td>
					<% for(int j = 0;j < times.length; j++) { %>
						<form action="<%= request.getContextPath() %>/ReserveCreate" method="post">
							<td class="cell">
								<input type="submit" name="time"
									value="<%= times[j] %>"
									<%= disabled[i][j] != null ? "disabled" : "" %>
									class="<%= disabled[i][j] != null ? "cant" : "can" %>">
								<input type="hidden" name="roomId" value="<%= roomsId[i] %>">
							</td> 
						</form>
					<% } %>
				</tr>
			<% } %>      
		</table>
 	<hr>
  
  	<form action="<%= request.getContextPath() %>/menu.jsp" method="post">
  		<input type="submit" value="戻る">
  	</form>	
  
	<%@ include file="footer.jsp"%>
</body>
</html>