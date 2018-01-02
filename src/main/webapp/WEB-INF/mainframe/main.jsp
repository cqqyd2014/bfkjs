<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title><s:property value="#application.app_name"/></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../common/include_easyui2.jsp" flush="true" />
</head>

<body>
<div id="fakeLoader"></div>
	<div class="easyui-layout" fit="true" id="body">
		<div id="top_panel"
			data-options="region:'north',href:'top_frame.action'"
			style="height: 62px"></div>
	
	<div id="navi_panel"
		data-options="region:'west',href:'west_frame.action'" split="true"
		title="导航" style="width: 150px">
	</div>

	<div data-options="region:'center',href:'home_page_frame.action'" 
		style="background: grey; padding: 10px;"></div>

	</div>
  </body>
</html>
