<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<script language='javascript' type='text/javascript'>
	var new_deliver_handler;

	function change_logistics_check() {

		var logistics = $('#new_deliver_logistics').combobox('getValue');

		var len = eval('express_no_len_' + logistics);

		
		easyui_textbox_type_len("new_deliver_express_no", len, function() {

			easyui_textbox_focus("new_deliver_pickup_barcode");
			pickup_barcode();

		});
	}

	function new_deliver_ready(page_init) {
		new_deliver_handler = page_init;
		dialog_init('new_deliver_div');
		$('#new_deliver_bill_logistics').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			onChange : function(newValue, oldValue) {

				//change_logistics_check();

			}
		});

		$('#new_deliver_bill_vehicle').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			onChange : function(newValue, oldValue) {

				//change_logistics_check();

			}
		});

		easyui_textbox_enter("new_deliver_pickup_barcode", function() {
			easyui_textbox_focus("new_deliver_pickup_barcode");
			
			//如果单号长度为22
			if ($("#new_deliver_pickup_barcode").textbox('getValue').length == 22) {
				pickup_barcode();
			}
			else{
				easyui_textbox_focus("new_deliver_pickup_barcode");
				}
		});

		easyui_textbox_enter("new_deliver_express_no", function() {
			
			easyui_textbox_focus("new_deliver_pickup_barcode");
			

			var logistics = $('#new_deliver_bill_logistics').combobox('getValue');
			var len = eval('express_no_len_' + logistics);

			
			
			if ($('#new_deliver_express_no').textbox('getValue').length == len) {
				
				
				pickup_barcode();
			}
			else{
				
				easyui_textbox_focus("new_deliver_pickup_barcode");
				}
		});

	}

	function new_deliver_init(order_no, logistics, logistics_name, vehicle,
			vehicle_name, wh_id, wh_name) {

		//$("#new_deliver_bill_logistics ").combobox('select','<s:property value="#session.default_logistics_com" />');
		//alert('1111');
		$.getJSON("new_deliver_bill_init.action", {
			"order_no" : order_no

		}, function(result) {

			var field = result.msg;
			ajax_authority(field);

			$('#new_deliver_order_no').val(order_no);
			$('#new_deliver_express_no').textbox('setValue', "");
			$('#new_deliver_pickup_barcode').textbox('setValue', "");
			$('#new_deliver_seq').val("");
			$('#new_deliver_wh_name').val(wh_name);
			//alert($('#wh_map').val());
			$('#new_deliver_wh_id').val(wh_id);
			$('#new_deliver_vehicle_name').text(vehicle_name);
			$('#new_deliver_vehicle').val(vehicle);
			$('#new_deliver_logistics_name').text(logistics_name);
			$('#new_deliver_logistics').val(logistics);
			//$('#new_deliver_div_wh_id').val(logistics);
			$('#new_deliver_bill_logistics').combobox('select', logistics);
			$('#new_deliver_bill_vehicle').combobox('select', vehicle);

			//clearDataTable("new_deliver_picked_barcode_table");
			var field = result.msg;

			if (field.success) {
				//初始化成功，清空session中的拣货sn清单

				show_new_deliver_need_table();

				$('#message').text('');

				dialog_init('new_deliver_div');
				$('#new_deliver_div').dialog('open');
				easyui_textbox_focus("new_deliver_express_no");

			} else {
				//alert("初始化session拣货清单出错");

				$.messager.alert("操作提示", "初始化发货单错误！原因：" + field.body, "error");

			}

		});

	}

	function show_new_deliver_picked_barcode_table() {

		$.getJSON("get_picked_barcode.action", {

		}, function(result) {

			var field = result.msg;
			ajax_authority(field);

			if (field.success) {
				var o = field.o;
				var n = o.length;

				$("#new_deliver_picked_barcode_table").datagrid('loadData', {
					total : n,
					rows : o
				});

			}

		});
	}

	function show_new_deliver_need_table() {

		$.getJSON("get_need_pickup.action", {
			order_no : $("#new_deliver_order_no").val()
		}, function(result) {

			var field = result.msg;
			ajax_authority(field);
			var o = field.o;
			$("#new_deliver_need_table").datagrid('loadData', {
				total : o.length,
				rows : o
			});

		});
	}

	function new_deiliver_save() {

		//检查快递单号是否正确
		if ($("#new_deliver_express_no").textbox('getValue') == '') {
			alert("快递单号不能为空");
			return;
		}
		//检查发货sn不能为空
		$.ajax({
			type : "post",
			url : "get_picked_barcode.action",

			async : false,
			success : function(result) {

				var field = result.msg;
				ajax_authority(field);

				if (field.success) {
					var o = field.o;
					var n = o.length;

					if (n == 0) {
						alert("已拣货清单不能为空");
						return;
					}

				}

			}
		});
		//alert($("#new_deliver_div_wh_id").val());
		$.getJSON("save_new_deliver_bill.action",
				{
					"order_no" : $("#new_deliver_order_no").val(),
					"logistics" : $("#new_deliver_bill_logistics").combobox(
							'getValue'),
					"express_no" : $("#new_deliver_express_no").textbox(
							'getValue'),
					"seq" : $("#new_deliver_seq").val(),
					"wh_id" : $("#new_deliver_wh_id").val(),
					"vehicle" : $('#new_deliver_bill_vehicle').combobox(
							'getValue')
				}, function(result) {
					var field = result.msg;
					ajax_authority(field);

					if (field.success) {
						//body 是返回的seq序号

						print_deliver_bill($("#new_deliver_order_no").val(),
								field.body);

						$('#new_deliver_div').dialog('close');
						new_deliver_handler();

					} else {
						alert("错误" + field.body);
					}

				});

	}

	function pickup_barcode() {
		var barcode = $("#new_deliver_pickup_barcode").textbox('getValue');

		if (barcode.length != 14 && barcode.length != 22
				&& barcode.length != 18) {
			$.messager
					.alert("操作提示", "请确认条码长度，商品条码长度为14或者22，预包装条码为18！", "error");
			easyui_textbox_focus("new_deliver_pickup_barcode");

			return;
		}

		$("#new_deliver_pickup_barcode").textbox('disable');

		//检测合法性
		$("#message").empty();

		$.getJSON("pickup_goodsbarcode.action",
						{pickup_barcode : $("#new_deliver_pickup_barcode")
									.textbox('getValue'),
							order_no : $("#new_deliver_order_no").val(),
							seq : $('#new_deliver_seq').val(),
							wh_id : $('#new_deliver_wh_id').val(),
							"logistics" : $("#new_deliver_bill_logistics")
									.combobox('getValue'),
							"express_no" : $("#new_deliver_express_no")
									.textbox('getValue'),
							"vehicle" : $('#new_deliver_bill_vehicle')
									.combobox('getValue')
						},
						function(result) {
							var field = result.msg;
							ajax_authority(field);

							if (field.success) {

								var html_message = "";
								//是否预包装发货
								if (field.body == '预包装发货') {
									html_message = html_message
											+ "<div><font color='blue'>预包装发货成功</font></div>";
									html_message = html_message
											+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

									$('#message').html(html_message);

									$.messager.alert("操作提示", "预包装发货成功", "info");
									$('#new_deliver_div').dialog('close');
									show_order_list_table(current_page, $(
											'#rows_in_page').val());

								} else {
									html_message = html_message
											+ "<div><font color='blue'>"
											+ field.body + "</font></div>";

									html_message = html_message
											+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

									$('#message').html(html_message);
									var logistics = $('#new_deliver_bill_logistics')
											.combobox('getValue');

									var len = eval('express_no_len_'
											+ logistics);

									if (field.sound == 'item_picked_complet') {
										//商品拣货完成，自动提交
										new_deiliver_save();
									} else {
										show_new_deliver_picked_barcode_table();
									}

								}

							} else {
								var html_message = "";
								html_message = html_message
										+ "<div><font color='red'>"
										+ field.body + "</font></div>";

								html_message = html_message
										+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';

								$('#message').html(html_message);
							}

							$("#new_deliver_pickup_barcode").textbox(
									'setValue', "");
							$("#new_deliver_pickup_barcode").textbox('enable');
							easyui_textbox_focus("new_deliver_pickup_barcode");

						});

	}
</script>









<div id="new_deliver_div" title="拣货" class="easyui-dialog"
	style="padding: 5px; width: 600px; height: 400px;" iconCls="qyd"
	buttons="#new_deliver_div_buttons">
	<input type="hidden" id="new_deliver_order_no" /> <input type="hidden"
		id="new_deliver_seq">

	<div>
		<div>
			当前仓库：<span id="new_deliver_wh_name"></span> <input type="hidden"
				id='new_deliver_wh_id'> <span>订单默认快递为：</span><input
				type='hidden' id='new_deliver_logistics'><span
				id='new_deliver_logistics_name'></span><span>，默认运输方式为：</span><input
				type='hidden' id='new_deliver_vehicle'><span
				id='new_deliver_vehicle_name'></span>
		</div>
		<div>
			1、快递单信息
			<s:select id="new_deliver_bill_logistics" list="logistics_map"
				listKey="key" listValue="value" style=" width: 150px;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_logistics_com',$('#new_deliver_bill_logistics').combobox('getValue'))"
				iconCls="qyd">默认</a> 运输方式
			<s:select id="new_deliver_bill_vehicle" list="vehicle_map"
				listKey="key" listValue="value" style=" width: 150px;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:set_default('default_logistics_vehicle',$('#new_deliver_bill_vehicle').combobox('getValue'))"
				iconCls="qyd">默认</a>

		</div>
		<div>
			<input class="easyui-textbox" id="new_deliver_express_no"
				style="height: 40px; font-size: 30px; width: 100%;" size="30">
		</div>


	</div>

	<div>2、拣货信息</div>


	<div id="users-contain">

		<table id="new_deliver_need_table" title="待发清单"
			class="easyui-datagrid" style="width: 100%;" iconCls="qyd"
			fitColumns="true" rownumbers="true" showFooter="true">
			<thead>
				<tr>

					<th field="goods_name">商品名称</th>
					<th field="order_count">应发数量</th>
					<th field="yue">未发数量</th>

				</tr>
			</thead>





		</table>
	</div>


	<div>请在此扫描商品条码</div>
	<div>
		<input class="easyui-textbox" id="new_deliver_pickup_barcode"
			style="height: 40px; font-size: 30px; width: 100%;" size="30">
	</div>
	<div>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:pickup_barcode()">拣货这个商品</a>
	</div>
	<div id="message"></div>
	<div>
		<table id="new_deliver_picked_barcode_table" class="easyui-datagrid"
			title="已经拣货清单" style="width: 100%;" iconCls="qyd" fitColumns="true"
			rownumbers="true" showFooter="true">
			<thead>
				<tr>
					<th field="goods_name">品名</th>
					<th field="barcode">已拣货条码</th>

				</tr>
			</thead>

		</table>

	</div>
</div>
<div id="new_deliver_div_buttons">
	<a id="b_new_express" href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:print_express($('#new_deliver_order_no').val(),'0000',$('#new_deliver_bill_logistics').combobox('getValue'),$('#new_deliver_bill_vehicle').combobox('getValue'),'BIG_',$('#wh_id').combobox('getValue'),'<s:property value="#session.user_id" />')"
		iconCls="qyd">打印纸质面单</a> <a id="b_electric_express"
		href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:print_express($('#new_deliver_order_no').val(),'0000',$('#new_deliver_bill_logistics').combobox('getValue'),$('#new_deliver_bill_vehicle').combobox('getValue'),'ELEC',$('#wh_id').combobox('getValue'),'<s:property value="#session.user_id" />')"
		iconCls="qyd">打印电子面单</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		onclick="javascript:new_deliver_init($('#new_deliver_order_no').val())">清空发货单</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:new_deiliver_save()">部分拣货并打印发货单</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:$('#new_deliver_div').dialog('close')">没想好先关闭</a>
</div>
