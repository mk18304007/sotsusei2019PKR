<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css" />
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
		<table class="head">
		<form method="post" action="home">
			<td><input type="submit" value="ホームへ" id="submit_btn"></td>
		</form>
		<form method="post" action="post">
			<td><input type="submit" value="投稿" id="submit_btn"></td>
		</form>
		<form method="post" action="logout">
			<td><input type="submit" value="ログアウト" id="submit_btn"></td>
		</form>
		</table>
		<h1>プロフィールページ</h1>
		<table class="ut">
			<tr>
				<td rowspan="3"><img src="${pageContext.request.contextPath}/images/${user.profilePicture}" title="プロフィール画像" width="200px" height="200px" id="icon"></td>
				<td class="uname"><p class="puname">${user.userName}</p></td>
				<td class="uid"><p class="puid">${user.userId}</p></td>
			</tr>
			<tr>
				<form method="post" action="followList">
					<input type="hidden" name="managementId" value="${user.managementId}">
					<td><input type="submit" formaction="followList" value="フォロー中 ${user.follows} 人"  class="list"></td>
					<td><input type="submit" formaction="followerList" value="フォロワー ${user.followers} 人"  class="list"></td>
				</form>
				<td>フォローする<input type="checkbox" class="fbtn" ></td>
				<td>ブロックする<input type="checkbox" class="bbtn" ></td>
			</tr>
			<tr>
				<td colspan="3">${user.profile}</td>
			</tr>
			
		</table>

		<table class="ht">
			<tr><td width="400"><hr></td><td class="t">投稿</td><td width="400"><hr></td></tr>
		</table>
		
		<ul class="postu">
			<c:forEach var="post" items="${post}">
				<form action="comment" method="POST" >
					<li class="pp">
						<input type="image" src="${pageContext.request.contextPath}/images/${post.contents1}" class="ppimg" width="400px" height="400px">
						<input type="hidden" name="postId" value="${post.postId}">
						<input type="submit" value="詳細" hidden>
						<div class="mask" hidden><p class="likeC">${post.likesCount}</p></div>	
					</li>
				</form>
			</c:forEach>
		</ul>
	</body>
</html>
