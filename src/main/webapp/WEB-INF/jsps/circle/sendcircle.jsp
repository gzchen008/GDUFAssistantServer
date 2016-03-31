<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发广金圈</title>
  </head>
  
  <body>
    <s:form action="circle/addCircle" method="post">
    	<s:textfield name="circle.sender.id" label="发送者"/>
    	<s:textfield name="circle.content" label="发送内容"/>
    	<s:textfield name="imgCode"label="图片1"/>
    	<s:textfield name="imgCode"label="图片2"/>
    	<s:submit/>
    </s:form>
  </body>
</html>
