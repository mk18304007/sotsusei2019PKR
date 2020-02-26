<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css" />
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
			<input type="submit" value="ホームへ" id="submit_btn">
		</form>
		<form method="post" action="post">
			<input type="submit" value="投稿" id="submit_btn">
		</form>
		<form method="post" action="logout">
			<input type="submit" value="ログアウト" id="submit_btn">
		</form>

		<h1>ユーザーを表示できません</h1>
		<table class="ut">
			<tr>
				<td rowspan="3"><img src="${pageContext.request.contextPath}/images/${user.profilePicture}" title="プロフィール画像" width="200px" height="200px" id="icon"></td>
				<td class="uname"><p class="puname">${user.userName}</p></td>
				<td class="uid"><p class="puid">${user.userId}</p></td>
			</tr>
			<tr>
				<td>ブロックする<input type="checkbox" class="bbtn" ></td>
			</tr>
		</table>
	</body>
</html>
