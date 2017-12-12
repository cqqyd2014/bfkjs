<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showWXUser.jsp' starting page</title>
    
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
    <p><span class="STYLE4">微信关注号</span><span class="STYLE1">共有公众号<s:property value="#request.userCount" />个</span></p>
    <table width="100%" border="1" cellspacing="0"
					cellpadding="0" bgcolor="#c0de98" style="border-collapse: collapse">
    <tr>
    <td background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE1">序号</td><td background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE1">用户名</span></td><td background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE1">关注时间</span></td><td background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE1">城市</span></td><td background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif"><span class="STYLE1">省份</span></td>
    
    </tr>
    <s:iterator value="userList" id="u" status="st">
    <tr>
    <td bgcolor="#FFFFFF"><s:property	value="#st.index" /></td>
    <td bgcolor="#FFFFFF"><img width="50px" alt="<s:property	value="#u.nickname" />" src="<s:property	value="#u.headimgurl" />"><s:property	value="#u.nickname" /></td>
    <td bgcolor="#FFFFFF"><s:date name="#u.subscribeTime" format="yyyy-MM-dd HH:mm:ss" /></td>
    <td bgcolor="#FFFFFF"><s:property	value="#u.city" /></td>
    <td bgcolor="#FFFFFF"><s:property	value="#u.provice" /></td>
    </tr>
    </s:iterator>
    
    </table>
  </body>
</html>
