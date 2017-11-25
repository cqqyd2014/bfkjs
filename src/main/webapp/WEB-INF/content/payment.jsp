<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'payment.jsp' starting page</title>
    
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
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}

a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.STYLE7 {
	font-size: 12
}
-->
</style>
  </head>
  
  <body bgcolor="#e5f6cf">
  <table width="500" border="0" cellpadding="0"
		cellspacing="0">
  <tr>
    <td colspan="8" height="26" background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE4">支付单</span></td>
  </tr>
  <tr>
    <td width="75"><span class="STYLE3">订单号</span></td>
    <td width="50"><span class="STYLE3">${orderNo }</span></td>
    <td width="75"><span class="STYLE3">商品金额</span></td>
    <td width="50"><span class="STYLE3">${amount }</span></td>
    <td width="75"><span class="STYLE3">税额</span></td>
    <td width="50"><span class="STYLE3">${tax }</span></td>
    <td width="75"><span class="STYLE3">估计毛重</span></td>
    <td width="50"><span class="STYLE3">${weight }</span></td>
  </tr>
  <tr>
    <td colspan="8"><s:if test="#request.p==null">
    	<span class="STYLE3">还没有支付单，请添加</span>
    </s:if>
    <s:form action="savePayment.action">
    <s:hidden name="amount" value="%{amount}"></s:hidden> 
    <s:hidden name="originalID" value="%{originalID}"></s:hidden> 
    <s:hidden name="orderNo" value="%{orderNo}"></s:hidden> 
    <s:hidden name="tax" value="%{tax}"></s:hidden> 
    <s:hidden name="user_id" value="%{user_id}"></s:hidden> 
    <s:if test="#request.p==null"> 
    	<s:textfield label="拟申报支付金额" name="jine" />
	</s:if>
    <s:if test="#request.p!=null"> 
    	<s:textfield label="已经申报支付金额" name="jine" readonly="true"/>
	</s:if>
	<s:if test="#request.p==null"> 
    	<s:submit value="确定"></s:submit>
    </s:if>
    <s:if test="#request.p!=null"> 
    	 <a href="orderDetail.action?orderNo=<s:property value="#request.orderNo" />">返回订单详情</a> |<a href="orderList.action">返回订单列表</a>
    </s:if>
    </s:form></td>
  </tr>
</table>
    
    
  </body>
</html>
