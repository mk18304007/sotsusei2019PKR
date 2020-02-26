<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Reply</title>
		<script>
			window.onload=function(){
				console.log("yaa");
				var postId=${postData.postBean.managementId};
				var managementId=${sessionScope.user.managementId};
				if(postId==managementId){
					document.getElementById("d_btn").removeAttribute("style");
				}
			}
			function showCommemtDelete(){
				console.log("a");
			}
		</script>
		
	</head>
	<body>
		<h1>Reply</h1>
		<table border="0">
		<tr>
			<td>
				<form method="post" action="profile">
					<input type="hidden" value="${postData.usersBean.managementId}" name="managementId">
					<input type="image" src="${pageContext.request.contextPath}/images/${postData.usersBean.profilePicture}" width="50px" height="50px" style="border-radius:50px;">
				</form>
			</td>
			<td>
				<form method="post" action="profile">
					<input type="hidden" value="${postData.usersBean.managementId}" name="managementId">
					<input type="submit" value="${postData.usersBean.userName}" id="submit_btn">
				</form>
			</td>
			<td>
				<form method="post" action="profile">
					<input type="hidden" value="${postData.usersBean.managementId}" name="managementId">
					<input type="submit" value="${postData.usersBean.userId}" id="submit_btn">
				</form>
			</td>
			<td>
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents1}" width="50px" height="50px">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents2}" width="50px" height="50px" id="${postData.postBean.postId}content2" onload="test('${postData.postBean.postId}content2');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents3}" width="50px" height="50px" id="${postData.postBean.postId}content3" onload="test('${postData.postBean.postId}content3');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents4}" width="50px" height="50px" id="${postData.postBean.postId}content4" onload="test('${postData.postBean.postId}content4');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents5}" width="50px" height="50px" id="${postData.postBean.postId}content5" onload="test('${postData.postBean.postId}content5');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents6}" width="50px" height="50px" id="${postData.postBean.postId}content6" onload="test('${postData.postBean.postId}content6');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents7}" width="50px" height="50px" id="${postData.postBean.postId}content7" onload="test('${postData.postBean.postId}content7');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents8}" width="50px" height="50px" id="${postData.postBean.postId}content8" onload="test('${postData.postBean.postId}content8');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents9}" width="50px" height="50px" id="${postData.postBean.postId}content9" onload="test('${postData.postBean.postId}content9');" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/${postData.postBean.contents10}" width="50px" height="50px" id="${postData.postBean.postId}content10" onload="test('${postData.postBean.postId}content10');" style="display:none;">
			</td>
		</tr>
		<tr>
			<td>${postData.postBean.text}</td>
			<td>${postData.postBean.timeStamp}</td>
		</tr>
		</table>
		<table border="0">
			<c:forEach var="reply" items="${replyList}">
			<tr>
				<td>
					<form method="post" action="profile">
						<input type="hidden" value="${reply.usersBean.managementId}" name="managementId">
						<input type="image" src="${pageContext.request.contextPath}/images/${reply.usersBean.profilePicture}" width="50px" height="50px" style="border-radius:50px;">
					</form>
				</td>
				<td>
					<form method="post" action="profile">
						<input type="hidden" value="${reply.usersBean.managementId}" name="managementId">
						<input type="submit" value="${reply.usersBean.userName}" id="submit_btn">
					</form>
				</td>
				<td>
					<form method="post" action="profile">
						<input type="hidden" value="${reply.usersBean.managementId}" name="managementId">
						<input type="submit" value="${reply.usersBean.userId}" id="submit_btn">
					</form>
				</td>
				<td>
					${reply.replyBean.reply}
				</td>
				<td>
					<div id="commentDelete${reply.replyBean.replyId}" onload="showCommentDelete()">
						<form method="post" action="commentDelete">
							<input type="hidden" value="${postData.postBean.postId}" name="postId">
							<input type="hidden" value="${reply.replyBean.replyId}" name="commentId">
							<input type="submit" value="×" id="submit_btn">
						</form>
					</div>
				</td>
			</tr>
			</c:forEach>
		</table>
	
		<form method="POST" action="commentPost">
			<input type="hidden" value="${postData.postBean.postId}" name="postId" />
			<textarea placeholder="ENTER THE COMMENT" name="comment"></textarea><br>
			<input type="submit" value="Comment" />
		</form>
		
		<div id="d_btn" style="display:none;">
			<form method="POST" action="postDelete">
				<input type="hidden" value="${postData.postBean.postId}" name="postId">
				<input type="submit" value="投稿を削除する">
			</form>
		</div>
	</body>
</html>
