<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>消息列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="../js/jquery.min.js"></script>
<link rel="stylesheet" href="../css/circle.css" type="text/css">
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
</head>

<body>
	<h4>我的通知</h4>
	<c:forEach items="${requestScope.myNotifaction}" var="notifaction">
	<div class="content"><h3>${notifaction.tid.content }	</h3>
	<c:if test="${!empty notifaction.tid.images }">
	<img class="littlepicture"
				class="rounded-half"
				src="../picture/${notifaction.tid.images[0].path}">
				
	</c:if>
			<div class="imagetext">
				<img class="other" src="../img/head/<%=new java.util.Random().nextInt(11)%>.png">
				${notifaction.sender.stuId }&nbsp回复您： ${notifaction.mes}
			<!-- 回复框  -->
		<input id="ccont${notifaction.tid.tid}" type="text" class="form-control input"
				placeholder="我要回复……" />
			<button id="cid" class="btn btn-success "
				onclick="addcom(${sessionScope.myId},${notifaction.tid.sender.stuId},${notifaction.tid.sender.id},${notifaction.tid.tid})">回复</button>
			</div>
		</div>
		
		
	</c:forEach>
	
	
</body>
<script>
var j = jQuery;
	/* 异步请求，添加评论 */
	/* sid 发送者id，ssid发送者学号，rrid接收者的学号，rid接收者id，tid朋友圈序号 */
	function addcom(sid, rrid, rid, tid) {
		j(document).ready(function() {
					j.post("addCommentjson.action", {
						"comment.sender.id" : sid,
						"comment.receiver.id" : rid,
						"comment.text" : j("#ccont" + tid).val(),
						"comment.tid.tid" : tid
					}, function(data) {
						console.log("tid:"+tid);
						console.log("cid:"+data.cid);
						console.log("空：" + j(".div" + tid).val());
						j("#ccont"+tid).prev().after("<p>我回复"+rrid+": "+j("#ccont" + tid).val()+"</p>");
						
					});
				});
	}
</script>
</html>
