<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>广金校园通管理后台</title>

<!-- Bootstrap core CSS -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/signin.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/js/libs/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="${pageContext.request.contextPath}/js/libs/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="header">
		<a href="${pageContext.request.contextPath }/club/club_registerPage.action">注册页</a>
	</div>
	<div class="container">

		<form class="form-signin"
			action="${pageContext.request.contextPath }/club/admin_loginIn.action"
			method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<label for="admin_name" class="sr-only">Email address</label> <input
				type="text" name="admin_name" id="admin_name" class="form-control"
				placeholder="管理员账号" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" name="admin_password"
				class="form-control" placeholder="密码" required>

			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住密码
				</label> <label><a
					href="${pageContext.request.contextPath }/club/admin_findPwdPage">忘记密码</a></label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->
	<div>
		<s:actionmessage />
	</div>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="${pageContext.request.contextPath}/js/libs/ie10-viewport-bug-workaround.js"></script>
</body>
</html>