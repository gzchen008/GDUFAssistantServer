<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<s:debug />
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<base href="${pageContext.request.contextPath }">

<title>广金校园通</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/css/module/commons/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/css/module/commons/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/css/module/commons/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/css/module/commons/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/css/module/upload/webuploader.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/css/module/commons/mystyle.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<jsp:include page="navbar.jsp" />
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h4 class="page-header">新增消息</h4>
						<!-- 	<div class="panel panel-default">
							<div class="panel-heading"></div>
						</div> -->
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<form
									action="${pageContext.request.contextPath }/msg/addClubMsg.action"
									method="post">
									<div class="form-group row">
										<label for="club_name" class="col-sm-2 control-label">消息标题</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="mTitle"
												placeholder="消息标题">
										</div>
									</div>
									<div class="form-group row">
										<label for="club_name" class="col-sm-2 control-label">消息标题</label>
										<div class="col-sm-6">
											<input type="url" class="form-control" name="icon"
												placeholder="封面图片">
										</div>
									</div>
									<div class="form-group row">
										<label for="" class="col-sm-2 control-label">选择作者</label>
										<div class="col-sm-5">
											<select class="from-control input-sm" name="clubId"
												style="width: 100%; height: 30px">
												<option value="">&nbsp;</option>
												<c:forEach items="${request.clubs }" var="club">
													<option value="${club.cid }">${club.cName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label for="" class="col-sm-2 control-label">选择消息类型</label>
										<div class="col-sm-5">
											<select class="from-control input-sm"
												style="width: 100%; height: 30px" name="type">
												<option value="1">社团消息</option>
												<option value="2">兼职就业信息</option>
												<option value="3">失物招领信息</option>
											</select>
										</div>
									</div>
							</div>
							<div class="form-group row">
								<label for="file-desc" class="col-sm-2 control-label">消息内容</label>
								<div class="col-sm-6">
									<script id="container" type="text/plain">
  									   这里写你的初始化内容
    								</script>
									<input type="hidden" name="content">
								</div>
							</div>
							<div class="form-group row">
								<label for="file-desc" class="col-sm-2 control-label"> </label>
								<div class="col-sm-6">
									<button class="btn btn-primary" type="submit">新增</button>
									<button class="btn btn-default" type="button">返回</button>
								</div>
							</div>
							</form>

						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
		<input id="url" type="hidden"
			value="${pageContext.request.contextPath}/club/manager.action" />
	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/jquery.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.min.js">
		
	</script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath }/js/ueditor/lang/zh-cn/zh-cn.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/sb-admin-2.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/module/upload/webuploader.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/module/upload/upload.js"></script>

	<script>
		var ue = UE.getEditor('container', {
			toolbars : [ [ 'undo', //撤销
			'redo', //重做
			'bold', //加粗
			'indent', //首行缩进
			'italic', //斜体
			'subscript', //下标
			'superscript', //上标
			'preview', //预览
			'time', //时间
			'date', //日期
			'cleardoc', //清空文档
			'fontfamily', //字体
			'fontsize', //字号
			'paragraph', //段落格式
			'simpleupload', //单图上传
			'emotion', //表情
			'justifyleft', //居左对齐
			'justifyright', //居右对齐
			'justifycenter', //居中对齐
			'forecolor', //字体颜色
			'autotypeset', //自动排版
			] ],
			initialFrameHeight : 200
		});
		$("button[type=submit]").on("click", function() {
			var html = ue.getContent();
			$("input[name=content]").val(html);
			console.info(html);
		});
	</script>
</body>
</html>
