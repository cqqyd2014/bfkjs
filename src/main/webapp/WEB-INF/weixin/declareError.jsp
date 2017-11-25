<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    
    <title>提交申报超时，请重新点击按钮“我要申报”。</title>
    
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

  </head>
  
  <body>
  <img alt="重庆海关" style="display:block;max-width:100%;"src="../img/wx/<s:property value='#request.com_id'/>/weixin_head.jpg">
    <h1>提交申报超时，请重新提交申请！</h1>
    <h3>1、请点击左上角“<img alt="" src="http://img04.taobaocdn.com/imgextra/i4/60893736/TB2a7dEdpXXXXazXXXXXXXXXXXX_!!60893736.jpg" width="10%">”按钮</h3>
    <h3>2、再次点击<img alt="" src="http://img04.taobaocdn.com/imgextra/i4/60893736/TB2w.lJdpXXXXXNXXXXXXXXXXXX_!!60893736.jpg" width="50%"></h3>
    <h3>3、填写申报信息</h3>
  </body>
</html>
