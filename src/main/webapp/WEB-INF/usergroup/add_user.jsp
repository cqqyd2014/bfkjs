<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>添加用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<script type="text/javascript">


$(document).ready(function() {

	});  
	//dialog_init_mid('add_quota');
	//dialog_init_mid('user_price');
	//$('#quota_table').window('resize',{width:dialog_width,height:dialog_height});
	//$('#quota_table').dialog('close');





//保存对基本信息的修改
function save_user_info(){
	



	var inputs = $("input");
	
	var index=1;
	var pars='';
	inputs.each(function(){
	   // $(this)  //遍历得到的每一个jquery对象
	   
	   if (index>=16){
			pars=pars+this.id+','+this.value+','+this.name+',';
		   }
		   //alert(index+this.id+this.value);
		   index++;
		   
	 });
	

	if ($('#order_from_id').val().length!=2){
		$.messager.alert("操作提示", "类型代码只能为两个字符",
		"error");

		return;	
		}
	if ($('#passwd1').val()==''){
		$.messager.alert("操作提示", "密码不能为空",
		"error");

		return;	
		}

	if ($('#passwd1').val()!=$('#passwd1').val()){
		$.messager.alert("操作提示", "两次输入的密码不一致",
		"error");

		return;
		}
	//alert($('input:radio[name=effective]:checked').val());

	$.getJSON("add_user_info.action", {
		
		login_id:$("#login_id").val(),
		login_name:$("#login_name").val(),
		passwd:$('#passwd1').val(),
		email:$('#email').val(),
		tell:$("#tell").val(),
		pickup_weighting:$("#pickup_weighting").val(),
		send_weighting:$("#send_weighting").val(),
		role_id:$("#role_map").val(),
		order_from_id:$("#order_from_id").val(),
		order_from_name:$("#order_from_name").val(),
		e_id:$("#e_id").val(),
		pars:pars,
		sender_name:$("#sender_name").val(),
		sender_province:$("#sender_province").val(),
		sender_city:$("#sender_city").val(),
		sender_district:$("#sender_district").val(),
		sender_addr:$("#sender_addr").val(),
		sender_tell:$("#sender_tell").val(),
		sender_com:$("#sender_com").val()


	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			$.messager.alert("操作提示", "新增用户成功！",
			"info");
		}
		else{
			$.messager.alert("操作提示", "新增用户出错！原因：" + field.body,
			"error");
			}

		
	});
	
	
}
//得到基本信息
function clean_user_info(id){
	

	$.getJSON("get_user_base_info.action", {
		user_id :id

	}, function(result) {
		var field = result.msg;

		$("#sys_id").text(field.o.id);
		
		$("#login_id").val(field.o.userLogin);
		$("#login_name").val(field.o.name);
		
		if (field.o.effective){
			
			$("input[name='effective'][value='true']").prop("checked",true);
			}
		else{
			
			$("input[name='effective'][value='false']").prop("checked",true);
			}
		if (field.o.online){
			$("input[name='online'][value='true']").prop("checked",true);
			}
		else{
			$("input[name='online'][value='false']").prop("checked",true);
			}
		$("#tell").val(field.o.tel);
		//alert(field.o.lastOnlineTime);
		$("#last_online_time").text(field.o.lastOnlineTime);
		$("#quota_current").text(field.o.quotaCurrent);
		$("#quota_in").text(field.o.quotaAdd);
		$("#quota_out").text(field.o.quotaSubtract);
		$("#quota_freez").text(field.o.quotaFreez);
		$("#pickup_weighting").val(field.o.pickupWeighting);
		$("#send_weighting").val(field.o.sendWeighting);
	});
	
}


</script>
</head>
<body>
<h1>新用户</h1>
	
			<table width="100%" class='box'>
			<tr><td colspan ='8'><h3>1、基本信息</h3></td></tr>
			<tr>
				
				<td width="5%">登录用户名：</td><td width="20%"><input type='text'  id = 'login_id'></td>
				<td width="5%">显示名称：</td><td width="20%"><input type='text'  id = 'login_name'></td>
				<td width="5%">密码：</td><td width="20%"><input type='password'  id = 'passwd1'></td>
				<td width="5%">确认密码：</td><td width="20%"><input type='password'  id = 'passwd2'></td>
				
			</tr>
			<tr>
				<td width="5%">联系电话：</td><td width="20%"><input type='text'  id = 'tell'></td>
				<td width="5%">邮箱：</td><td width="20%"><input type='text'  id = 'email'></td>
			
				<td width="5%">拣货权重：</td><td width="20%"><input type='text'  id = 'pickup_weighting'></td>
			
				<td width="5%">发货权重：</td><td width="20%"><input type='text'  id = 'send_weighting'></td>
			</tr>
			<tr>
			<td width="5%">默认发货渠道代码：</td><td width="20%"><input type='text'  id = 'order_from_id'></td>
			<td width="5%">默认发货渠道名称：</td><td width="20%"><input type='text'  id = 'order_from_name'></td>
				<td width="5%">默认发货渠道类型：</td><td width="20%"><s:select id="e_id"
						list="e_map" listKey="key"
						listValue="value" style=" width: 150px;" /></td>
				<td width="5%">用户组：</td><td width="20%"><s:select id="role_map"
						list="role_map" listKey="key"
						listValue="value" style=" width: 150px;" /></td>
				
			</tr>
			<tr>
			<td width="5%">发货人姓名：</td><td width="20%"><input type='text'  id = 'sender_name'></td>
			<td width="5%">发货人省：</td><td width="20%"><input type='text'  id = 'sender_province'></td>
			<td width="5%">发货人市：</td><td width="20%"><input type='text'  id = 'sender_city'></td>
			<td width="5%">发货人区：</td><td width="20%"><input type='text'  id = 'sender_district'></td>
				
			</tr>
			<tr>
			<td width="5%">发货人单位：</td><td width="20%"><input type='text'  id = 'sender_com'></td>
			<td width="5%">发货人电话：</td><td width="20%"><input type='text'  id = 'sender_tell'></td>
			<td>发货人详细地址：</td><td colspan='3'><input type='text'  id = 'sender_addr' style="width: 100%"></td>
			
				
			</tr>
			<tr>
				
				<td colspan='8'> 
				<div><h3>2、扩展参数</h3></div>
				<s:iterator value="ups" var="up">
<div><span><s:property value="param_desc"/> </span><span><input type='text'  id = '<s:property value="param"/>' name='<s:property value="param_desc"/>' value='<s:property value="value"/>'></span>
	
</div>
	</s:iterator>
				
				</td>
			</tr>
			</table>
			<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="javascript:save_user_info()">新增用户</a>


</body>
</html>