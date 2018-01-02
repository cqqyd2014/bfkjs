<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
省
<input class="easyui-combobox" style="width: 100px;" id="province"
	name="province"/>
	
<input class="easyui-combobox" style="width: 100px;" id='city'
	name="city"/>
	
县
<input class="easyui-combobox" style="width: 100px;" id='district'
	name="district"/>

<script type='text/javascript'>

	function region_ready() {
		//dialog_init_mid('cancel_order_div');

		$('#province').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			valueField : 'region_id',
			textField : 'region_name',
			onChange : function(newValue, oldValue) {

				change_province();
			}
		});
		$('#city').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			valueField : 'region_id',
			textField : 'region_name',
			onChange : function(newValue, oldValue) {

				change_city();
			}
		});

		$('#district').combobox({
			required : true,
			multiple : false, //多选
			editable : false, //是否可编辑
			valueField : 'region_id',
			textField : 'region_name'
		});

		get_province();

	}
	function get_province() {
		var id = $("#province").combobox("getValue");
		$.getJSON("../system/get_regions.action", {
			"pid" : 1
		}, function(result) {
			var rs = result.msg;
			if (rs.success) {
				//有效
				var province_list = rs.o;
				$("#province").combobox("loadData", province_list);
				$('#province').combobox('select', province_list[0].region_id);

			} else {
				//$('#original_id_msg').text('');
				//alert("sdfsdfsdf");

			}

		});

	}
	function change_province() {

		var id = $("#province").combobox("getValue");

		$.getJSON("../system/get_regions.action", {
			"pid" : id
		}, function(result) {

			var rs = result.msg;

			if (rs.success) {
				//有效
				var city_list = rs.o;
				$("#city").combobox("loadData", city_list);

				$('#city').combobox('select', city_list[0].region_id);

				change_city();

			} else {
				//$('#original_id_msg').text('');
				//alert("sdfsdfsdf");

			}

		});

	}

	function change_city() {

		var id = $("#city").combobox("getValue");

		$.getJSON("../system/get_regions.action", {
			"pid" : id
		}, function(result) {

			var rs = result.msg;

			if (rs.success) {
				//有效
				var district_list = rs.o;
				//console.log(district_list);
				$("#district").combobox("loadData", district_list);

				$('#district').combobox('select', district_list[0].region_id);

			} else {
				//$('#original_id_msg').text('');
				//alert("sdfsdfsdf");

			}

		});

	}
</script>

