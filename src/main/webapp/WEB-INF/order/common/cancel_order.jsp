<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<div id="cancel_order_div" class="easyui-dialog" title="取消订单"
	style="width: 600px; height: 400px; padding: 10px"
	data-options="	iconCls: 'qyd',	buttons: '#cancel_order_buttons'">

<table width="100%">
<tr>
	<td width='30%'>订单编号：</td><td><input class="easyui-textbox" id='cancel_order_no' style="width: 250px"/></td>
</tr>
<tr>
	<td>取消原因（备注）:</td><td><input class="easyui-textbox"  data-options="multiline:true" style="width: 250px;height:100px;" id='cancel_memo'/></td>
</tr>
</table>
	


</div>


<div id="cancel_order_buttons">

	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:cancel_order_ok()" iconCls="icon-ok">申请取消订单</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:$('#cancel_order_div').dialog('close')" iconCls="icon-cancel">暂不取消</a>
</div>

<script  type='text/javascript'>
var cancel_order_page_init;
	function cancel_order_ready(page_init){
		cancel_order_page_init=page_init;
		dialog_init_mid('cancel_order_div');
		$('#cancel_order_no').textbox('textbox').attr('readonly',true);
		}
	function cancel_order_init(order_no) {
		
		$.getJSON("cancel_order_init.action", {

			order_no : order_no

		}, function(result) {
			var field = result.msg;

			if (field.success) {

				var o = field.o;
				$('#cancel_order_no').textbox('setValue',order_no);

				dialog_init_mid('cancel_order_div');
				$('#cancel_order_div').dialog('open');

			} else {
				$.messager.alert("操作提示", "初始化出错！原因：" + field.body, "error");

			}

		});

	}
	//$("#deliverView").dialog('close');

	function cancel_order_ok() {

		$.getJSON("cancel_order.action", {
			order_no : $('#cancel_order_no').textbox('getValue'),
			cancel_memo : $('#cancel_memo').textbox('getValue')

		}, function(result) {
			var field = result.msg;
			if (field.success) {
				$.messager.alert("操作提示", "已经申请取消订单，请关注后续账单状态变化", "info");
				$('#cancel_order_div').dialog('close');
				cancel_order_page_init();

			} else {
				$.messager.alert("操作提示", "申请取消出错！原因：" + field.body, "error");
			}

		});

	}
</script>

