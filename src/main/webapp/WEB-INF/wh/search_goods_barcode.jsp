<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>条码管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<script type="text/javascript">


		
	

function cancel_barcode(){

    $.messager.confirm('提示', '是否作废该条码，作废之后已经入库商品需要再入库', function(b){  
        if (b){  
        	$.getJSON("set_un_effective_barcode.action", {barcode:$('#barcode2').text()
        		
        	}, function(result) {
        		
        		
        			
        		$.each(result, function(i, field) {
        			
        			if (field.success){
        				find();

        				
        				}
        			else{
        				alert("错误"+field.body);
        			}
        			
        		
        	});
        			
        		
        	});
        }else{  
              return;
        }  
  });  


	



	
	
}

function search_barcode(){

	$("#barcode").attr("disabled","disabled");
	

	
	$("#message").empty();
	$('#barcode2').text($("#barcode").val());
	$("#barcode").val("");

	$.getJSON("search_goods_barcode.action", {
		goods_barcode : $("#barcode2").text()
	}, function(result) {
		
	var field=result.msg;
			
				if (field.success){
					//合格数据
					
					var barcode=field.o;
					
					$('#goods_id').text(barcode.goods_id);
					$('#goods_name').text(barcode.goods_name);
					$('#current_wh').text(barcode.wh_name);
					$('#current_storage').text(barcode.storage_name);

					if (barcode.wh_id=='SUPPLY'){
						//供应商库位

						$("#effictive").attr('src','../img/barcode_blank.jpg'); 

						//setDisabled("b_barcode_cancel",true);
						
						$("#b_barcode_cancel").linkbutton({disabled:true}); 
						}
					else{
						if (barcode.effective){
							$("#effictive_barcode").attr('src','../img/barcode_normal.jpg'); 
							$("#b_barcode_cancel").linkbutton({disabled:false}); 

							}
						else{
							$("#effictive_barcode").attr('src','../img/barcode_cancel.jpg'); 
							
							$("#b_barcode_cancel").linkbutton({disabled:true}); 
							
							}
						$('#un_effctive_time').text(barcode.uneffective_dat);
						$('#into_wh_time').text(barcode.in_dat);
						$('#contract_no').text(barcode.contract_no);
						$('#paper_dat').text(barcode.contract_paper_dat);
						$('#supply_name').text(barcode.supply_name);
						$('#price').text(barcode.contract_price);
						$('#unit').text(barcode.unit);
						$('#country').text(barcode.country_name);
						$('#net_weight').text(barcode.contract_price);
						var orders=field.o5;
						var n=orders.length;
						

						$("#order_list").datagrid('loadData',{ total: n, rows: orders });

						var moves=field.o3;
						var n2=moves.length;
						$("#move_list").datagrid('loadData',{ total: n2, rows: moves });

						var weixin_sanqr=field.o4;
						var n3=weixin_sanqr.length;
						$("#weixin_sanqr_list").datagrid('loadData',{ total: n3, rows: weixin_sanqr });
						
						}
						
			
					//var goods_info=field.o2;
					//$('#ean13').text(goods_info.id.barcode);
					//$('#hscode').text(goods_info.id.CHs);
					
					
					
					}
				else{
					$("#message").append("<font color='red'>"+field.body+"</font>");


					}
				$("#message").append("<audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio>");
				$("#barcode").removeAttr("disabled");
			
		
		
	});
	
}




	$(document).ready(function() {
		$("#b_barcode_cancel").linkbutton({disabled:true}); 


		$("#barcode").keydown(function() {

			if ($("#barcode").val().length == 22) {
				
				search_barcode();
			}

		});
		$("#barcode").focus();
	});
</script>



</head>

<body style="width: 95%; height: 95%;">
<h1>条码查询</h1>
<div>
	
	<div>请输入条码</div>
	<div>
	<input type="text" name="barcode" id="barcode"
			style="height: 40px; font-size: 30px; width: 100%; background-color: #ffffcc;border-color:red"
			size=30 />
	</div>
	<div><a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:search_barcode()"
					iconCls="qyd">开始查询</a></div>
	<div id='message'></div>
	
	<div>
	<h2>商品在库信息</h2>
	
	<table width='100%' class='box'>
	<tr>
		<td width='10%'>商品条码</td><td width='23%'><span id="barcode2"></span></td>
		<td width='10%'>商品id</td><td width='23%'><span id="goods_id"></span></td>
		<td width='10%'>商品名称</td><td width='23%'><span id="goods_name"></span></td>
	</tr>
	<tr>
		<td width='10%'>当前库房</td><td width='23%'><span id="current_wh"></span></td>
		<td width='10%'>当前库位</td><td width='23%'><span id="current_storage"></span></td>
		<td width='10%'>temp</td><td width='23%'>temp</td>
		
	</tr>
		<tr>
		<td width='10%'>商品状态</td><td width='23%'><img id="effictive_goods" height='25px' width='50px'  src='../img/empty.jpg'/><a href="javascript:void(0)" id='b_barcode_cancel' class="easyui-linkbutton" onclick="javascript:cancel_goods()" iconCls="icon-edit">商品损毁，作废该条码，不退入库单</a></span></td>
		<td width='10%'>条码状态</td><td width='23%'><img id="effictive_barcode" height='25px' width='50px' src='../img/empty.jpg'/><a href="javascript:void(0)" id='b_barcode_cancel' class="easyui-linkbutton" onclick="javascript:cancel_barcode()" iconCls="icon-cancel">条码错误，作废该条码,退入库单</a></td>
		<td width='10%'>temp</td><td width='23%'>temp</td>
		
	</tr>
	<tr>
		<td>进入当前库位时间</td><td><span id="current_wh_time"></span></td>
		<td>采购入库时间</td><td><span id="into_wh_time"></span></td>
		<td>作废时间</td><td><span id="un_effctive_time"></span></td>
		
	</tr>
	<tr>
		<td>合同号</td><td><span id="contract_no"></span></td>
		<td>合同签订时间</td><td><span id="paper_dat"></span></td>
		<td>供应商</td><td><span id="supply_name"></span></td>
	</tr>
	<tr>
		<td>CIF价格</td><td><span id ="price"></span></td>
		<td>EAN13条码</td><td><span id="ean13"></span></td>
		<td>HSCODE</td><td><span id="hscode"></span></td>
	</tr>
	<tr>
		<td>单位</td><td><span id="unit"></span></td>
		<td>原产国</td><td><span id="country"></span></td>
		<td>净重</td><td><span id="net_weight"></span></td>
	</tr>
	<tr>
	<td colspan='6'>
	<table id="order_list" style="width: 100%; overflow-y: auto"
			title='涉及的订单' class="easyui-datagrid" 
			rownumbers="true" iconCls="qyd">
			<thead>
				<tr>


					<th field="order_dat">订单时间</th>
					<th field="original_id">原始平台单号</th>
					<th field="create_user_name">录单</th>
					<th field="order_no">订单号</th>
					<th field="seq">发货序号</th>
					
					<th field="wh_name">发货仓</th>
					<th field="receiver">购买人</th>
					<th field="mobile">手机</th>
					<th field="tell">座机</th>
					<th field="receiver_full_addr">地址</th>
					<th field="express_com_name">物流公司</th>
					<th field="express_no">物流单号</th>

				</tr>
			</thead>

		</table>
		<table id="move_list" style="width: 100%; overflow-y: auto"
			title='流转日志' class="easyui-datagrid" 
			rownumbers="true" iconCls="qyd">
			<thead>
				<tr>


					<th field="dat">时间</th>
					<th field="from_wh_name">源仓库</th>
					<th field="from_storage_name">源库位</th>
					<th field="to_wh_name">目标仓库</th>
					<th field="to_storage_name">目标库位</th>
					<th field="move_type_name">移库类型</th>
					

				</tr>
			</thead>

		</table>
		<table id="weixin_sanqr_list" style="width: 100%; overflow-y: auto"
			title='微信二维码扫描' class="easyui-datagrid" 
			rownumbers="true" iconCls="qyd">
			<thead>
				<tr>


					<th field="scan_dat">时间</th>
					<th field="ip">IP地址</th>
					<th field="effective">有效</th>
					
					

				</tr>
			</thead>

		</table>
	</td>
	</tr>
	</table>
	
	</div>
	<div></div>
	
</div>
	
</body>
</html>
