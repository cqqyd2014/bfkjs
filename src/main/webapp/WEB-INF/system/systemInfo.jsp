<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'systemInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
<!--
.col1 {
	width: 150px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}
.STYLE2 {
	font-size: 12px;
	color:red;
}

.STYLE4 {
	font-size: 18px;
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
  
  <body>
   <span class="STYLE4">系统信息</span>
   <table border="1" cellspacing="0"
					cellpadding="0" bgcolor="#c0de98" style="border-collapse: collapse">
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">用户：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.name" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">登录用户名：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.user" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">类型：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:if test="#request.user.type=='0'.toString()">管理员</s:if><s:if test="#request.user.type=='1'.toString()">分销商</s:if></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">地区：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.province" /><s:property value="#request.user.city" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">微信Token：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.wxToken" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">微信APPID：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.wxAppid" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">微信SECRET：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.wxSecret" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">微信DEBUG：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.wxDebug" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-MasAskInfo：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceiveMesaskinfo" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-BarCode：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceiveBarcode" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-OrderFB：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceiveOrderinfofb" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-PaymentFB：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceivePaymentinfofb" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-PersonValidate：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceivePersonValidate" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">邮件-WX：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.mailReceiveWx" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">我的微信OPENID：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.wxMe" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">每页记录数：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.pageNum" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">海关Post地址：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.postUrl" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">寸滩仓库Post地址：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.gtUrl" /></span></td>
   </tr>
   <tr>
   <td bgcolor="#FFFFFF"><span class="STYLE1">客服电话：</span></td><td bgcolor="#FFFFFF"><span class="STYLE1"><s:property value="#request.user.tel" /></span></td>
   </tr>
   </table>
   已申报的身份证号
   <table>
   <tr>
   <td>申报时间</td>
   <td>姓名</td>
   <td>身份证号</td>
   </tr>
   <tr>
   <td>申报时间</td>
   <td>姓名</td>
   <td>身份证号</td>
   </tr>
   
   </table>
  </body>
</html>
