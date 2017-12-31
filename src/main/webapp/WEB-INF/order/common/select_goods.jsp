<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<script language='javascript' type='text/javascript'>





	function select_goods_ready() {

        //失去焦点事件绑定
		$("input",$("#goods_id").next("span")).blur(function(){  
			getGoodsPrice(true);
		}) 
            
       
		 dialog_init_mid('find_goods_div');
		 $('#goods_name').textbox('textbox').attr('readonly',true);
		 
		 $('#yue').numberbox('disable', true);
		 $('#unit').textbox('textbox').attr('readonly',true);
		 $('#c_price').numberbox('disable', true);
		 

		 var table_goods_price_view = $
		 .extend(
		 		{},
		 		$.fn.datagrid.defaults.view,
		 		{
		 			renderRow : function(target, fields, frozen, rowIndex,
		 					rowData) {
		 				var cc = [];
		 				cc.push('<td width=\'100px\'>' + rowData.goods_id
		 						+ '</td>');
		 				cc
		 						.push('<td width=\'100px\'><img  width="90" src="../img/product/<s:property value="#request.com_id" />/'
		 								+ rowData.goods_id
		 								+ '.jpg" /></td>');

		 				cc.push('<td width=\'100px\'>' + rowData.goods_name
		 						+ '</td>');
		 				cc.push('<td width=\'100px\'>' + rowData.price
		 						+ '</td>');
		 				cc.push('<td width=\'100px\'>' + rowData.unit
		 						+ '</td>');
		 				cc.push('<td width=\'100px\'>' + rowData.yue
		 						+ '</td>');

		 				return cc.join('');
		 			}
		 		});
		 
		 $('#table_goods_price').datagrid({
				view : table_goods_price_view,

				onDblClickRow : function(rowIndex, rowData) {
					DclickPrice(rowData);
				}
			});

	}
	function DclickPrice(rowData) {
		//alert(rowData.goods_id);
		$("#goods_id").textbox('setValue',rowData.goods_id);
		getGoodsPrice(false);
		$('#find_goods_div').dialog('close');

	}

	//模糊/精确查询goods_id
	function getGoodsPrice(fuzzy) {
		

		$.getJSON("<s:property value='#application.context_path'/>/wh/get_goods_info.action", {
			goods_id:$("#goods_id").textbox('getValue'),
			fuzzy:fuzzy
		}, function(result) {

			var field2=result.msg;
			ajax_authority(field2);

				if (field2.success) {

					 $('#yue').numberbox('enable', true);
					 
					 $('#c_price').numberbox('enable', true);
					var o = field2.o;
					var len=o.length;
					if (len>0) {
						if (len == 1) {

							var field=o[0];
								//填充商品名称等
								$("#goods_name").textbox('setValue',field.goods_name);
								$("#unit").textbox('setValue',field.unit);
								$("#goods_id").textbox('setValue',field.goods_id);
								$("#yue").numberbox('setValue', field.yue);
								$("#c_price")
										.numberbox('setValue', field.price);
								$("#goods_id").css("color", "black");
								$('#search_msg').html('<font color="red"></font>');
								 $("#c_count").numberbox('setValue', '1');
							
						} else {
							$("#goods_name").textbox('setValue',"");
							$("#unit").textbox('setValue',"");
							$("#c_price").numberbox('setValue', '0');
							$("#goods_id").css("color", "red");
							$("#yue").numberbox('setValue', "0");
							$('#search_msg').html('');
							 $("#c_count").numberbox('setValue', '0');
							 dialog_init_mid('find_goods_div');
							 
							 $("#table_goods_price").datagrid('loadData', {
									total : o.length,
									rows : o
								});
							 $('#find_goods_div').dialog('open');
							 
						}
					} else {
						$("#goods_name").textbox('setValue',"");
						$("#unit").textbox('setValue',"");
						$("#c_price").numberbox('setValue', '0');
						$("#goods_id").css("color", "red");
						$("#yue").numberbox('setValue', "0");
						$('#search_msg').html('<font color="red">没有复合条件的产品</font>');
						 $("#c_count").numberbox('setValue', '0');
					}

					$('#yue').numberbox('disable', true);
					 
					 $('#c_price').numberbox('disable', true);
					 
					 

				} else {
					$.messager.alert("操作提示", "获取单价错误！原因：" + field2.body,
							"error");

				}

			

		});

	}




	</script>

<div id="find_goods_div" class="easyui-dialog" title="商品" iconCls="qyd"
	style="width: 600px; height: 400px; padding: 10px;"
	toolbar="#findGoods-toolbar" buttons="#findGoods-buttons">

	<table id="table_goods_price" title="商品" class="easyui-datagrid"
		style="width: 100%; height: 100%;" idField="itemid" fitColumns="true"
		iconCls="qyd">

		<thead>
			<tr>

				<th field="itemid" width="100px">商品编码</th>
				<th field="goods_id" width="100px">商品图片</th>
				<th field="productid" width="100px">商品名称</th>
				<th field="listprice" width="100px" align="right">价格</th>
				<th field="unitcost" width="100px" align="right">单位</th>
				<th field="status" width="100px" align="center">库存数量</th>
			</tr>
		</thead>

	</table>



</div>
<div id="findGoods-toolbar">
	<table cellpadding="0" cellspacing="0" style="width: 100%">
		<tr>

			<td style="text-align: right">选中需要发货的商品，双击</td>
		</tr>
	</table>
</div>
<div id="findGoods-buttons" style="text-align: right">


	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="javascript:$('#find_goods_div').dialog('close')">以上都不合适，关闭选择商品窗口</a>
</div>


<table border="1" align="left" width="100%" class="box">
	<tr>
		<td width="15%">商品编码</td>
		<td width="18%"><input class="easyui-textbox" id="goods_id"
			data-options="prompt:'请输入产品编码的关键字...' " style="width:150px"/>
			<span id='search_msg'></span>
			</td>
		<td width="15%">商品名称</td>
		<td width="18%"><input class="easyui-textbox" id="goods_name" style="width:150px"/></td>
		<td width="15%">单位</td>
		<td width="18%"><input class="easyui-textbox" id="unit" style="width:150px"/></td>
	</tr>
	<tr>
		<td width="15%">发货数量</td>
		<td width="18%"><input id="c_count" class="easyui-numberbox" style="width:50px" data-options="min:0,precision:2"/><span>现余</span><input
			class="easyui-numberbox" id="yue" style="width:50px"></td>
		<td width="15%">单价（元）</td>
		<td width="18%"><input id="c_price" class="easyui-numberbox" style="width:150px"/></td>
		<td width="15%">备注</td>
		<td width="18%"></td>
	</tr>

</table>