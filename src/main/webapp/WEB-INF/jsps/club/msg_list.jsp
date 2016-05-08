<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="MobileOptimized" content="240">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
<link
	href="${pageContext.request.contextPath }/css/module/mobile/commons.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/css/module/mobile/wap.css"
	type="text/css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/module/mobile/css.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/module/club/club.js"></script>
<title>广金校园通</title>
<style>
.over2 a {
	background-color: #fadda5 !important;
	color: #000 !important;
	border-radius: 15px !important;
	padding: 5px 20px !important;
}

.over2 a:visited {
	background-color: #fadda5 !important;
	color: #000 !important;
	border-radius: 15px !important;
	padding: 5px 20px !important;
}
</style>
</head>
<body>


	<ul class="ylmenu" style="background-color: black;">
		<!-- 		<li class="ylmenu1"><a href="index.html"><span>首页</span></a></li>
		<li class="ylmenu2"><a href="jianjie.html"><span>公司</span></a></li>
		<li class="ylmenu3"><a href="liebiao.html"><span>产品</span></a></li>
		<li class="ylmenu4"><a href="fuwu.html"><span>服务</span></a></li> -->
	</ul>

	<div class="clear"></div>
	<ul class="tjlist">
		<c:forEach items="${msgList }" var="msg">
			<li><a
				href="${pageContext.request.contextPath }/msg/clubMsgDetail.action?mid=${msg.mid}"><img
					class="img76" src="${image }" alt="" width="76" height="76">${msg.mTitle }
					<div class="jiantou"></div> </a></li>
		</c:forEach>
	</ul>
	<div class="pagelist">
		<ul>
			<c:if test="${!empty pager }">
				<c:if test="${pager.hasPre }">
					<li><a
						href="${pageContext.request.contextPath }/msg/clubMsgList.action?curPage=${pager.curPage -1}">上一页</a></li>
				</c:if>
				<c:if test="${pager.hasNext }">
					<li><a
						href="${pageContext.request.contextPath }/msg/clubMsgList.action?curPage=${pager.curPage +1}">下一页</a></li>
				</c:if>
			</c:if>
		</ul>
	</div>
	<div class="clear"></div>

</body>
</html>