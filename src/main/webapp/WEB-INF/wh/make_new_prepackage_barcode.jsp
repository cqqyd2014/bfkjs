<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>打印条码</title>

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


function print_prepackage_barcode(){

	window.open("prepackage_barcode_print.action");  

	
}
	function make() {

		if($('#num').numberbox('getValue')==''){
			alert("条码数量不能为空");
			return ;
		}
		if($('#c_goods_count').numberbox('getValue')==''){
			alert("商品件数不能为空");
			return ;
		}

		
		$.getJSON("make_new_prepackage_barcode.action", {
			num : $("#num").val(),
			c_goods_count : $("#c_goods_count").val()
		}, function(result) {
			//$('#print_pre_ok_div').dialog('open');
			


			var field=result.msg;

				if (field.success) {
					show_new_pre_barcode_table();
					

				} else {
					alert(field.body);

				}

			
		});
	}
	function show_new_pre_barcode_table(){

		$.getJSON("get_unprinted_prepackage_barcode.action", {
			
		}, function(result) {
			var o=result.msg.o;
			var n=o.length;
			$("#new_pre_barcode_table").datagrid('loadData',{ total: n, rows: o });
			
			
		});


		}


	function make_ok() {


		$.getJSON("prepakcage_barcode_clean.action", {
			
		}, function(result) {

			alert("待打印条码已经清空");
			//$('#print_pre_ok_div').dialog('close');
			show_new_pre_barcode_table();
			
		});
		

	}



	$(document).ready(function() {
		
		show_new_pre_barcode_table();
	});
	
</script>


</head>

<body style="width:97%;height:97%;">

	<h2>打印预包装条码</h2>

	<table width="100%" border="1px" class="box">


		<tr>
			<td>商品件数：</td>
			<td><input label="商品件数" id="c_goods_count" name="c_goods_count" value="1"
					class="easyui-numberbox" /></td>
		</tr>
		<tr>
			<td>条码数量：</td>
			<td><input type="text" id="num" name="num" value="20"
				class="easyui-numberbox" /></td>
		</tr>

		<tr>
			<td colspan="2" align="right"><a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:make()"
				iconCls="qyd">生成条码</a><a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:print_prepackage_barcode()"
				iconCls="qyd">打印条码</a><a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:make_ok()"
				iconCls="qyd">打印完毕</a></td>
		</tr>
		<tr>
			<td colspan="2">
			
			<table id="new_pre_barcode_table" title="新生成待打印预包装条码" class="easyui-datagrid" rownumbers="true"
			style="width: 100%;"  iconCls="qyd">

			<thead>
				<tr>

					
					<th field="num" >内件数量</th>
					<th field="prepackage_barcode" >预包装条码</th>
					
				</tr>
			</thead>

		</table>
			
			
			
			
			</td>
		</tr>



	</table>


</body>
</html>
