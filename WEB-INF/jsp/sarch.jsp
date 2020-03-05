<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>プロフィール</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/serch.css" />
		<script>
			function showUser(){
				document.getElementById("user_line").className="active";
				document.getElementById("user_area").className="active";
				document.getElementById("post_line").className="passive";
				document.getElementById("post_area").className="passive";

			}
			function showPost(){
				document.getElementById("user_line").className="passive";
				document.getElementById("user_area").className="passive";
				document.getElementById("post_line").className="active";
				document.getElementById("post_area").className="active";
			}
		</script>
		<style>
			.passive{
				display:none;
			}
		</style>
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

		<h1>検索結果</h1>
		<table class="ht passive" id="user_line">
			<tr><td class="line"><hr></td><td class="listname">ユーザー</td><td class="line"><hr></td></tr>
		</table>

		<table class="head">
			<tr>
				<td><label><input type="radio" name="target" onchange="showUser();" >ユーザー</label></td>
				<td><label><input type="radio" name="target" onchange="showPost();" checked>投稿</label></td>
			</tr>
		</table>

		<table class="usersTable passive" id="user_area">
			<c:forEach var="user" items="${user}">
					<form method="POST" action="profile">
						<td><input type="hidden" value="${user.usersBean.managementId}" name="managementId"></td>
						<tr class="truserName">
							<td rowspan="3" class="userImg"><input type="image" src="${pageContext.request.contextPath}/images/${user.usersBean.profilePicture}" class="im" id="icon"></td>					
							<td ><input type="submit" value="${user.usersBean.userName}" id="submit_btn" class="userName"></td>
						</tr>						
						<tr class="truserId">
							<td><input type="submit" value="${user.usersBean.userId}" id="submit_btn" class="userId"></td>
						</tr>
						<tr>
							<td class="userProf"><input type="submit" value="${user.usersBean.profile}" id="submit_btn" class="userProf"></td>
						</tr>
					</form>
			</c:forEach>
		</table>
		
		<table class="ht active" id="post_line">
			<tr><td class="line"><hr></td><td class="listname">投稿</td><td class="line"><hr></td></tr>
		</table>

		<div class="active"  id="post_area">
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
		</div>
	</body>
</html>

