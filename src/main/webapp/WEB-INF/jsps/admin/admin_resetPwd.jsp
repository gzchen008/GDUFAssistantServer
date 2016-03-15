<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>重置密码</title>


</head>

<body>
	<h1>
		<s:actionmessage />
	</h1>
	<s:debug />

	<s:form action="admin_afterReset.action">

		<s:textfield name="admin_name" label="帐号" readonly="true">
			<s:property value="admin_name" />
		</s:textfield>
		<s:password name="admin_password" label="新的密码" />
		<s:password name="repassword" label="重复密码" />
		<s:submit value="重设" />
	</s:form>
	<br>

</body>
</html>
