<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
    	<div id="return_div" class="easyui-dialog" title="退货申请"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#return_buttons'">

		
		<div>订单信息：<span id='return_order_info'></span></div>
		<div>发货序号：<span id='return_seq'></span></div>
		<div>商品编码/名称：<span id='return_goods_id'></span>/<span id='return_goods_name'></span></div>
		<div>退货商品条码：<span id='return_goods_barcode'></span></div>
		<div>退货至仓库：<s:select id="return_goods_wh_id"
						list="wh_list" listKey="wh_id"
						listValue="wh_name" cssStyle=" width: 150px;"    /><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_warehouse',$('#return_goods_wh_id').val())"
				iconCls="qyd">默认</a></div>
		<div>备注</div>
		<div><input class="easyui-textbox" data-options="multiline:true"
					style="width: 100%;height:50px" id='return_memo'/></div>
		

	</div>


	<div id="return_buttons">

		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:return_goods_ok()">确认退货</a>
			<a></a>
	</div>
<script language='javascript' type='text/javascript'>


var after_return_goods;

	function return_goods_ready(after_return_goods){
		after_return_goods=after_return_goods;

		dialog_init_mid('return_div');


		$('#return_goods_wh_id').combobox({
			required : true,
			multiple : false, //多选
			editable : false //是否可编辑
			
			
		});
		}
	


		function return_goods(barcode,order_no,seq){


$.getJSON("return_goods_init.action", {
	
	order_no : order_no,
	goods_barcode : barcode,
	seq:seq

}, function(result) {
	var field=result.msg;
	

	if (field.success) {

		var o=field.o;
		$('#return_order_info').text(o.order_no);
		$('#return_goods_id').text(o.goods_id);
		$('#return_goods_name').text(o.goods_name);
		$('#return_goods_barcode').text(o.goods_barcode);
		$('#return_seq').text(seq);
		
		$('#return_div').dialog('open');

		} else {
			$.messager.alert("操作提示", "初始化出错！原因：" + field.body,
			"error");
			

		}
		

	
});



			}
		//$("#deliverView").dialog('close');
		
		function return_goods_ok(){

			$.getJSON("return_goods.action", {
				
				order_no : $('#return_order_info').text(),
				goods_barcode : $('#return_goods_barcode').text(),
				seq:$('#return_seq').text(),
				memo:$('#return_memo').textbox('getValue'),
				goods_id:$('#return_goods_id').text(),
				wh_id:$('#return_goods_wh_id').combobox('getValue')

			}, function(result) {
				var field=result.msg;
				

				if (field.success) {
					$.messager.alert("操作提示", "退货成功",
					"info");
					$('#return_div').dialog('close');
					//view_deliver_div($('#return_order_info').text(),$('#return_seq').text());
					after_return_goods($('#return_order_info').text(),$('#return_seq').text());
					} else {
						$.messager.alert("操作提示", "退货出错！原因：" + field.body,
						"error");
						

					}
					

				
			});

			}



	</script>