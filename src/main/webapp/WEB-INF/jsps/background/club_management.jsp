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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/libs/clubMessage.js"></script>
<script type="text/javascript">
	function load() {
		var select = document.getElementById("search-sort");
		var state = '${param.state}';
		for (var i = 0; i < select.length; i++) {
			if (select.options[i].value == state) {
				select.options[i].selected = true;
				break;
			}
		}

	}
</script>

</head>
<body onload="load();">
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
						class="crumb-step">&gt;</span><span class="crumb-name">社团动态</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form id="headform" action="#" method="post">
						<table class="search-tab">
							<tr>
								<th width="120">选择分类:</th>
								<td><select name="search-sort" id="search-sort"
									onchange="updateList();">
										<option value="#">请选择</option>
										<option value="-1">全部</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
										<option value="2">不通过</option>
								</select></td>
								<th width="70">关键字:</th>
								<td><input class="common-text" placeholder="关键字"
									name="keywords" value="" id="" type="text"></td>
								<td><input class="btn btn-primary btn2" name="sub"
									value="查询" type="submit"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="myform" id="myform" method="post" action="#">
					<div class="result-title">
						<div class="result-list">
							<a
								href="${pageContext.request.contextPath }/clubMessage_insertPage.action"><i
								class="icon-font"></i>发布消息</a> <a id="batchDel"
								href="javascript:deleteItem(-1)"><i class="icon-font"></i>批量删除</a>
							<!-- <a id="updateOrd" href="javascript:void(0)"> <i
								class="icon-font"> </i>审核消息
							</a> -->

						</div>
					</div>
					<div class="result-content">
						<table id="table" class="result-tab" width="100%">
							<tr>
								<th class="tc" width="5%"><input class="allChoose"
									id="allChose" type="checkbox" onclick="allSelect()"></th>
								<th>ID</th>
								<th>标题</th>
								<th>审核状态</th>
								<th>发布社团</th>
								<th>更新时间</th>
								<th>操作</th>

								<s:iterator value="list" id="item">
									<tr>
										<td class="tc"><input name="clubMessage_id"
											value="<s:property value='id' />" type="checkbox"></td>
										<td><s:property value='id' /></td>
										<td title="<s:property value='message_title' />"><a
											target="_blank"
											href="${pageContext.request.contextPath }/clubMessage_readMessage?id=<s:property value='id' />"
											title="<s:property value='message_title' />"><s:property
													value='message_title' /></a> …</td>
										<td><s:if test="message_state==1">
											通过
											</s:if> <s:elseif test="message_state==0">未审核</s:elseif> <s:elseif
												test="message_state==2">不通过</s:elseif></td>
										<td><s:property value="club.club_name" /></td>
										<td><s:property value="message_publishdate" /></td>
										<td><a class="link-update"
											href="${pageContext.request.contextPath }/clubMessage_updateMessagePage?id=<s:property value='id' />">修改</a>
											<a class="link-del"
											href="javascript:deleteItem(<s:property value='id' />)">删除</a>
											<s:if test="message_state==0||message_state==2">
												<a
													href="${pageContext.request.contextPath }/clubMessage_checkMessagePage?message_id=<s:property value='id' />">审核</a>
											</s:if></td>
									</tr>
								</s:iterator>
						</table>
						<div class="list-page">
							<table>
								<s:iterator value="pageBean">
									<tr>

										<td colspan="6">共<s:property value="allRow" />条记录 共<s:property
												value="totalPage" />页 当前第<s:property value="currentPage" />页<br />
											<s:if test="%{currentPage==1}">
													第一页    上一页
												</s:if> <s:else>
												<a href="clubMessage_updateList.action?page=1">第一页</a>
												<a
													href="clubMessage_updateList.action?page=<s:property value="%{currentPage-1}"/>">
													上一页</a>
											</s:else> <s:if test="%{currentPage!=totalPage}">
												<a
													href="clubMessage_updateList.action?page=<s:property value="%{currentPage+1}"/>">
													下一页</a>
												<a
													href="clubMessage_updateList.action?page=<s:property value="totalPage"/>">最后一页</a>
											</s:if> <s:else>
												下一页  最后一页
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--/main-->
	</div>
</body>
</html>