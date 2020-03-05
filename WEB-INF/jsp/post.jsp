<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>投稿</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/css/lightbox.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/js/lightbox.min.js" type="text/javascript"></script>


		<script>

			var cnt = 0;
			var tmpId = 'tmp-' + cnt;

			function check(event, id){
				var fileList = document.getElementById("files").files;
				if(fileList.length>10 || fileList<0){
					$("#sbtn").prop("disabled",true);
					$("#sbtn").prop("value","投稿できません");
				}else{
					$("#sbtn").prop("disabled",false);
					$("#sbtn").prop("value","投稿");
				}
				for (let file of event.target.files){
					imgPreView(file, id);
				}
				tmpId = 'tmp-' + ++cnt;

			}

			function imgPreView(file, id){
				let preview = document.getElementById("preview-"+ id);
				let previewImages = document.getElementsByClassName(tmpId);
				let reader = new FileReader();

				if(previewImages != null) {
					for(let img of previewImages){
					preview.removeChild(img);
					}
				}

				reader.onload = function(event) {
					var a = document.createElement("a");
					a.setAttribute("href",reader.result);
					a.setAttribute("data-lightbox","group");
					a.setAttribute("type","hidden");
					preview.appendChild(a);
					var img = document.createElement("img");
					img.setAttribute("src", reader.result);
					img.setAttribute("id", "previewImage-" + id);
					img.setAttribute("class", tmpId);
					preview.appendChild(img);
				};
				reader.readAsDataURL(file);
			}


		</script>
	</head>
	<body>
		<h1>投稿</h1>
		<form method="POST" enctype="multipart/form-data" action="submit">
			<table>
				<tr>
					<td><div id="preview-item1"></div></td>
				</tr>
				<tr>
					<td class="imgbutton">
						<input type="file" id="files" name="contents" onchange="check(event,'item1')" multiple accept="image/*, video/*"　/>
					</td>
				</tr>
				<tr>
					<td class="postcoment"><textarea placeholder="コメントを入力" name="text" class="post" maxlength="2000" cols="60" rows="5"></textarea></td>
				</tr>
				<tr>
					<td class="postbutton"><input type="submit" id="sbtn" value="投稿できません" disabled/></td>
				</tr>
			</table>		
		</form>
	</body>
</html>