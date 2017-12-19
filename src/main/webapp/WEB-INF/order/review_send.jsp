<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>复核发出列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="../common/include_easyui2.jsp" flush="true" />

<script language='javascript' type='text/javascript'>



var current_page=1;


function page_init(){
	//获取数据
	show_package_list_table("get_all_orders_pages.action","get_all_ordres_count.action");
	}








	var package_list_table_view = $.extend(		{},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {

							var cc = [];

							cc.push('<table width=\'100%\' border=\'1\' class=\'box\'><tr><td colspan=\'6\' bgcolor=\'#cccccc\'><span style=\'color:white;background-color:');
							switch (rowData.c_status) {
							case '正在拣货':
								cc.push('#996699');
								break;
							case '拣货完成':
								cc.push('#CC9999');
								break;
							case '部分拣货':
								cc.push('#CC9999');
								break;
							case '订单关闭':
								cc.push('#666666');
								break;
							case '部分退货':
								cc.push('#669999');
								break;
							default:
								cc.push('CCCC99');
							}

							cc.push(';\'>' + rowData.c_status + '</span> 编号：'
									+ rowData.order_no +'<img width=\'40\' height=\'20\'src=\'../img/ebusiness/'+rowData.order_type_name+'.jpg\'/>'+rowData.original_id+ '</td></tr>');
							//alert(rowData.c_status);
							if (rowData.details != null) {
								var order_details = rowData.details;
								$.each(order_details,
												function(i, field) {
													if (i == 0) {
														var row_no=parseInt(rowIndex+1, 10)+parseInt((current_page-1)*$('#rows_in_page').val(),10);
														cc.push('<tr><td width=\'5%\' rowspan=\''+order_details.length+'\'>'+row_no+'</td><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		+ field.goods_name
																		+ '<br>编码：'
																		+ field.goods_id
																		+ '<br>'
																		+ field.price
																		+ '元*<font color=\'red\'>'
																		+ field.c_count
																		+ '</font></td><td width=\'15%\' rowspan=\''+order_details.length+'\'>商品金额：'
																		+ rowData.original_amount
																		+ '<br>第三方支付：'
																		+ rowData.card_pay
																		+ '<br>折扣：'
																		+ rowData.discount
																		+ '<br>运费：'
																		+ rowData.ship_fee
																		+ '<br>实付：'
																		+ rowData.actual_amount
																		+ '</td><td width=\'25%\' rowspan=\''+order_details.length+'\'>发货仓库：'+rowData.wh_name+'<br>');

														
														

															var delivers = rowData.delivers;
															$.each(delivers,function(j,deliver) {
																
																				cc.push('<a href=\'javascript:void(0)\' id=\'b_'+deliver.express_com+'\' class=\"easyui-linkbutton\" onclick=\'javascript:view_deliver_div(\"'
																								+ deliver.order_no
																								+ '\",\"'
																								+ deliver.seq
																								+ '\")\'>发货单'
																								+ deliver.seq
																								+ '<font color=\"');
																				if (deliver.if_send == '未发') {
																					cc.push('#996699');
																				} else {
																					cc.push('#CCCC99');
																				}
																				cc.push('\"> ['+ deliver.if_send+ ']</font></a><br>');

																			});

														

														cc.push('</td><td width=\'25%\' rowspan=\''+order_details.length+'\'>备注：<font color=\'red\'>'+rowData.memo+'</font><br>');

														
														cc.push('</td></tr>')

													} else {

														cc.push('<tr><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		+ field.goods_name
																		+ '<br>编码：'
																		+ field.goods_id
																		+ '<br>'
																		+ field.price
																		+ '元*<font color=\'red\'>'
																		+ field.c_count
																		+ '</font></td></tr>');

													}

												});
							}

							
							cc.push('<tr><td colspan=\'6\'><img width=\'25px\' src=\'../img/icon/icon_87.jpg\'/>'
									+ rowData.user_address +'<img width=\'25px\' src=\'../img/icon/icon_47.jpg\'/>'+rowData.user_name+'<img width=\'25px\' src=\'../img/icon/icon_93.jpg\'/>'+rowData.tell+ '<img width=\'25px\' src=\'../img/icon/icon_89.jpg\'/>'+rowData.order_dat+'</td><tr><tr><td colspan=\'6\'>&nbsp</td></tr></table>');

							//alert(cc.join(''));
							return cc.join('');
						}

				   

				    

				   
					
					});


	function order_list_table_Dclick(rowData) {

	}

	<s:iterator value="experss_no_len_map" var="entry">
    
    
    var express_no_len_<s:property value="key"/>=<s:property value="value"/>;
    
   </s:iterator>
	$(document).ready(function() {

		 
	     



	     

						$('#package_list_table').datagrid({
							view : package_list_table_view,

							onDblClickRow : function(rowIndex, rowData) {
								order_list_table_Dclick(rowData);
							},
							pagination : true,
							//rownumbers: true, 
							fitColumns : false,
							onLoadSuccess:function(data){ 
								
								

								
								$("a#b_qyd").linkbutton({  iconCls:'qyd' });

								<s:iterator value="logistics_map" var="entry">  
								$("a#b_<s:property value="key"/>").linkbutton({  iconCls:'express_<s:property value="key"/>' });
							     </s:iterator>

								
								

				            }
						});

						show_order_list_table(current_page,$('#rows_in_page').val());

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



						$("#new_deliver_express_no").keydown(function() {
							var logistics=$('#new_deliver_logistics').val();
							
							var len=eval('express_no_len_'+logistics);
							if ($("#new_deliver_express_no").val().length == len) {

								$("#pickup_barcode").focus();
								
							}

							


						});
						$('#new_deliver_express_no2').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});



						$("#new_deliver_express_no2").keydown(function() {
							var logistics=$('#new_deliver_logistics2').val();
							
							var len=eval('express_no_len_'+logistics);
							
							if ($("#new_deliver_express_no2").val().length == len) {
								//alert("ok");

								$("#pickup_prepac").focus();
								
							}

							


						});

						

						$('#pickup_prepac').keypress(function(e) {

							if (e.keyCode == 13) {

								e.preventDefault();

							}
						});

						$("#pickup_prepac").keydown(function() {

									if ($("#pickup_prepac").val().length == 10) {
										$("#pickup_prepac").attr("disabled","disabled");

										//检测合法性
										

										$.getJSON("PickupPrePackBarcode.action",
														{pickup_prepac : $("#pickup_prepac").val(),
															order_no : $("#new_deliver_prepackage_order_no2").val()
														},
														function(result) {

															$.each(result,function(i,field) {

																				if (field.success) {
																					//合格数据
																					var html_message ="<div><font color='blue'>"
																					+ field.body
																					+ "</font></div>";

																			html_message = html_message
																					+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

																			$('#pre_message').html(html_message);
																					
																					show_deliverPrePacTable(field.o);
																					
																					$('#save_deliver_from_pre_package_button_ok').linkbutton('enable');

																					var logistics=$('#new_deliver_logistics2').val();
																					
																					var len=eval('express_no_len_'+logistics);
																					
																					if (field.sound=='picked_ok'&&$("#new_deliver_express_no2").val().length == len){
																						save_deliver_from_pre_package_div();

																						}
																					

																				} else {
																					alert(field.body);
																					$("#pickup_prepac").removeAttr("disabled");
																					$("#pickup_prepac").focus();

																					var html_message ="<div><font color='red'>"
																					+ field.body
																					+ "</font></div>";

																			html_message = html_message
																					+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

																			$('#pre_message').html(html_message);
																					

																				}

																				
																				

																			});
															

														});

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

						$("#pickup_barcode").keydown(function() {

											if ($("#pickup_barcode").val().length == 14) {
												$("#pickup_barcode").attr("disabled",
														"disabled");

												//检测合法性
												$("#message").empty();

												$.getJSON("PickupGoodsBarcode.action",{
																	pickup_barcode : $("#pickup_barcode").val(),
																	order_no : $("#new_deliver_order_no").val()
																},
																function(result) {

																	$.each(result,function(i,field) {

																						if (field.success) {
																							//合格数据
																							var html_message = "";

																							html_message = html_message
																									+ "<div><font color='blue'>"
																									+ field.body
																									+ "</font></div>";

																							html_message = html_message
																									+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

																							$('#message').html(html_message);
																							var logistics=$('#new_deliver_logistics').val();
																							
																							var len=eval('express_no_len_'+logistics);
																							
																							if (field.sound=='picked_ok'&&$("#new_deliver_express_no").val().length == len){
																								//商品拣货完成，自动提交
																								new_deiliver_save();
																								}
																							else{
																								show_new_deliver_picked_sn_table();

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
																	

																});

											}

										});
					});







</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>复核发货列表</h2>
	<table class='box' style="width: 100%">
	<tr><td>
	
	<span style="float:right">每页显示<input id="rows_in_page" value=<s:property value="pageSize" /> type="text" class="easyui-numberbox" precision="0" />行<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" onclick="javascript:show_order_list_table(1, $('#rows_in_page').val())">查询</a></psan>
	</td></tr>
	</table>
	
		<table id="order_list_table" style="width: 100%" pagination="true"
			sortName="itemid" sortOrder="desc" singleSelect="true"
			fitColumns="true">

		</table>

	



	<div id="new_deliver_div" title="拣货" class="easyui-dialog"
		style="padding: 5px; width: 600px; height: 400px;" iconCls="qyd"
		buttons="#new_deliver_div_buttons">
		<input type="hidden" id="new_deliver_order_no" />

		
		<div>
			<div>1、快递单信息
			<s:select id="new_deliver_logistics" name="new_deliver_logistics" list="logisticsList" listKey="key" listValue="value" style=" width: 150px;" />
			<a	href="javascript:void(0)" class="easyui-linkbutton"	onclick="javascript:set_default('default_logistics_com',$('#new_deliver_logistics').val())"	iconCls="qyd">默认</a>
			<a id="b_new_express" href="javascript:void(0)"	class="easyui-linkbutton" onclick="javascript:print_express($('#new_deliver_order_no').val(),$('#new_deliver_logistics').val(),'','','','','','','','')" iconCls="qyd">打印快递单</a>
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

			<table id="new_deliver_need_table" title="待发清单" class="easyui-datagrid"
				style="width: 95%;" iconCls="qyd"
				fitColumns="true" rownumbers="true" showFooter="true">
				<thead>
					<tr>

						<th field="d_name">品名</th>
						<th field="d_count">应发数量</th>
						<th field="d_yue">未发数量</th>

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
		<div id="message"></div>
		<div>
			<table id="new_deliver_picked_sn_table" class="easyui-datagrid" title="已经拣货清单"
				style="width: 95%;" iconCls="qyd"
				fitColumns="true" rownumbers="true" showFooter="true">
				<thead>
					<tr>
						<th field="goods_name" >品名</th>
						<th field="barcode" >已拣货条码</th>

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

		function deiliverNewClean() {

			//同步清除暂存信息，异步跟新
			$.ajax({
				type : "post",
				url : "DeiliverNewCleanTemp.action",
				data : "order_no=" + $("#deliverNewOrderNo").val(),
				async : false,
				success : function(data) {
					deliverNewSnTableShow();

					deliverNeedTableShow();

				}
			});

		}
		function show_new_deliver_picked_sn_table() {

			$.getJSON("DeliverNewSnTableShow.action", {

			}, function(data) {

				var n = data.length;

				$("#new_deliver_picked_sn_table").datagrid('loadData', {
					total : n,
					rows : data
				});

			});
		}

		function show_new_deliver_need_table() {

			$.getJSON("DeliverNewNeedTable.action", {
				orderNo : $("#new_deliver_order_no").val()
			}, function(data) {
				$("#new_deliver_need_table").datagrid('loadData', {
					total : data.length,
					rows : data
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
				url : "DeliverNewSnTableShow.action",

				async : false,
				success : function(data) {
					if (data == '[]') {
						alert("发货清单不能为空");
						return;
					}

				}
			});

			$.getJSON("NewDeliverSave.action", {
				"order_no" : $("#new_deliver_order_no").val(),
				"logistics" : $("#new_deliver_logistics").val(),
				"express_no" : $("#new_deliver_express_no").val()
			}, function(result) {
				$.each(result, function(i, field) {

					if (field.success) {
						//body 是返回的seq序号
						window.open("../print/deliverToPDF.action?order_no="
								+ $("#new_deliver_order_no").val() + "&seq="
								+ field.body);
						$('#new_deliver_div').dialog('close');
						show_order_list_table(current_page,$('#rows_in_page').val());
						

					} else {
						alert("错误" + field.body);
					}

				});
			});

		}
	</script>





	<div id="view_deliver_div" class="easyui-dialog" title="发货单"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#view_deliver_buttons'">
		
			 <input
				type="hidden" id="view_deliver_order_no"><input
				type="hidden" id="view_deliver_seq"><input type="hidden"
				id="view_logistics_id">
		

		<table width="100%" class='box'>
		<tr>
			<td colspan='2'>发货单状态：<span id="deliver_status">发货单 </span></td>
		</tr>
			<tr>
				<td width="50%">订单编号：<input type="text" name="order_no2"
					id="order_no2" readonly="readonly" /></td>
				<td width="50%" colspan="2">发货单号：<input type="text"
					name="deliver_no2" id="deliver_no2" style="width: 250px;"
					readonly="readonly" /></td>

			</tr>
			<tr>
				<td>快递公司：<input type="text" name="express_com2" id="express_com2" readonly="readonly" />
				<input type="hidden" name="express_code2" id="express_code2">
				</td>
				<td >快递单号：<input type="text" name="express_no2"
					id="express_no2" readonly="readonly" /> <input type="hidden"
					name="seq" id="seq">
				</td>
				
				

			</tr>
			<tr>
				<td >预包装编号：<input type="text" name="view_prepack_sn" id="view_prepack_sn" readonly="readonly" /></td>
				<td>包裹重量：<input type="text" name="weight" id="weight"	class="easyui-numberbox" precision="2" />公斤 </td>
			<tr>
			<td  colspan='2'  align="right">
			<a id="b_kd100" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:deliverViewKd100()" iconCls="qyd">查询物流<kuaidi100></kuaidi100></a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:print_express($('#order_no2').val(),$('#view_logistics_id').val(),'','','','','','','','')" iconCls="qyd">打印</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:change_express($('#order_no2').val(),$('#view_deliver_seq').val())" iconCls="qyd">更新运单</a>
			</td>
			
			</tr>
			
			

		</table  >
		<hr>
		<table id="deliverViewSnTable" class="easyui-datagrid" title="发货清单" 
			style="width: 95%; singleSelect="true" rownumbers="true"
			fitColumns="true">
			<thead>
				<tr>
					<th field="CGoodsId" >商品编码</th>
					<th field="CName" >品名</th>
					<th field="CCount" >应发数量</th>
					<th field="send" >本次发货</th>
					<th field="unit" >单位</th>

				</tr>
			</thead>

		</table>
		<hr>
		
		<table id="deliverViewBarcodeTable" class="easyui-datagrid" title="条码清单" 
			style="width: 95%; singleSelect="true" rownumbers="true"
			fitColumns="true">
			<thead>
				<tr>
					<th field="barcode" >条码</th>
					<th field="goods_id" >商品编码</th>
					<th field="goods_name" >品名</th>
					<th field="return_flag">退货标志</th>

				</tr>
			</thead>

		</table>
		<div id='change_express_message'></div>
		
	</div>


	<div id="view_deliver_buttons">
	<a id="b_w" href="javascript:void(0)"	class="easyui-linkbutton" onclick="javascript:weight_deliverd()" iconCls="qyd">发货完成</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:print_express($('#order_no2').val(),$('#express_code2').val(),'','','','','','','','')">打印快递单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:alert('save')">作废发货单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:view_deliver_print()">打印发货单</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#view_deliver_div').dialog('close')">关闭</a>
	</div>
	<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');
		
		function change_express(order_no,seq){
			$('#change_order_no').val(order_no);
			$('#change_express_no').val("");
			$('#change_seq').val(seq);
			$('#change_express_div').dialog('open');
			$("#change_express_div").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 
			


		}
		
	
	</script>
	<div id="change_express_div" class="easyui-dialog"	title="更新物流信息" style="width: 600px; height: 400px; padding: 10px" data-options="	iconCls: 'qyd',	buttons: '#change_express_buttons'">
		<table class='box' width="100%">
		<tr>
			<td>订单编号：</td><td><input type="text" readonly="readonly" id ="change_order_no"/> </td>
			<td>发货单序号：</td><td><input type="text" readonly="readonly" id ="change_seq"/></td>
		</tr>
		<tr>
			<td>物流企业：</td><td><s:select id="change_express_logistics"	name="change_express_logistics" list="logisticsList" listKey="key" listValue="value" style=" width: 150px;" /></td>
			<td>物流单号：</td><td><input type="text" id ="change_express_no"/></td>
		</tr>
		
		</table>
	
	</div>
	<div id="change_express_buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="javascript:change_express_ok()">确定更新</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="javascript:$('#change_express_div').dialog('close')">取消关闭</a>
	</div>
	<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');
		
		function change_express_ok(){
			var logistics=$('#change_express_logistics').val();
			
			var len=eval('express_no_len_'+logistics);
			if ($("#change_express_no").val().length == len) {

				//符合长度要求

				
				$.getJSON("ChangeExpress.action", {
					"order_no" : $("#change_order_no").val(),
					logistics:$('#change_express_logistics').val(),
					seq:$('#change_seq').val(),
					express_no:$('#change_express_no').val()
				}, function(result) {
					$.each(result, function(i, field) {

						if (field.success) {
							alert('更新成功');
							$('#change_express_div').dialog('close');
							show_order_list_table(current_page,$('#rows_in_page').val());
							view_deliver_div( $("#change_order_no").val(),$('#change_seq').val());

						} else {
							$('#change_express_message').html('<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>');
							alert("错误" + field.body);
						}

					});
				});
				
			}
			else{
				alert('物流单号长度不对，应该为'+len+'位');

				 }


			


			}
		
		
	</script>
	
	
	<div id="new_deliver_prepackage_div" class="easyui-dialog"	title="捡预包装包裹发货" style="width: 600px; height: 400px; padding: 10px"
		data-options="iconCls: 'qyd',buttons: '#new_deliver_from_pre_package_buttons'">

		<input type="hidden" id="new_deliver_prepackage_order_no2">
		<div>1、快递单信息<s:select id="new_deliver_logistics2"	name="new_deliver_logistics2" list="logisticsList" listKey="key" listValue="value" style=" width: 150px;" />
			<a	href="javascript:void(0)" class="easyui-linkbutton"			onclick="javascript:set_default('default_logistics_com',$('#new_deliver_logistics2').val())"
						iconCls="qyd">默认</a><a id="b_new_express" href="javascript:void(0)"
						class="easyui-linkbutton"
						onclick="javascript:print_express($('#new_deliver_prepackage_order_no2').val(),$('#new_deliver_logistics2').val(),'','','','','','','','')" iconCls="qyd">打印快递单</a>
					<input type="text" name="new_deliver_express_no2"
						id="new_deliver_express_no2" value=""
						style="height: 40px; font-size: 30px; width: 400px; background-color: #FFFAF0; border-color: red"
						size="30">
					</div>
		
		<div>2、请录入预包装包裹条码</div>
		<div>
			<input type="text" name="pickup_prepac" id="pickup_prepac" value=""
				style="height: 40px; font-size: 30px; width: 100%; background-color: #FFFAF0; border-color: red"
				size="30">
		</div>
		<div id="pre_message"></div>
		<div>包裹内件如下</div>
		<div>
			<table id="deliverPrePacTable" class="easyui-datagrid"
				style="width: 100%;" singleSelect="true" rownumbers="true"
				fitColumns="true">
				<thead>
					<tr>
						<th field="goods_id" width='50%'>品名</th>
						<th field="barcode" width="50%">条码</th>

					</tr>
				</thead>

			</table>


		</div>
		
	</div>
	<div id="new_deliver_from_pre_package_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:save_deliver_from_pre_package_div()" id="save_deliver_from_pre_package_button_ok">确认发货</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:alert('取消')">取消</a>
	</div>
	


<jsp:include page="../common/print_logistics_bill.jsp" flush="true" /> 
</body>
</html>

