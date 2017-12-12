<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">


</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

<script type="text/javascript" src="../js/qyd.js"></script>

<script language='javascript' type='text/javascript'>
	function exit() {
		window.parent.location = "../system/exit.action";
	}
	function homepage() {
		self.parent.frames["mainFrame"].location = "center.action";
	}
	function pwdInit() {
		self.parent.frames["mainFrame"].frames["main"].document.location = "../system/pwdInit.action";
	}
	function go() {
		self.parent.frames["mainFrame"].frames["main"].history.go(-1);
	}
	function back() {
		self.parent.frames["mainFrame"].frames["main"].history.go(1);
	}
	function reload() {
		self.parent.frames["mainFrame"].frames["main"].location.reload();
	}
</script>

<title>框架头部文件</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript">



	/**
	 * jquery 定时刷新
	 * edit 
	 */
	setInterval("Push()", <s:property value="#request.interval_time" /> * 1000);
	function Push() {
		
		$.ajax({
			type : "POST",
			url : "../system/set_user_online.action",
			data: {user_id:"<s:property value='#request.user_id' />", com_id:"<s:property value='#request.com_id'/>"},
            dataType: "json",
			success : function(data) {
				var field=data.msg;
				ajax_authority2(field);
					
					
						$('#current_time').text(field.o);
					
					
				
			
			}
		});
	}
</script>

</head>

<body  style="padding: 0px;margin:0px">
<div class="easyui-layout" style="padding: 0px;width: 100%; height: 100%;background-color:#996699;color:white;margin-top:0px;margin-bottom:0px;" >

<table width="100%"  style="color:white;" >

<tr>
	<td rowspan="2" width="40%" align="left" ><h1>MIS 2017V3.0</h1>
	</td>
	<td width="60%" align="right">
	当前时间：<span id="current_time"><s:property value="#request.chineseDate"/></span>登录用户：<s:property value="#request.user_name" />
		
		
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:exit()">退出</a>
	</td>
</tr>

</table>

</div>
</body>
</html>