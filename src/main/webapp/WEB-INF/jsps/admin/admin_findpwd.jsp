<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>找回密码</title>
</head>

<body>
	<s:form action="admin_findPwd.action" label="发送验证邮件到注册邮箱">
		<s:textfield name="admin_name" label="邮箱地址" />
		<s:submit value="发送" />
	</s:form>
</body>
</html>
