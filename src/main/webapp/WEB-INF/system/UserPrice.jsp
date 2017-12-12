<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>进货到仓库</title>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<title>商品财务入账单价</title>
<script language='javascript' type='text/javascript'>


function add_new(){
	$('#new_goods_id_price').dialog('open');
	$("#new_goods_id_price").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 
	
}


function save_new(){

	$.getJSON("AddNewGoodsPrice.action",
			{new_goods_id:$('#new_goods_id').val(),new_start_dat:$('#new_start_dat').datebox('getValue'),new_end_dat:$('#new_end_dat').datebox('getValue'),new_price:$('#new_price').val()
		},function(result){
			$.each(result, function(i, field) {

				if (field.success) {
					alert('添加成功');
					window.location.href="UserPrice.action";
					

				} else {
					alert(field.body);

				}

			});
		
		
		

	 });
	
}

function goods_id_price_close(){

	$('#goods_id_price').dialog('close');

	
}

function new_update(){
	
	if ($('#start_dat').datebox('getValue')==''){
		alert ("开始时间不能为空");
		return;
		}
	
	if ($('#end_dat').datebox('getValue')==''){
		alert ("结束时间不能为空");
		return;
		}
	if ($('#price').val()==''){
		alert ("价格不能为空");
		return;
	}

	
	$.getJSON("GoodsIdPirceNewUpdate.action",
			{goods_id:$('#current_goods_id').val(),start_dat:$('#start_dat').datebox('getValue'),end_dat:$('#end_dat').datebox('getValue'),price:$('#price').val()
		},function(result){



			$.each(result, function(i, field) {
				
				if (field.success){
					//合格数据
					window.location.href="UserPrice.action";
					
					}
				else{
					alert(field.body);
				}
			
		});
			

		
		
		
		
		

	 });
}




function goods_price_table_dclick(rowData){

	$("#current_goods_id").val(rowData.goods_id);
	$("#h2").text(rowData.goods_name);
	show_goods_id_price_table();
	$('#goods_id_price').dialog('open');
	$("#goods_id_price").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 


	
}



function show_goods_id_price_table(){

	


	$.getJSON("GoodsIdPrice.action",{goods_id:$('#current_goods_id').val()},function(data){


		var n=data.length;

		$("#goods_id_price_table").datagrid('loadData',{ total: n, rows: data });
		
		
		

	 });
	
}

function show_goods_price_table(){

	

	
	$.getJSON("GetUserPrice.action",{},function(result){

		$.each(result, function(i, field) {
			
			if (field.success){
				var n = field.o;

				$("#goods_price_table").datagrid('loadData',{ total: n.length, rows: n });

				
				}
			else{
				alert("错误"+field.body);
			}
			
		
	});

		
		
		
		

	 });
	
}

$(document).ready(function() {
	$('#start_dat').datebox({
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

	$('#end_dat').datebox({
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

	$('#new_start_dat').datebox({
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

	$('#new_end_dat').datebox({
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




	
	$('#goods_price_table').datagrid({
		

		onDblClickRow : function(rowIndex, rowData) {
			
			goods_price_table_dclick(rowData);
		}
	});
	$('#new_goods_id_price').dialog('close');

	$('#goods_id_price').dialog('close');

	show_goods_price_table();

});
</script>


</head>
<body style="width: 95%; height: 95%;">
	<h2>用户默认商品金额</h2>
	
	<div align="right"><a href="#" class="easyui-linkbutton" iconCls="qyd"
			onclick="javascript:add_new()">新增</a></div>

	<table id="goods_price_table" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd" fitColumns="true"
		rownumbers="true" showFooter="true">

		<thead>
			<tr>

				<th field="goods_id" width="20%">商品编码</th>
				<th field="goods_name" width="25%">商品名称</th>

				<th field="unit" width="5%" align="right">单位</th>

				<th field="start_dat" width="10%" align="right">开始时间</th>
				<th field="end_dat" width="15%">结束时间</th>
				<th field="price" width="15%">价格</th>

			</tr>
		</thead>

	</table>
	<div id="goods_id_price" class="easyui-dialog"	style="padding: 5px; width: 600px; height: 400px;" title="用户默认商品价格"	iconCls="qyd" buttons="#goods_id_price-buttons">
		<span id="h2"></span> <input type="hidden" id="current_goods_id" />
		<table id="goods_id_price_table" class="easyui-datagrid" style="width: 100%;" iconCls="qyd"	fitColumns="true" rownumbers="true" showFooter="true">

			<thead>
				<tr>
					<th field="start_dat"  align="right">开始时间</th>
					<th field="end_dat" >结束时间</th>
					<th field="price" >价格</th>
				</tr>
			</thead>
		</table>

		<table style="width: 100%;" class="box" border="1" fitColumns="true">
			<tr>
				<td>开始时间</td>
				<td><input type="text" id="start_dat" /></td>
				<td>结束时间</td>
				<td><input type="text" id="end_dat" /></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input type="text" id="price" class="easyui-numberbox" /></td>
				<td colspan="2" align="right">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:new_update()" iconCls="icon-ok">新增或者更新</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:clear_order_detail()" iconCls="icon-clear">删除</a></td>
			</tr>
		</table>

		
	</div>

	<div id="goods_id_price-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"	onclick="javascript:goods_id_price_close()">关闭</a>

	</div>
	
	
	<div id="new_goods_id_price" class="easyui-dialog"	style="padding: 5px; width: 600px; height: 400px;" title="商品默认单价"	iconCls="qyd" buttons="#new_goods_id_price-buttons">
		<table>
			<tr>
				<td>商品：</td>
				<td><s:select name="new_goods_id" id="new_goods_id" list="goods_id_map" value="0" 	listKey="key" listValue="value" cssStyle=" width:200px;" /></td>
				<td>价格：</td>
				<td><input type="text" id="new_price" class="easyui-numberbox" /></td>
		</tr>
		<tr>
				<td>开始时间：</td>
				<td><input type="text" id="new_start_dat" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="new_end_dat" /></td>
		</tr>
		
		</table>
	</div>
	<div id="new_goods_id_price-buttons">
		<a href="#"	class="easyui-linkbutton" iconCls="icon-ok"	onclick="javascript:save_new()">保存</a>
		<a href="#"	class="easyui-linkbutton" iconCls="icon-cancel"	onclick="javascript:$('#new_goods_id_price').dialog('close')">取消</a>
	</div>
	
</body>
</html>