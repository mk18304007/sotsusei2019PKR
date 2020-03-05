<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singup.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<title>アカウント作成</title>
	</head>
	<script>
		$(function(){
			if($('#na').val().length==0 || $('#id').val().length == 0 || $('#ma').val().length == 0 || $('#p1').val().length == 0 || $('#p2').val().length == 0){
				$('#submit').prop("disabled",true);
			}

			var state=${notfound}+1;
			if(state==0){
				document.getElementById("failedMessage").removeAttribute("style");
			}

		});

		
		function sb(){
			var pas1 = document.getElementById("p1").value;
			var pas2 = document.getElementById("p2").value;
			$(".iteid").on("keydown",function(){
			let k = e.keyCode;
			let str = String.fromCharCode(k);
				if(!(str.match(/[0-9a-zA-Z]/)) || e.shiftKey || (37 <= k && k <= 40) || k === 8 || k === 46){
					return false;
				}
			});
			$(".iteid").on('keyup', function(e){
				if(e.keyCode === 9 || e.keyCode === 16) return;
				this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
			});
			$(".iteid").on('blur',function(){
				this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
			});	

			if($('#na').val().length !=0 && $('#id').val().length != 0 && $('#ma').val().length!= 0 && $('#p1').val().length > 3 && $('#p2').val().length > 3 ){	
				if(pas1 != pas2){
					$('#submit').prop("disabled",true);
				}else{
					$('#submit').prop("disabled",false);
				}
			}else{
				$('#submit').prop("disabled",true);
			}
		}
	</script>
	<body>
		<form action="signup" method="post">
			<table>
			<tr><td><h1>アカウント作成</h1>
					<tr><td><div id="failedMessage" style="display:none;">
						<p style="color:red;" class="message">ユーザーIDかメールアドレスが使用されています</p>
					</div></td></tr>
					<tr><td><input type="text" name="userName" placeholder="ユーザー名" class="ite" id="na" maxlength="15" onkeyup="sb()"></td></tr>
					<tr><td><input type="text" name="userId" placeholder="ユーザーID" class="iteid" id="id" maxlength="15" onkeyup="sb()"></td></tr>
					<tr><td class="tdnote"><label class="note">※半角英数字15文字</label></td></tr>
					<tr><td><input type="email" name="mailAddress" placeholder="メールアドレス" class="ite" id="ma" onkeyup="sb()"></td></tr>
					<tr><td><input type="password" name="password" placeholder="パスワード" class="itepass" id="p1" onkeyup="sb()" ></td></tr>
					<tr><td class="tdnote"><label class="note">※4文字以上</label></td></tr>
					<tr><td><input type="password" name="password" placeholder="確認用パスワード" class="ite" id="p2" onkeyup="sb()"></td></tr>
					<tr><td><input type="submit" value="新規登録" class="isub" id="submit"></td></tr>
				<tr><td><hr></td></tr>
				<tr><td class="topage"><a href="login">既にアカウントをお持ちの方</a></td></tr>
			</table>
		</form>
	</body>
</html>
