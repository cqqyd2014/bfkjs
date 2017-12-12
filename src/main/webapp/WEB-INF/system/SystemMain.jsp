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
   <div class="STYLE4">系统信息</div>
   
   <table width="100%">
   <tr>
   <td width="20%" align="center">
	   <a href="comInfoInit.action">
		   <div>
			   <div>
			   <img alt="" src="../img/icon/icon_40.jpg"/ width="92px">
			   </div>
			   <div>
			   公司基本信息
			   </div>
		   
		   </div>
	   
	   </a>
   </td>
   <td width="20%" align="center">
		<a href="userInit.action">
		   <div>
			   <div>
			   <img alt="" src="../img/icon/icon_24.jpg"/ width="92px">
			   </div>
			   <div>
			   用户管理
			   </div>
		   
		   </div>
	   
	   </a>
   </td>
   <td width="20%" align="center">
		<a href="roleInit.action">
		   <div>
			   <div>
			   <img alt="" src="../img/icon/icon_39.jpg"/ width="92px">
			   </div>
			   <div>
			   权限管理
			   </div>
		   
		   </div>
	   
	   </a>
   </td>
   <td width="20%" align="center">
		<a href="systemInit.action">
		   <div>
			   <div>
			   <img alt="" src="../img/icon/icon_27.jpg"/ width="92px">
			   </div>
			   <div>
			   系统管理
			   </div>
		   
		   </div>
	   
	   </a>
   </td>
   <td width="20%" align="center">
		<a href="logInit.action">
		   <div>
			   <div>
			   <img alt="" src="../img/icon/icon_11.jpg"/ width="92px">
			   </div>
			   <div>
			   日志管理
			   </div>
		   
		   </div>
	   
	   </a>
   </td>
   </tr>
   </table>
   
  </body>
</html>
