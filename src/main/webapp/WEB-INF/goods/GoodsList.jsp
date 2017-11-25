<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>商品清单</title>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/qyd.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<script type="text/javascript">


function new_goods_detail(){
	$('#goods_id').val("");
	$('#goods_id').removeAttr("readonly");
	$('#goods_name').val("")
	$('#goods_name_short').val("");
	
	$('#unit').val("");
	$('#country').val("");
	
	$('#hs_code').val("");
	$('#barcode').numberbox('setValue', "");
	$('#not_air').attr("checked",false)
	$('#memo').val("");
	
	$('#in_use').prop("checked",false);
	
	$('#weight').numberbox('setValue',"");
	$('#spec').val("");
	$('#sn_code').val("");
	$('#goods_detail').dialog('open');
	$("#goods_detail").panel("move",{top:$(document).scrollTop() + ($(window).height()-600) * 0.5});
	
}

function save_goods_detail(){
	if ($('#goods_id').val()==""){
		alert("商品编码不能为空!");
		return ;
		}

	if ($('#weight').val()==""){
		alert("商品重量不能为空!");
		return ;
		}


$.getJSON("save_goods_detail.action", {
	goods_id:$('#goods_id').val(),
	goods_name:$('#goods_name').val(),
	in_use:$('#in_use').is(':checked'),
	goods_name_short:$('#goods_name_short').val(),
	unit:$('#unit').val(),
	country:$('#country').val(),
	hs_code:$('#hs_code').val(),
	barcode:$('#barcode').val(),
	not_air:$('#not_air').is(':checked'),
	memo:$('#memo').val(),
	weight:$('#weight').val(),
	spec:$('#spec').val(),
	sn_code:$('#sn_code').val()
	
	
}, function(result) {

	var obj = result.msg;

		if (obj.success) {
			alert("保存成功");
			window.location.href="goods_list_init.action";

		}
		else{
			alert(field.body);
			}
		

	

});

	
}


	function show_goods_list_table() {

		$.getJSON("get_goods_list.action", {}, function(result) {

			var obj=result.msg;

				if (obj.success) {
					var o = obj.o;
					var n = o.length;
					$("#goodsList_table").datagrid('loadData', {
						total : n,
						rows : o
					});

				}

			

		});

	}

	$(document).ready(function() {
		$('#goodsList_table').datagrid({

			onDblClickRow : function(rowIndex, rowData) {

				

				$.ajax({
					type : "post",
					url : "get_goods_detail.action",
					data : {
						goods_id : rowData.goods_id
					},
					async : false,
					success : function(result) {
						
						
						var obj = result.msg;

						

							if (obj.success) {

								var goods_detail=obj.o;
								$('#goods_id').val(goods_detail.goods_id);
								$("#goods_id").attr("readonly","readonly");
								$('#goods_name').val(goods_detail.goods_name);
								$('#goods_name_short').val(goods_detail.goods_name_short);
								
								$('#unit').val(goods_detail.unit_code);
								$('#country').val(goods_detail.country_code);
								
								$('#hs_code').val(goods_detail.hs_code);
								$('#barcode').numberbox('setValue', goods_detail.barcode);
								$('#not_air').attr("checked",goods_detail.not_air)
								$('#memo').val(goods_detail.memo);
								
								$('#in_use').prop("checked",goods_detail.in_use);
								
								$('#weight').numberbox('setValue',goods_detail.weight);
								$('#spec').val(goods_detail.spec);
								$('#sn_code').val(goods_detail.sn_code);


							}

						

					}
				});

				$('#goods_detail').dialog('open');
				$("#goods_detail").panel("move",{top:$(document).scrollTop() + ($(window).height()-600) * 0.5});
				//alert(rowData.contract_no);
			},
			pagination : true,
			//rownumbers: true, 
			fitColumns : false
		});

		
		show_goods_list_table();
		$('#goods_detail').dialog('close');
		
	});
</script>
</head>
<body  style="width: 98%; height: 98%;">

<h2>商品清单</h2>
<div align="right"><a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:new_goods_detail()">新商品</a></div>
<table id="goodsList_table" title="商品清单" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				<th field="goods_id">商品编码</th>
				<th field="goods_name">商品名称</th>
				<th field="goods_name_short">短名</th>
				<th field="hs_code">海关条码</th>
				<th field="in_use">启用</th>
				<th field="origin">原产国</th>
				<th field="unit">单位</th>
				<th field="weight">重量</th>
			</tr>
		</thead>

	</table>
	<div id="goods_detail" class="easyui-dialog" title="商品"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="
				iconCls: 'qyd',
				buttons: '#goods_detail_buttons'
			">
		<input type="hidden"  id="sn_code"/>
			<table width='100%'>
				<tr>
				<td width='10%'>商品编码：</td><td width='23%'><input type="text" id="goods_id" name="goods_id"
							/></td>
				<td width='10%'>商品名称：</td><td width='23%'><input type="text" id="goods_name" name="goods_name"
							/></td>
				<td width='10%'>单位：</td><td width='23%'><s:select name="unitList"  id="unit" list="unitList" listKey="key" listValue="value" /></td>
				</tr>
				<tr>

					<td>海关HS编码：</td><td><input type="text" id="hs_code" name="hs_code"/></td>
					<td>规范申报元素：</td><td><input type="text" id="spec" name="spec"/></td>
					<td>EAN13：</td><td><input type="text" id="barcode" name="barcode" class="easyui-numberbox"/></td>
					
				</tr>
				<tr>
					<td>原产国：</td><td><s:select name="countryList"  id="country" list="countryList" listKey="key" listValue="value" /></td>
					<td>重量：</td><td><input type="text" id="weight" name="weight" class="easyui-numberbox" precision="2"/></td>
					<td>仅限陆运：</td><td><input name="not_air" id="not_air" type="checkbox" value="" /></td>
				</tr>
				<tr>
				<td>备注：</td><td><input type="text" id="memo" name="memo" /></td>
				<td>简称：</td><td><input type="text" id="goods_name_short" name="goods_name_short" /></td>
				
				<td>启用：</td><td><input name="in_use" id="in_use" type="checkbox" /></td>
				</tr>
			
			</table>
			

			
		


	</div>
	<div id="goods_detail_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:save_goods_detail()">保存</a> 
			<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#goods_detail').dialog('close')">取消</a>
	</div>
</body>
</html>