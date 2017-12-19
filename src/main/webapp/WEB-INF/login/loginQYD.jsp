<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:property value="#request.com_name" /></title>
<jsp:include page="../common/include_easyui2.jsp" flush="true" />

<script language='javascript' type='text/javascript'>

function login(){

	

	$.getJSON("../login/login.action", {"user_name":$("#user_name").textbox('getValue'),"password":$("#password").passwordbox('getValue')
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
	//移动焦点
	easyui_textbox_focus('user_name');
	easyui_textbox_enter('user_name',function(){
		easyui_textbox_focus('password');
		
		});
	easyui_textbox_enter('password',function(){
		login();
		});


});


function root(){
	window.location.href="../";
}
</script>

</head>

<body style="width: 98%; height: 98%;">



	<div id="login_dialog" style="width: 450px; height: 220px;"
		class="easyui-dialog" title="登录系统" iconCls="qyd"
		data-options="	iconCls: 'qyd',	buttons: '#login_buttons'">

		<table style="width: 100%">
			<tr>
				<td width="30%">公司名称：</td>
				<td width="70%"><s:property value="#request.com_name" /></td>
			</tr>
			<tr>
				<td>服务器时间：</td>
				<td><span id="time"></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input id="user_name" class="easyui-textbox" style="" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input id="password" class="easyui-passwordbox" style="" />
				</td>
			</tr>
		</table>





	</div>

	<div id="login_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:login()" icon="icon-ok">登录</a> <a
			href="
					javascript:void(0)" " class="easyui-linkbutton"
			icon="icon-cancel" onclick="javascript:root()">取消</a>
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
