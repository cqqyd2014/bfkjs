<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<s:form id="loginForm" action="dataReceiver.action" method="post" namespace="/" >
<s:hidden name="local_debug" value="是"></s:hidden>
<s:textarea cols="100" rows="30" name="data" id="data"  >
</s:textarea>

<s:submit></s:submit>
</s:form>
</body>
</html>