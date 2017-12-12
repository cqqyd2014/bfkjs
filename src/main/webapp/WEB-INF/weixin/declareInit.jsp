<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>包裹通关自助申报接口</title>
    
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
input {  
    color: #00008B;  
    background-color: #ADD8E6;  
    border: 1px solid #00008B;  
}  
 
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
  <p>重庆海关提醒您，请正确输入收货人姓名与收货人身份证号码，以便您的包裹顺利快捷通关！！！<br>
  <br>
  更多需要，请拨打我们的客服电话<a href="tel:<s:property value='#request.tel'/>"><s:property value="#request.tel"/></a>
  </p>
  <s:form action="declare" method="post" namespace="/weixin"  >
     <table width="100%">
    
   <tr><td>收货人姓名：</td><td><s:textfield name="buyername" label="收货人姓名"  cssClass="txt"></s:textfield></td></tr>
   <tr><td>收货人身份证号：</td><td><s:textfield name="idcard" label="收货人身份证号" cssClass="txt"></s:textfield></td></tr>
   
   <tr><td>&nbsp;</td><td><s:hidden name="OpenID" value="%{OpenID}"/><s:hidden name="uuid" value="%{uuid}"/><s:hidden name="com_id" value="%{com_id}"/>
    <s:submit value="确认无误，我要申报"></s:submit>
   
   </td></tr>
   </table>
  </s:form>
  <s:fielderror/>
  
  </body>
</html>
