<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>用户管理</title>
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
function new_user_price(){
	$('#d_uuid').val('');
	$('#d_user_price').val('');
	$('#user_price').dialog('open');
	
}
function save_user_price(){
	//alert($('#d_uuid').val());
	
$.getJSON("save_user_price.action", {
		
		userid :$("#sys_id").text(),
		uuid:$('#d_uuid').val(),
		start_time:$('#d_start_time').datebox('getValue'),
		end_time:$('#d_end_time').datebox('getValue'),
		goods_id:$('#d_goods_id').val(),
		user_price:$('#d_user_price').val()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			getUserPriceTable ();
			
			alert("更新价格成功");
			$('#user_price').dialog('close');
			
		}
		else{
			alert("更新价格出错:"+field.body);
			}

		
	});
	
	
	
}

function change_dat_user_price(uuid,goods_id,price,start_dat,end_dat){
	$('#d_uuid').val(uuid);
	$("#d_goods_id").val(goods_id);
	$('#d_user_price').val(price);
	$('#d_start_time').datebox('setValue', start_dat);
	$('#d_end_time').datebox('setValue', end_dat);
	$('#user_price').dialog('open');
	
	
}
function del_user_price(uuid){
	$.getJSON("del_user_price.action", {
		
		userid :$("#sys_id").text(),
		uuid:uuid
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			getUserPriceTable ();
			
			alert("删除价格成功");
			
		}
		else{
			alert("删除价格出错");
			}

		
	});
}
function red_flag_quota(uuid,type,status){
	if (status=='已经冲销'){
		alert('不能重复冲销');
		return
		}

	if (type=='普通充值'){
		$.getJSON("../quota/red_flag_quota.action", {
			
			userid :$("#sys_id").text(),
			uuid:uuid
			

		}, function(result) {
			var field=result.msg;
			

			if (field.success) {
				
				change_user();
			}
			else{
				alert("备用金冲销出错");
				}

			
		});
		}
	else{
		alert('只能冲销普通充值');
		}


	
	
}
function add_quota_init(){
	$('#add_quota_amount').val('');
	$('#add_quota_memo').val('');
	$('#add_quota').dialog('open');
}
function add_quota(){
	if ($("#sys_id").text()==""){
		alert("没有选中用户");
	}
	else{
		$.getJSON("../quota/save_add_quota.action", {
			amount :$('#add_quota_amount').val(),
			userid :$("#sys_id").text(),
			memo:$("#add_quota_memo").val()
			

		}, function(result) {
			var field=result.msg;
			

			if (field.success) {
				
				alert("备用金充值成功");
				dialog_init_mid('add_quota');
			}
			else{
				alert("备用金充值出错");
				}

			
		});
		}
	
}

function getUserPriceTable(){
	$.getJSON("get_user_price_table.action", {
		userid :$("#user_id").val()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			var o = field.o;
			var n = o.length;
			$("#price_table").datagrid('loadData', {
				total : n,
				rows : o
			});

		}
		else{
			alert("得到用户协议价格出错");
			}

		
	});
}

function getQuotaTransTable(){
	

	$.getJSON("get_quota_trans_table.action", {
		userid :$("#user_id").val()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			var o = field.o;
			var n = o.length;
			$("#quota_table").datagrid('loadData', {
				total : n,
				rows : o
			});

		}
		else{
			alert("得到备用金流水出错");
			}

		
	});
	
	
}

function getPackageTable(){
	

	$.getJSON("get_package_table.action", {
		package_userid :$("#sys_id").text()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			var o = field.o;
			var n = o.length;
			$("#package_doing_table").datagrid('loadData', {
				total : n,
				rows : o
			});

		}
		else{
			alert("得到package用户出错");
			}

		
	});
	
	
}

$(document).ready(function() {
	$('#d_start_time').datebox({
		formatter : function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			
			function formatNumber(value) {
				return (value < 10 ? '0' : '') + value;
			}

			return y + '-' + m + '-' + d;
		},
		parser : function(s) {
			var t = Date.parse(s);
			if (!isNaN(t)) {
				return new Date(t);
			} else {
				return new Date();
			}
		},

		required : true,
		okText : "确定",
		closeText : "关闭",
		currentText : "今日"
	});
	$('#d_end_time').datebox({
		formatter : function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			
			function formatNumber(value) {
				return (value < 10 ? '0' : '') + value;
			}

			return y + '-' + m + '-' + d;
		},
		parser : function(s) {
			var t = Date.parse(s);
			if (!isNaN(t)) {
				return new Date(t);
			} else {
				return new Date();
			}
		},

		required : true,
		okText : "确定",
		closeText : "关闭",
		currentText : "今日"
	});
//	$('#order_dat').datetimebox('setValue', '${order_time}');


	
	
	$('#package_doing_table').datagrid({  
	    //border:false,  
	    fitColumns:true,  
	    singleSelect: true,  
	    title:'拣货列表',
	    rownumbers:true,
	    columns:[[  
	        {field:'order_no',title:'订单号'},  
	        {field:'username',title:'订单录入员'},
	        {field:'createtime',title:'订单提交时间'},
	        {field:'paytime',title:'付款时间'},
	        {field:'print_count',title:'分配时间'},
	        {field:'last_print_dat',title:'等待拣货时长'},

	        
	        {field:'opt',title:'操作',width:'220px',align:'center',  
	            formatter:function(value,rec){  
	                var btn = '<a class="move_to_other" onclick="print_barcodes(\''+rec.contract_no+'\')" href="javascript:void(0)">移交其他拣货人</a><a class="tip" onclick="all_arrival(\''+rec.contract_no+'\')" href="javascript:void(0)">提醒捡货人操作</a>';  
	                return btn;  
	            }  
	        }  
	    ]],  
	    onLoadSuccess:function(data){  
	        $('.move_to_other').linkbutton({text:'移交其他拣货人',plain:true,iconCls:'icon-man'});
	        $('.tip').linkbutton({text:'提醒捡货人操作',plain:true,iconCls:'icon-tip'});  
	        
	    }  
	});  


	

	$('#price_table').datagrid({  
	    //border:false,  
	    fitColumns:true,  
	    singleSelect: true,  
	    title:'价格列表',
	    rownumbers:true,
	    columns:[[  
	    	{field:'uuid',title:'流水号'},  
	        {field:'goods_id',title:'商品编码'},  
	        {field:'goods_name',title:'商品名称'},
	        {field:'start_time',title:'开始时间'},
	        {field:'end_time',title:'结束时间'},
	        {field:'price',title:'协议价格'},
	        {field:'effective_now',title:'当前有效'},
	        {field:'out_date',title:'过期'},
	        {field:'in_the_futurn',title:'未生效'},
	        
	        


	        
	        {field:'opt',title:'操作',width:'200px',align:'center',  
	            formatter:function(value,rec){  
	                var btn = '<a class="del_user_price" onclick="del_user_price(\''+rec.uuid+'\')" href="javascript:void(0)">删除价格</a><a class="change_dat_user_price" onclick="change_dat_user_price(\''+rec.uuid+'\',\''+rec.goods_id+'\',\''+rec.price+'\',\''+rec.start_time+'\',\''+rec.end_time+'\')" href="javascript:void(0)">更新</a>';  
	                return btn;  
	            }  
	        }  
	    ]],  
	    onLoadSuccess:function(data){  
	        $('.del_user_price').linkbutton({text:'删除价格',plain:true,iconCls:'icon-cancel'});
	    	$('.change_dat_user_price').linkbutton({text:'更新',plain:true,iconCls:'icon-pencil'});
	        
	    }  
	});  

	

	
	$('#quota_table').datagrid({  
	    //border:false,  
	    fitColumns:true,  
	    singleSelect: true,  
	    title:'备用金流水',
	    rownumbers:true,
	    columns:[[  
	    	{field:'uuid',title:'流水号'},  
	        {field:'op_time',title:'操作时间'},  
	        {field:'begin_amount',title:'期初余额'},
	        {field:'trans_amount',title:'发生额'},
	        {field:'end_amount',title:'期末余额'},
	        {field:'begin_freez_amount',title:'期初冻结余额'},
	        {field:'trans_freez_aount',title:'冻结发生额'},
	        {field:'end_freez_amount',title:'期末冻结余额'},
	        {field:'order_no',title:'涉及订单号'},
	        {field:'wrong_uuid',title:'冲销流水号'},
	        {field:'trans_type_name',title:'类型'},
	        {field:'status',title:'状态'},
	        {field:'memo',title:'备注'},
	        


	        
	        {field:'opt',title:'操作',width:'100px',align:'center',  
	            formatter:function(value,rec){  
	                var btn = '<a class="red_flag" onclick="red_flag_quota(\''+rec.uuid+'\',\''+rec.trans_type_name+'\,\''+rec.status+'\')" href="javascript:void(0)">冲销充值记录</a>';  
	                return btn;  
	            }  
	        }  
	    ]],  
	    onLoadSuccess:function(data){  
	        $('.red_flag').linkbutton({text:'冲销充值记录',plain:true,iconCls:'icon-undo'});
	        
	        
	    }  
	});  
	dialog_init_mid('add_quota');
	dialog_init_mid('user_price');
	//$('#quota_table').window('resize',{width:dialog_width,height:dialog_height});
	//$('#quota_table').dialog('close');
}




);



//保存对基本信息的修改
function save_base_info(){
	//alert($('input:radio[name=effective]:checked').val());
	
	$.getJSON("save_user_base_info.action", {
		user_id :$("#sys_id").text(),
		login_id:$("#login_id").val(),
		login_name:$("#login_name").val(),
		effective:$('input:radio[name=effective]:checked').val(),
		online:$('input:radio[name=online]:checked').val(),
		tell:$("#tell").val(),
		pickup_weighting:$("#pickup_weighting").val(),
		send_weighting:$("#send_weighting").val()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			alert("更新基本信息成功");
		}

		
	});
	
	
}
//得到基本信息
function get_base_info(id){
	

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


	function change_user() {
		//alert($("#user_id").val());
		get_base_info($("#user_id").val());
		getPackageTable();
		getQuotaTransTable();
		getUserPriceTable();
		//
		/*
		$.post($("#server_addr").val(), {
			xml : encodeURI($("#xml_text").val()),
			

		}, function(result) {
			result ='<pre>' + result + '</pre>';
			$("#xml_response_text").html(result);
		});
		*/
	}

</script>
</head>
<body>

	<table width='100%' class='box'>
		
		
		<tr>
			<td width="30%">用户：</td>
			<td width="70%"><s:select name="user_id" id="user_id"
					list="user_map" listKey="key" listValue="value" /><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:change_user()">查看该用户</a></td>
		</tr>
		<tr>
			
			<td colspan ='2'>
			<table width="100%" class='box'>
			<tr><td colspan ='8'>基本信息：</td></tr>
			<tr>
				<td width="5%">系统ID：</td><td width="20%"><span id = 'sys_id'></span></td>
				<td width="5%">登录用户名：</td><td width="20%"><input type='text'  id = 'login_id'></td>
				<td width="5%">显示名称：</td><td width="20%"><input type='text'  id = 'login_name'></td>
				<td width="5%">是否有效：</td><td width="20%"><label><input type="radio"  name = 'effective' value='true'>有效</label><label><input type="radio"  name = 'effective' value='false'>无效</label></td>
			</tr>
			<tr>
				<td width="5%">联系电话：</td><td width="20%"><input type='text'  id = 'tell'></td>
				<td width="5%">最后上线时间：</td><td width="20%"><span  id = 'last_online_time'></span></td>
				<td width="5%">当前是否在线：</td><td width="20%"><label><input type="radio"  name = 'online' value='true'>在线</label><label><input type="radio"  name = 'online' value='false'>不在线</label></td>
				<td width="5%">备用金（可用）：</td><td width="20%"><span id="quota_current"></span></td>
			</tr>
			<tr>
				<td width="5%">备用金（入账）：</td><td width="20%"><span id="quota_in"></span></td>
				<td width="5%">备用金（出账）：</td><td width="20%"><span id="quota_out"></span></td>
				<td width="5%">备用金（冻结）：</td><td width="20%"><span id="quota_freez"></span></td>
				<td width="5%">拣货权重：</td><td width="20%"><input type='text'  id = 'pickup_weighting'></td>
			</tr>
			<tr>
				<td width="5%">发货权重：</td><td width="20%"><input type='text'  id = 'send_weighting'></span></td>
				<td width="5%"></td><td width="20%"></td>
				<td width="5%"></td><td width="20%"></td>
				<td width="5%">操作</td><td width="20%"><a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:save_base_info()">保存对基本信息的修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:add_quota_init()">充值备用金</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:new_user_price()">增加商品价格</a></td>
			</tr>
			</table></td>
		</tr>
		
		<tr>
			<td colspan='2'><table id="package_doing_table" title="正在打包列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				
			</tr>
		</thead>

	</table></td>
		</tr>
<tr>
			<td colspan='2'><table id="send_doing_table" title="正在发货列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				
			</tr>
		</thead>

	</table></td>
		</tr>
		<tr>
			<td colspan='2'><table id="quota_table" title="充值列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				
			</tr>
		</thead>

	</table></td>
		</tr>
		
		<tr>
			<td colspan='2'><table id="price_table" title="价格列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				
			</tr>
		</thead>

	</table></td>
		</tr>
		
	</table>


	
		<div id="add_quota" class="easyui-dialog" title="充值备用金"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="
				iconCls: 'qyd',
				buttons: '#add_quota_buttons'
			">
		<div>
			<div>
				<span>充值的金额为:</span><input type="text" id="add_quota_amount" style="width:100%"/></span>
			
				
			</div>
			<div>备注</div>
			<div><textarea rows="5" cols="30" id="add_quota_memo" style="width:100%"></textarea></div>
			

			
		</div>


	</div>
	<div id="add_quota_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:add_quota()">保存</a> 
			<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#add_quota').dialog('close')">取消</a>
	</div>
	
	
	
		<div id="user_price" class="easyui-dialog" title="用户协议价格"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="
				iconCls: 'qyd',
				buttons: '#user_price_buttons'
			">
			<input type='hidden' id='d_uuid'>  
		<div>
			<div>
				<span>商品:</span><s:select id="d_goods_id"
								list="goods_id_map" listKey="key" listValue="value" /></span>
			
				
			</div>
			<div><span>价格：</span><input type="text" id="d_user_price" /></div>
			<div><span>开始时间：</span><input id="d_start_time" type="text" class="easyui-datebox"></div>
			<div><span>结束时间：</span><input id="d_end_time" type="text" class="easyui-datebox"></div>
			

			
		</div>


	</div>
	<div id="user_price_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:save_user_price()">保存</a> 
			<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#user_price').dialog('close')">取消</a>
	</div>
</body>
</html>