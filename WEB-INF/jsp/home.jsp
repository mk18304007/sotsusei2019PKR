<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
	</head>
	<body>
		<h1>ほーむ</h1>
		<p>ログイン成功</p>
		<p>ID:${sessionScope.user.userId}</p>
		<p>pass:${sessionScope.user.password}</p>
		<p><a href="profile">プロフィール</a></p>
		<p><a href="post">投稿</a></p>
		<p><a href="logout">ログアウト</a></p>
	</body>
</html>
