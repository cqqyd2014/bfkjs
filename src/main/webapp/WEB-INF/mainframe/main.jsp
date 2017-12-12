<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>勤驿达</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<frameset rows="40,*,25" cols="*" framespacing="0px" frameborder="no" border="0px">
  <frame style="padding: 0px;margin-top:0px;margin-bottom:0px;" src="top_frame.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frame style="padding: 0px;margin-top:0px;margin-bottom:0px;" src="center_frame.action" name="mainFrame" id="mainFrame" />
  <frame style="padding: 0px;margin-top:0px;margin-bottom:0px;" src="down_frame.action" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>

  </body>
</html>
