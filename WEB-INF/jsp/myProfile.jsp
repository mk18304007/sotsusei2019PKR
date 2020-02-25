<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css" />
		<title>プロフィール</title>
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
		<table border="1" class="ut">
			<tr>
				<td rowspan="3"><img src="${pageContext.request.contextPath}${user.profilePicture}" title="プロフィール画像" width="200px" height="200px" id="icon"></td>
				<td class="uname">ユーザー名:${user.userName}</td>
				<td class="uid">ユーザーID:${user.userId}</td>
				<td><form method="post" action="profileEdit">
					<input type="submit" value="プロフィールを編集" id="submit_btn" class="pebtn">
				</form></td>
			</tr>
			<tr>
				<form method="post" action="followList">
				<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
					<td><input type="submit" formaction="followList" value="フォロー中${user.follows}人" class="fl"></td>
					<td><input type="submit" formaction="followerList" value="フォロワー${user.followers}人" class="frl"></td>
				</from>
				
				<form method="post" action="blockList">
					<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
					<td><input type="submit" formaction="blockList" value="ブロックしたユーザー"></td>
				</from>
			</tr>
			<tr>
				<td colspan="3">${user.profile}</td>
			</tr>
		</table>
		
		投稿
		<table border="0">
			<tr>
				<c:forEach var="post" items="${post}">
						<td><img src="${pageContext.request.contextPath}${post.contents}" width="100px" height="100px"></td></tr>
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
