<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>勤驿达微信回复页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />

	<link rel="stylesheet" type="text/css" href="../css/mobile.css">


</head>

<body>
	<s:form action="replyWX.action" method="post" namespace="/weixin">
		
			
				<div class="shop-title"><s:property value="#request.nikeName" />发来信息：</div><br>
				
				<s:if test="#request.msg_type=='TXT'">
				<div class="content"><s:property	value="#request.msg" /></div>
				</s:if>
				<s:if test="#request.msg_type=='PIC'">
				<div class="content-step"><img src='GetPic.action?com_id=<s:property value="#request.com_id" />&mediaid=<s:property value="#request.msg" />'/></div>
				</s:if>
				<div class="content">回复信息如下</div>
				<div class="content"><s:textarea name="reply" cols="300px" rows="5"></s:textarea> 
				</div>
				<s:hidden
					name="com_id" value="%{com_id}"></s:hidden> <s:hidden
					name="fromUserName" value="%{fromUserName}"></s:hidden>
					<div class="author"><s:submit value="回复信息"></s:submit></div>
	</s:form>
</body>
</html>
