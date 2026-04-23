<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除</title>
<link rel="stylesheet" href="stylesheet.css"> <!-- 既存のCSSを読み込み -->
</head>
<style>
    /* メインコンテンツの枠を広げる */
    .main-container {
        min-height: 60vh; /* 画面の高さの60%を最低限確保する */
        display: flex;
        flex-direction: column;
        justify-content: center; /* 中身を中央寄りに配置 */
        align-items: center;
    }

    /* 実行ボタンの文字を上下中央にする（以前の修正も反映） */
    input[type="submit"] {
        height: 40px;
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 0 20px;
        box-sizing: border-box;
        line-height: 1;
    }
</style>

<!-- JSPの構成例 -->
<body>
    <jsp:include page="header.jsp" />

    <div class="main-container"> <!-- このクラスで高さを確保します -->
        <h1 class="page-title">ユーザー削除</h1>
        <hr>

        <p>削除するユーザーIDを入力してください：</p>
        <p style="color:red; font-weight:bold;">${errorMessage}</p>
        <p>${msg}</p>
        <form action="DeleteUser" method="post">
            <input type="text" name="userId" style="width: 250px; margin-bottom: 15px; padding: 5px;">
            <div style="text-align: center;">
                <input type="submit" value="実行">
            </div>
        </form>
        
		<hr style="margin-top: 80px;">
        <form action="adminMenu.jsp">
			<input type="submit" value="戻る">
		</form>
    </div>

    <jsp:include page="footer.jsp" />
</body>

</html>
