<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<script type='text/javascript'>
	function view_route_ready() {
		dialog_init('view_route_div');
	}

	//$("#deliverView").dialog('close');

	function view_route(order_no, seq) {

		clearDataTable('view_route_table');
		//只对顺丰电子面单有效
		ajax_start();
		$.getJSON("../express/sf/get_route.action", {

			order_no : order_no,
			seq : seq

		}, function(result) {
			ajax_stop();
			var field = result.msg;

			if (field.success) {
				var data = field.o;

				$('#view_route_table').datagrid('loadData', data);
				dialog_init_mid('view_route_div');
				$('#view_route_div').dialog('open');

			} else {
				$.messager.alert("操作提示", "查询顺丰路由出错！原因：" + field.body, "error");

			}

		});

	}
</script>



<div id="view_route_div" class="easyui-dialog" title="查看路由"
	style="width: 600px; height: 400px; padding: 10px"
	data-options="	iconCls: 'qyd',	buttons: '#view_route_div_buttons'">
	<div class="easyui-panel" collapsible="false" fit="true" border="false">
		<div class="easyui-layout" fit="true">
			<div region="north" border="false" >
				<label>实时查询顺丰运单的路由信息</label>
			</div>
			<div region="center" border="false" fit="true">
				<table id="view_route_table" class="easyui-datagrid"
					style="width: 100%;" rownumbers="true" fitColumns="true">
					<thead>
						<tr>
							<th field="accept_time">时间</th>
							<th field="accept_addr">地址</th>
							<th field="remark">描述</th>


						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>
</div>


<div id="view_route_div_buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:$('#view_route_div').dialog('close')"
		iconCls="icon-ok">关闭查看路由窗口</a>
</div>