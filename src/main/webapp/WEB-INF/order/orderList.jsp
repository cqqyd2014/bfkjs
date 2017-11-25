<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>订单列表</title>

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
<link rel="stylesheet" type="text/css" href="../js/print_logistics_bill.css">


<script type="text/javascript" src="../js/datagrid-detailview.js"></script>

<script language='javascript' type='text/javascript'>


function deliverViewKd100(){
	
	window.open("http://www.kuaidi100.com/chaxun?com="+$("#express_com2").val()+"&nu="+$("#express_no2").val());
}



var current_page=1;





function delete_order(order_no){


	$.messager.confirm("操作提示", "您确定要删除订单"+order_no+"吗？",function (data) {  
        if (data) {  
        	

        	$.getJSON("DeleteOrder.action", {
        		
        		"order_no" : order_no
        		

        	}, function(result) {
        		$.each(result, function(i, field) {
        			
        			if (field.success) {
        				$.messager.alert("操作提示", "删除订单操作成功！","info");  

        				show_order_list_table(current_page,$('#rows_in_page').val());

        			} else {
        				 $.messager.alert("操作提示", "删除订单操作错误！原因："+field.body,"error");  
        				

        			}
        			
        			

        		});
        	});

        	
        }  
        else {  
            return;
        } 
	});


}
	




	function view_deliver_div(order_no, seq) {

		$('#view_deliver_order_no').val(order_no);
		$('#view_deliver_seq').val(seq);

		$.getJSON("DeliverViewInit.action", {
			"order_no" : order_no,
			"seq" : seq

		}, function(result) {
			$.each(result, function(i, x) {
				var field=x.o;
				var barcodes=x.o2;

				$('#deliver_status').text(field.if_send);
				$("#deliver_no2").val(field.deliver_no);
				
				$("#order_no2").val(field.order_no);
				$("#express_com2").val(field.express_com);
				$("#express_no2").val(field.express_no);
				$('#express_code2').val(field.express_code);
				$('#view_prepack_sn').val(field.prepack_sn);
				if (field.sended) {
					//已发

					$("#weight").numberbox('setValue',field.weight);
					$("#weight").numberbox('disable',true);
					$("#b_w").hide();
					
					
				}
				else{
					//未发
					$("#weight").numberbox('enable',true);

					$("#weight").numberbox('setValue',field.weight);
					$("#b_w").show();
					
				}
				var n = field.length;

				$("#deliverViewSnTable").datagrid('loadData', {
					total : n,
					rows : field.list
				});

				var n2 = barcodes.length;

				$("#deliverViewBarcodeTable").datagrid('loadData', {
					total : n2,
					rows : barcodes
				});
				
				

				$("#view_logistics_id").val(field.express_com_id);
				/*
				
				
				
				
				 

				

				 //var dataJson=JSON.parse(list1.list);
				
				 */
				
				$("#view_deliver_div").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 
				$('#view_deliver_div').dialog('open');

			});
		});
	}



	var order_list_table_view = $.extend(		{},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {

							var cc = [];

							cc.push('<table width=\'100%\' border=\'1\' class=\'box\'><tr><td colspan=\'6\' bgcolor=\'#cccccc\'><span style=\'color:white;background-color:');
							switch (rowData.c_status) {
							case '正在拣货':
								cc.push('#336699');
								break;
							
							case '等待拣货':
								cc.push('#996699');
								break;
							case '正在发货':
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

														

														cc.push('</td><td width=\'25%\' rowspan=\''+order_details.length+'\'>查看详情<br>');
														
														cc.push('备注：<font color=\'red\'>'+rowData.memo+'</font><br>');

														switch (rowData.c_status) {
														
														case '发货完毕':
															cc.push('<a href=\'javascript:void(0)\' class=\"easyui-linkbutton\" id=\'b_back\' onclick=\'javascript:return_order(\"'	+ rowData.order_no	+  '\")\'>已发货商品退货</a>');
															break;
														case '部分退货':
															cc.push('<a href=\'javascript:void(0)\' class=\"easyui-linkbutton\" id=\'b_back\' onclick=\'javascript:return_order(\"'	+ rowData.order_no	+  '\")\'>已发货商品退货</a>');
															break;
														case '订单关闭':
															//cc.push('#666666');
															break;
														
														default:
															cc.push('<a href=\'javascript:void(0)\' class=\"easyui-linkbutton\" id=\'b_delete\' onclick=\'javascript:delete_order(\"'	+ rowData.order_no	+  '\")\'>取消未发货订单</a>');
														}
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
</script>
<script language='javascript' type='text/javascript'>
	function show_order_list_table(page, rows) {
		//alert($('input[name="order_status"]:checked ').val());
		current_page=page;

		$.getJSON("GetOrderList.action", {
			"page" : page,
			"rows" : rows,
			order_status:$('#order_status').val(),
			user_name:$('#user_name').val(),
			goods_name:$('#goods_name').val(),
			user_tell:$('#user_tell').val(),
			original_id:$('#original_id').val(),
			barcode:$('#s_barcode').val(),
			express_no:$('#s_express_no').val()
			
		}, function(result) {
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#order_list_table').datagrid('loadData', result);

			show_order_list_table_pages(page);

		});
	}
	function show_order_list_table_pages(page) {
		$.getJSON("GetOrderListPages.action", {
			order_status:$('#order_status').val(),
			user_name:$('#user_name').val(),
			goods_name:$('#goods_name').val(),
			user_tell:$('#user_tell').val(),
			original_id:$('#original_id').val(),
			barcode:$('#s_barcode').val(),
			express_no:$('#s_express_no').val()

		}, function(result) {
			$.each(result, function(i, field) {

				if (field.success) {
					var count = parseInt(field.body,10);
					var rows_in_page=parseInt($('#rows_in_page').val(),10);
					
					var pages = Math.ceil(count /rows_in_page);
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
							current_page=pageNumber;

							show_order_list_table(pageNumber, pageSize);
							$("html,body").finish().animate({"scrollTop":"0px"},900); 
						}

					});

				} else {
					alert("错误" + field.body);
				}

			});
		});

		
		

	}

	function order_list_table_Dclick(rowData) {

	}


	<s:iterator value="experss_no_len_map" var="entry">
    
    
    var express_no_len_<s:property value="key"/>=<s:property value="value"/>;
    
   </s:iterator>
	$(document).ready(function() {
		$('#print_logistics_bill').dialog('close');
		 
	     $('#goods_return_table').datagrid({
	    	 
	    	 
	    	 onLoadSuccess:function(data){
				  
	    	 
		    	 if(data){
			    	 
		    	 
			    	 $.each(data.rows, function(index, item){
			    	 
			    	 	
			    	 
			    	 		$('#goods_return_table').datagrid('checkRow', index);
			    	 
			    	 	
			    	 
			    	 });
		    	 
		    	 }
	    	 
	    	 }                
	    	 
	    	 });



	     

						$('#order_list_table').datagrid({
							view : order_list_table_view,

							onDblClickRow : function(rowIndex, rowData) {
								order_list_table_Dclick(rowData);
							},
							pagination : true,
							//rownumbers: true, 
							fitColumns : false,
							onLoadSuccess:function(data){ 
								
								

								
								$("a#b_qyd").linkbutton({  iconCls:'qyd' });

								<s:iterator value="logisticsList" var="entry">  
								$("a#b_<s:property value="key"/>").linkbutton({  iconCls:'express_<s:property value="key"/>' });
							     </s:iterator>

								
								
								$("a#b_delete").linkbutton({  iconCls:'icon-cancel' });
								$("a#b_back").linkbutton({  iconCls:'icon-undo' });
				            }
						});

						show_order_list_table(current_page,$('#rows_in_page').val());

						//初始化，隐藏对话框

						$('#new_deliver_div').dialog('close');

						$('#view_deliver_div').dialog('close');
						$('#change_express_div').dialog('close');
						$('#new_deliver_prepackage_div').dialog('close');
						$('#order_return_div').dialog('close');

						function endDeliver() {
							alert("end");
						}
						/*
						function printExpressNo() {
							window.open("../pdf/expressToPDF.action?orderNo="
									+ $("#order_no").val() + "&logistics="
									+ $("#logistics").val());
						}
						*/

						$('.deliver').click(function() {
							$("#message").empty();
							var order_no = $(this).next().attr("value");

							$("#order_no").val(order_no);
							sn_table_show();

							deliver_table_show();

							dialog.dialog("open");

						});



						
						


					});







</script>






</head>

<body style="width: 95%; height: 95%;">
	<h2>订单列表</h2>
	<table class='box' style="width: 100%">
	<tr><td>
	<span>
	1、订单状态：
	<SELECT id="order_status">
		<option VALUE="0" SELECTED>所有订单</option>
		<option VALUE="等待拣货">等待拣货</option>
		<option VALUE="拣货完成、待发" >拣货完成、待发</option>
	</SELECT>
	
	2、收件人姓名:<input id="user_name" type="text" />
	3、收件人手机:<input id="user_tell" type="text" />
	4、内件：<input id="goods_name" type="text" />
	5、电商平台单号：<input id="original_id" type="text" />
	6、快递单号：<input id="s_express_no" type="text" />
	7、商品条码：<input id="s_barcode" type="text" />
	</span>
	<span style="float:right">每页显示<input id="rows_in_page" value=<s:property value="pageSize" /> type="text" class="easyui-numberbox" precision="0" />行<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" onclick="javascript:show_order_list_table(1, $('#rows_in_page').val())">查询</a></psan>
	</td></tr>
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
			<td>物流企业：</td><td><s:select id="change_express_logistics"	name="change_express_logistics" list="logisticsList2" listKey="key" listValue="value" style=" width: 150px;" /></td>
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
	

	
<div id="order_return_div" class="easyui-dialog"	title="退货" style="width: 600px; height: 400px; padding: 10px"
		data-options="iconCls: 'qyd',buttons: '#order_return_buttons'">
		<input type="hidden" id="return_order_no" />
		<table class='box' width='100%'>
			<tr>
				<td width='13%'>订单编号：</td><td width='20%'><span id='return_order'></span></td>
				<td width='13%'>下单时间：</td><td width='20%'><span id='return_order_dat'></span></td>
				<td width='13%'>退货仓库：</td><td width='20%'><s:select name="toWhId" id="toWhId" list="wh_map"  listKey="key" listValue="value" /></td>
			</tr>
			<tr>
				<td >发货时间：</td><td colspan='5'><span id='return_order_express_start_dat'></span></td>
				
			</tr>
			<tr>
				<td>客户信息：</td><td colspan='5'><span id='return_order_customer'></span></td>
				
			</tr>
			<tr>
				<td>退货备注：</td><td colspan='5'><input type='text' style='width:100%' id='return_memo'/></td>
			</tr>
			
		</table>
		<table id="goods_return_table" class="easyui-datagrid" style="width: 100%"  rownumbers="true"
				fitColumns="true">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="goods_id" >商品编码</th>
						<th field="goods_name">品名</th>
						<th field="barcode" >条码</th>
					</tr>
				</thead>

			</table>
			<div id="return_message"></div>



</div>
<div id="order_return_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:return_order_ok()" id="order_return_ok">确认退货</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:alert('取消')">取消</a>
	</div>

<script language='javascript' type='text/javascript'>
		//$("#deliverView").dialog('close');
		
		function return_order(order_no) {
			$('#return_message').empty();

			//先初始化
			$('#return_order_no').val(order_no);
			$('#return_memo').val('');
			$.getJSON("ReturnOrderDialogInit.action", {
				"order_no" : $("#return_order_no").val()
			}, function(result) {
				$.each(result, function(i, field) {

					if (field.success) {
						var order=field.o;
						var barcodes=field.o2;
						var delivers=field.o3;
						$('#return_order').text(order.id.orderNo);
						$('#return_order_dat').text(date2String(order.id.orderDat));
						var deliver_span='';
						$.each(delivers, function(j, deliver) {
							deliver_span='['+deliver.id.seq+':'+date2String(deliver.sendDat)+']';
							
						});
						$('#return_order_express_start_dat').text(deliver_span);

						var custom=order.id.CUserName+' '+order.id.CTell+' '+order.id.addrProvince+' '+order.id.addrCity+' '+order.id.addrDistrict+' '+order.id.CUserAddr;
						$('#return_order_customer').text(custom);
						var n = barcodes.length;

						$("#goods_return_table").datagrid('loadData', {
							total : n,
							rows : barcodes
						});
						
						

					} else {
						$('#return_message').html('<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>');
						alert("错误" + field.body);
					}

				});
			});
			$('#order_return_div').dialog('open');
			$("#order_return_div").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 
		}
function return_order_ok(){

	var checkedItems = $('#goods_return_table').datagrid('getChecked');
	
	var barcodes = [];
	var barcode_str;
	
	$.each(checkedItems, function(index, item){
	
		barcodes.push(item.barcode);
	
	});               
	
	barcode_str=barcodes.join(",");

	$.getJSON("ReturnOrder.action", {
		"order_no" : $("#return_order_no").val(),
		toWhId:$('#toWhId').val(),
		"barcodes":barcode_str,
		memo:$('#return_memo').val()
	}, function(result) {
		$.each(result, function(i, field) {

			if (field.success) {
				alert(field.body);
				$('#order_return_div').dialog('close');
				show_order_list_table(current_page,$('#rows_in_page').val());

			} else {
				$('#return_message').html('<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>');
				alert("错误" + field.body);
			}

		});
	});

	
}


		
	</script>
<jsp:include page="../common/print_logistics_bill.jsp" flush="true" /> 
</body>
</html>

