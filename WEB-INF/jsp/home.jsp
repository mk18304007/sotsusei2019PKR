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
						url:"LikeAjaxServlet",
						type:"POST",
						data:{managementId:managementId,postId:postId}
					}).done(function(result){
						//通信成功
						console.log("成功");
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
			.on{
				background:url(${pageContext.request.contextPath}/images/default_icon.jpg);
				width:50px;
				height:50px;
			}
			.off{
				background:url(${pageContext.request.contextPath}/images/8bqmz0oH_normal.jpg);
				width:50px;
				height:50px;
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
		<table border="1">
			<th>icon</th><th>userName</th><th>userId</th><th>postId</th><th>contents</th><th>text</th><th>like</th><th>likesCount</th>
			<c:forEach var="post" items="${data}">
				<tr>
					<td id="profilePicture"><img src="${pageContext.request.contextPath}${post.usersBean.profilePicture}" width="50px" height="50px"></td>
					<td id="userName">${post.usersBean.userName}</td>
					<td id="userId">${post.usersBean.userId}</td>
					<td id="${post.postBean.postId}">${post.postBean.postId}</td>
					<td id="contents"><img src="${pageContext.request.contextPath}/images/${post.postBean.contents}" width="50px" height="50px"></td>
					<td id="text">${post.postBean.text}</td>
					<td id="like"><input type="checkbox" class="like_btn" id="${post.postBean.postId}"></td>
					<td id="likesCount"><p>わるいね${post.postBean.likesCount}</p></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
