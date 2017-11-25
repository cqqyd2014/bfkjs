<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>拣货打包列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js"></script>
<script type="text/javascript" src="../js/print_logistics_bill.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<link rel="stylesheet" type="text/css"
	href="../js/print_logistics_bill.css">



<script language='javascript' type='text/javascript'>


function pickup_barcode(){

	if ($("#pickup_barcode").val().length!=14
			&&$("#pickup_barcode").val().length!=22
			&&$("#pickup_barcode").val().length!=18){
		$.messager.alert("操作提示", "请确认条码长度，商品条码长度为14或者22，预包装条码为18！",
		"error");
		
		$("#pickup_barcode").focus();
		return;
		}
		

	$("#pickup_barcode").attr(
			"disabled", "disabled");

	//检测合法性
	$("#message").empty();

	$.getJSON("pickup_goodsbarcode.action",
					{
						pickup_barcode : $("#pickup_barcode").val(),
						order_no : $("#new_deliver_order_no").val(),
						seq:$('#new_deliver_seq').val(),
						wh_id: $('#new_deliver_div_wh_id').val(),
						"logistics" : $("#new_deliver_logistics").val(),
						"express_no" : $("#new_deliver_express_no").val(),
						"vehicle":$('#deliver_div_order_vehicle').val()
					},
					function(result) {
						var field = result.msg;

						if (field.success) {

							
							var html_message = "";
							//是否预包装发货
							if (field.body=='预包装发货'){
								html_message = html_message
								+ "<div><font color='blue'>预包装发货成功</font></div>";
								html_message = html_message
								+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

								$('#message').html(html_message);
								
								$.messager.alert("操作提示", "预包装发货成功",
								"info");
								$('#new_deliver_div').dialog('close');
								show_order_list_table(current_page, $('#rows_in_page')
										.val());
								
							}
							else{
								html_message = html_message
								+ "<div><font color='blue'>"
								+ field.body
								+ "</font></div>";

						html_message = html_message
								+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

						$('#message').html(html_message);
						var logistics = $('#new_deliver_logistics').val();

						var len = eval('express_no_len_'+ logistics);

						if (field.sound == 'item_picked_complet'
								&& $("#new_deliver_express_no").val().length == len) {
							//商品拣货完成，自动提交
							new_deiliver_save();
						} 
						else{
							show_new_deliver_picked_barcode_table();
							}

						}

							
							
							

						} else {
							var html_message = "";
							html_message = html_message
									+ "<div><font color='red'>"
									+ field.body
									+ "</font></div>";

							html_message = html_message
									+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

							$('#message').html(html_message);
						}

						$("#pickup_barcode").val("");
						$("#pickup_barcode").removeAttr("disabled");
						$("#pickup_barcode").focus();
					});
	
}


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

	function new_deliver_div(order_no, logistics, logistics_name, vehicle,
			vehicle_name) {

		$.getJSON("new_deliver_bill_init.action", {
			"order_no" : order_no

		}, function(result) {

			$('#new_deliver_order_no').val(order_no);
			$('#new_deliver_express_no').val("");
			$('#pickup_barcode').val("");
			$('#new_deliver_seq').val("");
			$('#new_deliver_div_wh_name').val($('#wh_id').text());
			//alert($('#wh_map').val());
			$('#new_deliver_div_wh_id').val($('#wh_id').val());
			$('#deliver_div_order_vehicle_name').text(vehicle_name);
			$('#deliver_div_order_vehicle').val(vehicle);
			$('#deliver_div_order_logistics_name').text(logistics_name);
			$('#deliver_div_order_logistics').val(logistics);
			//$('#new_deliver_div_wh_id').val(logistics);
			$('#new_deliver_logistics').val(logistics);
			$('#new_deliver_logistics_vehicle').val(vehicle);

			clearDataTable("new_deliver_picked_barcode_table");
			var field = result.msg;

			if (field.success) {
				//初始化成功，清空session中的拣货sn清单

				show_new_deliver_need_table();

				$('#message').text('');

				dialog_init('new_deliver_div');
				$('#new_deliver_div').dialog('open');
				

			} else {
				//alert("初始化session拣货清单出错");
				
				$.messager.alert("操作提示", "初始化发货单错误！原因：" + field.body,
				"error");

			}

		});

	}

	var order_list_table_view = $
			.extend({},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {

							var cc = [];

							cc.push('<table width=\'100%\' border=\'1\' class=\'box\'><tr><td colspan=\'6\' bgcolor=\'#cccccc\'><span style=\'color:white;background-color:');
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
							cc.push(';\'>'
											+ rowData.order_status
											+ '</span> 编号：'
											+ rowData.order_no
											+ '<img width=\'40\' height=\'20\'src=\'../img/ebusiness/'+rowData.order_ebusiness_code+'.jpg\'/>'
											+ rowData.original_id + ','
											+ rowData.order_dat + '</td></tr>');
							//alert(rowData.c_status);
							if (rowData.details != null) {
								var order_details = rowData.details;
								$.each(
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
														cc.push('<tr><td width=\'5%\' rowspan=\''
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

														if (rowData.wh_status == '等待拣货'
																|| rowData.wh_status == '拣货处理') {
															cc.push('<a href=\'javascript:void(0)\' id=\'b_qyd\' class=\'easyui-linkbutton\' onclick=\'javascript:new_deliver_div(\"'
																			+ rowData.order_no
																			+ '\",\"'
																			+ rowData.logistics
																			+ '\",\"'
																			+ rowData.logistics_name
																			+ '\",\"'
																			+ rowData.vehicle
																			+ '\",\"'
																			+ rowData.vehicle_name
																			+ '\")\'>拣货制发货单</a><br>');
															/*
															cc
																	.push('<a href=\'javascript:void(0)\' id=\'b_qyd\' class=\'easyui-linkbutton\' onclick=\'javascript:new_deliver_from_pre_package_div(\"'
																			+ rowData.order_no
																			+ '\")\'>拣预包装包裹发货</a><br>');
															*/
														}
														var delivers = rowData.deliver_bills;
														$.each(delivers,function(j,deliver) {
																			cc.push('<a href=\'javascript:void(0)\' id=\'b_'
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

														cc.push('</td><td width=\'25%\' rowspan=\''
																		+ order_details.length
																		+ '\'>默认快递：'
																		+ rowData.logistics_name
																		+ '<br>运输方式：'
																		+ rowData.vehicle_name
																		+ '<br>备注：<font color=\'red\'>'
																		+ rowData.memo
																		+ '</font><br>');

														cc.push('</td></tr>')

													} else {

														cc.push('<tr><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
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

							cc.push('<tr><td colspan=\'6\'>收货地址：'
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
	
		$.getJSON("get_wait_package_pages.action", {
			"page" : page,
			"rows" : rows,

			user_name : $('#user_name').val(),
			goods_name : $('#goods_name').val(),
			user_tell : $('#user_tell').val(),
			original_id : $('#original_id').val(),
			order_status:$('#order_status').val()

		}, function(result) {
			
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#order_list_table').datagrid('loadData', result);

			show_order_list_table_pages(page);

		});
	}
	function show_order_list_table_pages(page) {
		
		$.getJSON("get_wait_package_count.action", {

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

	<s:iterator value="experss_no_len_map" var="entry">

	var express_no_len_<s:property value="key"/> = <s:property value="value"/>;

	</s:iterator>
	$(document).ready(
					function() {
						$('#wh_id').val('<s:property value="#request.default_warehouse" />');
						dialog_init('print_logistics_bill');
						dialog_init('new_deliver_div');
						dialog_init('view_deliver_div');
						dialog_init('change_express_div');
						

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

						show_order_list_table(current_page, $('#rows_in_page').val());

						//初始化，隐藏对话框

						/*

							$('.deliver').click(function() {
								$("#message").empty();
								var order_no = $(this).next().attr("value");

								$("#order_no").val(order_no);
								sn_table_show();

								deliver_table_show();

								dialog.dialog("open");

							});

						 */

						$('#new_deliver_express_no').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});

						$("#new_deliver_express_no")
								.keydown(
										function() {
											var logistics = $(
													'#new_deliver_logistics')
													.val();

											var len = eval('express_no_len_'
													+ logistics);
											if ($("#new_deliver_express_no")
													.val().length == len) {

												$("#pickup_barcode").focus();

											}

										});
						$('#new_deliver_express_no2').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});

						$("#new_deliver_express_no2")
								.keydown(
										function() {
											var logistics = $(
													'#new_deliver_logistics2')
													.val();

											var len = eval('express_no_len_'
													+ logistics);

											if ($("#new_deliver_express_no2")
													.val().length == len) {
												//alert("ok");

												$("#pickup_prepac").focus();

											}

										});



						$('#pickup_barcode').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});
						$('#express_no').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});

						$("#pickup_barcode").keydown(
								function() {

											if ($("#pickup_barcode").val().length == 22) {
												
												pickup_barcode();
											}

										});
					});
</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>拣货打包列表</h2>
	<div><span>当前仓库为</span><s:select id="wh_id"
						name="wh_id" list="wh_map" listKey="key"
						listValue="value" style=" width: 150px;" /><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_warehouse',$('#wh_id').val())"
				iconCls="qyd">默认</a></div>



	<table class='box' style="width: 100%">
		<tr>
			<td><span> 1、订单状态： <SELECT id="order_status">
						<option VALUE="0" SELECTED>所有订单</option>
						<option VALUE="订单生成">订单生成</option>
						<option VALUE="订单已付">订单已付</option>
						<option VALUE="订单处理">订单处理</option>
						<option VALUE="发货完毕">发货完毕</option>
				</SELECT> 2、收件人姓名:<input id="user_name" type="text" /> 3、收件人手机:<input
					id="user_tell" type="text" /> 4、电商平台单号：<input id="original_id"
					type="text" />

			</span> <span style="float: right">每页显示<input id="rows_in_page"
					value=<s:property value="pageSize" /> type="text"
					class="easyui-numberbox" precision="0" />行<a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-search"
					onclick="javascript:show_order_list_table(1, $('#rows_in_page').val())">查询</a>
				</psan></td>
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










	<div id="new_deliver_div" title="拣货" class="easyui-dialog"
		style="padding: 5px; width: 600px; height: 400px;" iconCls="qyd"
		buttons="#new_deliver_div_buttons">
		<input type="hidden" id="new_deliver_order_no" /> <input
			type="hidden" id="new_deliver_seq">

		<div>
			<div>当前仓库：<span id="new_deliver_div_wh_name"></span>
			<input type="hidden" id='new_deliver_div_wh_id'>
				<span>订单默认快递为：</span><input type='hidden'
					id='deliver_div_order_logistics'><span
					id='deliver_div_order_logistics_name'></span><span>，默认运输方式为：</span><input
					type='hidden' id='deliver_div_order_vehicle'><span
					id='deliver_div_order_vehicle_name'></span>
			</div>
			<div>
				1、快递单信息
				<s:select id="new_deliver_logistics" name="new_deliver_logistics"
					list="logisticsList" listKey="key" listValue="value"
					style=" width: 150px;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_logistics_com',$('#new_deliver_logistics').val())"
					iconCls="qyd">默认</a> 运输方式
				<s:select id="new_deliver_logistics_vehicle"
					name="new_deliver_logistics_vehicle" list="vehicle_map"
					listKey="key" listValue="value" style=" width: 150px;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_logistics_vehicle',$('#new_deliver_logistics_vehicle').val())"
					iconCls="qyd">默认</a> <a id="b_new_express"
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:print_express($('#new_deliver_order_no').val(),'0000',$('#new_deliver_logistics').val(),$('#new_deliver_logistics_vehicle').val(),'BIG_',$('#wh_id').val(),'<s:property value="#request.userid" />')"
					iconCls="qyd">打印多联纸质快递单</a> <a id="b_electric_express"
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:print_express($('#new_deliver_order_no').val(),'0000',$('#new_deliver_logistics').val(),$('#new_deliver_logistics_vehicle').val(),'ELEC',$('#wh_id').val(),'<s:property value="#request.userid" />')"
					iconCls="qyd">打印电子面单</a>
			</div>
			<div>
				<input type="text" name="new_deliver_express_no"
					id="new_deliver_express_no" value=""
					style="height: 40px; font-size: 30px; width: 400px; background-color: #FFFAF0; border-color: red"
					size="30">
			</div>


		</div>

		<div>2、拣货信息</div>


		<div id="users-contain">

			<table id="new_deliver_need_table" title="待发清单"
				class="easyui-datagrid" style="width: 95%;" iconCls="qyd"
				fitColumns="true" rownumbers="true" showFooter="true">
				<thead>
					<tr>

						<th field="goods_name">商品名称</th>
						<th field="order_count">应发数量</th>
						<th field="yue">未发数量</th>

					</tr>
				</thead>





			</table>
		</div>


		<div>请在此扫描商品条码</div>
		<div>
			<input type="text" name="pickup_barcode" id="pickup_barcode" value=""
				style="height: 40px; font-size: 30px; width: 100%; background-color: #FFFAF0; border-color: red"
				size="30">
		</div>
		<div><a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:pickup_barcode()">拣货这个商品</a></div>
		<div id="message"></div>
		<div>
			<table id="new_deliver_picked_barcode_table" class="easyui-datagrid"
				title="已经拣货清单" style="width: 95%;" iconCls="qyd" fitColumns="true"
				rownumbers="true" showFooter="true">
				<thead>
					<tr>
						<th field="goods_name">品名</th>
						<th field="barcode">已拣货条码</th>

					</tr>
				</thead>

			</table>

		</div>
	</div>
	<div id="new_deliver_div_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:new_deliver_div($('#new_deliver_order_no').val())">清空发货单，重新填写</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:new_deiliver_save()">部分拣货，保存拣货信息并打印发货单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#new_deliver_div').dialog('close')">没想好先关闭</a>
	</div>


	<script language='javascript' type='text/javascript'>

		function show_new_deliver_picked_barcode_table() {

			$.getJSON("get_picked_barcode.action", {

			}, function(data) {

				var field=data.msg;

				if (field.success) {
					var o=field.o;
					var n = o.length;

					$("#new_deliver_picked_barcode_table").datagrid('loadData', {
						total : n,
						rows : o
					});

				}


				

				

			});
		}

		function show_new_deliver_need_table() {
			

			$.getJSON("get_need_pickup.action", {
				order_no : $("#new_deliver_order_no").val()
			}, function(data) {
				var field=data.msg;
				var o=field.o;
				$("#new_deliver_need_table").datagrid('loadData', {
					total : o.length,
					rows : o
				});

			});
		}

		function new_deiliver_save() {

			//检查快递单号是否正确
			if ($("#new_deliver_express_no").val() == '') {
				alert("快递单号不能为空");
				return;
			}
			//检查发货sn不能为空
			$.ajax({
				type : "post",
				url : "get_picked_barcode.action",

				async : false,
				success : function(data) {


					var field=data.msg;

					if (field.success) {
						var o=field.o;
						var n = o.length;

						if (n == 0) {
							alert("已拣货清单不能为空");
							return;
						}

					}
					

				}
			});
			//alert($("#new_deliver_div_wh_id").val());
			$.getJSON("save_new_deliver_bill.action", {
				"order_no" : $("#new_deliver_order_no").val(),
				"logistics" : $("#new_deliver_logistics").val(),
				"express_no" : $("#new_deliver_express_no").val(),
				"seq" : $("#new_deliver_seq").val(),
				"wh_id":$("#new_deliver_div_wh_id").val(),
				"vehicle":$('#deliver_div_order_vehicle').val()
			}, function(result) {
				var field=result.msg;

				if (field.success) {
						//body 是返回的seq序号
						window.open("../print/package_list_print.action?order_nos="
								+ $("#new_deliver_order_no").val() + "&seqs="
								+ field.body);
						$('#new_deliver_div').dialog('close');
						show_order_list_table(current_page, $('#rows_in_page')
								.val());

					} else {
						alert("错误" + field.body);
					}

				
			});

		}
	</script>





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
				<td colspan='2' align="right"><a id="b_kd100"
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:deliverViewKd100()" iconCls="qyd">查询物流<kuaidi100></kuaidi100></a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:print_express($('#order_no2').val(),$('#view_logistics_id').val(),'','','','','','','','','BIG_')"
					iconCls="qyd">打印</a> <a href="javascript:void(0)"
					class="easyui-linkbutton"
					onclick="javascript:change_express($('#order_no2').val(),$('#view_deliver_seq').val())"
					iconCls="qyd">更新运单</a></td>

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
					<th field="barcode">条码</th>
					<th field="goods_id">商品编码</th>
					<th field="goods_name">品名</th>
					

				</tr>
			</thead>

		</table>
		<div id='change_express_message'></div>

	</div>


	<div id="view_deliver_buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:print_express($('#order_no2').val(),$('#express_code2').val(),'','','','','','','','')">打印快递单</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:cancel_deliver_bill($('#order_no2').val(),$('#express_code2').val())">作废发货单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:view_deliver_print()">打印发货单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#view_deliver_div').dialog('close')">关闭</a>
	</div>
	<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');
		
		function cancel_deliver_bill(order_no,seq){
			//没有发出，或者发货清单为空的可以作废

			//cancel_deliver_bill
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

		function change_express(order_no, seq) {
			$('#change_order_no').val(order_no);
			$('#change_express_no').val("");
			$('#change_seq').val(seq);
			dialog_init('new_deliver_div');
			$('#change_express_div').dialog('open');
			

		}
	</script>
	<div id="change_express_div" class="easyui-dialog" title="更新物流信息"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#change_express_buttons'">
		<table class='box' width="100%">
			<tr>
				<td>订单编号：</td>
				<td><input type="text" readonly="readonly" id="change_order_no" />
				</td>
				<td>发货单序号：</td>
				<td><input type="text" readonly="readonly" id="change_seq" /></td>
			</tr>
			<tr>
				<td>物流企业：</td>
				<td><s:select id="change_express_logistics"
						name="change_express_logistics" list="logisticsList" listKey="key"
						listValue="value" style=" width: 150px;" /></td>
				<td>物流单号：</td>
				<td><input type="text" id="change_express_no" /></td>
			</tr>

		</table>

	</div>
	<div id="change_express_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:change_express_ok()">确定更新</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#change_express_div').dialog('close')">取消关闭</a>
	</div>
	<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');

		function change_express_ok() {
			var logistics = $('#change_express_logistics').val();

			var len = eval('express_no_len_' + logistics);
			if ($("#change_express_no").val().length == len) {

				//符合长度要求

				$
						.getJSON(
								"ChangeExpress.action",
								{
									"order_no" : $("#change_order_no").val(),
									logistics : $('#change_express_logistics')
											.val(),
									seq : $('#change_seq').val(),
									express_no : $('#change_express_no').val()
								},
								function(result) {
									$
											.each(
													result,
													function(i, field) {

														if (field.success) {
															alert('更新成功');
															$(
																	'#change_express_div')
																	.dialog(
																			'close');
															show_order_list_table(
																	current_page,
																	$(
																			'#rows_in_page')
																			.val());
															view_deliver_div(
																	$(
																			"#change_order_no")
																			.val(),
																	$(
																			'#change_seq')
																			.val());

														} else {
															$(
																	'#change_express_message')
																	.html(
																			'<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>');
															alert("错误"
																	+ field.body);
														}

													});
								});

			} else {
				alert('物流单号长度不对，应该为' + len + '位');

			}

		}
	</script>




	<jsp:include page="../common/print_logistics_bill.jsp" flush="true" />
</body>
</html>

