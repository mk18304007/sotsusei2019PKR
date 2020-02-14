<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
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
			}
		</script>
		<style>
			#icon{
				 border-radius:50px;
			}
		</style>
	</head>
	<body>
		<p><a href="home">ホームへ</a></p>
		<p><a href="logout">ログアウト</a></p>
		<h1>プロフィールページ</h1>
		<table border="0">
			<tr>
				<td><img src="${pageContext.request.contextPath}${user.profilePicture}" title="プロフィール画像" width="100px" height="100px" id="icon"></td>
				<td>${user.userName}</td>
				<td>${user.userId}</td>
				<td>${user.profile}</td>
			</tr>
		</table>
		フォローする<input type="checkbox" class="fbtn"><br>
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
