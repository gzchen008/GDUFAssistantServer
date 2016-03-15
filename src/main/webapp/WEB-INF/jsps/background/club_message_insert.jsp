<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<!-- 编辑器配置文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/clubMessage.js"></script>
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
						class="crumb-step">&gt;</span><span>新增消息</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form
						action="${pageContext.request.contextPath}/clubMessage_insertNew.action"
						method="post" id="myform" name="myform"
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
										name="message_title" size="50" value="" type="text"></td>
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
											</script><span style="color: red;">${requestScope.error }</span></td>
								<tr>
									<th></th>
									<td><input class="btn btn-primary btn6 mr10" value="提交"
										type="submit"> <input class="btn btn6"
										onclick="history.go(-1)" value="返回" type="button"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>

		</div>

	</div>
</body>
</html>