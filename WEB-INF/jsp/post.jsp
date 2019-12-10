<%@ page pageEncoding="Windows-31J" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head><title>POST</title></head>
<body>
	<h1>POST</h1><br>
	<h2>POST SCREEN</h2>
	<video controls>
		<source src="video.mp4" type="video/mp4">
		<source src="video.webm" type="video/webm">
	</video>
	<div class="${data[0].post_id}" id="${data[0].post_id}">
		<img class="user_profilepicture src="${data[0].user_profilepicture}">
			<a href="${data[0].user_id}">${data[0].user_id}</a>
			<a>${data[0].post_time}</a>
			
			<div class="postdata">
			<figure>
				<c:set var="url" value="${data[0].post_picture}"/>
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
								<img src="${data[0].post_picture}">
							</div>
						</c:if>
						<c:if test="${fn:endsWith(file[0],'video')}">
							<div class="imagedata">
								<video controls>
									<source src="${data[0].post_picture}" type="video/mp4">
									<source src="${data[0].post_picture}" type="video/webm">
									<source src="${data[0].post_picture}" type="video/ogg">
								</video>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
			</figure>
		</div>
	</div>
	<div class="post">
	<h2>POST</h2>
	
	<form class="form-inline" method="post" action="post">
		<input type="hidden" name="url" />
		<textarea placeholder="COMMENT" name="caption" class="captiontext rounded"></textarea>
	<form method="POST" enctype="multipart/form-data" action="post">
		<input type="file" name="file"/><br />
		<input type="submit" value="POST" />
		<!--<button type="submit" class="post">POST</button>-->
	</form>
    </div>
</body>
</html>