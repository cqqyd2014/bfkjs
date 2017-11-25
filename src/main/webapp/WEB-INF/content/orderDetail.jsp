<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'orderDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>订单详情</h1>
    <hr>
    <p>系统订单编号：<s:property value="#request.orderNo" /></p>
    <p>电商平台订单编号：<s:property value="#request.originalID" /></p>
    <p>收货人姓名：<s:property value="#request.om.CUserName" /></p>
    <p>收货人身份证：<s:property value="#request.om.CUserId" /></p>
    <p>收货人地址：<s:property value="#request.om.CUserAddr" /></p>
    <h1>订单明细</h1>
    <table border="1px">

		<s:if test="das.size()>0">
		<tr>
		<td>商品名称</td><td>数量</td><td>单价</td><td>总金额</td><td>税额</td>
		</tr>
			<s:iterator value="%{das}" id="da">
				<tr>
					
					<td><s:property value="#da.id.CName" /></td>
					<td><s:property value="#da.id.CCount" /></td>
					<td><s:property value="#da.id.CPrice" /></td>
					<td><s:property value="#da.id.amount" /></td>
					<td><s:property value="#da.id.tax" /></td>
					
				</tr>
			</s:iterator>
		</s:if>
	</table>
	<h1>支付单</h1>
	
  </body>
</html>
