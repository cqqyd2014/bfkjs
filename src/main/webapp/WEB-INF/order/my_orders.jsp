<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>我的订单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js"></script>

<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">



<script type="text/javascript" src="../js/datagrid-detailview.js"></script>

<script language='javascript' type='text/javascript'>




	var current_page = 1;




	function view_deliver_div(order_no, seq) {

		$('#view_deliver_order_no').val(order_no);
		$('#view_deliver_seq').val(seq);

		$.getJSON("view_deliver_bill_init.action", {
			"order_no" : order_no,
			"seq" : seq

		}, function(result) {
			var field=result.msg;
			

			if (field.success) {
				var deliver_bill = field.o;
				var goods = field.o2;

				$('#deliver_status').text(deliver_bill.deliver_bill_status);
				$("#deliver_no2").val(deliver_bill.deliver_no);

				$("#order_no2").val(deliver_bill.order_no);
				$("#express_com2").val(deliver_bill.express_com_name);
				$("#express_no2").val(deliver_bill.express_no);
				$('#express_code2').val(deliver_bill.express_com);
				$('#view_prepack_sn').val(deliver_bill.pre_package_barcode);

				var n = deliver_bill.dbds.length;

				$("#deliverViewPickupTable").datagrid('loadData', {
					total : n,
					rows : deliver_bill.dbds
				});

				var n2 = goods.length;

				$("#deliverViewBarcodeTable").datagrid('loadData', {
					total : n2,
					rows : goods
				});

				$("#view_logistics_id").val(field.express_com_id);
				/*
				
				
				
				
				 

				

				 //var dataJson=JSON.parse(list1.list);
				
				 */

				 dialog_init('view_deliver_div');
				$('#view_deliver_div').dialog('open');

			}
			else{

				$.messager.alert("操作提示", "获取运单基本信息出错！原因：" + field.body,
				"error");
			}
		});
	}



	var order_list_table_view = $
			.extend(
					{},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {

							var cc = [];

							cc
									.push('<table width=\'100%\' border=\'1\' class=\'box\'><tr><td colspan=\'6\' bgcolor=\'#cccccc\'><span style=\'color:white;background-color:');
							switch (rowData.order_status) {
							case '订单生成':
								cc.push('#CC99CC');
								break;
							case '订单已付':
								cc.push('#CC3399');
								break;
							case '订单处理':
								cc.push('#996666');
								break;
							case '发货完毕':
								cc.push('#666666');
								break;

							default:
								cc.push('CCCCCC');
							}
							//alert(rowData.order_type_name);
							cc
									.push(';\'>'
											+ rowData.order_status
											+ '</span> 编号：'
											+ rowData.order_no
											+ '<img width=\'40\' height=\'20\'src=\'../img/ebusiness/'+rowData.order_ebusiness_code+'.jpg\'/>'
											+ rowData.original_id + ','
											+ rowData.order_dat + '</td></tr>');
							//alert(rowData.c_status);
							if (rowData.details != null) {
								var order_details = rowData.details;
								$
										.each(
												order_details,
												function(i, field) {
													if (i == 0) {
														var row_no = parseInt(
																rowIndex + 1,
																10)
																+ parseInt(
																		(current_page - 1)
																				* $(
																						'#rows_in_page')
																						.val(),
																		10);
														cc
																.push('<tr><td width=\'5%\' rowspan=\''
																		+ order_details.length
																		+ '\'>'
																		+ row_no
																		+ '<br>'
																		+ rowData.sys_user_name
																		+ '</td><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		+ field.goods_name
																		+ '<br>编码：'
																		+ field.goods_id
																		+ '<br>'
																		+ field.price
																		+ '元*<font color=\'red\'>'
																		+ field.num
																		+ '</font></td><td width=\'15%\' rowspan=\''
																		+ order_details.length
																		+ '\'>商品金额：'
																		+ rowData.original_amount
																		+ '<br>第三方支付：'
																		+ rowData.card_pay
																		+ '<br>折扣：'
																		+ rowData.discount
																		+ '<br>运费：'
																		+ rowData.ship_fee
																		+ '<br>实付：'
																		+ rowData.actual_amount
																		+ '</td><td width=\'25%\' rowspan=\''
																		+ order_details.length
																		+ '\'>仓库状态：'
																		+ rowData.wh_status
																		+ '<br>');

														
														var delivers = rowData.deliver_bills;
														$
																.each(
																		delivers,
																		function(
																				j,
																				deliver) {
																			cc
																					.push('<a href=\'javascript:void(0)\' id=\'b_'
																							+ deliver.express_com
																							+ '\' class=\"easyui-linkbutton\" onclick=\'javascript:view_deliver_div(\"'
																							+ deliver.order_no
																							+ '\",\"'
																							+ deliver.seq
																							+ '\")\'>发货单'
																							+ deliver.seq
																							+ '<font color=\"');
																			/*
																			switch (deliver.deliver_bill_status) {
																			case '分配单号':
																				cc.push('#CCCCCC');
																				break;
																			case '拣货完成':
																				cc.push('#CCCC99');
																				break;
																			case '发货完毕':
																				cc.push('#996699');
																				break;
																			

																			default:
																				cc.push('CCCCCC');
																			}

																			*/
																			cc.push('black');

																			cc.push('\"> [发货单状态：'
																							+ deliver.deliver_bill_status
																							+ ']</font></a><br>');

																		});

														cc
																.push('</td><td width=\'25%\' rowspan=\''
																		+ order_details.length
																		+ '\'>默认快递：'
																		+ rowData.logistics_name
																		+ '<br>运输方式：'
																		+ rowData.vehicle_name
																		+ '<br>备注：<font color=\'red\'>'
																		+ rowData.memo
																		+ '</font><br>'
																		+'<div>操作：<a	href="javascript:void(0)" id=\"cancel_order\" class="easyui-linkbutton"	onclick="javascript:cancel_order(\''+rowData.order_no+'\')">取消订单</a></div>');

														cc.push('</td></tr>')

													} else {

														cc
																.push('<tr><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		+ field.goods_name
																		+ '<br>编码：'
																		+ field.goods_id
																		+ '<br>'
																		+ field.price
																		+ '元*<font color=\'red\'>'
																		+ field.num
																		+ '</font></td></tr>');

													}

												});
							}

							cc
									.push('<tr><td colspan=\'6\'>收货地址：'
											+ rowData.user_name
											+ ','
											+ rowData.tell
											+ ','
											+ rowData.tell2
											+ ','
											+ rowData.province
											+ ','
											+ rowData.city
											+ ','
											+ rowData.district
											+ ','
											+ rowData.user_addr
											+ '</td><tr><tr><td colspan=\'6\'>&nbsp</td></tr></table>');

							//alert(cc.join(''));
							return cc.join('');
						}

					});
</script>
<script language='javascript' type='text/javascript'>
	function show_order_list_table(page, rows) {
		//alert($('input[name="order_status"]:checked ').val());
		current_page = page;

		$.getJSON("get_my_orders_pages.action", {
			"page" : page,
			"rows" : rows,

			user_name : $('#user_name').val(),
			goods_name : $('#goods_name').val(),
			user_tell : $('#user_tell').val(),
			original_id : $('#original_id').val(),
			order_status:$('#order_status').val(),
			express_no:$('#express_no').val(),
			barcode:$('#goods_barcode').val()

		}, function(result) {
			//alert("sdfsfds");
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#order_list_table').datagrid('loadData', result);

			show_order_list_table_pages(page);

		});
	}
	function show_order_list_table_pages(page) {
		$.getJSON("get_my_ordres_count.action", {
			user_name : $('#user_name').val(),
			goods_name : $('#goods_name').val(),
			user_tell : $('#user_tell').val(),
			original_id : $('#original_id').val(),
			order_status:$('#order_status').val(),
			express_no:$('#express_no').val(),
			barcode:$('#goods_barcode').val()

		}, function(result) {
			

				
					var count = parseInt(result, 10);
					var rows_in_page = parseInt($('#rows_in_page').val(), 10);

					var pages = Math.ceil(count / rows_in_page);
					//alert(pages);
					var p = $('#order_list_table').datagrid('getPager');
					//alert(page);
					$(p).pagination({
						pageNumber : page,
						total : count,
						pageSize : rows_in_page,//每页显示的记录条数，默认为10   
						pageList : [ rows_in_page ],//可以设置每页记录条数的列表   
						beforePageText : '第',//页数文本框前显示的汉字   
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
						onSelectPage : function(pageNumber, pageSize) {
							current_page = pageNumber;

							show_order_list_table(pageNumber, pageSize);
							$("html,body").finish().animate({
								"scrollTop" : "0px"
							}, 900);
						}

					});

				

			
		});

	}

	function order_list_table_Dclick(rowData) {

	}

	$(document)			.ready(
					function() {
						
						dialog_init('view_route_div');
						dialog_init('view_deliver_div');
						dialog_init_mid('cancel_order_div');

						$('#order_list_table').datagrid(
										{
											view : order_list_table_view,

											onDblClickRow : function(rowIndex,
													rowData) {
												order_list_table_Dclick(rowData);
											},
											pagination : true,
											//rownumbers: true, 
											onLoadSuccess : function(data) {

												$("a#b_qyd").linkbutton({
													iconCls : 'qyd'
												});
												$("a#cancel_order").linkbutton({
													iconCls : 'icon-cancel'
												});
												<s:iterator value="logisticsList" var="entry">
												$(
														"a#b_<s:property value="key"/>")
														.linkbutton(
																{
																	iconCls : 'express_<s:property value="key"/>'
																});
												</s:iterator>

											}
										});

						show_order_list_table(current_page, $('#rows_in_page')
								.val());




					});
</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>我的订单列表</h2>
	



	<table class='box' style="width: 100%">
		<tr>
			<td>1、订单状态： <SELECT id="order_status">
						<option VALUE="0" SELECTED>所有订单</option>
						<option VALUE="订单生成">订单生成</option>
						<option VALUE="订单已付">订单已付</option>
						<option VALUE="订单处理">订单处理</option>
						<option VALUE="发货完毕">发货完毕</option></SELECT>
						2、收件人姓名:<input id="user_name" type="text" style="width: 70px"  />
						3、收件人手机:<input id="user_tell" type="text" />
						4、电商平台单号：<input id="original_id" type="text" />
						5、运单号：<input id="express_no" type="text" />
						6、商品条码：<input id="goods_barcode" type="text" />

			每页显示<input style="width: 50px"   id="rows_in_page" value=<s:property value="pageSize" /> type="text"
					class="easyui-numberbox" precision="0" />行
					<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_rows_in_page',$('#rows_in_page').numberbox('getValue'))"
				iconCls="qyd">默认</a>
					
					<a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-search"
					onclick="javascript:show_order_list_table(1, $('#rows_in_page').val())">查询</a>
				</td>
		</tr>
		<tr>
			<td>
				<table id="order_list_table" style="width: 100%" pagination="true"
					sortName="itemid" sortOrder="desc" singleSelect="true"
					fitColumns="true">

				</table>

			</td>
		</tr>
	</table>

















	<div id="view_deliver_div" class="easyui-dialog" title="发货单"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#view_deliver_buttons'">

		<input type="hidden" id="view_deliver_order_no"><input
			type="hidden" id="view_deliver_seq"><input type="hidden"
			id="view_logistics_id">


		<table width="100%" class='box'>
			<tr>
				<td colspan='2'>发货仓库：<span id="view_deliver_div_wh_name"></span>发货单状态：<span id="deliver_status">发货单 </span></td>
			</tr>
			<tr>
				<td width="50%">订单编号：<input type="text" name="order_no2"
					id="order_no2" readonly="readonly" /></td>
				<td width="50%" colspan="2">发货单号：<input type="text"
					name="deliver_no2" id="deliver_no2" style="width: 250px;"
					readonly="readonly" /></td>

			</tr>
			<tr>
				<td>快递公司：<input type="text" name="express_com2"
					id="express_com2" readonly="readonly" /> <input type="hidden"
					name="express_code2" id="express_code2">
				</td>
				<td>快递单号：<input type="text" name="express_no2"
					id="express_no2" readonly="readonly" /> <input type="hidden"
					name="seq" id="seq">
				</td>



			</tr>
			<tr>
				<td colspan='2'>预包装编号：<input type="text" name="view_prepack_sn"
					id="view_prepack_sn" readonly="readonly" /></td>
				
			<tr>
				<td colspan='2' align="right"><a 
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:view_route($('#order_no2').val(),$('#view_deliver_seq').val())" iconCls="qyd">查看顺丰路由</a>
					</td>

			</tr>



		</table>
		<hr>
		<table id="deliverViewPickupTable" class="easyui-datagrid" title="发货清单"
			style="width: 95%;" true" rownumbers="true" fitColumns="true">
			<thead>
				<tr>
					<th field="goods_id">商品编码</th>
					<th field="goods_name">品名</th>
					<th field="num">应发数量</th>
					<th field="sended_count">本次发货</th>
					<th field="unit">单位</th>

				</tr>
			</thead>

		</table>
		<hr>

		<table id="deliverViewBarcodeTable" class="easyui-datagrid"
			title="条码清单" style="width: 95%;" true" rownumbers="true"
			fitColumns="true">
			<thead>
				<tr>
					<th field="goods_barcode">条码</th>
					<th field="goods_id">商品编码</th>
					<th field="goods_name">品名</th>
					

				</tr>
			</thead>

		</table>
		<div id='change_express_message'></div>

	</div>


	<div id="view_deliver_buttons">

		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#view_deliver_div').dialog('close')">关闭</a>
	</div>
	
	
	
	<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');
		
		
		function view_route(order_no,seq){



			clearDataTable('view_route_table');
			//只对顺丰电子面单有效
			$.getJSON("../express/sf/get_route.action", {
				
				order_no :order_no,
				seq : seq

			}, function(result) {
				var field=result.msg;
				

				if (field.success) {
					if (field.o==null){
						$.messager.alert("操作提示", "还没有路由更新" ,
						"info");
						}
					else{
						var data=field.o;
						
						
						
						$('#view_route_table').datagrid('loadData', data);
							$('#view_route_div').dialog('open');
						}
					

					} else {
						$.messager.alert("操作提示", "查询顺丰路由出错！原因：" + field.body,
						"error");
						

					}
					

				
			});
			
			}
		
		function cancel_deliver_bill(order_no,seq){
			//没有发出，或者发货清单为空的可以作废

			cancel_deliver_bill
			$.getJSON("cancel_deliver_bill.action", {
				
				order_no : $("#view_deliver_order_no").val(),
				seq : $("#view_deliver_seq").val()

			}, function(result) {
				var field=result.msg;
				

				if (field.success) {
					$.messager.alert('操作提示','"取消成功','info');
						//alert("取消成功");
						$('#view_deliver_div').dialog('close');
						show_order_list_table(current_page, $('#rows_in_page')
								.val());

					} else {
						$.messager.alert("操作提示", "取消运单出错！原因：" + field.body,
						"error");
						

					}
					

				
			});

			}


	</script>
	<div id="view_route_div" class="easyui-dialog" title="查看路由"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#view_deliver_buttons'">

		<table id="view_route_table" class="easyui-datagrid" title="查看路由"
			style="width: 95%;" true" rownumbers="true" fitColumns="true">
			<thead>
				<tr>
					<th field="accept_time">时间</th>
					<th field="accept_addr">地址</th>
					<th field="remark">描述</th>
					

				</tr>
			</thead>

		</table>


	</div>


	<div id="view_route_buttons">

		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#view_route_div').dialog('close')">关闭</a>
	</div>



<jsp:include page="common/cancel_order.jsp" flush="true" />

</body>
</html>

