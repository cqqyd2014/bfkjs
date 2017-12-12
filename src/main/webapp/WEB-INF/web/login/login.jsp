<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>


<title><s:property value="#request.com_name" />移动MIS</title>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="../../js/jquery.mobile-1.4.5.min.css" />
<script src="../../js/jquery-2.1.4.min.js"></script>
<script src="../../js/jquery.mobile-1.4.5.min.js"></script>
<script src="../../js/qyd.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">

function login(){
	$.getJSON("../../login/login.action", {"user_name":$("#user_name").val(),"password":$("#password").val()
	}, function(result) {
		
		$.each(result, function(i, field) {
			
			
				if (field.success){
					//成功登录

				window.location.href='../manage/main_init.action';
				}
				else{
					message_dialog("登录错误","请确认登录用户名密码","alert");


					}
				
			
		});
		
	});
}


	$(document).ready(function() {
		
		
		//init();
	});
</script>

</head>

<body>

	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1><s:property value="#request.com_name" />移动MIS</h1>
			<div data-role="navbar">
				<ul>
					<li><a href="#" data-icon="home">首页</a></li>
					<li><a href="#" data-icon="refresh">刷新</a></li>
					<li><a href="#" data-icon="search">搜索</a></li>
				</ul>
			</div>
		</div>

		<div data-role="content">
		<label>系统时间：<span id="system_dat"></span></label>
			<div data-role="fieldcontain">
      <label for="fname" >登录名：</label>
      <input type="text" name="user_name" id="user_name" placeholder="姓名...">
      <label for="password" >密码：</label>
      <input type="password" name="password" id="password" placeholder="密码...">
    </div>
    <input type="submit" value="提交" onclick="javascript:login()">
		</div>

		<div data-role="footer">
			<h1>V2017 V3.0</h1>
		</div>
	</div>

<jsp:include page="../dialog.jsp" flush="true" />
</body>
</html>