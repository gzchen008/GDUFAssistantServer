<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>广金校园通后台管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/club_message_check.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<style type="text/css">
body {
	padding: 0;
	margin: 0;
}

#topics .title {
	font-size: 250%;
	font-weight: bold;
	border-bottom: 1px solid #999;
	float: left;
	text-align: center;
	line-height: 2em;
	width: 100%;
	padding-left: 5px;
}

h1 {
	display: block;
	font-size: 2em;
	-webkit-margin-before: 0.67em;
	-webkit-margin-after: 0.67em;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
	font-weight: bold;
}

.time-soure {
	float: right;
	height: 50px;
	line-height: 50px;
	font-family: Arial, "\5B8B\4F53", sans-serif;
	color: #888888;
	font-size: 12px;
	margin-right: 80px;
}

p {
	display: block;
	-webkit-margin-before: 1em;
	-webkit-margin-after: 1em;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
}

.msg_content {
	margin: 10px auto;
	text-indent: 0;
	color: #000;
	line-height: 1.5;
	margin: 10px auto;
	color: #000;
	text-align: center;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
</head>

<body>
	<div class="topbar-wrap white">
		<%@ include file="topbar.jsp"%>
	</div>
	<div class="container clearfix">
		<div class="sidebar-wrap">
			<%@ include file="sidebar.jsp"%>
		</div>
		<!--/sidebar-->
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a
						href="${pageContext.request.contextPath }/admin_backToIndex.action">首页</a><span
						class="crumb-step">&gt;</span><a class="crumb-name"
						href="${pageContext.request.contextPath}/clubMessage_updateList.action">社团动态</a><span
						class="crumb-step">&gt;</span><span>动态详情</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content"></div>
				<div id="topics">
					<div class="title">
						<h2>${requestScope.clubMessage.message_title }</h2>
						<div class="time-soure">
							<span>来源:${requestScope.clubMessage.club.club_name }</span><span
								style="color: red;">
								${requestScope.clubMessage.message_publishdate } </span>
						</div>
					</div>

					<div class="msg_content">
						&nbsp;<br /> ${requestScope.clubMessage.message_content }
					</div>
				</div>

			</div>
			<!--/main-->
		</div>
</body>
</html>