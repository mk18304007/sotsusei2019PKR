<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>投稿</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post.css" />
		<script>
			function check(){
				var fileList = document.getElementById("files").files;
				if(fileList.length>10 || fileList<=0){
					$("#sbtn").prop("disabled",true);
				}else{
					$("#sbtn").prop("disabled",false);
				}
			}	

			$('#files').on('change', function (e) {
    			var reader = new FileReader();
    			reader.onload = function (e) {
        			$("#preview").attr('src', e.target.result);
   				}
    			reader.readAsDataURL(e.target.files[0]);
			});

		</script>
	</head>
	<body>
		<h1>投稿</h1>
		<!-- <table border="1">
			<c:forEach var="post" items="${data}">
			<tr>
				<td><img src ="images/${post.contents}"></td>
				<td>${post.postId}</td>
				<td>${post.managementId}</td>
				<td>${post.text}</td>
				<td>${post.report}</td>
			</tr>
			</c:forEach>
		</table> -->

		<table>
			<form method="POST" enctype="multipart/form-data" action="submit">
				<tr>
					<td><img id="preview"></td>
				</tr>
				<tr>
					<td><label for="files">
						写真を選択
					<input type="file" id="files" name="contents" onchange="check();" multiple accept="image/*, video/*" / style="display:none;"></label></td>
				</tr>
				<tr>
					<td><textarea placeholder="コメントを入力" name="text" class="post"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" id="sbtn" value="投稿" /></td>
				</tr>
			</form>
		</table>
		
	</body>
</html>