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
<jsp:include page="../common/include_easyui2.jsp" flush="true" />




<script language='javascript' type='text/javascript'>



	var current_page ;
	var rows_in_page;


	function page_init(){
		show_order_list_table(current_page,rows_in_page);
		}



	var order_list_table_view = $
			.extend(
					{},
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
														$.each(
																		delivers,
																		function(
																				j,
																				deliver) {
																			cc.push('<a href=\'javascript:void(0)\' id=\'b_'
																							+ deliver.express_com
																							+ '\' class=\"easyui-linkbutton\" onclick=\'javascript:view_deliver_init(\"'
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
																			cc.push('black')

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
																		+ '</font><br>'
																		+'<div>操作：<a	href="javascript:void(0)" id=\"cancel_order\" class="easyui-linkbutton"	onclick="javascript:cancel_order_init(\''+rowData.order_no+'\')">取消订单</a></div>');

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

		ajax_start();
		$.getJSON("get_all_orders_pages.action", {
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
			ajax_stop();
			//alert("sdfsfds");
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#order_list_table').datagrid('loadData', result);

			show_order_list_table_pages(page);

		});
	}
	function show_order_list_table_pages(page) {
		$.getJSON("get_all_ordres_count.action", {
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

	$(document).ready(
					function() {
						current_page = 1;
						rows_in_page=<s:property value="#session.default_rows_in_page"/>;

						$('#rows_in_page').val(rows_in_page);

						view_deliver_ready();
						

						
						cancel_order_ready(page_init);
						
						
						
						dialog_init('print_logistics_bill');
						$('#wh_id').val('<s:property value="#session.default_warehouse" />');
			


						
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

												<s:iterator value="logistics_map" var="entry">
												$(
														"a#b_<s:property value="key"/>")
														.linkbutton(
																{
																	iconCls : 'express_<s:property value="key"/>'
																});
												</s:iterator>

											}
										});

						
												page_init();



					});
</script>






</head>

<body style="width: 95%; height: 95%;">

	<h2>所有订单</h2>
	



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
						6、商品名称：<input id="goods_name" type="text" />

			每页显示<input style="width: 50px"   id="rows_in_page" value=<s:property value="#session.default_rows_in_page" /> type="text"
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





	
	
	<script language='javascript' type='text/javascript'>


		
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
	
	
	
	
	
		
	

<jsp:include page="common/view_deliver.jsp" flush="true" />

<jsp:include page="common/cancel_order.jsp" flush="true" />
<jsp:include page="common/deliver_bill.jsp" flush="true" />
	<jsp:include page="../common/print_logistics_bill.jsp" flush="true" />
</body>
</html>

