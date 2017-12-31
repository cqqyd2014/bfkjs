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
<jsp:include page="../common/include_easyui2.jsp" flush="true" />

<script type="text/javascript">
function print_barcode(){
	window.open("barcode_print.action");   
}
	function make() {

		if($('#num').numberbox('getValue')==''){
			alert("数量不能为空");
			return ;
			}

		
		$.getJSON("make_new_barcode.action", {
			num : $("#num").val(),
			c_goods_id : $("#c_goods_id").val()
		}, function(result) {

			var field=result.msg;
			

				if (field.success) {
					show_new_barcode_table();
					

				} else {
					alert(field.body);

				}

			
			
		});
	}
	function show_new_barcode_table(){
		
		$("body").append('<div id="pload" style="position:fixed;top:30%;z-index:1200;background:url(../img/processing.gif) top center no-repeat;width:100%;height:100%;margin:auto auto;"></div>');
		
		$.getJSON("get_new_barcode_un_printed.action", {
			
		}, function(result) {
			var field=result.msg;
			var o=field.o;

			var n=o.length;
			$("#new_barcode_table").datagrid('loadData',{ total: n, rows: o });
			 $("#pload").remove();
			
			
		});

		
		

		}

	function make_ok() {

		$("#sn").empty();
		$.getJSON("barcode_printed.action", {
			
		}, function(result) {

			alert("待打印条码已经清空");
			window.location.href='make_new_barcode_init.action';
			
		});
		

	}



	$(document).ready(function() {
		
		show_new_barcode_table();
	});
	
</script>


</head>

<body style="width:97%;height:97%;">

	<h2>打印新条码</h2>

	<table width="100%" border="1px" class="box">


		<tr>
			<td>商品：</td>
			<td><s:select label="商品" id="c_goods_id" name="c_goods_id"
					list="giList" listKey="key" listValue="value" /></td>
		</tr>
		<tr>
			<td>条码数量：</td>
			<td><input type="text" id="num" name="num"
				class="easyui-numberbox" /></td>
		</tr>

		<tr>
			
			<td colspan="2" align="right"><a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:make()"
				iconCls="qyd">生成条码</a>
				<a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:print_barcode()"
				iconCls="qyd">打印条码</a><a href="javascript:void(0)"
				class="easyui-linkbutton"
				onclick="javascript:make_ok()"
				iconCls="qyd">打印完毕</a></td>
		</tr>
		<tr>
			<td colspan="2">
			
			<table id="new_barcode_table" title="新生成待打印条码" class="easyui-datagrid" rownumbers="true"
			style="width: 100%;"  iconCls="qyd">

			<thead>
				<tr>

					
					<th field="goods_id" >商品编码</th>
					<th field="goods_name" >商品名称</th>
					<th field="barcode" >条码</th>
				</tr>
			</thead>

		</table>
			
			
			
			
			</td>
		</tr>

		


	</table>


</body>
</html>
