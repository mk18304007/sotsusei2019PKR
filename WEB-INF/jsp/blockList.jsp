<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>ブロックリスト</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script>
			window.onload=function(){
				$('.bbtn').on('click',function() {
					var managementId=$(this).attr("id");
					
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
		<form method="post" action="profile">
			<input type="text" value="${sessionScope.user.managementId}" name="managementId" style="display:none;">
			<input type="submit" value="プロフィール" id="submit_btn">
		</form>
		<h1>ブロックしたユーザー</h1>
		<table class="ut">
			
			<c:forEach var="block" items="${block}">
				<tr>
					<td class="up utd" rowspan="2">
						<form method="post" action="profile" class="fos">
							<input type="hidden" value="${block.managementId}" name="managementId" >
							<input type="image" src="${pageContext.request.contextPath}/images/	${block.profilePicture}" width="140px" height="140px" id="icon">
						</form>
					</td>
					<td class="utd">
						<form method="post" action="profile" class="fos">
							<input type="hidden" value="${block.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${block.userName}" id="submit_btn" class="uname">
						</form>
					</td>
					<td class"utd>
						<form method="post" action="profile" class="fos">
							<input type="hidden" value="${block.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${block.userId}" id="submit_btn" class="uid">
						</form>
					</td>
					<td class="utd">ブロックを解除する<input type="checkbox" class="fbtn" id="${block.managementId}"></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
