<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
	</head>
	<body>
		<h1>プロフィールページ</h1>
		<p>ログイン成功</p>
		<p>ID:${data.id}</p>
		<p>pass:${data.pass}</p>
		<p><img src="${data.image}" title="プロフィール画像"></p>
		<p><a href="logout">ログアウト</a></p>
	</body>
</html>
