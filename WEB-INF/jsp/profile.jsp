<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
	</head>
	<body>
		<h1>プロフィールページ</h1>
		<p>ログイン成功</p>
		<p>ID:${sessionScope.user.userId}</p>
		<p>pass:${sessionScope.user.password}</p>
		<p><img src="${sessionScope.user.profilePicture}" title="プロフィール画像"></p>
		<p><a href="logout">ログアウト</a></p>
	</body>
</html>
