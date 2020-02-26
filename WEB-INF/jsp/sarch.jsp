<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<style>
			#icon{
				 border-radius:50px;
			}
		</style>
		<script>
			function showUsers(a,b){
				if(b==2){
					document.getElementById(a).setAttribute("style","display:none;")
				}
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
		
		<table border="0">
			<tr>
				<c:forEach var="user" items="${user}">
					<td>
						<div id="user${user.usersBean.managementId}">
							<form method="POST" action="profile">
								<input type="hidden" value="${user.usersBean.managementId}" name="managementId">
								<input type="image" src="${pageContext.request.contextPath}/images/${user.usersBean.profilePicture}" width="50px" height="50px" style="border-radius:50px;" onload="showUsers('user${user.usersBean.managementId}','${user.state}');">
								<input type="submit" value="${user.usersBean.userName}" id="submit_btn">
								<input type="submit" value="${user.usersBean.userId}" id="submit_btn">
							</form>
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
		
		<table border="0">
			<tr>
				<c:forEach var="post" items="${post}">
					<td><img src="${pageContext.request.contextPath}/images/${post.contents1}" width="100px" height="100px"></td>
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
