<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
						<h4 class="page-header">新增社团</h4>
						<!-- 	<div class="panel panel-default">
							<div class="panel-heading"></div>
						</div> -->
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<form
									action="${pageContext.request.contextPath}/club/addClub.action"
									method="post">
									<div class="form-group row">
										<label for="club_name" class="col-sm-2 control-label">社团名称</label>
										<div class="col-sm-6">
											<input type="text" name="cName" class="form-control"
												id="club_name" placeholder="社团名称" value="${club.cName}">
										</div>
									</div>
									<div class="form-group row">
										<label for="" class="col-sm-2 control-label">社团负责人</label>
										<div class="col-sm-3">
											<select class="from-control input-sm" name="adminName"
												style="width: 100%; height: 30px">
												<c:if test="${empty request.admins }">
													<option value="1">超级管理员</option>
												</c:if>
												<c:if test="${!empty request.admins }">
													<c:forEach items="${request.admins }" var="admin">
														<c:if test="${admin.aid eq club.admin.aid }">
															<option value="${admin.aid }" selected="selected">${admin.aName }</option>
														</c:if>
														<c:if test="${admin.aid ne club.admin.aid }">
															<option value="${admin.aid }">${admin.aName }</option>
														</c:if>
													</c:forEach>
												</c:if>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label for="file-desc" class="col-sm-2 control-label">社团描述</label>
										<div class="col-sm-6">
											<textarea class="form-control" rows="5" style="resize: none"
												placeholder="请输入社团描述" name="cdescribe">${club.cdescribe }</textarea>
										</div>
									</div>
									<div class="form-group row">
										<label for="file-desc" class="col-sm-2 control-label">
										</label>
										<div class="col-sm-6">
											<button class="btn btn-primary" type="submit">新增</button>
											<button class="btn btn-default" type="button"
												onclick="history.go(-1)">返回</button>
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
</body>
</html>
