<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<title>プロフィール</title>
		<style>
			#icon{
				 border-radius:50px;
			}
		</style>
	</head>
	<body>
		<form method="post" action="home">
			<input type="submit" value="ホームへ" id="submit_btn"><br>
		</form>
		<form method="post" action="post">
			<input type="submit" value="投稿" id="submit_btn"><br>
		</form>
		<form method="post" action="logout">
			<input type="submit" value="ログアウト" id="submit_btn">
		</form>
		<h1>Myプロフィールページ</h1>
		<table border="0">
			<tr>
				<td><img src="${pageContext.request.contextPath}${user.profilePicture}" title="プロフィール画像" width="100px" height="100px" id="icon"></td>
				<td>${user.userName}</td>
				<td>${user.userId}</td>
				<td>${user.profile}</td>
			</tr>
		</table>
		<form method="post" action="profileEdit">
			<input type="submit" value="編集" id="submit_btn"><br>
		</form>
		<form method="post" action="followList">
			<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
			<input type="submit" formaction="followList" value="フォロー ${user.follows}">
			<input type="submit" formaction="followerList" value="フォロワー ${user.followers}">
		</from>
		
		<form method="post" action="blockList">
			<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
			<input type="submit" formaction="blockList" value="ブロックしたユーザー">
		</from>
		
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
