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



	var deliver_list_table_view = $
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
														cc
																.push('<tr><td width=\'5%\' rowspan=\''
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
																		+ rowData.create_username
																		+ '<br>已经过去时间：'
																		+ rowData.create_username
																		
																		+ '</td><td width=\'25%\' rowspan=\''
																		+ dbds.length
																		+ '\'>仓库状态：'
																		+ rowData.create_username
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

														cc
																.push('</td><td width=\'25%\' rowspan=\''
																		+ dbds.length
																		+ '\'>默认快递：'
																		+ rowData.express_com_name
																		+ '<br>运输方式：'
																		+ rowData.vehicle_name
																		+ '<br>取消状态：<font color=\'red\'>'
																		+ rowData.cancel_status
																		+ '</font><br>');

														cc.push('</td></tr>')

													} else {

														cc
																.push('<tr><td width=\'15%\'><img width=\'60px\' src=\'../img/product/'+rowData.com_id+'/'+field.goods_id+'.jpg\'/></td><td width=\'15%\'>'
																		 	+field.goods_name
																			+ '<br>编码：'
																			+ field.goods_id
																			+ '<br>条码：'
																			+field.goods_barcode
																			+'</td><td width=\'15%\' rowspan=\''
																			+ dbds.length
																			+ '\'>拣货打包人：'
																			+ rowData.create_username
																			+ '<br>打包时间：'
																			+ rowData.create_username
																			+ '<br>已经过去时间：'
																			+ rowData.create_username
																		+ '</td></tr>');

													}

												});
							}

							cc
									.push('<tr><td colspan=\'6\'>收货地址：'
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
	function show_deliverbill_list_table(page, rows) {
		//alert($('input[name="order_status"]:checked ').val());
		current_page = page;

		$.getJSON("get_all_deliver_list.action", {
			"page" : page,
			"rows" : rows,

			reciever_addr : $('#reciever_addr').val(),
			receiver_name : $('#receiver_name').val(),
			receiver_mobile : $('#receiver_mobile').val(),
			express_no : $('#express_no').val(),
			deliverbill_status:$('#deliverbill_status').val(),
			goods_barcode:$('#goods_barcode').val(),
			original_id:$('#original_id').val(),
			create_userid:$('#create_userid').val(),
			order_no:$('#search_order_no').val()

		}, function(result) {
			//alert("sdfsfds");
			//var field=result.msg;
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#deliver_list_table').datagrid('loadData', result);

			show_deliverbill_list_table_pages(page);

		});
	}
	function show_deliverbill_list_table_pages(page) {
		$.getJSON("get_all_deliver_count.action", {
			"page" : page,
	

			reciever_addr : $('#reciever_addr').val(),
			receiver_name : $('#receiver_name').val(),
			receiver_mobile : $('#receiver_mobile').val(),
			express_no : $('#express_no').val(),
			deliverbill_status:$('#deliverbill_status').val(),
			goods_barcode:$('#goods_barcode').val(),
			original_id:$('#original_id').val(),
			create_userid:$('#create_userid').val(),
			order_no:$('#search_order_no').val()

		}, function(result) {
			

				
					var count = parseInt(result, 10);
					var rows_in_page = parseInt($('#rows_in_page').val(), 10);

					var pages = Math.ceil(count / rows_in_page);
					//alert(pages);
					var p = $('#deliver_list_table').datagrid('getPager');
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

							show_deliverbill_list_table(pageNumber, pageSize);
							$("html,body").finish().animate({
								"scrollTop" : "0px"
							}, 900);
						}

					});

				

			
		});

	}

	function deliver_list_table_Dclick(rowData) {

	}

	$(document)			.ready(
					function() {
						
						//dialog_init('view_route_div');
						//dialog_init('view_deliver_div');
						

						$('#deliver_list_table').datagrid(
										{
											view : deliver_list_table_view,

											onDblClickRow : function(rowIndex,
													rowData) {
												deliver_list_table_Dclick(rowData);
											},
											pagination : true,
											//rownumbers: true, 
											onLoadSuccess : function(data) {
/*
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
*/
											}
										});

						show_deliverbill_list_table(current_page, $('#rows_in_page')
								.val());




					});
</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>发货单列表</h2>
	



	<table class='box' style="width: 100%">
		<tr>
			<td>1、订单状态： <SELECT id="deliverbill_status">
						<option VALUE="0" SELECTED>所有运单</option>
						<option VALUE="分配单号">分配单号</option>
						<option VALUE="拣货完成">拣货完成</option>
						<option VALUE="分派发货">分派发货</option>
						<option VALUE="发货完毕">发货完毕</option>
						<option VALUE="运单取消">运单取消</option></SELECT>
						2、收件人姓名:<input id="receiver_name" type="text" style="width: 70px"  />
						3、收件人手机:<input id="receiver_mobile" type="text" />
						4、收件人地址：<input id="reciever_addr" type="text" />
						5、商品条码：<input id="goods_barcode" type="text" />
						6、运单号：<input id="express_no" type="text" />
						7、电商原始单号：<input id="original_id" type="text" />
						8、创建录入：<input id="create_userid" type="text" />

			每页显示<input style="width: 50px"   id="rows_in_page" value=<s:property value="pageSize" /> type="text"
					class="easyui-numberbox" precision="0" />行
					<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_rows_in_page',$('#rows_in_page').numberbox('getValue'))"
				iconCls="qyd">默认</a>
					
					<a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-search"
					onclick="javascript:show_deliverbill_list_table(1, $('#rows_in_page').val())">查询</a>
				</td>
		</tr>
		<tr>
			<td>
				<table id="deliver_list_table" style="width: 100%" pagination="true"
					sortName="itemid" sortOrder="desc" singleSelect="true"
					fitColumns="true">

				</table>

			</td>
		</tr>
	</table>



</body>
</html>

