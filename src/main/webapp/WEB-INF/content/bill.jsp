<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'bill.jsp' starting page</title>
    
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
   <h1>运单</h1>
    <hr>
    <h3>相关的订单情况</h3>
    <p>订单号：<s:property value="#request.om.COrderId" /></p>
    <p>姓名：<s:property value="#request.om.CUserName" /></p>
    <p>身份证号：<s:property value="#request.om.CUserId" /></p>
    <p>地址：<s:property value="#request.om.CUserAddr" /></p>
    <p>数量：<s:property value="#request.om.CQty" /></p>
    <hr>
    <h3>运单情况</h3>
    <s:if test="#request.bill==null">
    	<p>还没有运单单，请添加</p>
    </s:if>
    <s:form action="declareBill.action">
    
    <s:hidden name="orderNo" value="%{orderNo}"></s:hidden> 
    <s:hidden name="Qty" value="%{qty}"></s:hidden> 
    <s:if test="#request.bill==null"> 
    	<s:textfield label="拟申报运单号" name="bill_no" />
	</s:if>
    <s:if test="#request.bill!=null"> 
    	<s:textfield label="已经申报报运单号" name="bill_no" readonly="true"/>
	</s:if>
	<s:if test="#request.bill==null"> 
    	<s:submit value="确定"></s:submit>
    </s:if>
    <s:if test="#request.bill!=null"> 
    	 <a href="orderDetail.action?orderNo=<s:property value="#request.orderNo" />">返回订单详情</a> |<a href="orderList.action">返回订单列表</a>
    </s:if>
    </s:form>
  </body>
</html>
