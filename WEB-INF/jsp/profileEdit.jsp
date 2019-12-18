<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>プロフィール編集</title>
		<style>
			.active{
				display:block;
			}
			.passive{
				display:none;
			}
		</style>
		<script>
			window.onload=function(){
				//データベースから取得した値をもとに、ラジオボタンの初期値を設定する
				if(${sessionScope.user.release}==0){
					document.getElementById("public").checked=true;
				}else{
					document.getElementById("private").checked=true;
				}
			}
			
			function showPublic(){
				document.getElementById("public_area").className="active";
				document.getElementById("private_area").className="passive";
			}
			function showPrivate(){
				document.getElementById("public_area").className="passive";
				document.getElementById("private_area").className="active";
			}
		</script>
	</head>
	<body>
		<h3>プロフィール編集</h3>
		
		<label><input type="radio" name="target" value="profile" onchange="showPublic();" checked>公開情報の編集</label><br>
		<label><input type="radio" name="target" value="password" onchange="showPrivate();">非公開情報の編集</label>
		
		<form action="edit" method="post">
			<table id="public_area" class="active">
				<tr>
					<td>名前</td>
					<td><input type="text" name="userName" value="${sessionScope.user.userName}"></td>
				<tr>
				<tr>
					<td>ユーザーID</td>
					<td><input type="text" name="userId" value="${sessionScope.user.userId}"></td>
				<tr>
				<tr>
					<td>プロフィール</td>
					<td><input type="text" name="profile" value="${sessionScope.user.profile}"></td>
				<tr>
				<tr>
					<td>プロフィール画像</td>
					<td><img src="${pageContext.request.contextPath}${sessionScope.user.profilePicture}"></td>
					<td><input type="file" name="profilePicuture"></td>
				<tr>
			</table>
			
			<table id="private_area" class="passive">
				<tr>
					<td>メールアドレス</td>
					<td><input type="text" name="mailAddress" value="${sessionScope.user.mailAddress}"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="text" name="password" value="${sessionScope.user.password}"></td>
				</tr>
				<tr>
					<td>確認用パスワード</td>
					<td><input type="text" name="password" value="${sessionScope.user.password}"></td>
				</tr>
				<tr>
					<td>アカウントの公開範囲</td>
					<td><label>すべてのユーザー<input type="radio" name="release" id="public" value="0"><label></td>
					<td>フォロワーのみ<input type="radio" name="release" id="private" value="1"></td>
				</tr>
			</table>
			<input type="submit" value="変更を保存する">
		</form>
	</body>
</html>