<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>home</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
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
		<h1>ブロックしたユーザー</h1>
		<table border="0">
			<c:forEach var="block" items="${block}">
				<tr>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${block.managementId}" name="managementId" style="display:none;">
							<input type="image" src="${pageContext.request.contextPath}${block.profilePicture}" width="50px" height="50px" style="border-radius:50px;">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${block.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${block.userName}" id="submit_btn">
						</form>
					</td>
					<td>
						<form method="post" action="profile">
							<input type="text" value="${block.managementId}" name="managementId" style="display:none;">
							<input type="submit" value="${block.userId}" id="submit_btn">
						</form>
					</td>
					<td>フォローする<input type="checkbox" class="fbtn" id="${block.managementId}"></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
