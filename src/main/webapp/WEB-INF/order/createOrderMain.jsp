<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>创建订单</title>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/qyd.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">


<script type="text/javascript">
	function check_orderno_exist(field, value) {
		//判断该订单是否已经录入过

		$.getJSON("check_order_exist.action", {
			"field" : field,
			"value" : value
		}, function(result) {

			$.each(result, function(i, rs) {

				if (rs.success) {
					//有重复

					switch (field) {
					case "original_id":
						$('#original_id_msg').text(
								'原始订单号' + $("#originalID").val() + '在系统中重复');
						$('#original_id_msg').css({
							color : "red"
						});
						$('#memo_msg').text(rs.o);
						$('#memo_msg').css({
							color : "red"
						});
						break;
					case "user_name":
						$('#user_name_msg').text(
								'收件人' + $("#userName").val() + '在系统中重复');
						$('#user_name_msg').css({
							color : "red"
						});
						$('#memo_msg').text(rs.o);
						$('#memo_msg').css({
							color : "red"
						});
						break;
					case "tell":
						$('#tell_msg').text(
								'收件人电话' + $("#tell").val() + '在系统中重复');
						$('#tell_msg').css({
							color : "red"
						});
						$('#memo_msg').text(rs.o);
						$('#memo_msg').css({
							color : "red"
						});
						break;
					case "user_addr":
						$('#user_addr_msg').text(
								'收件人详细地址' + $("#userAddr").val() + '在系统中重复');
						$('#user_addr_msg').css({
							color : "red"
						});
						$('#memo_msg').text(rs.o);
						$('#memo_msg').css({
							color : "red"
						});
						break;
					}

				} else {
					//$('#original_id_msg').text('');
					//alert("sdfsdfsdf");

				}

			});

		});
	}
	function originalId_orderFrom() {
		//根据订单号自动判断平台类型
		<s:iterator value="otls">
		var order_no_len_<s:property value="len"/> = '<s:property value="type_code"/>';
		</s:iterator>

		var len = ($('#originalID').val()).length;

		var order_len = 'order_no_len_' + len;

		try {

			var ebusiness = eval(order_len);

			$('#orderFrom').val(ebusiness);
			$('#original_id_msg').text("");

		} catch (error) {

			//write("出现了异常<br>"); 
			//write("异常类型："+error.name+"<br>"); 
			//write("异常消息："+error.message); 
			if (error.message == order_len + ' is not defined') {
				$('#original_id_msg').text('没有订单长度为' + len + "的平台");
				$('#original_id_msg').css({
					color : "red"
				});
			} else {
				$('#original_id_msg').text('');
			}

		} finally {

		}

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
					change();
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
		$("input[name=not_air]").attr("disabled",false);
		//alert($('input:radio[name=not_air]:checked').val());
		

		if ($('input:radio[name=not_air]:checked').val()=='true'&&$('#vehicle_map').val()=='AIR_'){
			alert("该订单不能发航空");
			return;
			}
		if ($("#originalID").val() == '0000000000') {
			alert('订单号不能为0000000000');
			return;
		}

		if ($('#province option:selected').text() == '--未选择--') {
			alert('请选择省');
			return;
		}
		if ($('#city option:selected').text() == '--未选择--') {
			alert('请选择市');
			return;

		}
		if ($('#district option:selected').text() == '--未选择--') {
			alert('请选择区');
			return;
		}
		if ($('#userAddr').val() == '') {
			$('#user_addr_msg').text('请输入详细地址');
			$('#user_addr_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#user_addr_msg').text('');
		}
		$('#userName').val(trim($('#userName').val()));
		if ($('#userName').val() == '') {

			$('#user_name_msg').text('请输入收件人姓名');
			$('#user_name_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#user_name_msg').text('');

		}
		if ($('#tell').val() == '') {

			$('#tell_msg').text('请输入收件人电话(手机)');
			$('#tell_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#tell_msg').text('');
		}
		if ($('#originalID').val() == '') {
			$('#original_id_msg').text('请输入电商品台原始单号');
			$('#tell_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#original_id_msg').text('');
		}
		if ($('#order_dat').datebox('getValue') == '') {
			$('#dat_msg').text('请输入下单时间');
			$('#tell_msg').css({
				color : "red"
			});
			return;
		}

		var now = new Date();
		var d_order_dat = new Date($('#order_dat').datebox('getValue'));
		if (now < d_order_dat) {
			$('#dat_msg').text('订单时间在未来');
			$('#dat_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#dat_msg').text('');
		}

		if ($('#original_amount').numberbox('getValue') == '0') {
			$('#original_amount_msg').text('订单金额为0');

			$('#original_amount_msg').css({
				color : "red"
			});
			return;
		} else {
			$('#original_amount_msg').text('');
		}

		$.getJSON("push_order.action", {
			original_no : $("#originalID").val(),
			OrderFrom : $("#orderFrom").val(),
			order_dat : $('#order_dat').datebox('getValue'),
			userName : $('#userName').val(),
			tell : $('#tell').val(),
			province : $('#province option:selected').text(),
			city : $('#city option:selected').text(),
			district : $('#district option:selected').text(),
			userAddr : $('#userAddr').val(),
			original_amount : $('#original_amount').numberbox('getValue'),
			discount : $('#discount').numberbox('getValue'),
			card_pay : $('#card_pay').numberbox('getValue'),
			ship_fee : $('#ship_fee').numberbox('getValue'),
			actual_amount : $('#actual_amount').numberbox('getValue'),
			memo : $('#memo').val(),
			tell2 : $('#tell2').val(),
			user_com : $('#user_com').val(),
			logistics:$('#logistics_map').val(),
			vehicle:$('#vehicle_map').val()

		}, function(result) {

			$.each(result, function(i, field) {

				if (field.success) {
					alert('保存成功');
					window.location.href = "create_order_init.action";

				} else {
					alert(field.body);

				}

			});

		});
		$("input[name=not_air]").attr("disabled",true);
	}

	function updatePay(data) {
		//跟新页面金额数据和发货仓库
		var order_sum = 0;

		var num=0;
		$("input[name='not_air'][value='false']").prop("checked",true);
		
		$.each(data, function(i, o) {
			
				num=num+o.num;
				order_sum = order_sum + o.price*o.num;


				
				if (o.not_air){
					
					$("input[name='not_air'][value='true']").prop("checked",true);
					}
				

				
			
		});
		$("#detail_num").val(num);
		$("#original_amount").numberbox('setValue', order_sum);
		
		update_actual_amount();
		//跟新仓库
	}
	function update_actual_amount() {
		//得到运费
		if ($("#detail_num").val()==0){
			return
		}
		
		$.getJSON("get_logistics_fee.action", {
					"num" : $("#detail_num").val(),
					logistics:$('#logistics_map').val(),
					vehicle:$('#vehicle_map').val()

				}, function(result) {
					var field=result.msg;
					

						if (field.success) {
							var o = field.o;
							$("#ship_fee").numberbox('setValue', o);
							//更新商品最终价格
							var sum_amount=Number($("#original_amount").numberbox('getValue'))
							- Number($("#discount").numberbox('getValue'))
							- Number($("#card_pay").numberbox('getValue'))
							+ Number($("#ship_fee").numberbox('getValue'));
							
							//alert(sum_amount);
							$("#actual_amount").numberbox('setValue',
									sum_amount);
						

						} else {
							$.messager.alert("操作提示", "得到运费错误！原因：" + field.body,
									"error");

						}

					

				});
		
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

		if ($("#goods_name").val() == "") {
			alert("商品信息不能为空");
			return;
		}
		if ($("#c_price").val() == "") {
			alert("商品价格不能为空");
			return;
		}
		if ($("#c_count").val() == "") {
			alert("商品数量不能为空");
			return;
		}
		if (Number($("#c_count").val()) > Number($("#yue").val())) {
			alert("购买数量不能大于存货数量");
			return;
		}

		$.getJSON("add_order_detail.action", {
			"goods_id" : $("#goods_id").val(),
			"goods_name" : $("#goods_name").val(),
			"c_price" : $("#c_price").val(),
			"c_count" : $("#c_count").val(),
			"unit" : $("#unit").val()

		}, function(result) {

			var field2=result.msg;

				if (field2.success) {
					var o = field2.o;
					updatePay(o);
					
					show_table_order_detail();

				} else {
					$.messager.alert("操作提示", "添加的商品错误！原因：" + field2.body,
							"error");

				}

			

		});

	}

	//模糊/精确查询goods_id
	function getGoodsPrice(fuzzy) {
		

		$.getJSON("get_goods_info.action", {
			goods_id:$("#goods_id").val(),
			fuzzy:fuzzy

		}, function(result) {

			var field2=result.msg;
			ajax_authority(field2);

				if (field2.success) {
					var o = field2.o;
					if (o != "[]") {
						if (o.length == 1) {

							$.each(o, function(i, field) {
								//填充商品名称等
								$("#goods_name").val(field.goods_name);
								$("#unit").val(field.unit);
								$("#goods_id").val(field.goods_id);
								$("#yue").val(field.yue);
								$("#c_price")
										.numberbox('setValue', field.price);
								$("#goods_id").css("color", "black");

							});
						} else {
							$("#goods_name").val("");
							$("#unit").val("");
							$("#c_price").numberbox('setValue', '0');
							$("#goods_id").css("color", "red");
							$("#yue").val("0");
						}
					} else {
						$("#goods_name").val("");
						$("#unit").val("");
						$("#c_price").numberbox('setValue', '0');
						$("#goods_id").css("color", "red");
						$("#yue").val("0");
					}

				} else {
					$.messager.alert("操作提示", "获取单价错误！原因：" + field2.body,
							"error");

				}

			

		});

	}

	function DclickPrice(rowData) {
		//alert(rowData.goods_id);
		$("#goods_id").val(rowData.goods_id);
		getGoodsPrice(false);
		$('#findGoods').dialog('close');

	}
	function DclickPrice2(rowData) {
		$("#goods_id").val(rowData.goods_id);
		getGoodsPrice(false);
		$("#c_count").numberbox('setValue', rowData.c_count);
		$("#c_price").numberbox('setValue', rowData.price);

	}

	var table_goods_price_view = $
			.extend(
					{},
					$.fn.datagrid.defaults.view,
					{
						renderRow : function(target, fields, frozen, rowIndex,
								rowData) {
							var cc = [];
							cc.push('<td width=\'100px\'>' + rowData.goods_id
									+ '</td>');
							cc
									.push('<td width=\'100px\'><img  width="90" src="../img/product/<s:property value="#request.com_id" />/'
											+ rowData.goods_id
											+ '.jpg" /></td>');

							cc.push('<td width=\'100px\'>' + rowData.c_name
									+ '</td>');
							cc.push('<td width=\'100px\'>' + rowData.price
									+ '</td>');
							cc.push('<td width=\'100px\'>' + rowData.unit
									+ '</td>');
							cc.push('<td width=\'100px\'>' + rowData.yue
									+ '</td>');

							return cc.join('');
						}
					});

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

				} else {
					$.messager.alert("操作提示", "获取订单明细错误！原因：" + field.body,
							"error");

				}

			

		});

	}

	function show_table_goods_pirce() {

		$.getJSON("get_goods_info.action", {
			"goods_id" : $("#goods_id").val(),
			fuzzy : true

		}, function(result) {

			var field=result.msg;
			ajax_authority(field);

				if (field.success) {

					var o = field.o;

					$("#table_goods_price").datagrid('loadData', {
						total : o.length,
						rows : o
					});

				} else {
					$.messager.alert("操作提示", "获取商品列表错误！原因：" + field.body,
							"error");

				}

			

		});

	}

	function findGoods() {

		show_table_goods_pirce();

		$('#findGoods').dialog('open');

	}
	$(document).ready(function() {
		$("input[name=not_air]").attr("disabled",true);
		$("#detail_num").val("0"); 

		$('#originalID').blur(function() {
			//原始订单号与订单来源
			$('#originalID').val(trim($('#originalID').val()));
			originalId_orderFrom();
			check_orderno_exist('original_id', $('#originalID').val());

		});
		$('#logistics_map').val('<s:property value="#request.logistics" />');
		
		$('#vehicle_map').val('<s:property value="#request.vehicle" />');
		$("#original_amount").numberbox('setValue', 0);
		$("#discount").numberbox('setValue', 0);
		$("#card_pay").numberbox('setValue', 0);
		$("#ship_fee").numberbox('setValue', 0);

		$("#actual_amount").numberbox('setValue', 0);

		$('#table_goods_price').datagrid({
			view : table_goods_price_view,

			onDblClickRow : function(rowIndex, rowData) {
				DclickPrice(rowData);
			}
		});
		$('#order_detail_table').datagrid({

			onDblClickRow : function(rowIndex, rowData) {
				DclickPrice2(rowData);
			}
		});

		//初始化对话框

		$("#findGoods").dialog('close');
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

		//失去焦点，得到产品
		$("#goods_id").blur(function(e) {
			getGoodsPrice(true);
		});
		//失去焦点，验证是否为手机号
		$("#tell").blur(function(e) {
			$('#tell_msg').text('');

			if ($.trim($('#tell').val()).length == 0) {

				$('#tell_msg').text('手机号没有输入');
				$('#tell_msg').css({
					color : "red"
				});
			} else {
				if (isPhoneNo($.trim($('#tell').val()) == false)) {

					//$('#phone').focus();
					$('#tell_msg').text('手机号码不正确');
					$('#tell_msg').css({
						color : "red"
					});
				} else {
					$('#tell_msg').text('');
				}
			}
			check_orderno_exist('tell', $("#tell").val())
		});

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

	function change() {

		var id = $("#province").val();

		$.getJSON("province_and_city.action", {
			"id" : id
		}, function(result) {

			$.each(result, function(i, rs) {

				if (rs.success) {
					//有效
					var city_list = rs.o;
					var city_object = $("#city");
					city_object.empty();

					$.each(city_list, function(j, city) {
						city_object
								.append("<option value = '"+city.regionId+"'>"
										+ city.regionName + "</option>");
					});

					change2();

				} else {
					//$('#original_id_msg').text('');
					//alert("sdfsdfsdf");

				}

			});

		});

	}

	function change2() {

		var id = $("#city").val();

		$
				.getJSON(
						"province_and_city.action",
						{
							"id" : id
						},
						function(result) {

							$
									.each(
											result,
											function(i, rs) {

												if (rs.success) {
													//有效
													var district_list = rs.o;
													var district_object = $("#district");
													district_object.empty();

													$
															.each(
																	district_list,
																	function(j,
																			district) {
																		district_object
																				.append("<option value = '"+district.regionId+"'>"
																						+ district.regionName
																						+ "</option>");
																	});

												} else {
													//$('#original_id_msg').text('');
													//alert("sdfsdfsdf");

												}

											});

						});

	}
</script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="../js/qyd.css">
</head>

<body width="98%">
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
				<td align="right" width="15%">电商原始订单号</td>
				<td width="35%"><input id="originalID" name="originalID"
					style="width: 200px;" /><span id="original_id_msg"></span></td>


				<td align="right" width="15%">订单来源</td>
				<td width="35%"><s:select label="订单来源" id="orderFrom"
						name="OrderFrom" list="ofus" listKey="type_code" listValue="type_name" /><a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_order_from',$('#orderFrom').val())"
					iconCls="qyd">默认</a></td>
			</tr>
			<tr>
				<td align="right" width="15%">下单时间</td>
				<td width="35%"><s:textfield id="order_dat" name="order_dat"
						label="下单时间" /><span id="dat_msg"></span></td>







				<td align="right">姓名</td>
				<td><input name="userName" id="userName" class="easyui-textbox"
					required="true" data-options="prompt:'请输入收件人姓名',iconCls:'icon-man'"
					style="width: 200px;" /><span id="user_name_msg"></span></td>
			</tr>
			<tr>
				<td align="right">主要联系电话（手机）</td>
				<td><input name="tell" class="easyui-textbox" required="true"
					id="tell" style="width: 200px;" /><span id="tell_msg"></span></td>
				<td align="right">第二联系电话（座机）</td>
				<td><input name="tell2" class="easyui-textbox" id="tell2"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">单位</td>
				<td><input name="user_com" class="easyui-textbox" id="user_com"
					style="width: 200px;" /></td>



				<td align="right">地址(省市)</td>
				<td>省<s:select name="province" id="province" list="provinces"
						listValue="regionName" listKey="regionId " onchange="change();"
						headerKey="-1" headerValue="--未选择--" />市<s:select name="city"
						id="city" onchange="change2();" headerKey="-1"
						list="#{-1:'--未选择--'}" />区<s:select name="district" id="district"
						headerKey="-1" list="#{-1:'--未选择--'}" /></td>
			</tr>

			<tr>
				<td align="right">详细地址（街道小区）</td>
				<td colspan='3'><input name="userAddr" class="easyui-textbox"
					required="true" multiline="true" id="userAddr" label="地址"
					style="width: 100%; height: 30px" /><span id='user_addr_msg'></span></td>
			</tr>

			<tr>
				<td colspan="2">订单金额（元）：<input type="text" class="easyui-numberbox" id="original_amount"
					readonly="readonly" style="width: 50px;" />优惠及运费（元）：（-）优惠金额<input
					type="text" id="discount" class="easyui-numberbox"
					style="width: 50px;" readonly="readonly" />（-）卡支付<input
					type="text" class="easyui-numberbox" id="card_pay" class="easyui-numberbox"
					style="width: 50px;" readonly="readonly" />（+）运费<input type="text" id="ship_fee"
					class="easyui-numberbox" style="width: 50px;" readonly="readonly" />=实付（元）<input
					type="text" class="easyui-numberbox" id="actual_amount" readonly="readonly"
					style="width: 50px;" /><span id='actual_amount_msg'></span><span id='original_amount_msg'></span></td>
				<td>默认快递：</td>
				<td><s:select id="logistics_map" list="logistics_map"
						listKey="key" listValue="value" onchange='update_actual_amount()'/><a href="javascript:void(0)"
					class="easyui-linkbutton"
					onclick="javascript:set_default('default_logistics_com',$('#logistics_map').val())"
					iconCls="qyd">默认</a>
				<s:select id="vehicle_map"  onchange='update_actual_amount()' list="vehicle_map" listKey="key"
						listValue="value" /><a href="javascript:void(0)"
					class="easyui-linkbutton"
					onclick="javascript:set_default('default_logistics_vehicle',$('#vehicle_map').val())"
					iconCls="qyd">默认</a><label><input type="radio"
						name='not_air' value='false' readonly="readonly">可航空</label><label><input
						type="radio" name='not_air' value='true' readonly="readonly">禁发航空</label></td>
			</tr>
			</tr>
			<tr>
				<td align="right">备注/智能分析填单<a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="javascript:auto_analysis()"
					iconCls="qyd">开始分析</a></td>
				<td colspan="3"><textarea id="memo"
						style="width: 100%; height: 50px"></textarea></td>

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
		<div>

			<table border="1" align="left" width="100%" class="box">
				<tr>
					<td width="13%">商品编码</td>
					<td width="20%"><input type="text" id="goods_id" /><a
						href="javascript:void(0)" onclick="javascript:findGoods()"
						class="easyui-linkbutton" iconCls="icon-search"></a></td>
					<td width="13%">商品名称</td>
					<td width="20%"><input type="text" id="goods_name"
						readonly="readonly" /></td>
					<td width="13%">单位</td>
					<td width="20%"><input type="text" id="unit"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td width="13%">数量</td>
					<td width="20%"><input type="text" id="c_count"
						class="easyui-numberbox" /><span id="wh_name_available"><input type='hidden'
						id="yue""></td>
					<td width="13%">单价</td>
					<td width="20%"><input type="text" id="c_price"
						class="easyui-numberbox" readonly="readonly"/></td>
					<td width="13%">操作</td>
					<td width="20%"><a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="javascript:add_order_detail()"
						iconCls="icon-ok">添加(更新)</a><a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="javascript:del_order_detail()"
						iconCls="icon-cancel">删除</a> <input type="hidden" id="detail_num"></td>
				</tr>

			</table>
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

	<div id="findGoods" class="easyui-dialog" title="商品" iconCls="qyd"
		style="width: 600px; height: 400px; padding: 10px;"
		toolbar="#findGoods-toolbar" buttons="#findGoods-buttons">

		<table id="table_goods_price" title="商品" class="easyui-datagrid"
			style="width: 100%; height: 100%;" idField="itemid" pagination="true"
			iconCls="qyd">

			<thead>
				<tr>

					<th field="itemid" width="100">商品编码</th>
					<th field="goods_id" width="100">商品图片</th>
					<th field="productid" width="100">商品名称</th>
					<th field="listprice" width="100" align="right">价格</th>
					<th field="unitcost" width="100" align="right">单位</th>
					<th field="status" width="100" align="center">库存数量</th>
				</tr>
			</thead>

		</table>



	</div>
	<div id="findGoods-toolbar">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>

				<td style="text-align: right">选中需要发货的商品，双击<input></input><a
					href="#" class="easyui-linkbutton" iconCls="icon-search"
					plain="true"></a></td>
			</tr>
		</table>
	</div>
	<div id="findGoods-buttons">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>

				<td style="text-align: right"><a href="#"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#findGoods').dialog('close')">关闭</a></td>
			</tr>
		</table>
	</div>


</body>
</html>
