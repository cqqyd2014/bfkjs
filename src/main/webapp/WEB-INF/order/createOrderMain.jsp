<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>创建订单</title>
<jsp:include page="../common/include_easyui2.jsp" flush="true" />



<script type="text/javascript">
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

	function change_amount() {
		var amount_before = $('#amount_before').val();
		var amount_end = $('#amount_end').val();
		var diff = amount_end - amount_before;
		if (diff > 0) {
			//价格上涨，变为运费
			$('#discount').numberbox('setValue', 0);
			$('#ship_fee').numberbox('setValue', diff);
		} else {
			//价格下降，为优惠

			$('#discount').numberbox('setValue', diff);
			$('#ship_fee').numberbox('setValue', 0);

		}
		update_actual_amount();
		$('#one_click_change_amount').dialog('close');

	}

	function one_click_change_amount() {
		//alert($('#original_amount').val());

		$('#amount_before').val($('#original_amount').numberbox('getValue'));
		$('#amount_end').val($('#original_amount').numberbox('getValue'));
		$('#one_click_change_amount').dialog('open');

	}

	function clear_order_detail() {
		window.location.href = "createOrderInit.action";
	}

	function push_order() {
		
		if ($("#original_no").textbox('getValue') == '0000000000') {
			
			$.messager.alert("操作提示", "订单号不能为0000000000",
			"error");
			return;
		}

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
		$.getJSON("push_order.action", {
			original_no : $("#original_no").textbox('getValue'),
			OrderFrom : $('#order_from').combobox("getValue"),
			order_dat : $('#order_dat').datebox('getValue'),
			userName : user_name,
			tell :tell,
			province : $('#province').combobox("getText"),
			city : $('#city').combobox("getText"),
			district : $('#district').combobox("getText"),
			userAddr : $('#user_addr').textbox('getValue'),
			original_amount : $('#original_amount').numberbox('getValue'),
			discount : $('#discount').numberbox('getValue'),
			card_pay : $('#card_pay').numberbox('getValue'),
			ship_fee : $('#ship_fee').numberbox('getValue'),
			actual_amount : $('#actual_amount').numberbox('getValue'),
			memo : $('#memo').textbox('getValue'),
			tell2 : $('#tell2').textbox('getValue'),
			user_com : $('#user_com').textbox('getValue'),
			logistics:$('#logistics_com').combobox(	'getValue'),
			vehicle:$('#vehicle').combobox('getValue')

		}, function(result) {

			ajax_stop();
			var field=result.msg;
			ajax_authority(field);

				if (field.success) {
					alert('保存成功');
					window.location.href = "create_order_init.action";

				} else {
					alert(field.body);

				}

			

		});
		$("input[name=not_air]").attr("disabled",true);
	}


	
		
		

	function del_order_detail() {
		if ($("#goods_name").val() == "") {
			alert("商品信息不能为空");
			return;
		}

		$.messager.confirm('Confirm', '确认删除这个商品\'' + $("#goods_name").val()
				+ '\'', function(r) {
			if (r) {
				
				$.getJSON("del_order_detail.action", {
					"goods_id" : $("#goods_id").val()

				}, function(result) {

					$.each(result, function(i, field2) {

						if (field2.success) {
							var o = field2.o;
							updatePay(o);
							show_table_order_detail();

						} else {
							$.messager.alert("操作提示", "删除商品错误！原因：" + field2.body,
									"error");

						}

					});

				});
				
				
				
			}
		});

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

	}
	function sum_total(){
	//根据商品和快递计算总价
		$.getJSON("get_amount.action", {
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

		check_order_exist("tell",value);
		$('#memo_msg').text('');
	}

	
}


	$(document).ready(function() {


		region_ready();
		
		orders_ready();
		
		logistics_ready();


		select_goods_ready();
		 
		
		$("#detail_num").val("0"); 

		/*
		$('#originalID').blur(function() {
			//原始订单号与订单来源
			$('#originalID').val(trim($('#originalID').val()));
			originalId_orderFrom();
			check_orderno_exist('original_id', $('#originalID').val());

		});
		*/
		
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

		
		$("#one_click_change_amount").dialog('close');
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

	});

	function replaceOrderFromTip(userName, userCardId) {

		document.getElementById("userName").value = userName;
		document.getElementById("userCardId").value = userCardId;
		document.getElementById("userCardId").readonly = "readonly";
	}


</script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />


</head>

<body width="100%">



	<div>
		<span style="float: left;"><h2>新建订单</h2></span><span
			style="float: right;"> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:push_order()"
			iconCls="icon-ok">提交订单</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:clear_order_detail()"
			iconCls="icon-clear">清空订单</a>
		</span>
	</div>

	<div>
		<table border="1" width="100%" class="box">


			<tr>
				<td align="right" width="15%">电商平台原始订单号</td>
				<td width="35%"><input id="original_no" style="width: 200px;"
					class="easyui-textbox" required="true" /></td>


				<td align="right" width="15%">订单来源</td>
				<td width="35%"><s:select label="订单来源" id="order_from"
						list="ofus" listKey="type_code" listValue="type_name"
						cssClass="easyui-combobox" /><a href="javascript:void(0)"
					class="easyui-linkbutton"
					onclick="javascript:set_default('default_order_from',$('#order_from').combobox('getValue'))"
					iconCls="qyd">默认</a></td>
			</tr>
			<tr>
				<td align="right" width="15%">下单时间</td>
				<td width="35%"><input id="order_dat" /></td>







				<td align="right">姓名</td>
				<td><input id="user_name" class="easyui-textbox"
					required="true" data-options="prompt:'请输入收件人姓名',iconCls:'icon-man'"
					style="width: 200px;" /></span></td>
			</tr>
			<tr>
				<td align="right">主要联系电话（手机）</td>
				<td><input name="tell" class="easyui-textbox" required="true"
					id="tell" style="width: 200px;" /></td>
				<td align="right">第二联系电话（座机）</td>
				<td><input name="tell2" class="easyui-textbox" id="tell2"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">单位</td>
				<td><input name="user_com" class="easyui-textbox" id="user_com"
					style="width: 200px;" /></td>



				<td align="right">地址(省市)</td>
				<td><jsp:include page="common/region.jsp" flush="true" /></td>
			</tr>

			<tr>
				<td align="right">详细地址（街道小区）</td>
				<td colspan='3'><input  class="easyui-textbox"
					required="true" multiline="true" id="user_addr"
					style="width: 100%; height: 30px" /></span></td>
			</tr>

			<tr>
				<td colspan="2">订单金额（元）：<input
					class="easyui-numberbox" id="original_amount" readonly="readonly"
					style="width: 50px;" />优惠及运费（元）：（-）优惠金额<input type="text"
					id="discount" class="easyui-numberbox" style="width: 50px;"
					readonly="readonly" />（-）卡支付<input 
					class="easyui-numberbox" id="card_pay" class="easyui-numberbox"
					style="width: 50px;" readonly="readonly" />（+）运费<input 
					id="ship_fee" class="easyui-numberbox" style="width: 50px;"
					readonly="readonly" />=实付（元）<input 
					class="easyui-numberbox" id="actual_amount" readonly="readonly"
					style="width: 50px;" /></td>
				<td>默认快递：</td>
				<td><jsp:include page="common/logistics.jsp" flush="true" /></td>
			</tr>
			<tr>
				<td >备注</td><td colspan='3'><input
					class="easyui-textbox" id="memo"  data-options="multiline:true"
					style="width: 100%;height:50px" /></td>
			</tr>

			<tr>
				<td colspan='4'><span id='memo_msg'></span></td>
			</tr>


		</table>
	</div>
	<div style="width: 100%;">
		<table id="order_detail_table" title="清单" class="easyui-datagrid"
			style="width: 100%;" iconCls="qyd" fitColumns="true"
			rownumbers="true" showFooter="true">

			<thead>
				<tr>

					<th field="goods_id" width="20%">商品编码</th>
					<th field="goods_name" width="25%">商品名称</th>
					<th field="price" width="10%" align="right">价格</th>
					<th field="unit" width="5%" align="right">单位</th>
					<th field="num" width="5%" align="right">数量</th>
					<th field="total1" width="10%">小计</th>
					<th field="memo" width="10%">可用仓库</th>

				</tr>
			</thead>

		</table>
		<div></div>

	</div>
	<div>
		<jsp:include page="common/select_goods.jsp" flush="true" />
		<div style="float: right">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:add_order_detail()" iconCls="icon-ok">添加订单明细</a><input
				type="hidden" id="detail_num">

		</div>

	</div>



	<div id="one_click_change_amount" class="easyui-dialog" title="一键改价"
		iconCls="qyd" style="width: 600px; height: 400px; padding: 10px;"
		buttons="#one_click_change_amount-buttons">



		<div>
			订单原价是<input type="text" id="amount_before" readonly="readonly" />元
		</div>
		<div>
			需要更改为<input type="text" id="amount_end" class="easyui-numberbox" />元
		</div>





	</div>
	<div id="one_click_change_amount-buttons">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>

				<td style="text-align: right"><a href="#"
					class="easyui-linkbutton" iconCls="icon-ok"
					onclick="javascript:change_amount()">确定</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#one_click_change_amount').dialog('close')">取消</a></td>
			</tr>
		</table>
	</div>

	<jsp:include page="common/orders.jsp" flush="true" />

</body>
</html>
