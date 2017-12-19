<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div>
	快递公司<select class="easyui-combobox" id="logistics_com"
		style="width: 90px">

	</select> <a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:set_default('default_logistics_com',$('#logistics_com').combobox('getValue'))"
		iconCls="qyd">默认</a>运输方式<select class="easyui-combobox"
		style="width: 60px;" id="vehicle">

	</select><a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="javascript:set_default('default_logistics_vehicle',$('#vehicle').combobox('getValue'))"
		iconCls="qyd">默认</a>
		<span id="not_air" ></span>
</div>

	
	

<script type='text/javascript'>
	var after_logistics_change;

	function set_not_air(not_air) {
		if (not_air){
			$('#not_air').css("color", "red");
			$('#not_air').text('不可发送航空');
			}
		else{
			$('#not_air').css("color", "black");
			$('#not_air').text('可发送航空');
			}
		

		var current_vehicle = $('#vehicle').combobox('getValue');
		if (current_vehicle == "") {
			//第一次运行

		} else {

			//重新获取vehicle
			change_logistics();

		}

	}

	function logistics_ready() {
		
		$('#logistics_com').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			valueField : 'logistics_code',
			textField : 'logistics_name',
			onChange : function(newValue, oldValue) {
				
				change_logistics();

			}
		});
		$('#vehicle').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			valueField : 'vehicle_code',
			textField : 'vehicle_name',
			onChange : function(newValue, oldValue) {

				sum_total();;
			}
		});
		//默认是不限制航空
		set_not_air(false,null);

		$
				.getJSON(
						"<s:property value='#application.context_path'/>/logistics/get_logistics.action",
						{

						},
						function(result) {
							var rs = result.msg;
							if (rs.success) {
								//有效

								var logistics_list = rs.o;
								$("#logistics_com").combobox("loadData",
										logistics_list);
								$('#logistics_com')
										.combobox('select',
												'<s:property value="#session.default_logistics_com" />');

							} else {
								//$('#original_id_msg').text('');
								//alert("sdfsdfsdf");

							}

						});

	}

	function change_logistics() {
		var current_vehicle = $('#vehicle').combobox('getValue');
		var text=$('#not_air').text();
		var not_air;
		if (text=='不可发送航空'){
			not_air=true;
			}
		else{
			not_air=false;
			}
		

		
		$.getJSON(
						"<s:property value='#application.context_path'/>/logistics/get_vehicle.action",
						{
							"logistics_code" : $('#logistics_com').combobox(
									'getValue'),
							not_air : not_air
						},
						function(result) {
							var rs = result.msg;
							if (rs.success) {
								//有效
								var vehicle_list = rs.o;
								$("#vehicle")
										.combobox("loadData", vehicle_list);
								if (current_vehicle==''){
									$('#vehicle')
									.combobox('select',
											'<s:property value="#session.default_logistics_vehicle" />');
									}
								else{

									if (not_air || current_vehicle == 'AIR_') {

										
										$('#vehicle').combobox('select',
												vehicle_list[0].vehicle_code);

									} else {
										$('#vehicle').combobox('select',
												current_vehicle);
									}

									}
								
								sum_total();
								
								

							} else {
								//$('#original_id_msg').text('');
								//alert("sdfsdfsdf");

							}

						});

	}
</script>

