<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head><title>POST</title></head>
<body>
	<h1>POST</h1><br>
	<h2>POST SCREEN</h2>
	<video controls>
		<source src="myVideo.mp4" type="video/mp4">
		<source src="myVideo.webm" type="video/webm">
	</video>

	<table>
		<c:forEach var="postProduct"items="${data}">
		<tr><td>${postProduct.managementId}</td><td>${postProduct.contents}</td><td>${postProduct.text}</td></tr>
		</c:forEach>
	</table>
	
	<table>
		<c:forEach var="postProduct" items="${data}">
		<tr><td>${data[0].managementId}</td><td>${data[0].contents}</td><td>${data[0].text}</td></tr>	
		</c:forEach>
	</table>

	<figure>
		<table>
			<c:forEach var="postProduct" items="${data}">
			<tr><td>${data[0].managementId}</td><td>${data[0].contents}</td><td>${data[0].text}</td></tr>
			<video>
				<source src="${i}" type="video/mp4">
				<source src="${i}" type="video/webm">
				<source src="${i}" type="video/ogg">
			</video>
			<video controls>
				<source src="${data[0].contents}" type="video/mp4">
				<source src="${data[0].contents}" type="video/webm">
				<source src="${data[0].contents}" type="video/ogg">
			</video>
			</c:forEach>
		</table>
	</figure>

	<table>
		<c:forEach var="postProduct" items="${data}">
			<tr><td>${postProduct.managementId}</td><td>${postProduct.text}</td></tr>
		</c:forEach>
		<c:forEach var="postProduct" items="${data}">
		<video controls>
			<source src="${data[0].contents}" type="video/mp4">
			<source src="${data[0].contents}" type="video/webm">
			<source src="${data[0].contents}" type="video/ogg">
			${postProduct.contents}
		</video>
		</c:forEach>
	</table>




	<!--
		<div class="${data[0].postId}" id="${data[0].postId}">
		<img class="profilePicture src="${data[0].profilePicture}">
		<a href="${data[0].userId}">${data[0].userId}</a>
		<a>${data[0].time}</a>
	-->

			<!--
				<div class="postdata">
				<figure>
					<c:set var="url" value="${data[0].contents}"/>
					<c:set var="file" value="${fn:split(url, '|')}"/>
					<c:choose>
					<c:when test="${fn:length(file) > 1}">
					<c:forEach var="i" items="${file}">
					<c:if test="${fn:endsWith(i, 'image')}">
						
						<div class="imagedata">
							<img src="${i}">
						</div>
					</c:if>
					<c:if test="${fn:endsWith(i,'video')}">

					<div>
						<video>
							<source src="${i}" type="video/mp4">
							<source src="${i}" type="video/webm">
							<source src="${i}" type="video/ogg">
						</video>
					</div>
					</c:if>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:if test="${fn:endsWith(file[0], 'image')}">

					<div class="imagedata">
						<img src="${data[0].contents}">
					</div>

					</c:if>
					<c:if test="${fn:endsWith(file[0],'video')}">
				
					<div class="imagedata">
						<video controls>
							<source src="${data[0].contents}" type="video/mp4">
							<source src="${data[0].contents}" type="video/webm">
							<source src="${data[0].contents}" type="video/ogg">
						</video>
					</div>
				
					</c:if>
					</c:otherwise>
					</c:choose>
				</figure>
			</div>
		</div>
	-->

	<div>
	<h2>POST</h2>
	<form method="post" enctype="multipart/form-data" class="form-inline" action="home">
		<input type="file" name="contents"/><br />
		<!--<input type="hidden" name="managementId" />-->
		<textarea placeholder="ENTER THE COMMENT" name="contents" class="post"></textarea><br>
		<input type="submit" value="POST" />
	</form>
    </div>
</body>
</html>