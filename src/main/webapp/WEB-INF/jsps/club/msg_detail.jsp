<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${pageContext.request.contextPath }/css/module/commons/bootstrap.min.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<!-- Fixed navbar -->
	<div class="header clearfix">
		<nav>
			<ul class="nav nav-pills pull-right">
				<li role="presentation" class="active"><a type="button"
					href="#" style="margin-right: 10px; margin-top: 5px"
					onclick="history.go(-1)">返回</a></li>
			</ul>
		</nav>
		<h3 class="text-muted">&nbsp;&nbsp;&nbsp;广金校园通</h3>
	</div>
	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<h2 style="display: inline; margin: 5px">${msg.mTitle }</h2>
			<div style="float: right; display: block">
				<a href="#" style="text-decoration: none;"><span
					style="display: block; text-align: right;">${msg.club.cName }</span></a><span>${msg.m_publish_date }
				</span>
			</div>
		</div>
		<div style="margin: 12px">
			<p class="lead" style="">${msg.mContent }</p>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath }/js/module/commons/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/module/commons/bootstrap.min.js"></script>
	<script>
		$(function() {
			$("img").css("width", "200px").css("height", "200px");
		});
	</script>

</body>
</html>