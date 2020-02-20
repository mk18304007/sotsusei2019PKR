<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
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
	</head>
	<body>
		<h1>ほーむ</h1>
		<form method="post" action="sarch">
			<input type="text" name="keyword" placeholder="検索ゥ">
			<input type="submit" value="検索" id="submit_btn">
		</form>
		<form method="post" action="profile">
			<input type="text" value="${sessionScope.user.managementId}" name="managementId" style="display:none;">
			<input type="submit" value="プロフィール" id="submit_btn">
		</form>
		<form method="post" action="post">
			<input type="submit" value="投稿" id="submit_btn"><br>
		</form>
		<form method="post" action="logout">
			<input type="submit" value="ログアウト" id="submit_btn">
		</form>
		<br><br>
		<table border="0">
			<th>icon</th><th>userName</th><th>userId</th><th>contents</th><th>text</th><th>like</th>
			<c:forEach var="post" items="${posts}">
				<tr id="${post.postBean.postId}">
					<td>
						<form method="post" action="profile">
							<input type="text" value="${post.usersBean.managementId}" name="managementId" style="display:none;">
							<input type="image" src="${pageContext.request.contextPath}${post.usersBean.profilePicture}" width="50px" height="50px" style="border-radius:50px;">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${post.usersBean.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${post.usersBean.userName}" id="submit_btn">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${post.usersBean.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${post.usersBean.userId}" id="submit_btn">
						</form>
					</td>
					<td>
						<img src="${pageContext.request.contextPath}${post.postBean.contents}" width="50px" height="50px">
					</td>
					<td>
						${post.postBean.text}
					</td>
					<td>
						<input type="checkbox" class="like_btn" id="${post.postBean.postId}" ${post.likesBean.likeFlag}>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
