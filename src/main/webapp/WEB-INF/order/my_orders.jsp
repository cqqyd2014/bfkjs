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





function page_init(){
	//获取数据
	show_order_list_table("get_my_orders_pages.action","get_my_ordres_count.action");
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


	function order_list_table_Dclick(rowData) {

	}

	$(document)			.ready(
					function() {
						
						search_order_ready();
						page_init();

						

						view_deliver_ready();
						

						
						cancel_order_ready(page_init);
						print_logistics_ready();

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

						




					});
</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>我的订单列表</h2>
	

	
<jsp:include page="common/search_order.jsp" flush="true" />
	
	
		
	

<jsp:include page="common/view_deliver.jsp" flush="true" />

<jsp:include page="common/cancel_order.jsp" flush="true" />
<jsp:include page="common/deliver_bill.jsp" flush="true" />
	<jsp:include page="../common/print_logistics_bill.jsp" flush="true" />

</body>
</html>

