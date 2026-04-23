<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.UserBean"%>
<%@ page import="jp.co.seminar.beans.MeetingRoom"%>

<%
MeetingRoom MR = (MeetingRoom) session.getAttribute("MR");
UserBean user = MR.getUser();
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">

<title>会議室予約システム</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1>会議室予約</h1>
	<hr>
	<h2>メニュー</h2>

	<div class="menu">
		<form action="<%=request.getContextPath()%>/reserveInput.jsp"
			method="get">
			<input type="submit" value="会議室予約">
		</form>
		<form action="<%=request.getContextPath()%>/cancelInput.jsp"
			method="get">
			<input type="submit" value="予約キャンセル">
		</form>
		<form action="<%=request.getContextPath()%>/userMenu.jsp">
			<input type="submit" value="ユーザー情報">
		</form>
		
		<%
		if (user != null && user.getIsAdmin()) {
		%>
		<form action="<%=request.getContextPath()%>/adminMenu.jsp" class="admin">
			<input type="submit" value="管理者メニュー">
		</form>
		<%
		}
		%>

	</div>
	
	<hr>
	
	<form action="<%=request.getContextPath()%>/Logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>