<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<script language='javascript' type='text/javascript'>

	function orders_ready() {
		 dialog_init('orders_div');
		 
		 
	}


	function orders_init(data){
		

				$('#view_orders_table').datagrid('loadData', data);
				dialog_init_mid('orders_div');
				$('#orders_div').dialog('open');


		
		

		
		}
		
		
		

	</script>

<div id="orders_div" class="easyui-dialog" title="类似订单"
	style="width: 600px; height: 400px; padding: 10px"
	data-options="	iconCls: 'qyd',	buttons: '#orders_div_buttons'">
	<table id="view_orders_table" class="easyui-datagrid"
					style="width: 100%;" rownumbers="true" fitColumns="true">
					<thead>
						<tr>
							<th field="original_id">电商平台原始单号</th>
							<th field="order_dat">订单时间</th>
							<th field="order_no">系统订单编号</th>
							<th field="user_name">收货人姓名</th>
							<th field="effective">是否有效</th>
							<th field="province">省直辖市</th>
							<th field="city">地市</th>
							<th field="district">区县</th>
							<th field="user_addr">街道小区门牌号</th>
							<th field="tell">主要联系电话</th>
							<th field="detail_memo">订单详情</th>
							<th field="order_status">订单状态</th>
							<th field="cancel_status">取消状态</th>
							<th field="logistics_name">默认物流</th>
							


						</tr>
					</thead>

				</table>



</div>
<div id="orders_div_buttons">
	<a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#orders_div').dialog('close')">关闭窗口</a>
</div>