<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<title>ログイン</title>
	</head>
	<script>
		$(function(){
			if($('#id').val().length==0 || $('#pas').val().length == 0){
				$('#submit').prop("disabled",true);
				console.log("true");
			}
		});

		function sb(){
			if($('#id').val().length !=0 && $('#pas').val().length != 0){
				$('#submit').prop("disabled",false);
			}
		}
	</script>
	<body>
		<table class="loginBox">
			<tr><td><h1>ログイン</h1></td></tr>
			<form action="login_check" method="post" >
				<tr><td><input type="text" name="userId" placeholder="ID" class="ite" id="id" onkeyup="sb()"></td></tr>
				<tr><td><input type="password" name="password" placeholder="パスワード" class="ite" id="pas" onkeyup="sb()"></td></tr>
				<tr><td><input type="submit" value="ログイン" class="isub" id="submit"></td></tr>
			</form>
			<tr><td><hr></td></tr>
			<tr><td><a href="signup_screen">アカウントをお持ちでない方</a></td></tr>
		</table>
	</body>
</html>
