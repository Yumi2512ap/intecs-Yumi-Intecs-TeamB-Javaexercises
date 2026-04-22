<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.seminar.beans.UserBean"%>

<%
//セッションから取り出して、変数UBに代入する
UserBean UB = (UserBean) session.getAttribute("UB");

// もしUBがnullだったらログイン画面へ飛ばす
if (UB == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2>ユーザーメニュー</h2>
	<hr>
	<h2>ユーザー情報編集</h2>
	<form action="<%=request.getContextPath()%>/UserEditServlet"
		method="post">

		<table class="result">

			<tr>
				<%--入力不可（表示のみ）--%>
				<th>利用者ID：</th>
				<td><input type="text" name="userId" value="<%=UB.getId()%>"
					readonly required></td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><input type="password" name="userPw"
					value="<%=UB.getPassword()%>" required></td>
			</tr>
			<tr>
				<th>表示ユーザー名：</th>

				<td><input type="text" name="userName"
					value="<%=UB.getName()%>" required></td>

			</tr>
			<tr>
				<th>居住地：</th>
				<td><input type="text" name="useraddress"
					value="<%=UB.getAddress()%>" placeholder="都道府県を入力" required></td>
			</tr>
		</table>

		<input type="submit" name="action" value="変更を保存する">
	</form>

	<form action="<%=request.getContextPath()%>/UserEditServlet"
		method="post">
		<input type="submit" name="action" value="退会する">
	</form>

	${ msg }
	<form action="userMenu.jsp">
		<input type="submit" value="戻る">
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>