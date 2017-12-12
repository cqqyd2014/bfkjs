<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盘点——按仓库和商品</title>

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

	function show_barcode_table(o){

		var n=o.length;

		$("#barcode_table").datagrid('loadData',{ total: n, rows: o });



		}
	function getWhGoodsIdBarcode(){

		$.getJSON("GetWhGoodsIdBarcode.action", {wh_id:$('#wh_id').val(),goods_id:$('#goods_id').val()
			
		}, function(result) {
			
			
				
			$.each(result, function(i, field) {
				
				if (field.success){


					show_barcode_table(field.o);
					

					
					}
				else{
					alert("错误"+field.body);
				}
				
			
		});
				
			
		});




		}




	




	function set_default(code, value) {

		$.ajax({
			type : "POST",
			url : "../system/SetUserDefault.action",
			data : {
				"par_code" : code,
				"par_value" : value
			},
			success : function(data) {

				if (data == "True") {
					alert("设置默认参数成功");

				} else {
					alert("设置默认参数失败");
				}
			}
		});
	}
	$(document).ready(function() {


		

		$("#barcode").keydown(function() {
			$("#message").empty();
			//商品条码
			if ($("#barcode").val().length == 14) {
				$("#barcode").attr("disabled","disabled");

				
				$.getJSON("ScanGoodsBarcode.action", {
					barcode : $("#barcode").val(),wh_id:$('#wh_id').val()
				}, function(result) {
					
					$.each(result, function(i, field) {
						
							if (field.success){
								//合格数据
								$("#message").append("<font color='blue'>"+field.body+"</font>");
								show_barcode_table(field.o);

								
								}
							else{
								$("#message").append("<font color='red'>"+field.body+"</font>");


								}
							$("#barcode").val("");
							$("#barcode").removeAttr("disabled");
							$("#barcode").focus();
						
					});
					
				});

				
			}
			//预包装条码
			if ($("#prepack").val().length == 10) {
				
				$("#prepack").attr("disabled","disabled");

				
				$.getJSON("AddVolTempAddSaan.action", {
					prepack : $("#prepack").val(),contract_m:$("#contract_m").val()
				}, function(result) {
					
					$.each(result, function(i, field) {
						
							if (field.success){
								//合格数据
								$("#message").append("<font color='blue'>"+field.body+"</font>");
								show_sn_list_table();

								
								}
							else{
								$("#message").append("<font color='red'>"+field.body+"</font>");


								}
							$("#prepack").val("");
							$("#prepack").removeAttr("disabled");
							$("#prepack").focus();
						
					});
					
				});

				
			}

		});



		

		
		$("#hw_type_d").click(function() {

			set_default("default_hwtype", $("#hw_type").val());
		});
		

	});
</script>	
	
</head>
<body style="width: 95%; height: 95%;">
<h2>盘点——按仓库和商品</h2>
<div>仓库：
<s:select label="仓库" name="wh_id" id="wh_id" list="whList" listKey="key" listValue="value" />
商品：
<s:select label="商品" name="goods_id" id="goods_id" list="giList" listKey="key" listValue="value" />
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:getWhGoodsIdBarcode()" iconCls="qyd">查询库存</a></div>
<div>请在此扫描商品条码：<input type="text" name="barcode" id="barcode"	style="height: 40px; font-size: 30px;  background-color: #ffffcc;border-color:red" size=30 /></div>
<div>请在此扫描预包装条码：<input type="text" name="prepack" id="prepack"	style="height: 40px; font-size: 30px;  background-color: #ffffcc;border-color:red" size=30 /></div>
<div id="message"></div>
<table id="barcode_table" style="width: 100%; overflow-y: auto"	title='存货条码' class="easyui-datagrid" idField="itemid"
			rownumbers="true" iconCls="qyd">
			<thead>
				<tr>


					<th field="goods_id">商品编码</th>
					<th field="goods_name">商品名称</th>
					<th field="barcode">条码</th>
					<th field="storage_id">库位</th>

				</tr>
			</thead>

</table>




</body>
</html>