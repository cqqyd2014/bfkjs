<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>复核发货</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="../common/include_easyui2.jsp" flush="true" />

<script language='javascript' type='text/javascript'>


var current_page=1;
var rows_in_page;

function page_init(){
	//获取数据
	show_package_list_table("get_wait_send_deliver_list.action","get_wait_send_deliver_count.action");
	}






	function send(deliver_no){

		
		$.messager.confirm('操作提示', '确认发货这个包裹'+deliver_no+'？', function(r){
			if (r){
				
				$.getJSON("send_package.action", {
					"deliver_no" : deliver_no,
					"weight":$('#weight_'+deliver_no).val(),
					"wh_id":$('#wh_id').combobox('getValue')

		}, function(result) {
					var field=result.msg;
					if (field.success){
					$.messager.alert("操作提示", "发货成功：" + field.body,
					"info");
					page_init();
					}
					else{

						$.messager.alert("操作提示", "发货失败！原因：" + field.body,
						"error");
					}
				});
			}
		});
	}





	var package_list_table_view = $
			.extend(
					{},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {

							var cc = [];

							cc
									.push('<table width=\'100%\' border=\'1\' class=\'box\'><tr><td colspan=\'6\' bgcolor=\'#cccccc\'><span style=\'color:white;background-color:');
							switch (rowData.deliver_bill_status) {
							case '分配单号':
								cc.push('#CC99CC');
								break;
							case '拣货完成':
								cc.push('#CC3399');
								break;
							case '分派发货':
								cc.push('#996666');
								break;
							case '发货完毕':
								cc.push('#666666');
								break;
							case '运单取消':
								cc.push('#666633');
								break;
							default:
								cc.push('CCCCCC');
							}
							//alert(rowData.order_type_name);
							cc
									.push(';\'>'
											+ rowData.deliver_bill_status
											+ '</span> 运单号：'
											+ rowData.deliver_no
											//+ '<img width=\'40\' height=\'20\'src=\'../img/ebusiness/'+rowData.order_ebusiness_code+'.jpg\'/>'
											+ '，订单号：'+rowData.order_no + ','
											+ '，分配发货时间：'+rowData.send_user_assgin_dat + '</td></tr>');
							//alert(rowData.send_user_assgin_dat);
							if (rowData.dbds != null) {
								var dbds = rowData.dbds;
								$.each(dbds,function(i, field) {
													if (i == 0) {
														var row_no = parseInt(rowIndex + 1,10)+ parseInt((current_page - 1)* $('#rows_in_page').val(),10);
														cc.push('<tr><td width=\'5%\' rowspan=\''
																		+ dbds.length
																		+ '\'>'
																		+ row_no
																		+ '<br>'
																		+ rowData.create_username
																		+ '</td><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		+ field.goods_name
																		+ '<br>编码：'
																		+ field.goods_id
																		+ '<br>条码：'
																		+field.goods_barcode
																		+'</td><td width=\'15%\' rowspan=\''
																		+ dbds.length
																		+ '\'>拣货打包人：'
																		+ rowData.create_username
																		+ '<br>打包时间：'
																		+ rowData.package_dat
																		+ '<br>包裹预估重量：'
																		+ rowData.package_weight
																		
																		+ '</td><td width=\'25%\' rowspan=\''
																		+ dbds.length
																		+ '\'>实际重量：<input type=\'text\' id=\'weight_'+rowData.deliver_no+'\' value=\''+rowData.package_weight+'\'><br>'
																		+' <a href=\"javascript:void(0)\" id=\'send_'+rowData.deliver_no+'\' onclick=\"javascript:send(\''+rowData.deliver_no+'\');\">发货</a>'
																		+ '<br>');

														/*
														var delivers = rowData.deliver_bills;
														$.each(delivers,function(j,deliver) {cc
																					.push('<a href=\'javascript:void(0)\' id=\'b_'
																							+ deliver.express_com
																							+ '\' class=\"easyui-linkbutton\" onclick=\'javascript:view_deliver_div(\"'
																							+ deliver.order_no
																							+ '\",\"'
																							+ deliver.seq
																							+ '\")\'>发货单'
																							+ deliver.seq
																							+ '<font color=\"');

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

																			

																			cc
																					.push('\"> [发货单状态：'
																							+ deliver.deliver_bill_status
																							+ ']</font></a><br>');

																		});
														*/

														cc.push('</td><td width=\'25%\' rowspan=\''
																		+ dbds.length
																		+ '\'>电商平台单号：'
																		+rowData.original_id
																		+'<br>快递：'
																		+ rowData.express_com_name+'/'+rowData.vehicle_name
																		+ '<br>运单号：'
																		+ rowData.express_no
																		+ '<br>取消状态：<font color=\'red\'>'
																		+ rowData.cancel_status
																		+ '</font><br>');

														cc.push('</td></tr>')

													} else {
													

														cc.push('<tr><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		 	+field.goods_name
																			+ '<br>编码：'
																			+ field.goods_id
																			+ '<br>条码：'
																			+field.goods_barcode
																			+'</td></tr>');
														

													}

												});
							}

							cc.push('<tr><td colspan=\'6\'>收货地址：'
											+ rowData.receive_user
											+ ','
											+ rowData.receive_province
											+ ','
											+ rowData.receive_city
											+ ','
											+ rowData.receive_district
											+ ','
											+ rowData.receive_addr
											+ '</td><tr><tr><td colspan=\'6\'>&nbsp</td></tr></table>');

							//alert(cc.join(''));
							return cc.join('');
						}

					});
</script>
<script language='javascript' type='text/javascript'>


	function package_list_table_Dclick(rowData) {

	}

	$(document)			.ready(
					function() {
						
						
						$('#wh_id').val('<s:property value="#session.default_warehouse" />');
						page_init();

						$('#package_list_table').datagrid(
										{
											view : package_list_table_view,

											onDblClickRow : function(rowIndex,
													rowData) {
												package_list_table_Dclick(rowData);
											},
											pagination : true,
											//rownumbers: true, 
											onLoadSuccess : function(data) {
												var rows=data.rows;
												
												for (var i=0;i<rows.length;i++){
													var row=rows[i];

													$('#weight_'+row.deliver_no).numberbox({
													    min:0,
													    precision:2
													});

													$('#send_'+row.deliver_no).linkbutton(
															{
																iconCls : 'icon-ok'
															});
													
													}

												/*
												<s:iterator value="logisticsList" var="entry">
												$("input#weight_<s:property value="key"/>")
														.linkbutton(
																{
																	iconCls : 'express_<s:property value="key"/>'
																});
												</s:iterator>
												*/

											}
										});

					




					});
</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>复核发货</h2>
	<div><span>当前仓库为</span><s:select id="wh_id"
						 list="wh_list" listKey="wh_id"
						listValue="wh_name" style=" width: 150px;" /><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_warehouse',$('#wh_id').combobox('getValue'))"
				iconCls="qyd">默认</a></div>



		<jsp:include page="common/search_package.jsp" flush="true" />



</body>
</html>

