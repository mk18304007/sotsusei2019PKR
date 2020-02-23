<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Reply</title>
	</head>
	<body>
		<h1>Reply</h1>
		<table border="1">
		<tr>
			<td>${data1.postId}</td>
			<td>
				${data1.contents1}
				${data1.contents2}
				${data1.contents3}
				${data1.contents4}
				${data1.contents5}
				${data1.contents6}
				${data1.contents7}
				${data1.contents8}
				${data1.contents9}
				${data1.contents10}
			</td>
		</tr>
		<tr>
			<td>${data1.text}</td>
		</tr>
		</table>
		<table border="1">
			<c:forEach var="rep1y" items="${data2}">
			<tr>
				<td>${rep1y.reply}</td>
				<td>${rep1y.managementId}</td>
			</tr>
			</c:forEach>
		</table>
	
		<form method="POST" action="handIn">
			<input type="hidden" value="${param.post_id}" name="post_id" />
			<textarea placeholder="ENTER THE COMMENT" name="reply" class="reply"></textarea><br>
			<input type="submit" value="Reply" />
		</form>
	</body>
</html>
