<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:property value="#request.com_name" /></title>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/qyd.js"></script>

<script language='javascript' type='text/javascript'>

function login(){

	

	$.getJSON("../login/login.action", {"user_name":$("#user_name").val(),"password":$("#password").val()
	}, function(result) {
		
		$.each(result, function(i, field) {
			
			
				if (field.success){
					//成功登录

				window.location.href='../mainframe/main_frame.action';
				}
				else{
					alert(field.body);


					}
				
			
		});
		
	});
	
}

$(document).ready(function() {
	//$("#login_dialog").panel("move",{top:$(document).scrollTop() + ($(window).height()-250) * 0.5});  
	dialog_init_mid('login_dialog');
	$('#login_dialog').dialog('open');

});


function root(){
	window.location.href="../";
}
</script>

</head>

<body style="width:98%;height:98%;">



	<div id="login_dialog" 
		style="width: 450px; height: 220px;"
		class="easyui-dialog" title="登录系统" iconCls="qyd"
		data-options="	iconCls: 'qyd',	buttons: '#login_buttons'"
		>
		
	<table style="width: 100%">
	<tr>
		<td width="30%">公司名称：
		</td>
		<td width="70%"><s:property value="#request.com_name" />
		</td>
	</tr>
	<tr>
		<td>服务器时间：
		</td>
		<td><span id="time">
		</td>
	</tr>
	<tr>
		<td>用户名:
		</td>
		<td><s:textfield id="user_name" name="userName" style="width 250px;"/>
		</td>
	</tr>
	<tr>
		<td>密码：
		</td>
		<td><s:password id ="password" name="pwd" label="密码" style="width 250px;"/>
		</td>
	</tr>
	</table>
			
			
			

		
	</div>

	<div id="login_buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:login()"
					icon="icon-ok">登录</a> <a href="
					javascript:void(0)"" class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:root()">取消</a>
	</div>




	<script type="text/javascript">
/**
* jquery 定时刷新
* edit 
*/

function Push() {
	$.getJSON("../system/get_system_dat.action", {}, function(result) {
		var field=result.msg;
		if (field.success) {
			$('#time').text(field.body);
		}

	});
	
}

Push();


setInterval(function(){ 
	Push()
	}, 1000*60); 



</script>
</body>
</html>
