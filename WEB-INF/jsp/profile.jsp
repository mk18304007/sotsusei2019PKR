<%@page pageEncoding="Windows-31J" contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>�v���t�B�[��</title>
	</head>
	<body>
		<h1>�v���t�B�[���y�[�W</h1>
		<p>���O�C������</p>
		<p>ID:${sessionScope.user.userId}</p>
		<p>pass:${sessionScope.user.password}</p>
		<p><img src="${sessionScope.user.profilePicture}" title="�v���t�B�[���摜"></p>
		<p><a href="logout">���O�A�E�g</a></p>
	</body>
</html>
