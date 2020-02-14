<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script>
			window.onload=function(){
				$('.like_btn').on('click',function() {
					var postId=$(this).attr("id");
					var managementId=${sessionScope.user.managementId};
					console.log("postId:"+postId+"\n managementId:"+managementId);
					
					$.ajax({
						url:"like",
						type:"POST",
						data:{managementId:managementId,postId:postId}
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
			input#submit_btn{
				background:none;
				border:none;
				outline: none;
				-webkit-appearance: none;
				-moz-appearance: none;
				appearance: none;
			}
		</style>
	</head>
	<body>
		<h1>ほーむ</h1>
		<p>ログイン成功</p>
		<p>ID:${sessionScope.user.userId}</p>
		<p>pass:${sessionScope.user.password}</p>
		<p><a href="profile">プロフィール</a></p>
		<p><a href="post">投稿</a></p>
		<p><a href="logout">ログアウト</a></p>
		<br><br>
		<table border="0">
			<th>icon</th><th>userName</th><th>userId</th><th>contents</th><th>text</th><th>like</th>
			<c:forEach var="post" items="${posts}">
				<tr id="${post.postBean.postId}">
					
					<td><img src="${pageContext.request.contextPath}${post.usersBean.profilePicture}" width="50px" height="50px" id="icon"></td>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${post.usersBean.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${post.usersBean.userName}" id="submit_btn">
						</form>
					</td>
					<td>${post.usersBean.userId}</td>
					<td><img src="${pageContext.request.contextPath}${post.postBean.contents}" width="50px" height="50px"></td>
					<td>${post.postBean.text}</td>
					<td><input type="checkbox" class="like_btn" id="${post.postBean.postId}" ${post.likesBean.likeFlag}></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
