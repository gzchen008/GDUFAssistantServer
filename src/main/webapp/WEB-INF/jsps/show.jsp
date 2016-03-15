<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>测试用
</head>
<body>
	<h1>系统消息页</h1>
	<s:debug />
	<s:actionmessage />
	<%-- <h1>显示内容:${requestScope.msg }</h1>
	<h1>${requestScope.email }</h1> --%>
</body>
</html>