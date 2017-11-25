<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="UTF-8">
<title><s:property value="#request.com_name" /></title>
</head>
<body>
<h1>当前单位：<s:property value="#request.com_name" /></h1>
<a>新增订单</a>
<a>订单列表</a>
<a>库存情况</a>

</body>
</html>