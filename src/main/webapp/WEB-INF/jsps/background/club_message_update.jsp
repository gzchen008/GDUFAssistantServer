<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>广金校园通后台管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
<!-- 配置文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/clubMessage.js"></script>
<script type="text/javascript">
	function getselectElement() {
		var xmlhttp = createHttpXML();
		xmlhttp.open("GET",
				"${pageContext.request.contextPath}/club_getAllClubs.action",
				true);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var returned = xmlhttp.responseText;
				var obj = eval(returned);
				var select = document.getElementById("catid");
				for (var i = 0; i < obj.length; i++) {
					var option = document.createElement("option");
					option.appendChild(document
							.createTextNode(obj[i].club_name));
					option.setAttribute("value", obj[i].id);
					select.appendChild(option);
				}
				var club_id = '${requestScope.clubMessage.club.id}';
				for (var i = 0; i < select.length; i++) {
					if (select.options[i].value == club_id) {
						select.options[i].selected = true;
						break;
					}
				}
			}
		}

		xmlhttp.send(null);
	}
</script>
</head>
<body onload="getselectElement()">
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
					<i class="icon-font"></i><a
						href="${pageContext.request.contextPath }/admin_backToIndex.action">首页</a><span
						class="crumb-step">&gt;</span><a class="crumb-name"
						href="${pageContext.request.contextPath}/clubMessage_updateList.action">社团动态</a><span
						class="crumb-step">&gt;</span><span>更改消息</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form action="#" method="post" id="myform" name="myform"
						enctype="multipart/form-data"
						onsubmit="return checkTheForm(myform);">
						<table class="insert-tab" width="100%">

							<tbody>
								<tr>
									<th width="120"><i class="require-red">*</i>社团：</th>
									<td><select name="club_id" id="catid" class="required">
											<option value="-1">请选择</option>
									</select></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>标题：</th>
									<td><input class="common-text required" id="title"
										name="message_title" size="50"
										value="${requestScope.clubMessage.message_title}" type="text"></td>
								</tr>

								<tr>
									<th>作者：</th>
									<td><input class="common-text" name="author" size="50"
										value="admin" type="text" readonly="readonly"></td>
								</tr>
								<tr>
									<th>内容：</th>
									<td><script id="container" name="message_content"
											type="text/plain"></script> <!-- 实例化编辑器 --> <script
											type="text/javascript">
												var ue = UE
														.getEditor(
																'container',
																{
																	toolbars : [
																			[
																					'source',
																					'undo',
																					'redo',
																					'simpleupload' ],
																			[
																					'bold',
																					'italic',
																					'underline',
																					'fontborder',
																					'strikethrough',
																					'superscript',
																					'subscript',
																					'removeformat',
																					'formatmatch',
																					'blockquote',
																					'pasteplain',
																					'|',
																					'forecolor',
																					'backcolor',
																					'insertorderedlist',
																					'insertunorderedlist',
																					'selectall',
																					'cleardoc' ] ],
																	autoHeightEnabled : true,
																	autoFloatEnabled : true,
																	initialFrameWidth : 700,
																	initialFrameHeight : 300
																});
												var contentText = '${requestScope.clubMessage.message_content}';
												ue.ready(function() {
													ue.setContent(contentText);
												});
											</script><span style="color: red;">${requestScope.error }</span></td>
								</tr>
								<tr>
									<th></th>
									<td><input class="btn btn-primary btn6 mr10" value="提交"
										type="submit"
										onclick="updateMessage(${requestScope.clubMessage.id});">
										<input class="btn btn6" onclick="history.go(-1);" value="返回"
										type="button"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<s:property value="message_content" />
		<!--/main-->

	</div>
</body>
</html>