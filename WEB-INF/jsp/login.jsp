<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<title>ログイン</title>
	</head>
	<script>
		
		$(function(){
			if($('#id').val().length==0 || $('#pas').val().length == 0){
				$('#submit').prop("disabled",true);
			}

			var state=${notfound}+0;
			if(state==1){
				document.getElementById("failedMessage").removeAttribute("style");
			}

		});

		function sb(){
			$(".ite").on("keydown",function(){
				let k = e.keyCode;
				let str = String.fromCharCode(k);
				if(!(str.match(/[0-9a-zA-Z]/)) || e.shiftKey || (37 <= k && k <= 40) || k === 8 || k === 46){
					return false;
				}
			});
			$(".ite").on('keyup', function(e){
				if(e.keyCode === 9 || e.keyCode === 16) return;
				this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
			});

			$(".ite").on('blur',function(){
				this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
			});	
			
			if($('#id').val().length !=0 && $('#pas').val().length > 3){
				$('#submit').prop("disabled",false);
				$('#submit').prop("value","ログイン");
			}else{
				$('#submit').prop("disabled",true);
				$('#submit').prop("value","ログインできません");
			}
		}

	</script>
	<body>
		<table class="loginBox">
			<tr><td><h1>ログイン</h1></td></tr>
			<form action="login_check" method="post" >
				<tr><td><div id="failedMessage" style="display:none;">
					<p style="color:red;" class="message">ユーザーID又はパスワードが間違っているか、<br>ユーザーが存在しません</p>
				</div><td><tr>
				<tr><td><input type="text" name="userId" placeholder="ユーザーID" class="ite" id="id" maxlength="15" onkeyup="sb()"></td></tr>
				<tr><td><input type="password" name="password" placeholder="パスワード" class="itepass" id="pas" maxlength="15" onkeyup="sb()"></td></tr>
				<tr><td><input type="submit" value="ログインできません" class="isub" id="submit" ></td></tr>
			</form>
			<tr><td><hr></td></tr>
			<tr><td class="topage"><a href="signup_screen">アカウントをお持ちでない方</a></td></tr>
		</table>
	</body>
</html>


