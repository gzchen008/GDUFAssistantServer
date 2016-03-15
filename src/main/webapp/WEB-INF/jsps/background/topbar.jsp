<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="topbar-inner clearfix">
		<div class="topbar-logo-wrap clearfix">
			<h1 class="topbar-logo none">
				<a href="index.html" class="navbar-brand">后台管理</a>
			</h1>
			<ul class="navbar-list clearfix">
				<!-- 	<li><a class="on" href="index.html">首页</a></li> -->
				<li><a
					href="${pageContext.request.contextPath }/admin_backToIndex.action">网站首页</a></li>
			</ul>
		</div>
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<li><a href="#">${sessionScope.admin.admin_name }</a></li>
				<li><a href="#">修改密码</a></li>
				<li><a href="admin_exit.action">退出</a></li>
			</ul>
		</div>
	</div>
</body>
</html>