<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>进货到仓库</title>

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


		
	

function init(){

	alert("入库成功");
	window.location.href="add_vol_init.action"
	
}
function into_warehouse(){
	

	if ($('#add_num').text()==0){

		$.messager.alert("操作提示", "还没有录入商品",
		"error");
	return ;
		}
	
		if ($('#in_date').datebox('getValue')==''){
			$.messager.alert("操作提示", "入库时间不能为空",
			"error");
			return;

			}

	$.getJSON("save_temp_to_warehouse.action", {
		wh_id:$('#wh_id').val(),
		goods_id:$('#goods_id').val(),
		contract_id:$('#contract_id').val(),
		in_date:$('#in_date').datebox('getValue'),
		memo:$('#memo').val(),
		type_id:$('#type_id').val()
		
	}, function(result) {
		
		
			
		$.each(result, function(i, field) {
			
			if (field.success){
				init();

				
				}
			else{
				alert("错误"+field.body);
			}
			
		
	});
			
		
	});




	
}



	function show_sn_list_table(){


		$.getJSON("get_temp_barcode.action", {
		}, function(result) {
			
			var field=result.msg;
				
			if (field.success){
				var o=field.o;
				var n=o.length;

				$("#barcode_list_table").datagrid('loadData',{ total: n, rows: o });
				}
				
				
			
		});




		}

	$(document).ready(function() {

		$('#in_date').datebox({
			formatter : function(date) {
				var y = date.getFullYear();
				var m = date.getMonth() + 1;
				var d = date.getDate();
				var h = date.getHours();
				var M = date.getMinutes();
				var s = date.getSeconds();
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
			}
			,
		    
		        required:true
		    ,
		    okText:"确定",
		    closeText:"关闭",
		    currentText:"今日"
		});
		
		$('#in_date').datebox('setValue','${in_date}');
		$("#barcode").keydown(function() {


			

			if ($("#barcode").val().length==22) {
				$("#barcode").attr("disabled","disabled");

				//检测合法性
				$("#message").empty();


				
				$.getJSON("add_vol_add_temp_barcode.action", {
					barcode : $("#barcode").val(),contract_id:$("#contract_id").val()
				}, function(result) {
					
					var field=result.msg;
						
							if (field.success){
								//合格数据
								$("#message").append("<font color='blue'>"+field.body+"</font>");
								show_sn_list_table();
								$('#add_num').text(field.o2);
								
								}
							else{
								$("#message").append("<font color='red'>"+field.body+"</font>");


								}
							$("#message").append("<audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio>");
							$("#barcode").val("");
							$("#barcode").removeAttr("disabled");
							$("#barcode").focus();
						
					
					
				});

				
			}
			

		});



		

		

		$("#barcode").focus();
		$('#wh_id').val('<s:property value="#request.wh_id"/>')

	});
</script>



</head>

<body style="width: 95%; height: 95%;">
	<h2>进货到仓库</h2>

		<table width="100%" border="1px" class="box">
			<tr>
				<td width="10%"><span>仓库：</span></td>
				<td width="23%"><s:select label="仓库" name="wh_id"
						id="wh_id" list="wh_map" listKey="key" listValue="value" /><a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_warehouse',$('#wh_id').val())"
					iconCls="qyd">设为默认值</a></td>

				

				<td width="10%"><span>合同号：</span></td>"
				<td width="23%"><s:select name="contract_id" id="contract_id"
						list="contract_map" listKey="key" listValue="value" /> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_contract_m',$('#contract_id').val())"
					iconCls="qyd">设为默认值</a></td>
			

				<td width="10%"><span>入库时间：</span></td>
				<td width="23%"><s:textfield name="in_date" id="in_date"
						class="easyui-datebox" required="required" /></td>
			</tr>
			<tr>
				<td><span>入库类型：</span></td>
				<td><s:select label="入库类型" name="type_id" list="type_map" id="type_id"
						listKey="key" listValue="value" /></td>

				

				<td><span>备注：</span></td>
				<td colspan="3" ><s:textarea cols="30" rows="2" name="memo" id="memo"/></td>
			</tr>



			<tr>

				<td colspan="6" align="right">已经录入<span id="add_num">0</span>件商品<a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="javascript:into_warehouse()"
					iconCls="icon-ok">确认到货</a></td>
			</tr>
			</tr>
		</table>
		


	<div>
		请在此扫描商品条码：<input type="text" name="barcode" id="barcode"
			style="height: 40px; font-size: 30px; width: 100%; background-color: #ffffcc;border-color:red"
			size=30 />
	</div>
	<div id="message" style="font-size: 30px;"></div>

	<div>
		<table id="barcode_list_table" style="width: 100%; overflow-y: auto"
			title='已经录入以下商品' class="easyui-datagrid" idField="itemid"
			rownumbers="true" iconCls="qyd">
			<thead>
				<tr>


					<th field="goods_id">商品编码</th>
					<th field="goods_name">商品名称</th>
					<th field="barcode">条码</th>

				</tr>
			</thead>

		</table>

	</div>
</body>
</html>
