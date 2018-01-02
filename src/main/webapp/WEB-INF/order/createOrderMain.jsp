<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>





<script type="text/javascript">



	function del_detail(detail_uuid){
		$.messager.confirm('操作提示','确认删除这个订单明细?',function(r){
		    if (r){
				//alert('ok');
				ajax_start();
		    	$.getJSON("../order/del_order_detail.action", {
					"detail_uuid" : detail_uuid

				}, function(result) {

					ajax_stop();
					var field=result.msg;

						if (field.success) {					
							show_table_order_detail();

						} else {
							$.messager.alert("操作提示", "删除订单明细错误！原因：" + field2.body,
									"error");

						}

					

				});
		    }
		});

		}
	function check_order_exist(field, value) {
		//判断该订单是否已经录入过

		ajax_start();
		$.getJSON("check_order_exist.action", {
			"field" : field,
			"value" : value
		}, function(result) {

			ajax_stop();
			var rs=result.msg;

				if (rs.success) {
					//有重复

					var orders=rs.o;
					
					orders_init(orders);
				} else {
					//$('#original_id_msg').text('');
					//alert("sdfsdfsdf");

				}

			

		});
	}

	function auto_analysis() {

		$.getJSON("auto_analysis.action", {

			memo : $('#memo').val()

		}, function(result) {

			$.each(result, function(i, field) {

				if (field.success) {
					//地址解码成功
					var om = field.o;
					$('#originalID').val(om.id.orderNo);
					originalId_orderFrom();
					check_orderno_exist('original_id', om.id.orderNo);
					$('#tell').textbox("setValue", om.CTell);
					check_orderno_exist('tell', om.CTell);
					$('#tell2').textbox("setValue", om.tell2);
					$('#userAddr').textbox("setValue", om.CUserAddr);
					check_orderno_exist('user_addr', om.CUserAddr);
					$('#userName').textbox("setValue", om.CUserName);
					check_orderno_exist('user_name', om.CUserName);
					var par_province = "option:contains(" + om.addrProvince
							+ ")";

					$('#province').find(par_province).attr("selected", true);

					$('#memo_msg').text($('#memo').val());
					$('#memo').val(field.o2);
					/*
					//更新城市列表
					
					if (om.addrProvince=='北京市'||om.addrProvince=='天津市'||om.addrProvince=='上海市'||om.addrProvince=='重庆市'){
						}
					else{
						var par_city="option:contains("+om.addrCity+")";
						$('#city').find(par_city).attr("selected",true);
						}
					//更新区
					var par_district="option:contains("+om.addrDistrict+")";
						$('#district').find(par_district).attr("selected",true);
					
					alert( $('#city').find("option:selected").text());
					 */
				} else {
					//地址解码失败
					alert(field.body);

				}

			});

		});

	}



	function clear_order_detail() {
		$('#body').layout('panel','center').panel('refresh',"../order/create_order_init.action");
		
	}

	function push_order() {
		
		

		var user_addr=easyui_textbox_tirm('user_addr');
		if (user_addr  == '') {
			$.messager.alert("操作提示", "收件人街道不能为空",
			"error");
			return;
		} 
		
		var user_name=easyui_textbox_tirm('user_name');
		if ($('#userName').val() == '') {

			$.messager.alert("操作提示", "收件人姓名不能为空",
			"error");
			return;
		} 
		var tell=easyui_textbox_tirm('tell');
		if (tell== '') {

			$.messager.alert("操作提示", "收件人主要联系方式不能为空",
			"error");
			return;
		} 
		
		

		var now = new Date();
		var d_order_dat = new Date($('#order_dat').datebox('getValue'));
		if (now < d_order_dat) {
			$.messager.alert("操作提示", "订单时间在未来",
			"error");
			return;
		}

		if ($('#original_amount').numberbox('getValue') == '0') {
			$.messager.alert("操作提示", "订单金额为0",
			"error");
			return;
		} 

		ajax_start();
		$.getJSON("../order/push_order.action", 

				$('#new_order_form').serializeObject()
				, function(result) {

			ajax_stop();
			var field=result.msg;
			ajax_authority(field);

				if (field.success) {
					alert('保存成功');
					$('#body').layout('panel','center').panel('refresh',"../order/create_order_init.action");
					

				} else {
					alert(field.body);

				}

			

		});
		$("input[name=not_air]").attr("disabled",true);
	}


	
		
	

	function add_order_detail() {

		if ($("#goods_name").textbox('getValue') == "") {

			$.messager.alert('操作提示','商品名称不能为空!','error');
			
			return;
		}
		if ($("#c_price").numberbox('getValue') == "") {
			
			$.messager.alert('操作提示','商品价格不能为空!','error');
			return;
		}
		if ($("#c_count").numberbox('getValue')== "") {
			
			$.messager.alert('操作提示','商品数量不能为空!','error');
			return;
		}
		var count=Number($("#c_count").numberbox('getValue'));
		var yue=Number($("#yue").numberbox('getValue'));
		if (count> yue) {
			
			$.messager.alert('操作提示','购买数量不能大于存货数量!','error');
			return;
		}

		$.getJSON("add_order_detail.action", {
			"goods_id" : $("#goods_id").textbox('getValue'),
			"goods_name" : $("#goods_name").textbox('getValue'),
			"c_price" : $("#c_price").numberbox('getValue'),
			"c_count" : $("#c_count").numberbox('getValue'),
			"unit" : $("#unit").textbox('getValue')

		}, function(result) {

			var field2=result.msg;

				if (field2.success) {					
					show_table_order_detail();

				} else {
					$.messager.alert("操作提示", "添加的商品错误！原因：" + field2.body,
							"error");

				}

			

		});

	}



	/*
	function DclickPrice2(rowData) {
		$("#goods_id").val(rowData.goods_id);
		getGoodsPrice(false);
		$("#c_count").numberbox('setValue', rowData.c_count);
		$("#c_price").numberbox('setValue', rowData.price);

	}

	*/

	function show_table_order_detail() {


		var gridOpts = $('#order_detail_table').datagrid('options');   
		gridOpts.url="../order/get_temp_order_detail.action";
		//gridOpts.queryParams=$('#search_bill_pars').serializeObject();
		//console.log(gridOpts.queryParams);
		$("#order_detail_table").datagrid("load");

		sum_total();

		/*

		$.getJSON("get_temp_order_detail.action", {}, function(data) {
			var field=data.msg;

				if (field.success) {

					var data = field.o;

					var n = data.length;
					$("#order_detail_table").datagrid('loadData', {
						total : n,
						rows : data
					});
					//添加和删除订单，金额变化由现实触发。
					//检测是否有不能发送航空的商品
					var not_air=false;
					for(var index in data){
			            if (data[index].not_air){
				            not_air=true}
			        }
					set_not_air(not_air);
					
					

				} else {
					$.messager.alert("操作提示", "获取订单明细错误！原因：" + field.body,
							"error");

				}

			

		});
		*/

	}
	function sum_total(){
	//根据商品和快递计算总价
		$.getJSON("../order/get_amount.action", {
			logistics_com:$('#logistics_com').combobox("getValue"),
			vehicle:$('#vehicle').combobox('getValue')
			}, function(data) {
			var field=data.msg;

				if (field.success) {

					var total1=field.o;
					$("#original_amount").numberbox('setValue', total1);
					var num=field.o2;
					var fee=field.o3;
					$("#ship_fee").numberbox('setValue', fee);
					var sum_amount=Number($("#original_amount").numberbox('getValue'))
					- Number($("#discount").numberbox('getValue'))
					- Number($("#card_pay").numberbox('getValue'))
					+ Number($("#ship_fee").numberbox('getValue'));
					
					//alert(sum_amount);
					$("#actual_amount").numberbox('setValue',
							sum_amount);
					

				} else {
					
					
					
				}

			

		});
		
		}



	function original_no_check(){
		var original_no=easyui_textbox_tirm('original_no');

		check_order_exist("original_no",original_no);

		$.getJSON("check_original_no.action", {
			order_from:$('#order_from').combobox("getValue"),
			original_no:$('#original_no').textbox('getValue'),
			order_from_name:$('#order_from').combobox("getText")
			}, function(data) {
			var field=data.msg;

				if (field.success) {

					
					$('#memo_msg').html('');

				} else {
					
					if (field.body=='单号长度错误'){
						
						
						var html='<font color="red">'+field.o+'</font>';
						$('#memo_msg').html(html);
						}
					
					
				}

			

		});

		}


	function user_name_check(){

		var value=easyui_textbox_tirm('user_name');

		if (!isChinaName(value)){
			$('#memo_msg').text('看起来不像是中文名字。');
			$('#memo_msg').css({
				color : "red"
			});
			}
		else {
			//这个人是否近30天有包裹
			
			check_order_exist("user_name",value);
			
			
			


			
			$('#memo_msg').text('');
		}

		}
function tell_check(){
	var tell=easyui_textbox_tirm('tell');
	

		

	if (!isPhoneNo(tell)) {

		//$('#phone').focus();
		$('#memo_msg').text('看起来不像是手机号，建议首选手机号。');
		$('#memo_msg').css({
			color : "red"
		});
	} else {

		check_order_exist("tell",tell);
		$('#memo_msg').text('');
	}

	
}
$.parser.onComplete = function(){
	
	load_ready();
};	

	function load_ready(){




		
		$('#order_detail_table')
		.datagrid(
				{
					//border:false,  
					fitColumns : true,
					singleSelect : true,
					title : '订单明细',
					rownumbers : true,
					columns : [ [
							{
								field : 'bank_name',
								title : '商品编码'
							},
							{
								field : 'goods_name',
								title : '商品名称'
							},
							{
								field : 'price',
								title : '价格'
							},
							{
								field : 'unit',
								title : '单位'
							},
							{
								field : 'num',
								title : '数量'
							},
							{
								field : 'total1',
								title : '小计'
							},
							{
								field : 'memo',
								title : '可用仓库'
							},

							{
								field : 'opt',
								title : '操作',
								width : '100px',
								align : 'center',
								formatter : function(
										value, rec) {
									var btn = '<a class="del_detail" onclick="del_detail(\''
											+ rec.detail_uuid
											+ '\')" href="javascript:void(0)">删除</a>';
									return btn;
								}
							} ] ],
					onLoadSuccess : function(data) {
						$('.del_detail').linkbutton({
							text : '删除',
							plain : true,
							iconCls : 'icon-cancel'
						});

						$('#order_detail_table').datagrid(
								'fixRowHeight')

					}
				});


		region_ready();
		
		orders_ready();
		
		logistics_ready();


		select_goods_ready();
		 
		
		$("#detail_num").val("0"); 

		
		
		$("input",$("#original_no").next("span")).blur(function(){  
			original_no_check();
		}) 


		
		
		
		$("#original_amount").numberbox('setValue', 0);
		$("#discount").numberbox('setValue', 0);
		$("#card_pay").numberbox('setValue', 0);
		$("#ship_fee").numberbox('setValue', 0);

		$("#actual_amount").numberbox('setValue', 0);

		/*
		$('#order_detail_table').datagrid({

			onDblClickRow : function(rowIndex, rowData) {
				DclickPrice2(rowData);
			}
		});
*/
		//初始化对话框

		
		
		//初始化清单
		show_table_order_detail();

		$('#order_dat').datetimebox({
			formatter : function(date) {
				var y = date.getFullYear();
				var m = date.getMonth() + 1;
				var d = date.getDate();
				var h = date.getHours();
				var M = date.getMinutes();
				var s = date.getSeconds();
				function formatNumber(value) {
					return (value < 10 ? '0' : '') + value;
				}

				return y + '-' + m + '-' + d + ' ' + h + ':' + M + ":" + s;
			},
			parser : function(s) {
				
				var t = Date.parse(s);
				if (!isNaN(t)) {
					return new Date(t);
				} else {
					return new Date();
				}
			},

			required : true,
			okText : "确定",
			closeText : "关闭",
			currentText : "今日"
		});

		$('#order_dat').datetimebox('setValue', '${order_time}');

		

		$("input",$("#tell").next("span")).blur(function(){  
			tell_check();
		}) 
$("input",$("#user_name").next("span")).blur(function(){  
			user_name_check();
		}) 
		
		

		$('#c_count').numberbox({
			min : 0,
			precision : 2,

		});
		$('#c_price').numberbox({
			min : 0,
			precision : 2,

		});

	}

	function replaceOrderFromTip(userName, userCardId) {

		document.getElementById("userName").value = userName;
		document.getElementById("userCardId").value = userCardId;
		document.getElementById("userCardId").readonly = "readonly";
	}
function random_original_no(){
	var Num=""; 
	for(var i=0;i<10;i++) 
	{ 
		Num+=Math.floor(Math.random()*10); 
	}
	$('#original_no').textbox('setValue', Num);
}

</script>


<div class="easyui-layout" data-options="fit:true" style="display:table;">
<div style="display:table-cell;text-align:center;">
<div 
		style="background:white;display:inline-block;vertical-align: middle;box-shadow:5px 2px 6px #000;padding: 10px; margin: 10px;border:1px solid Gray">
	<h1 align='center'>新建订单</h1>
	
		
		
	

	<form id="new_order_form">
		<table border="1" width="100%" class="box">
		<tr>
			<td colspan='4' align="right"> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:push_order()"
			iconCls="icon-ok">提交订单</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:clear_order_detail()"
			iconCls="icon-clear">清空订单</a></td>
		</tr>


			<tr>
				<td align="left" width="15%">电商平台订单号</td>
				<td width="35%"><input id="original_no" name='original_no'style="width: 200px;"
					class="easyui-textbox" required="true" /><a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:random_original_no()"
			iconCls="icon-ok">随机生成</a></td>


				<td align="left" width="15%">订单来源</td>
				<td width="35%"><s:select label="订单来源" id="order_from" name="order_from"
						list="ofus" listKey="type_code" listValue="type_name"
						cssClass="easyui-combobox" /><a href="javascript:void(0)"
					class="easyui-linkbutton"
					onclick="javascript:set_default('default_order_from',$('#order_from').combobox('getValue'))"
					iconCls="qyd">默认</a></td>
			</tr>
			<tr>
				<td align="left" width="15%">下单时间</td>
				<td width="35%"><input id="order_dat" name="order_dat"/></td>







				<td align="left">姓名</td>
				<td><input id="user_name" class="easyui-textbox" name="user_name"
					required="true" data-options="prompt:'请输入收件人姓名',iconCls:'icon-man'"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="left">主要联系电话（手机）</td>
				<td><input name="tell" class="easyui-textbox" required="true" name="tell"
					id="tell" style="width: 200px;" /></td>
				<td align="right">第二联系电话（座机）</td>
				<td><input name="tell2" class="easyui-textbox" id="tell2" name="tell2"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="left">收件人单位</td>
				<td><input name="user_com" class="easyui-textbox" id="user_com" name="user_com"
					style="width: 200px;" /></td>



				<td align="left">地址(省市)</td>
				<td><jsp:include page="common/region.jsp" flush="true" /></td>
			</tr>

			<tr>
				<td align="right">详细地址（街道小区）</td>
				<td colspan='3'><input  class="easyui-textbox"
					required="true" multiline="true" id="user_addr" name="user_addr"
					style="width: 100%; height: 30px" /></span></td>
			</tr>

			<tr>
				<td colspan="2">订单金额（元）：<input
					class="easyui-numberbox" id="original_amount" readonly="readonly" name="original_amount"
					style="width: 50px;" />优惠及运费（元）：（-）优惠金额<input type="text"
					id="discount" class="easyui-numberbox" style="width: 50px;" name="discount"
					readonly="readonly" />（-）卡支付<input 
					class="easyui-numberbox" id="card_pay" class="easyui-numberbox" name="card_pay"
					style="width: 50px;" readonly="readonly" />（+）运费<input 
					id="ship_fee" class="easyui-numberbox" style="width: 50px;" name="ship_fee"
					readonly="readonly" />=实付（元）<input 
					class="easyui-numberbox" id="actual_amount" readonly="readonly" name="actual_amount"
					style="width: 50px;" /></td>
				<td>默认快递：</td>
				<td><jsp:include page="common/logistics.jsp" flush="true" /></td>
			</tr>
			<tr>
				<td >备注</td><td colspan='3'><input
					class="easyui-textbox" id="memo"  data-options="multiline:true" name="memo"
					style="width: 100%;height:50px" /></td>
			</tr>

			<tr>
				<td colspan='4'><span id='memo_msg'></span></td>
			</tr>


		</table>
	</form>
	
		<table id="order_detail_table" width="100%">

		</table>
		<div></div>

	
	<!-- 添加订单明细 -->
	<div>
		<jsp:include page="common/select_goods.jsp" flush="true" />
		<div style="float: right">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:add_order_detail()" iconCls="icon-ok">添加订单明细</a><input
				type="hidden" id="detail_num">

		</div>

	</div>




	</div>
	</div>
</div>
	<jsp:include page="common/orders.jsp" flush="true" />

