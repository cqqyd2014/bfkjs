<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
		<div id="cancel_order_div" class="easyui-dialog" title="取消订单"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="	iconCls: 'qyd',	buttons: '#cancel_order_buttons'">

		
		<div>订单编号：<span id='cancel_order_no'></span></div>
		
		<div>备注</div>
		<div><textarea rows="3" style="width: 100%" id='cancel_memo'></textarea></div>
		

	</div>


	<div id="cancel_order_buttons">

		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:cancel_order_ok()">确认取消</a>
	</div>

<script language='javascript' type='text/javascript'>




		function cancel_order(order_no){


$.getJSON("cancel_order_init.action", {
	
	order_no : order_no

}, function(result) {
	var field=result.msg;
	

	if (field.success) {

		var o=field.o;
		$('#cancel_order_no').text(order_no);
		
		dialog_init_little('cancel_order_div');
		$('#cancel_order_div').dialog('open');
		
		} else {
			$.messager.alert("操作提示", "初始化出错！原因：" + field.body,
			"error");
			

		}
		

	
});



			}
		//$("#deliverView").dialog('close');
		
		function cancel_order_ok(){

			$.getJSON("cancel_order.action", {
				order_no : $('#cancel_order_no').text(),
				cancel_memo:$('#cancel_memo').val()

			}, function(result) {
				var field=result.msg;
				if (field.success) {
					$.messager.alert("操作提示", "已经申请取消订单，请关注后续账单状态变化","info");
					$('#cancel_order_div').dialog('close');
					show_order_list_table(current_page, $('#rows_in_page')
							.val());
					
					} else {
						$.messager.alert("操作提示", "申请取消出错！原因：" + field.body,
						"error");
					}
					
			});

			}



	</script>

