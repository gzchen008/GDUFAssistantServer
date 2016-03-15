<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>社团注册页</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/club_register.css"
	type="text/css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->


<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<!-- 社团管理员注册JS文件 -->
<script
	src="${pageContext.request.contextPath }/js/libs/club_register.js"></script>
</head>
<body>
	<div class="container">
		<h1>社团管理员注册</h1>

		<form action="${pageContext.request.contextPath }/club/club_regist.action"
			id="regiester_form" method="post">
			<div class="form-group">
				<label for="admin_name">社团账号(邮箱)</label> <input type="email"
					class="form-control" id="admin_name" name="admin.admin_name"
					placeholder="请输入邮箱">
					
			</div>
			<div class="form-group">
				<label for="admin_password">登录密码</label> <input type="password"
					class="form-control" id="admin_password"
					name="admin.admin_password" placeholder="请输入登录密码">
			</div>
			<div class="form-group">
				<label for="rePassword">重复密码</label> <input type="password"
					class="form-control" id="rePassword" name="rePassword"
					placeholder="请重复输入密码">

			</div>
			<div class="form-group">
				<label for="club_name">社团名称</label> <input type="text"
					class="form-control" id="club_name" name="club.club_name"
					placeholder="请输入社团名称">
			</div>
			<div class="form-group">
				<div>
					<!-- 社团头像-->
				</div>
			</div>
			<div class="form-group">
				<label for="club_summary">社团简介</label>
				<textarea class="form-control" id="club_summary" rows="3"
					name="club.club_summary" placeholder="请输入社团的简要介绍信息"></textarea>
			</div>
			<!-- 	<div class="form-group">
				<label for="club_setup">成立时间</label> <input type="date"
					class="form-control" id="club_setup" name="club.club_setup">
			</div>
			 -->
			<!-- 验证码-->
			<div class="form-group">
				<label for="validateCode">验证码</label><br /> <input type="text"
					id="validateCode" class="form-control" name="validateCode"
					style="display: inline;width: 150px"> <img
					src="${pageContext.request.contextPath }/club/autoCodeAction"
					style="height: 34px;margin-left: 20px" id="validateImg">
				<div class="info">
					<span style="color: red">${requestScope.errror}</span>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" value="注册"
					class="btn btn-primary btn-lg active" id="register_submit"
					style="width: 100%">
			</div>
		</form>
	</div>

</body>
</html>