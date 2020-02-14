<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<style>
			#icon{
				 border-radius:50px;
			}
		</style>
	</head>
	<body>
		<p><a href="profileEdit">編集</a></p>
		<p><a href="home">ホームへ</a></p>
		<p><a href="logout">ログアウト</a></p>
		<h1>Myプロフィールページ</h1>
		<table border="0">
			<tr>
				<td><img src="${pageContext.request.contextPath}${user.profilePicture}" title="プロフィール画像" width="100px" height="100px" id="icon"></td>
				<td>${user.userName}</td>
				<td>${user.userId}</td>
				<td>${user.profile}</td>
			</tr>
		</table>
		投稿
		<table border="0">
			<tr>
				<c:forEach var="post" items="${post}">
						<td><img src="${pageContext.request.contextPath}${post.contents}" width="100px" height="100px"></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="post" items="${post}">
						<td>${post.likesCount}</td>
				</c:forEach>
			</tr>
		</table>
	</body>
</html>
