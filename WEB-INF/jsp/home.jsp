<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script>
			function test(b){
				//console.log(document.getElementById(b));
				document.getElementById(b).removeAttribute("style");
			}
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
			<input type="text" name="keyword" placeholder="検索するワードを入力">
			<input type="submit" value="検索" id="submit_btn">
		</form>
		<form method="post" action="profile">
			<input type="hidden" value="${sessionScope.user.managementId}" name="managementId">
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
							<input type="hidden" value="${post.usersBean.managementId}" name="managementId">
							<input type="image" src="${pageContext.request.contextPath}/images/${post.usersBean.profilePicture}" width="50px" height="50px" style="border-radius:50px;">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="hidden" value="${post.usersBean.managementId}" name="managementId">
							<input type="submit" value="${post.usersBean.userName}" id="submit_btn">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="hidden" value="${post.usersBean.managementId}" name="managementId">
							<input type="submit" value="${post.usersBean.userId}" id="submit_btn">
						</form>
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents1}" width="50px" height="50px">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents2}" width="50px" height="50px" id="${post.postBean.postId}content2" onload="test('${post.postBean.postId}content2');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents3}" width="50px" height="50px" id="${post.postBean.postId}content3" onload="test('${post.postBean.postId}content3');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents4}" width="50px" height="50px" id="${post.postBean.postId}content4" onload="test('${post.postBean.postId}content4');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents5}" width="50px" height="50px" id="${post.postBean.postId}content5" onload="test('${post.postBean.postId}content5');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents6}" width="50px" height="50px" id="${post.postBean.postId}content6" onload="test('${post.postBean.postId}content6');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents7}" width="50px" height="50px" id="${post.postBean.postId}content7" onload="test('${post.postBean.postId}content7');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents8}" width="50px" height="50px" id="${post.postBean.postId}content8" onload="test('${post.postBean.postId}content8');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents9}" width="50px" height="50px" id="${post.postBean.postId}content9" onload="test('${post.postBean.postId}content9');" style="display:none;">
						<img src="${pageContext.request.contextPath}/images/${post.postBean.contents10}" width="50px" height="50px" id="${post.postBean.postId}content10" onload="test('${post.postBean.postId}content10');" style="display:none;">
					</td>
					<td>
						${post.postBean.text}
					</td>
					<td>
						<input type="checkbox" class="like_btn" id="${post.postBean.postId}" ${post.likesBean.likeFlag}>
					</td>
					<td>
						<form action="comment" method="POST">
							<input type="hidden" name="postId" value="${post.postBean.postId}">
							<input type="submit" value="詳細">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
