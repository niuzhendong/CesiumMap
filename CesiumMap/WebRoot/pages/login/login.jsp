<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en" class="no-js"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>login</title>
<link rel="stylesheet" type="text/css" href="./asserts/css/pages/login/normalize.css">
<link rel="stylesheet" type="text/css" href="./asserts/css/pages/login/demo.css">
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="./asserts/css/pages/login/component.css">
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header" style="height: 613px;">
					<canvas id="demo-canvas" width="1366" height="613"></canvas>
					<div class="logo_box">
						<h3>欢迎你</h3>
						<form action="./rest/im/login" method="POST">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" value="" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2">
								<input class="act-but submit" type="submit" value="登录" style="color: #FFFFFF ; width: 100%"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="./asserts/js/plugin/jquery.js"></script>
		<script src="./asserts/js/pages/login/TweenLite.min.js"></script>
		<script src="./asserts/js/pages/login/EasePack.min.js"></script>
		<script src="./asserts/js/pages/login/rAF.js"></script>
		<script src="./asserts/js/pages/login/demo-1.js"></script>
		<script src="./asserts/js/pages/login/login.js"></script>
</body></html>
