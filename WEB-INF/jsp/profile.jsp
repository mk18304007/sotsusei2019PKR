<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script>
			window.onload=function(){
				$('.fbtn').on('click',function() {
					var managementId=${user.managementId}
					
					$.ajax({
						url:"FollowAjaxServlet",
						type:"POST",
						data:{managementId:managementId}
					}).done(function(result){
						//通信成功
					}).fail(function(){
						//通信失敗
						console.log("読み込み失敗");
					}).always(function(result){
						//常に実行する処理
					})
				});
				
				$('.bbtn').on('click',function() {
					var managementId=${user.managementId}
					
					$.ajax({
						url:"BlockAjaxServlet",
						type:"POST",
						data:{managementId:managementId}
					}).done(function(result){
						//通信成功
					}).fail(function(){
						//通信失敗
						console.log("読み込み失敗");
					}).always(function(result){
						//常に実行する処理
					})
				});
			}
			
		</script>
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
		<h1>プロフィールページ</h1>
		<table border="0">
			<tr>
				<td><img src="${pageContext.request.contextPath}${user.profilePicture}" title="プロフィール画像" width="100px" height="100px" style="border-radius:50px;"></td>
				<td>${user.userName}</td>
				<td>${user.userId}</td>
				<td>${user.profile}</td>
			</tr>
		</table>
		
		<form method="post" action="followList">
			<input type="text" name="managementId" value="${user.managementId}" style="display:none;">
			<input type="submit" formaction="followList" value="フォロー ${user.follows}">
			<input type="submit" formaction="followerList" value="フォロワー ${user.followers}">
		</from>
		
		フォローする<input type="checkbox" class="fbtn">
		ブロック<input type="checkbox" class="bbtn"><br>
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
