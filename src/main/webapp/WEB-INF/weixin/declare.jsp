<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>申报成功</title>
    
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>  
<!--  

form{  
    margin:0px; padding:0px;  
    font:14px;  
}  
input{  
    font:14px Arial;  
}  
.txt{  
    border-bottom:1px solid #005aa7;    /* 下划线效果 */  
    color:#005aa7;  
    border-top:0px; border-left:0px;  
    border-right:0px;  
    background-color:transparent;       /* 背景色透明 */  
}  
.btn{  
    background-color:transparent;       /* 背景色透明 */  
    border:0px;                         /* 边框取消 */  
}  
-->  
</style>  
  </head>
  
  <body>
    <img alt="重庆海关" style="display:block;max-width:100%;"src="../img/wx/<s:property value='#request.com_id'/>/weixin_head.jpg">
    申报成功，请继续关注微信公众号反馈的通关信息。<br>
    如有任何问题，请拨打我们的客服热线<a href="tel:<s:property value='#request.tel'/>"><s:property value="#request.tel"/></a>
  </body>
</html>
