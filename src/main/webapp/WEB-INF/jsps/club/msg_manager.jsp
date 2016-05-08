<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

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
						<h4 class="page-header">社团管理</h4>
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class=row>
									<div class="title">
										<p class="title">社团管理</p>
									</div>
									<div class="options ">
										<a class="btn btn-primary"
											href="${pageContext.request.contextPath }/msg/addClubMsg.action"><span
											class="glyphicon glyphicon-plus"></span>新增消息</a> <a id="editor"
											class="btn btn-primary"><span
											class="glyphicon glyphicon-pencil"></span>编辑</a><a id="delete"
											class="btn btn-primary"><span
											class="glyphicon glyphicon-trash"></span>删除</a>
										<!-- <a
											class="btn btn-primary"><span
											class="glyphicon glyphicon-refresh"></span>刷新</a> -->
									</div>
								</div>
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<div id="dataTables-example_wrapper"
										class="dataTables_wrapper form-inline dt-bootstrap no-footer">
										<div class="row">
											<div class="col-sm-2">
												<select class="form-control input-sm">
													<option value="#">请选择</option>
													<option value="#">全部</option>
													<option value="#">活动</option>
													<option value="#">冻结</option>
												</select>
											</div>
											<div class="col-sm-6 calendar">
												<label>日期</label> <span class="glyphicon glyphicon-calendar"></span><input
													type="date" /><span class="calendar-text">到</span><input
													type="date" />
											</div>
										</div>
										<br />
										<div class="row">
											<div class="col-sm-12">
												<table
													class="table table-striped table-bordered table-hover dataTable no-footer"
													id="dataTables-example" role="grid"
													aria-describedby="dataTables-example_info">
													<thead>
														<tr role="row">
															<th class="" tabindex="0"
																aria-controls="dataTables-example" rowspan="1"
																colspan="1"
																aria-label="Rendering engine: activate to sort column ascending"
																aria-sort="descending" style="width: 10px;"><input
																type="checkbox" name="" /></th>
															<th class="sorting_desc" tabindex="0"
																aria-controls="dataTables-example" rowspan="1"
																colspan="1"
																aria-label="Rendering engine: activate to sort column ascending"
																aria-sort="descending" style="width: 163px;">消息标题</th>
															<th class="sorting" tabindex="0"
																aria-controls="dataTables-example" rowspan="1"
																colspan="1"
																aria-label="Browser: activate to sort column ascending"
																style="width: 190px;">发布者</th>
															<th class="sorting" tabindex="0"
																aria-controls="dataTables-example" rowspan="1"
																colspan="1"
																aria-label="Browser: activate to sort column ascending"
																style="width: 190px;">类型</th>
															<th class="sorting" tabindex="0"
																aria-controls="dataTables-example" rowspan="1"
																colspan="1"
																aria-label="Platform(s): activate to sort column ascending"
																style="width: 173px;">发布时间</th>
														</tr>
													</thead>
													<tbody>

														<c:if test="${empty msgList }">

															<tr class="gradeA odd" role="row">
																<td colspan="6" class="center">没有相关的社团记录</td>
															</tr>

														</c:if>
														<c:if test="${!empty msgList}">
															<c:forEach items="${msgList }" var="msg">
																<tr class="gradeA odd" role="row">
																	<td><input type="checkbox" name="msg_id"
																		value="${msg.mid }" /></td>
																	<td><a
																		href="${pageContext.request.contextPath }/msg/clubMsgDetail.action?mid=${msg.mid}">${msg.mTitle }</a></td>
																	<td>${msg.club.admin.aName }</td>
																	<td><c:if test="${msg.type == 1 }">社团动态</c:if> <c:if
																			test="${msg.type == 2 }">兼职就业</c:if></td>
																	<td>${msg.m_publish_date }</td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="dataTables_info" id="dataTables-example_info"
													role="status" aria-live="polite">
													<%-- 显示 &nbsp;<select
														class="form-control input-sm" id="size">
														<option value="3">10</option>
														<option value="4">50</option>
														<option value="6">100</option>
													</select>&nbsp; 条记录 --%>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="dataTables_paginate paging_simple_numbers"
													id="dataTables-example_paginate">
													<c:if test="${pager.totalPage >0}">
														<ul class="pagination">
															<c:if test="${pager.hasPre }">
																<li><a
																	href="${pageContext.request.contextPath }/msg/manager.action?curPage=${pager.curPage -1}">上一页</a></li>
															</c:if>
															<c:if test="${pager.hasNext }">
																<li><a
																	href="${pageContext.request.contextPath }/msg/manager.action?curPage=${pager.curPage +1}">下一页</a></li>
															</c:if>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
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
		<!-- /#page-wrapper -->
	</div>

	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/sb-admin-2.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/module/commons/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/module/commons/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/module/club/club.js"></script>
	<script>
		$(function() {
			$('#editor')
					.bind(
							'click',
							function() {
								var url = "";
								var flag = false;
								$("input[name=msg_id]")
										.each(
												function() {
													console.info($(this).is(
															':checked'));
													if ($(this).is(':checked')) {
														id = $(this).val();
														url = "${pageContext.request.contextPath}/club/updateClub.action?cid="
																+ id;
														flag = true;
														return false;
													}
												});

								if (flag) {
									location.href = url;
								} else {
									alert("没有选中任何记录");
								}
							});
			$("#delete")
					.bind(
							"click",
							function() {
								var url = "${pageContext.request.contextPath}/msg/delete.action";
								var array = new Array();
								var index = 0;
								$("input[name=msg_id]").each(function() {
									console.info($(this).is(':checked'));
									if ($(this).is(':checked')) {
										array[index] = $(this).val();
										index++;
									}
								});
								console.info(array.length);
								console.info(array);
								if (array.length > 0) {
									$
											.ajax({
												url : url,
												data : {
													list : array.toString()
												},
												method : 'POST',
												success : function() {
													window.href = "${pageContext.request.contextPath}/assistant/msg/manager.action";
												}
											});
								} else {
									alert("没有选中任何记录");
								}
							});
		});
	</script>
</body>
</html>
