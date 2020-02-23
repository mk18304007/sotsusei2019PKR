<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>POST</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script>
			function check(){
				var fileList = document.getElementById("files").files;
				if(fileList.length>10 || fileList<0){
					$("#sbtn").prop("disabled",true);
				}else{
					$("#sbtn").prop("disabled",false);
				}
			}	
		</script>
	</head>
	<body>
		<h1>POST</h1><br>
		<h2>POST SCREEN</h2>
		<table border="1">
			<c:forEach var="post" items="${data}">
			<tr>
				<td><img src ="images/${post.contents}"></td>
				<td>${post.postId}</td>
				<td>${post.managementId}</td>
				<td>${post.text}</td>
				<td>${post.report}</td>
			</tr>
			</c:forEach>
		</table>

		<form method="POST" enctype="multipart/form-data" action="submit">
		<input type="file" id="files" "name="contents" onchange="check();" multiple accept="image/*, video/*" /><br />
		<textarea placeholder="ENTER THE COMMENT" name="text" class="post"></textarea><br>
		<input type="submit" id="sbtn" value="POST" />
		</form>

	</body>
</html>