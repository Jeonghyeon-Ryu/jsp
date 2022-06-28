<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		* {
			margin: 0px;
			padding: 0px;
			text-decoration: none;
			font-family:sans-serif;
		}
	
		body {
			background-image:#34495e;
		}
	
		.signUpForm {
			position:absolute;
			width:400px;
			height:400px;
			padding: 30px, 20px;
			background-color:#FFFFFF;
			text-align:center;
			top:30%;
			left:50%;
			transform: translate(-50%,-50%);
			border-radius: 15px;
		}
	
		.signUpForm h2 {
			text-align: center;
			margin: 30px;
		}
	
		.textForm {
			border-bottom: 2px solid #adadad;
			margin: 30px;
			padding: 10px 10px;
		}
	
	
		.id {
			width: 100%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
		}
	
		.pw {
			width: 100%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
		}
	
		.name {
			width: 100%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
		}
	
		.email {
			width: 100%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
		}
		.birth {
			display:flex;
		}
		.birthday {
			width: 18%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
			text-align: center;
		}
		.birthday.first {
			float:left;
			border:1px solid rgba(0,0,0,0.3);
		}
		.birthday.second {
			float:left;
			margin-left:5px;
			width:3%;
			border:1px solid rgba(0,0,0,0.3);
		}
	
		.cellphoneNo {
			width: 100%;
			border:none;
			outline:none;
			color: #636e72;
			font-size:16px;
			height:25px;
			background: none;
		}
	
		.btn {
			position:relative;
			left:40%;
			transform: translateX(-50%);
			margin-bottom: 40px;
			width:80%;
			height:40px;
			background: linear-gradient(125deg,yellow,white,red);
			background-position: left;
			background-size: 200%;
			color:black;
			font-weight: bold;
			border:none;
			cursor:pointer;
			transition: 0.4s;
			display:inline;
		}
	
		.btn:hover {
			background-position: right;
		}
	</style>
	<script src="signup.js"></script>
</head>
<body>
	<form action="signupAction.jsp" method="POST" class="signUpForm" onsubmit="return checkAll()">
		<h2>회원가입</h2>
		<div class="textForm">
			<input name="id" type="text" class="id" placeholder="아이디" onchange="checkID()">
		</div>
		<div class="textForm">
			<input name="pw" type="password" class="pw" placeholder="비밀번호">
		</div>
		<div class="textForm">
			<input name="repw" type="password" class="pw" placeholder="비밀번호 확인" onchange="checkPw()">
		</div>
		<div class="textForm">
			<input name="name" type="text" class="name" placeholder="이름">
		</div>
		<div class="textForm birth">
			<input name="birth" type="text" class="birthday first" placeholder="901111" maxlength="6">
			<label>-</label>
			<input name="birth2" type="text" class="birthday second" placeholder="1" maxlength="1">
			<label>＊＊＊＊＊＊</label>
		</div>
		<div class="textForm">
			<input name="email" type="text" class="email" placeholder="이메일">
		</div>
		<div class="textForm">
			<input name="phone" type="text" class="cellphoneNo" placeholder="전화번호">
		</div>
		<input type="submit" class="btn" value="J O I N" />
	</form>
</body>
</html>