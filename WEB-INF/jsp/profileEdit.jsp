<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profileEdit.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<title>プロフィール編集</title>
		<style>
			.passive{
				display:none;
			}
		</style>
		<script>			
			window.onload=function(){
				var state=${sessionScope.user.state}
				//データベースから取得した値をもとに、ラジオボタンの初期値を設定する
				if(state==1){
					document.getElementById("public").checked=true;
				}else{
					document.getElementById("private").checked=true;
				}
				
				var state=${notfound}+0;
				if(state==1){
					document.getElementById("failedMessage").removeAttribute("style");
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

		<table class="head">
			<tr><td colspan="2"><h1>プロフィール編集</h1></td></tr>
			<tr>
				<td><label><input type="radio" name="target"  onchange="showPublic();" checked>公開情報の編集</label></td>
				<td><label><input type="radio" name="target"  onchange="showPrivate();">非公開情報の編集</label></td>
				<div id="failedMessage" style="display:none;">
					<p style="color:red;">IDかメールアドレスが既に使用されています</p>
				</div>
			</tr>
		</table>
	
		<form action="edit" enctype="multipart/form-data" method="post">
		<input type="hidden" value="${sessionScope.user.managementId}" name="managementId">

			<table id="public_area" class="active" style="width:450px">
				<tr>
					<td class="itename">ユーザーネーム</td>
					<td class="ite"><input type="text" name="userName" value="${sessionScope.user.userName}" maxlength="15"></td>
				</tr>
				<tr>
					<td class="itename">ユーザーID</td>
					<td class="ite"><input type="text" name="userId" value="${sessionScope.user.userId}" maxlength="15"></td>
				</tr>
				<tr>
					<td class="itename">プロフィール</td>
					<td class="ite"><input type="text" name="profile" value="${sessionScope.user.profile}" maxlength="1000"></td>
				</tr>
				<tr>
					<td class="itename">プロフィール画像</td>
					<td class="pic"><img src="${pageContext.request.contextPath}/images/${sessionScope.user.profilePicture}"></td>
				</tr>
				<tr>
					<td colspan="2" class="ite"><input type="file" name="contents" value="${sessionScope.user.profilePicture}" accept="image/*" /></td>
				</tr>
			</table>
			
			<table id="private_area" class="passive">
				<tr>
					<td class="ite">メールアドレス</td>
					<td class="ite"><input type="text" name="mailAddress" value="${sessionScope.user.mailAddress}"></td>
				</tr>
				<tr>
					<td class="ite">パスワード</td>
					<td class="ite"><input type="text" name="password" value="${sessionScope.user.password}"></td>
				</tr>
				<tr>
					<td class="ite">確認用パスワード</td>
					<td class="ite"><input type="text" name="password" value="${sessionScope.user.password}"></td>
				</tr>
				<tr>
					<td class="ite">アカウントの公開範囲</td>
					<td class="ite"><label>すべてのユーザー<input type="radio" name="state" id="public" value="0"><label></td>
					<td class="ite"><label>フォロワーのみ<input type="radio" name="state" id="private" value="1"><label></td>
				</tr>
			</table>
			<input type="submit" value="変更を保存する" style="margin-left: auto;margin-right: auto;">

		</form>
	</body>
</html>
