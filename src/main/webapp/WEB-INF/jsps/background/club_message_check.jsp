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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
</head>
<script type="text/javascript">
	function checkpass(g) {
		var gValue = g.value;
		var unpass = document.getElementById("unpass");
		var th = unpass.getElementsByTagName("th");
		var td = unpass.getElementsByTagName("td");
		if (gValue == -1) {
			th[0].innerHTML = "理由";
			td[0].innerHTML = "<textarea cols='100' rows='6' name='reason'>请输入理由</textarea>";
		} else if (gValue == 1) {
			th[0].innerHTML = "";
			td[0].innerHTML = "";
		}
	}
</script>
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
						class="crumb-step">&gt;</span><span>审核消息</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form
						action="${pageContext.request.contextPath}/clubMessage_checkOver.action?message_id=${requestScope.clubMessage.id }"
						method="post" id="myform" name="myform"
						enctype="multipart/form-data">
						<table class="insert-tab" width="100%">
							<tbody>
								<tr>
									<th width="120">文章：</th>
									<td><div class="MessageDtail">
											<h1>${requestScope.clubMessage.message_title }</h1>
											<span class="time-source">
												${requestScope.clubMessage.club.club_name } <br />
												${requestScope.clubMessage.message_publishdate }
											</span>
											<hr />
											<p>${requestScope.clubMessage.message_content }</p>
										</div></td>
								</tr>
								<tr>
									<th>审核情况：</th>
									<td>通过&nbsp;<input type="radio" name="checkMsg" value="1"
										checked="checked" onclick="checkpass(checkMsg);" />&nbsp;|&nbsp;不通过<input
										type="radio" name="checkMsg" value="-1"
										onclick="checkpass(checkMsg);" />
									</td>
								</tr>
								<tr id="unpass">
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th></th>
									<td><input class="btn btn-primary btn6 mr10" value="确定"
										type="submit"> <input class="btn btn6"
										onclick="history.go(-1)" value="返回" type="button"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

			</div>

		</div>
		<!--/main-->
	</div>
</body>
</html>