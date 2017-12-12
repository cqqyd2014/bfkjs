<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>快递单</title>

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
<script type="text/javascript" src="../js/print_logistics_bill.js"></script>
<link rel="stylesheet" type="text/css" href="../js/print_logistics_bill.css">
<script type="text/javascript">


		
	

function print_bill(){
	//alert($('#sender_addr').val());

	print_express('',$('#logistics').val(),$('#sender').val(),$('#sender_com').val(),$('#sender_addr').val(),$('#sender_tel').val(),$('#something').val(),$('#receiver').val(),$('#receiver_addr').val(),$('#receiver_tel').val());
}




	function show_sn_list_table(){


		$.getJSON("AddVolGetTempSn.action", {
		}, function(result) {
			
			
				
			
				var n=result.length;

				$("#sn_list_table").datagrid('loadData',{ total: n, rows: result });
				
			
		});




		}

	$(document).ready(function() {
		$('#print_logistics_bill').dialog('close');

	});
</script>




</head>

<body style="width: 95%; height: 95%;">
	<h2>打印快递单</h2>

		<table width="100%" border="1px" class="box">
			<tr>
				<td width="10%">物流企业：</td>
				<td width="23%"><s:select name="logistics"
						id="logistics" list="logistics_map" listKey="key" listValue="value" /></td>

				

				<td width="10%"><span>发件人：</span></td>
				<td width="23%"><input type='text' id='sender'/></td>
			

				<td width="10%"><span>发件单位：</span></td>
				<td width="23%"><input type='text' id='sender_com'/></td>
			</tr>
			<tr>
				
				<td><span>发件人地址：</span></td>
				<td><textarea  rows="2" name="sender_addr" id="sender_addr"></textarea></td>

				<td><span>发件人电话：</span></td>
				<td ><input type='text' id='sender_tel'/></td>
				<td><span>物品：</span></td>
				<td><input type='text' id='something'/></td>
				
			</tr>
			<tr>
				<td>收件人：</td>
				<td><input type='text' id="receiver"/></td>
				<td>收件人电话：</td>
				<td><input type='text' id="receiver_tel"/></td>
				<td> 收件人地址：</td>
				<td><textarea  rows="2" name="receiver_addr" id="receiver_addr"></textarea></td>
			</tr>
			<tr>
			<td colspan='6' ALIGN='right'><a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="javascript:print_bill()"
					iconCls="icon-print">打印</a></td>
			</tr>



		<table id="sn_list_table" style="width: 100%; overflow-y: auto"
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
<jsp:include page="../common/print_logistics_bill.jsp" flush="true" /> 

</body>
</html>
