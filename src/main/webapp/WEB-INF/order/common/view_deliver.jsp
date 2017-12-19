<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>



<script type='text/javascript'>



function set_return_button_disable(list_data){
	
	for (var i=0;i<list_data.length;i++){
		var item=$('#return_'+list_data[i].goods_barcode);
		//alert(item);
		
		if (list_data[i].returned){
			item.linkbutton('disable');
			
			}
		
		}
	
}


function view_deliver_ready(){


	 view_route_ready();
	change_express_ready(view_deliver_init);

	return_goods_ready(view_deliver_init);
	$('#view_deliver_order_no').textbox('textbox').attr('readonly',true);
	$('#view_deliver_seq').textbox('textbox').attr('readonly',true);
	$('#view_deliver_express_name').textbox('textbox').attr('readonly',true);
	$('#view_deliver_express_no').textbox('textbox').attr('readonly',true);
	$('#view_deliver_prepack_sn').textbox('textbox').attr('readonly',true);
	
	dialog_init('view_deliver_div');
	$('#deliverViewBarcodeTable').datagrid({  
	    //border:false,  
	    fitColumns:true,  
	    singleSelect: true,  
	    title:'商品列表',
	    rownumbers:true,
	    columns:[[  
	        {field:'goods_barcode',title:'商品条码'},  
	        {field:'goods_id',title:'商品编码'},
	        {field:'goods_name',title:'商品名称'},
	        {field:'returned',title:'退货标志'},
	        {field:'return_dat_chinese',title:'退货时间'},
	        {field:'returned_memo',title:'退货备注'},
	        {field:'opt',title:'操作',width:'100px',align:'center',  
	            formatter:function(value,rec){  
	                var btn = '<a class="return_goods" id="return_'+rec.goods_barcode+'" onclick="return_goods(\''+rec.goods_barcode+'\',\''+rec.order_no+'\',\''+rec.seq+'\')" href="javascript:void(0)">退货</a>';  
	                return btn;  
	            }  
	        }  
	    ]],  
	    onLoadSuccess:function(data){  
	        $('.return_goods').linkbutton({text:'退货',plain:true,iconCls:'icon-undo'});
	        
	        
	    }  
	});  

}




function view_deliver_init(order_no, seq) {
	
	$("#view_deliver_order_no").textbox('setValue',order_no); 
	
	$('#view_deliver_seq').textbox('setValue',seq);

	$.getJSON("view_deliver_bill_init.action", {
		"order_no" : $('#view_deliver_order_no').textbox('getValue'),
		"seq" : $('#view_deliver_seq').textbox('getValue')

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			var deliver_bill = field.o;
			var goods = field.o2;

			$('#deliver_status').text(deliver_bill.deliver_bill_status);
			$('#view_bill_wh_id').val(deliver_bill.wh_id);
			$('#view_deliver_div_wh_name').text(deliver_bill.wh_name);
			
			$("#view_deliver_express_name").textbox("setValue", deliver_bill.express_com_name);
			$("#view_deliver_express_no").textbox("setValue", deliver_bill.express_no);
			$('#view_deliver_express_com').val(deliver_bill.express_com);
			$('#view_deliver_prepack_sn').textbox("setValue", deliver_bill.pre_package_barcode);
			

			$('#view_vehicle_id').val(deliver_bill.vehicle_id);
			
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
			set_return_button_disable(goods);
			dialog_init('view_deliver_div');
			$('#view_deliver_div').dialog('open');

		}
		else{

			$.messager.alert("操作提示", "获取运单基本信息出错！原因：" + field.body,
			"error");
		}
	});
}


	</script>


<div id="view_deliver_div" class="easyui-dialog" title="包裹单"
	style="width: 600px; height: 400px; padding: 10px"
	data-options="
				iconCls: 'qyd',
				
				buttons: '#view_deliver_div_buttons'
			">

	<input type="hidden" id="view_logistics_id"><input
		type="hidden" id="view_vehicle_id">
		<input type="hidden" id="view_bill_wh_id">


	<table width="100%">
		<tr>
			<td width='18%'>发货仓库：</td>
			<td width='32%'><span id="view_deliver_div_wh_name"></span></td>
			<td width='18%'>包裹状态：</td>
			<td width='32%'><span id="deliver_status">发货单 </span></td>
		</tr>
		<tr>
			<td>订单编号：</td>
			<td><input class="easyui-textbox" id="view_deliver_order_no"
				style="width: 100%" /></td>
			<td>发货序号：</td>
			<td><input class="easyui-textbox" style="width: 100%"
				id="view_deliver_seq" /></td>

		</tr>
		<tr>
			<td>快递公司：</td>
			<td><input class="easyui-textbox" style="width: 100%"
				id="view_deliver_express_name" /> <input type="hidden"
				id="view_deliver_express_com"></td>
			<td>快递单号：</td>
			<td><input class="easyui-textbox" style="width: 100%"
				id="view_deliver_express_no" /></td>



		</tr>
		<tr>
			<td>预包装编号：</td>
			<td><input class="easyui-textbox" style="width: 100%"
				id="view_deliver_prepack_sn" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
	</table>
	<hr>
	<table id="deliverViewPickupTable" class="easyui-datagrid" title="发货清单"
		style="width: 100%;" true" rownumbers="true" fitColumns="true">
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
		title="条码清单" style="width: 100%;" true" rownumbers="true"
		fitColumns="true">


	</table>
	
	<hr>
</div>


<div id="view_deliver_div_buttons">
	<a id="b_new_express" href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:print_express($('#view_deliver_order_no').textbox('getValue'),$('#view_deliver_seq').textbox('getValue'),$('#view_deliver_express_com').val(),$('#view_vehicle_id').val,'BIG_',$('#view_bill_wh_id').val(),'<s:property value="#request.user_id" />')"
		iconCls="icon-print">打印纸质面单</a> <a id="b_electric_express"
		href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:print_express($('#view_deliver_order_no').textbox('getValue'),$('#view_deliver_seq').textbox('getValue'),$('#view_deliver_express_com').val(),$('#view_vehicle_id').val,'ELEC',$('#view_bill_wh_id').val(),'<s:property value="#request.user_id" />')"
		iconCls="icon-print">打印电子面单</a><a href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:view_route($('#view_deliver_order_no').textbox('getValue'),$('#view_deliver_seq').textbox('getValue'))"
		iconCls="express_500528000A">查看顺丰路由</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:change_express_init($('#view_deliver_order_no').textbox('getValue'),$('#view_deliver_seq').textbox('getValue'))"
		iconCls="icon-reload">变更物流信息</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:print_deliver_bill($('#view_deliver_order_no').textbox('getValue'),$('#view_deliver_seq').textbox('getValue'))"
		iconCls="icon-tip">查看发货单</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:$('#view_deliver_div').dialog('close')" iconCls="icon-ok">关闭查看包裹窗口</a>
</div>
<jsp:include page="change_express.jsp" flush="true" />
<jsp:include page="view_route.jsp" flush="true" />
	<s:action name="return_goods_div_init" executeResult="true" namespace="/order/common"> </s:action>
