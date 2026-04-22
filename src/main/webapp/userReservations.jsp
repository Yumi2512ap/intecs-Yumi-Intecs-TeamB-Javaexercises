<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom"%>
<%@ page import="jp.co.seminar.beans.SearchBean"%>

<%
MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
SearchBean SB = (SearchBean) request.getAttribute("searchBean");
String[] roomsName = MR.getRoomsName();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>予約履歴</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>予約履歴</h1>
	<form action="<%=request.getContextPath()%>/ResListSort" method="post">
		並び順： <select name="order">
			<option value="ASC" ${ searchBean.isASC }>古い順</option>
			<option value="DESC" ${ searchBean.isDESC }>新しい順</option>
		</select> <br>
		
		日付： <input type="date" name="date1" value="${searchBean.date1}">～ 
		<input type="date" name="date2" value="${searchBean.date2}"> <br>
		
		会議室： <select name="room">
			<option value="all" ${ searchBean.isAll }>全て</option>
			<% for (int i = 0; i < roomsName.length; i++) { %>
				<option value="<%=i%>" <%= SB.isRoom(i) %>><%=roomsName[i]%></option>
			<% } %>
		</select> <br>
		
		<input type="hidden" name="user" value="${MR.user.name}">
		<input type="hidden" name="page" value="user">
		<input type="submit" value="検索">
	</form>
	<form action="<%=request.getContextPath()%>/ResListSort" method="post">
		<input type="hidden" name="order" value="">
		<input type="hidden" name="date1" value="">
		<input type="hidden" name="date2" value="">
		<input type="hidden" name="room" value="all">
		<input type="hidden" name="user" value="${MR.user.name}">
		<input type="hidden" name="page" value="user">
	<input type="submit" value="リセット">
	</form>
	<hr>

	<table>
		<tr>
			<th>予約日</th>
			<th>時間</th>
			<th>会議室</th>
			<th>予約者</th>
		</tr>
		${reservations }

	</table>


	<hr>
	<form action="userMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>