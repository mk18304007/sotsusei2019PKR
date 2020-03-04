<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
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

		<h1 >Myプロフィールページ</h1>
		<table class="ut">
			<tr>
				<td rowspan="3"><img src="${pageContext.request.contextPath}/images/${user.profilePicture}" title="プロフィール画像" width="200px" height="200px" id="icon"></td>
				<td class="uname"><p class="puname">${user.userName}</p></td>
				<td class="uid"><p class="puid">${user.userId}</p></td>
				<form method="post" action="profileEdit">
					<td><input type="submit" value="プロフィールを編集" id="submit_btn" class="pebtn"></td>
				</form>
			</tr>
			<tr>
				<form method="post" action="followList">
				<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
					<td><input type="submit" formaction="followList" value="フォロー中 ${user.follows} 人" class="list"></td>
					<td><input type="submit" formaction="followerList" value="フォロワー ${user.followers} 人" class="list"></td>
				</form>
				
				<form method="post" action="blockList">
					<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
					<td><input type="submit" formaction="blockList" value="ブロックしたユーザー" class="list"></td>
				</form>
			</tr>
			<tr>
				<td colspan="3">${user.profile}</td>
			</tr>
		</table>

		<table class="ht">
			<tr><td width="400"><hr></td><td class="t">投稿</td><td width="400"><hr></td></tr>
		</table>

		<table class="pt">
			<tr>
				<c:forEach var="post" items="${post}">
					<td><img src="${pageContext.request.contextPath}/images/${post.contents1}" width="400px" height="400px"></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="post" items="${post}">
					<td>${post.likesCount}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="post" items="${post}">
					<td>
						<form action="comment" method="POST">
							<input type="hidden" name="postId" value="${post.postId}">
							<input type="submit" value="詳細">
						</form>
					</td>
				</c:forEach>
			</tr>
		</table>

	</body>
</html>
