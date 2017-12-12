<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>商品移库</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">

<script type="text/javascript">

function show_temp_table(){
	$.getJSON("get_goods_sum.action", {
		
	}, function(result) {
		var field=result.msg;
			
				if (field.success){
					var o=field.o;
					var n=o.length;
					$('#catalog_count').text(n);

					$("#catalog_list_table").datagrid('loadData',{ total: n, rows: o });
					

					
					}
				else{
				}
				
			
		
		
	});
	$.getJSON("../web_common/get_array_list_goods_in_session.action", {
		par:"temp_move_goods"
		
	}, function(result) {
		var field=result.msg;
			
				if (field.success){
					var o=field.o;
					var n=o.length;
					
					$('#goods_count').text(n);
					$("#goods_barcode_table").datagrid('loadData',{ total: n, rows: o });
					

					
					}
				else{
				}
				
			
		
		
	});
	



	
}


function move_wh(){
	//alert($('#wh_id_from').val());
	//alert($('#move_date').datebox('getValue'));
	if ($('#wh_id_from').val()==$('#wh_id_to').val()){
		alert("不能源仓库与目标仓库相同");
		return ;
		}
	if ($('#goods_count').text()=='0'){
		alert("还没有扫描商品哦！");
		return ;
		}
	
	$.getJSON("move_warehouse_to_warehouse.action", {
		fromWh:$('#wh_id_from').val(),
		toWh:$('#wh_id_to').val(),
		move_date:$('#move_date').datebox('getValue'),
		memo:$('#memo').val()
		
	}, function(result) {
	var field=result.msg;
			
				if (field.success){
					alert("移库成功");
					window.location.href="../wh/MoveWarehouseInit.action";

					
					}
				else{
					alert(field.body);
					
				}
				
			

		
	});
	
}


function add_goods(){
	$("#goods_barcode").attr("disabled","disabled");

	//检测合法性
	$("#message").html("");


	
	$.getJSON("add_goods_to_move.action", {
		goods_barcode : $("#goods_barcode").val(),wh_id_from:$('#wh_id_from').val()
	}, function(result) {
		$.each(result, function(i, field) {
			
				if (field.success){
					//合格数据
					$("#message").html("<font color='blue'>"+field.body+"</font>");
					
					show_temp_table();
					

					
					}
				else{
					$("#message").html("<font color='red'>"+field.body+"</font>");
					

					}
				$("#goods_barcode").removeAttr("disabled");
				$("#goods_barcode").val("");
				$("#goods_barcode").focus();
				
			
		});
		
	});

}




	$(document).ready(function() {
		$('#move_date').datetimebox({
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

				return y + '-' + m + '-' + d+' '+h+':'+M+":"+s;
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

		$('#move_date').datetimebox('setValue','${move_date}');
		$("#goods_barcode").focus();

		$("#goods_barcode").keydown(function() {

			if ($("#goods_barcode").val().length == 22) {
				add_goods();

				
			}

		});
		
	
		 
	});
	
</script>


</head>

<body style="width:97%;height:97%;">
<h2>商品移库</h2>
<div>请选择移动的仓库再扫描需要移动的产品条码

		<table width="100%" border="1px" class="box">
		
		<tr>
			<td width="10%">源仓库：</td>
			<td width="23%"><s:select name="wh_id_from" id="wh_id_from" list="wh_map" listKey="key" listValue="value" /> </td>
		
		
		<td width="10%">目标仓库：</td>
		<td width="23%">	<s:select name="wh_id_to" id="wh_id_to" list="wh_map" listKey="key" listValue="value" /> </td>
		
		
		<td width="10%">移库时间：</td>
		<td widht="23%"><s:textfield name="move_date" id="move_date" class="easyui-datebox" required="required" /></td>
		</tr>
		<tr>
			<td>请在此扫描商品：</td><td colspan="2" ><input type="text" name="goods_barcode" id="goods_barcode"
			style="height: 40px; font-size: 30px; width: 300px; background-color: #ffffcc;border-color:red"
			size=30 /><span id="message" style="font-size: 30px;"></span></td>
			<td colspan="2">备注：<input type="text" id="memo"></td><td><a href="javascript:void(0)"	 class="easyui-linkbutton" onclick="javascript:move_wh()" iconCls="icon-ok">确认移库</a></td>
		</tr>
		<tr>
			<td colspan="6">已经录入<span id="catalog_count">0</span>个品种,共<span id="goods_count">0</span>个商品。
			
			</td>
			
		</tr>
		
		<tr>
		<td colspan="6">
		

		<table id="get_goods_sum" style="width: 100%; overflow-y: auto"
			title='商品分类统计' class="easyui-datagrid" rownumbers="true"
			 iconCls="qyd">
			<thead>
				<tr>


					
					<th field="name">商品名称</th>
					<th field="num">数量</th>

				</tr>
			</thead>

		</table>


		
		</td>
		</tr>
		<tr>
		<td colspan="6">
		

		<table id="goods_barcode_table" style="width: 100%; overflow-y: auto"
			title='已经录入的商品条码' class="easyui-datagrid"  rownumbers="true"
			 iconCls="qyd">
			<thead>
				<tr>


					<th field="barcode">商品条码</th>
					<th field="goods_id">商品编码</th>
					<th field="goods_name">商品名称</th>
					<th field="wh_id">仓库</th>
					
				</tr>
			</thead>

		</table>


		
		</td>
		</tr>
		
		</table>

</body>
</html>