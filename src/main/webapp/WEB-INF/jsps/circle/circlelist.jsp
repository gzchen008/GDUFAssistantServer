<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>广金圈</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/circle.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/myjquery.js"></script>
<body>
	<!--导航条-->
	<ul class=" nav nav-pills title">
	
		<li role="presentation"><a href="javascript:windows.close();">返回</a></li>
		<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/circle/addCirclePage">添加</a></li>
	</ul>
	<!--右上角头像-->
	<div class="mediv">
	
	<a href="queryMyNotifaction.action?myId=${sessionScope.myId}">
		<img id="me" src="${pageContext.request.contextPath}/img/head/<%=new java.util.Random().nextInt(11)%>.png"></a>
	</div>
	<c:forEach items="${sessionScope.circles}" var="circle">
		<div class="content">
			<!--头像和文字-->
			
			<div class="imagetext">
				<img class="other" src="${pageContext.request.contextPath}/img/head/<%=new java.util.Random().nextInt(11)%>.png">


				${circle.sender.stuId}<br> ${circle.content}
			</div>
			<!--照片-->
			
			
			
			<c:if test="${!empty circle.images }">
				<c:forEach items="${circle.images}" var="image">
					
						<a href="${pageContext.request.contextPath}/picture/${image.path}" class="apic"> <img
							src="${pageContext.request.contextPath}/picture/${image.path}" border="0" class="rounded-half" /></a>
							<!-- <img src="${pageContext.request.contextPath}/img/shadow.jpg" border="0" class="shadow" /> -->
					
				</c:forEach>
			</c:if>
		
			<div class="time">${circle.createTime}</div>
			<!--点赞-->

			<div id="sup${circle.tid}" class="one bottomline">
				<a href="javascript:void(0);"
					onclick="support(${circle.sender.id},${circle.tid})"> <img
					class="support" src="${pageContext.request.contextPath}/img/support.png" /></a>
				<c:forEach items="${circle.comments }" var="comment">
					<c:if test="${empty comment.text }">
				<!-- 如果点赞人是自己，则加一个span标签，方便后续取消点赞使用 -->
				<c:choose><c:when test="${comment.sender.stuId==sessionScope.myStuId}">
				<span id="mysup${circle.tid}" class="mysupport">${comment.sender.stuId}&nbsp</span></c:when>
				<c:otherwise>${comment.sender.stuId}&nbsp</c:otherwise>
				</c:choose>
				</c:if>
				</c:forEach>
			</div>
			<!--评论-->
			<c:forEach items="${circle.comments}" var="comment">
				<c:if test="${!empty comment.text}">
					<div class="one div${circle.tid } div2${comment.cid}">${comment.sender.stuId } &nbsp 回复
						${comment.receiver.stuId } ：<a href="javascript:void(0);"
							onclick="repCom(${comment.cid })">${comment.text}</a>
							<!-- 本人评论的内容删除 -->
							<c:if test="${comment.sender.stuId eq sessionScope.myStuId }">
							<a href="javaScript:void(0);" onclick="deleteCom(${comment.cid })" style="color:#A6A6A6">删除</a></c:if>
						<!-- 每条评论中的回复，默认隐藏 -->
						<div id="div${comment.cid }" style="display:none">
							<input onfocusout="disp(${comment.cid })"
								id="ccont${comment.cid}" type="text" class="form-control input" />
							<button class="btn btn-default "
								onclick="replcom(${comment.cid},${sessionScope.myId},${comment.sender.stuId},${comment.sender.id},${circle.tid})">回复</button>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<input id="ccont${circle.tid}" type="text" class="form-control input"
				placeholder="我也说一句……" />
			<button id="cid" class="btn btn-success "
				onclick="addcom(${sessionScope.myId},${circle.sender.stuId},${circle.sender.id},${circle.tid})">发送</button>


		</div>
	</c:forEach>
	<!-- 显示更多 -->
	<div class="morelist">
		<a href="javascript:void(0);" onclick="getmorelist()">显示更多</a>
	</div>

</body>
<script>

	var currentNum = 0;
	var j = jQuery;
	/* 异步请求，添加评论 */
	/* sid 发送者id，ssid发送者学号，rrid接收者的学号，rid接收者id，tid朋友圈序号 */
	function addcom(sid, rrid, rid, tid) {
		j(document).ready(
				function() {
					j.post("addCommentjson.action", {
						"comment.sender.id" : sid,
						"comment.receiver.id" : rid,
						"comment.text" : j("#ccont" + tid).val(),
						"comment.tid.tid" : tid
					}, function(data) {
						console.log("tid:"+tid);
						console.log("cid:"+data.cid);
						console.log("空：" + j(".div" + tid).val());
						if (j(".div" + tid).val() == null) {
							j("#sup" + tid).after(
									"<div class='one div2"+data.cid+"'>"
											+ ${sessionScope.myStuId} + " &nbsp回复 "
											+ rrid + "："
											+ j("#ccont" + tid).val()
											+ "<a href='javaScript:void(0);' onclick='deleteCom("+data.cid +")' style='color:#A6A6A6'>删除</a></div>");
							
							j("#ccont"+tid).val("");
						} else {
							j(".div" + tid).last().after(
									"<div class='one div2"+data.cid+"'>"
											+ ${sessionScope.myStuId} + " &nbsp回复 "
											+ rrid + "："
											+ j("#ccont" + tid).val()
											+ "<a href='javaScript:void(0);' onclick='deleteCom("+data.cid +")' style='color:#A6A6A6'>删除</a></div>");
							j("#ccont"+tid).val("");
						}
					});
				});
	}
	/* 回复评论 */
	function replcom(cid, sid, rrid, rid, tid) {
		j(document).ready(
				function() {
					j("#ccont" + cid).focus();
					j.post("addCommentjson.action", {
						"comment.sender.id" : sid,
						"comment.receiver.id" : rid,
						"comment.text" : j("#ccont" + cid).val(),
						"comment.tid.tid" : tid
					}, function() {
						j(".div" + tid).last().after(
								"<div class='one div"+tid+" div2"+cid+"'>" + ${sessionScope.myStuId}
										+ " &nbsp回复 " + rrid + "："
										+ j("#ccont" + cid).val() + "</div>");
						j("#div" + cid).css("display", "none");
					});
				});
	}
	/* 点赞 */
	function support(sid, tid) {
		j(document).ready(
				function() {
					j.post("addCommentjson.action", {
						"comment.sender.id" : ${sessionScope.myId},
						"comment.receiver.id" : sid,
						"comment.text" : "",
						"comment.tid.tid" : tid
					}, function(data) {
						if (data.result == "success") {
							j("#sup" + tid).last().append("<span id='mysup"+tid+"'>"+
									${sessionScope.myStuId} + "</span> ");
						}if(data.result=="fail"){
							alert("取消点赞");
							j("#mysup"+tid).remove();
						}
					});
				});
	}
	/* 回复评论,显示输入框 */
	function repCom(divid) {
		j(document).ready(function() {
			j("#div" + divid).css("display", "block");
			j("#ccont" + divid).focus();

		});
	}
	function disp(divid) {
		j(document).ready(function() {
			var content = j("#ccont" + divid).val();
			if (content == "") {
				j("#div" + divid).css("display", "none");
			} else {
				return false;
			}
		});
	}

	function getmorelist() {
		currentNum += 10;
		console.log(currentNum);
		j(document)
				.ready(
						function() {
							j
									.post(
											"queryCirclesJson.action",
											{
												"listnum" : currentNum
											},
											function(data) {
												var list = data.circleList;
												for (var i = 0; i < list.length; i++) {
													var
													str = "<div class='content'><div class='imagetext'><img class='other' src='${pageContext.request.contextPath}/img/img1.jpg'>"
															+ list[i].sender.stuId
															+ "<br> "
															+ list[i].content
															+ "</div>";
															/* str+="</div>"; */
													var images=list[i].images;
												/* 	照片 */
													if(0!=images.length){
														
														for(var k=0;k<images.length;k++){
 															str+="<a href='${pageContext.request.contextPath}/picture/"+images[k].path+"'><img src='${pageContext.request.contextPath}/picture/"+images[k].path+"' border='0' class='rounded-half' /></a>";
  															 console.log(images[k].path); 
													}
														}
													/* 时间 */
													str+="<div class='time'>"
													+ list[i].createTime
													+ "</div>";
													str+="<div id='sup"+list[i].tid+"' class='one bottomline'><a href='javascript:void(0);' onclick='support("+list[i].sender.id+","+list[i].tid+")'><img class='support' src='${pageContext.request.contextPath}/img/support.png' /></a>";
													/* 点赞 */
													var comments=list[i].comments;
													for(var k=0;k<comments.length;k++){
														if(comments[k].text==null||comments[k].text=="")
															/* str+=comments[k].sender.stuId; */
															/* 判断点赞是否是自己，如果是则加span */
															var sender=comments[k].sender.stuId;
															var mystuid="${sessionScope.myStuId}";
															if(sender==mystuid){
																str+="<span id='mysup"+list[i].tid+"' class='mysupport'>"+comments[k].sender.stuId+"&nbsp</span>";
															}else{
																str+=comments[k].sender.stuId+"&nbsp";
															}
															
													}
												str+="</div>";
												/* 评论 */
												for(var k=0;k<comments.length;k++){
													if(comments[k].text!=null&&comments[k]!="")
														/* console.log("text:"+comments[k].text); */
												
															str+="<div class='one div"+list[i].tid +" div2"+comments[k].cid+"'>"+comments[k].sender.stuId+" &nbsp回复 "+comments[k].receiver.stuId+"：<a href='javascript:void(0);' onclick='repCom("+comments[k].cid+" )'>"+comments[k].text+"</a>";
															 if(${sessionScope.myStuId}==comments[k].sender.stuId){ 
																str+="<a href='javaScript:void(0);' onclick='deleteCom("+comments[k].cid+")' style='color:#A6A6A6'>删除</a>";
															 } 
														str+="</div><div id='div"+comments[k].cid+"' style='display:none'><input onfocusout  ='disp("+comments[k].cid +")' id='ccont"+comments[k].cid+"' type='text' class='form-control input'/><button  class='btn btn-default' onclick='replcom("+comments[k].cid+","+${sessionScope.myId}+","+comments[k].sender.stuId+","+comments[k].sender.id+","+list[i].tid+")'>回复</button></div>";
												}
												
												str+="<input id='ccont"+list[i].tid+"' type='text' class='form-control input' placeholder='我也说一句……' /><button id='cid' class='btn btn-success 'onclick='addcom(${sessionScope.myId},"+list[i].sender.stuId+","+list[i].sender.id+","+list[i].tid+")'>发送</button>";
												
												str+="</div>";
													j(".content").last().after(
															str);
												}
												noFindImg();
											});
						});
		
	}
/* 	没有找到图片时加载的默认图片 */
	function noFindImg(){
		j(document).ready(function(){
			$(".rounded-half").error(function(){
				  $(this).attr({ src: "${pageContext.request.contextPath}/img/error.jpg", alt: "图片丢失"});
				  $(this).parent().attr({href:"javaScript:void(0);"});
				});
	});
	};
	noFindImg();	
	/* 删除自己的评论 */
	function deleteCom(cid){
		j(document).ready(function(){
			j.get("deleteCommentjson.action?cid="+cid,function(data){
				if(data.result=="success"){
					alert("删除成功");
					j(".div2"+cid).remove();
				}else
					alert("删除失败");
			});
		});
	}
	function closewind(){
		 //window.opener=null;
	     window.close();
	}
</script>
</html>