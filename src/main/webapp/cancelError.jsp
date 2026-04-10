<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会議室予約エラー</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<label>キャンセルエラー</label><br>
	<label>${errorReason}既にキャンセルされています</label><br>
	予約日<label>${reservation.date }4月22日</label><br>
	会議室<label>${room.name}</label>大会議室<br>
	予約時刻<label>${reservation.start}</label>10:00<label>${reservation.end}</label><br>
	予約者<label>${meetingRoom.user.name}</label>太郎<br>
	<form action="<%=request.getContextPath()%>/menu.jsp"method="get">
	<input type="submit" value="確認">
	</form>
	
	<%@ include file="footer.jsp"%>
</body>
</html>