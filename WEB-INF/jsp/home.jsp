<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
	</head>
	<body>
		<h1>�ف[��</h1>
		<p>���O�C������</p>
		<p>ID:${sessionScope.user.userId}</p>
		<p>pass:${sessionScope.user.password}</p>
		<p><a href="profile">�v���t�B�[��</a></p>
		<p><a href="post">���e</a></p>
		<p><a href="logout">���O�A�E�g</a></p>
	</body>
</html>
