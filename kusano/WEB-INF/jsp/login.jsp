<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<title>ログイン</title>
	</head>
	<body>
		<h1>ログイン</h1>
		<form action="login_check" method="post">
			<input type="text" name="userId" placeholder="ID"><br>
			<input type="text" name="password" placeholder="パスワード"><br>
			<input type="submit" value="ログイン">
		</form>
	</body>
</html>
