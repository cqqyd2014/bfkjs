<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'pwd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language='javascript' type='text/javascript'>

	function	msg(){
		var msg='${requestScope.msg}';
		if (msg!=''){
			alert("${requestScope.msg}");
		}
	}
	msg();
	</script>
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
  
  <body bgcolor="#e5f6cf">
  <span class="STYLE4">当前登录用户：<s:property value="#request.user_name" /></span>
  <hr>
   <span class="STYLE4">修改用户密码</span>
   <s:form id="pwdForm" action="pwd.action" method="post" namespace="/system">
   <table width ="500">
	<tr>
   		<td width="100"><span class="STYLE1">原始密码：</span></td>
   		<td width="400"><s:password id="pwdOld" name="pwdOld" cssStyle="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:password>    </td>
   	</tr>
	<tr>
   		<td><span class="STYLE1">新密码：</span></td>
   		<td><s:password id="pwdNew1" name="pwdNew1" cssStyle="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:password></td>
	</tr>
	<tr>
		<td><span class="STYLE1">再次确认新密码：</span></td>
		<td><s:password id="pwdNew2" name="pwdNew2" cssStyle="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:password></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><s:submit></s:submit></td>
	</tr>
   </tr></table>
   <span class="STYLE4"><s:fielderror/></span>
   </s:form>
  </body>
</html>
