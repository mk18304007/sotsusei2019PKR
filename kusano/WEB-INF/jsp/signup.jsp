<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<title>アカウント作成</title>
	</head>
	<body>
		<h1>アカウント作成</h1>
		<form action="signup" method="post">
			<input type="text" name="name" placeholder="ユーザー名"><br>
			<input type="text" name="id" placeholder="ID"><br>
			<input type="text" name="mail" placeholder="メールアドレス"><br>
			<input type="text" name="pass" placeholder="パスワード"><br>
			<input type="text" name="confirm" placeholder="確認用パスワード"><br>
			<input type="submit" value="新規登録">
		</form>
	</body>
</html>
