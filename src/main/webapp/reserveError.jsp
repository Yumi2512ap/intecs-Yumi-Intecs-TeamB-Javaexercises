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

	<label>予約エラー</label><br>
	<label>${errorReason}時刻が過ぎているため予約できません</label><br>
	予約日<label>${reservation.date }4月6日</label><br>
	会議室<label>${room.name}</label>大会議室<br>
	予約時刻<label>${reservation.start}9：00</label><label>${reservation.end}10：00</label><br>
	予約者<label>${meetingRoom.user.name}太郎</label><br>
	<form action="<%=request.getContextPath()%>/menu.jsp"method="get">
	<input type="submit" value="確認">
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>