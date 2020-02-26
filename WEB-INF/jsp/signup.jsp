<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singup.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<title>アカウント作成</title>
	</head>
	<script>
		$(function(){
			if($('#na').val().length==0 || $('#id').val().length == 0 || $('#ma').val().length == 0 || $('#p1').val().length == 0 || $('#p2').val().length == 0){
				$('#submit').prop("disabled",true);
			}
		});

		
		function sb(){
			if($('#na').val().length !=0 && $('#id').val().length != 0 && $('#ma').val().length!= 0 && $('#p1').val().length!= 0 && $('#p2').val().length != 0){
				var pas1 = document.getElementById("p1").value;
				var pas2 = document.getElementById("p2").value;
				if(pas1 != pas2){
					$('#submit').prop("disabled",true);
				}else{
					$('#submit').prop("disabled",false);
				}
			}
		}
	</script>
	<body>
		<table>
		<tr><td><h1>アカウント作成</h1>
			<form action="signup" method="post">
				<tr><td><input type="text" name="userName" placeholder="ユーザー名" class="ite" id="na" ></td></tr>
				<tr><td><input type="text" name="userId" placeholder="ID" class="ite" id="id"></td></tr>
				<tr><td><input type="email" name="mailAddress" placeholder="メールアドレス" class="ite" id="ma"></td></tr>
				<tr><td><input type="password" name="password" placeholder="パスワード" class="ite" id="p1" onkeyup="sb()"></td></tr>
				<tr><td><input type="password" name="password" placeholder="確認用パスワード" class="ite" id="p2" onkeyup="sb()"></td></tr>
				<tr><td><input type="submit" value="新規登録" class="isub" id="submit"></td></tr>
			</form>
			<tr><td><hr></td></tr>
			<tr><td><a href="login">既にアカウントをお持ちの方</a></td></tr>
		</table>
	</body>
</html>
