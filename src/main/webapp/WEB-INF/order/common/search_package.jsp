<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>


<table class='box' style="width: 100%">
	<tr>
		<td>1、订单状态： <SELECT id="search_order_order_status"
			style="width: 70px">
				<option VALUE="0" SELECTED>所有订单</option>
				<option VALUE="订单生成">订单生成</option>
				<option VALUE="订单已付">订单已付</option>
				<option VALUE="订单处理">订单处理</option>
				<option VALUE="发货完毕">发货完毕</option>
		</SELECT> 2、收件人姓名:<input id="search_order_user_name" class="easyui-textbox"
			style="width: 70px" /> 3、收件人手机:<input id="search_order_user_tell"
			class="easyui-textbox" style="width: 70px" /> 4、电商平台单号：<input
			id="search_order_original_no" class="easyui-textbox"
			style="width: 70px" /> 5、运单号：<input id="search_order_express_no"
			class="easyui-textbox" style="width: 70px" /> 6、商品条码：<input
			id="search_order_goods_barcode" class="easyui-textbox"
			style="width: 70px" /> 6、商品名称：<input id="search_order_goods_name"
			class="easyui-textbox" style="width: 70px" /> 每页显示<input
			style="width: 50px" id="rows_in_page"
			value=<s:property value="#session.default_rows_in_page" />
			type="text" class="easyui-numberbox" precision="0" />行 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:set_default('default_rows_in_page',$('#rows_in_page').numberbox('getValue'))"
			iconCls="qyd">默认</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconcls="icon-search"
			onclick="javascript:page_init()">查询</a>
		</td>
	</tr>
	<tr>
		<td>
			<table id="order_list_table" style="width: 100%" pagination="true"
				sortName="itemid" sortOrder="desc" singleSelect="true"
				fitColumns="true">

			</table>

		</td>
	</tr>
</table>




<script type='text/javascript'>


	var current_page;
	var rows_in_page;



	function show_package_list_table(page, rows) {
		//alert($('input[name="order_status"]:checked ').val());
		current_page=page;

		$.getJSON("GetWaitReviewSendList.action", {
			"page" : page,
			"rows" : rows
			
		}, function(result) {
			//.each(data,function(i,item)//alert(item);//);.each(data,function(i,item)//alert(item);//);.messager.progress('close'); 
			$('#order_list_table').datagrid('loadData', result);

			show_order_list_table_pages(page);

		});
	}
	function show_order_list_table_pages(page) {
		$.getJSON("GetWaitReviewSendListPages.action", {

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
	
	
	function search_package_ready() {
		current_page = 1;
		rows_in_page = <s:property value="#session.default_rows_in_page" />;
		$('#rows_in_page').val(rows_in_page);
		$('#search_package_package_status').combobox({
			required : true,
			multiple : false, //多选
			editable : false //是否可编辑
			
		});

	}
	var rowaction_handler;
	var pagesaction_handler;
	//获取明细记录
	function show_package_list_table(rowaction, pagesaction) {

		rowaction_handler = rowaction;
		pagesaction_handler = pagesaction;
		ajax_start();
		$.getJSON(rowaction,
				{
					"page" : current_page,
					"rows" : rows_in_page,

					user_name : $('#search_order_user_name')
							.textbox('getValue'),
					goods_name : $('#search_order_goods_name').textbox(
							'getValue'),
					user_tell : $('#search_order_user_tell')
							.textbox('getValue'),
					original_id : $('#search_order_original_no').textbox(
							'getValue'),
					order_status : $('#search_order_order_status').combobox(
							'getValue'),
					express_no : $('#search_order_express_no').textbox(
							'getValue'),
					barcode : $('#search_order_goods_barcode').textbox(
							'getValue')

				}, function(result) {
					ajax_stop();
					$('#order_list_table').datagrid('loadData', result);

					show_order_list_table_pages(pagesaction);

				});
	}
	function show_order_list_table_pages(pagesaction) {
		$
				.getJSON(
						"get_all_ordres_count.action",
						{
							user_name : $('#search_order_user_name').textbox(
									'getValue'),
							goods_name : $('#search_order_goods_name').textbox(
									'getValue'),
							user_tell : $('#search_order_user_tell').textbox(
									'getValue'),
							original_id : $('#search_order_original_no')
									.textbox('getValue'),
							order_status : $('#search_order_order_status')
									.combobox('getValue'),
							express_no : $('#search_order_express_no').textbox(
									'getValue'),
							barcode : $('#search_order_goods_barcode').textbox(
									'getValue')

						},
						function(result) {

							var count = parseInt(result, 10);
							var rows_in_page = parseInt($('#rows_in_page')
									.val(), 10);

							var pages = Math.ceil(count / rows_in_page);
							//alert(pages);
							var p = $('#order_list_table').datagrid('getPager');
							//alert(page);
							$(p)
									.pagination(
											{
												pageNumber : current_page,
												total : count,
												pageSize : rows_in_page,//每页显示的记录条数，默认为10   
												pageList : [ rows_in_page ],//可以设置每页记录条数的列表   
												beforePageText : '第',//页数文本框前显示的汉字   
												afterPageText : '页    共 {pages} 页',
												displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
												onSelectPage : function(
														pageNumber, pageSize) {
													current_page = pageNumber;

													show_order_list_table(
															rowaction_handler,
															pagesaction_handler);
													$("html,body")
															.finish()
															.animate(
																	{
																		"scrollTop" : "0px"
																	}, 900);
												}

											});

						});

	}
</script>

