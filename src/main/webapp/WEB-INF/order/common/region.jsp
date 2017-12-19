<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
省/自治区/直辖市
<select class="easyui-combobox" style="width: 100px;" id="province">
	<option value="-1">--未选择--</option>
地/市
</select>
<select class="easyui-combobox" style="width: 100px;" id='city'>
	<option value="-1">--未选择--</option>
</select>
区/县
<select class="easyui-combobox" style="width: 100px;" id='district'>
	<option value="-1">--未选择--</option>
</select>
<script type='text/javascript'>

	function region_ready(){
		//dialog_init_mid('cancel_order_div');
		
		


	$('#province').combobox({    
        required:true,    
        multiple:false, //多选
        editable:false,  //是否可编辑
        valueField:'regionId',
        textField:'regionName',
        onChange:function(newValue,oldValue){
        	
       	 change_province();  
        }  
        });
	 $('#city').combobox({    
         required:true,    
         multiple:false, //多选
         editable:false,  //是否可编辑
         valueField:'regionId',
         textField:'regionName',
        	 onChange:function(newValue,oldValue){
                 
               	 change_city();  
                } 
         }); 

	 $('#district').combobox({    
         required:true,    
         multiple:false, //多选
         editable:false,  //是否可编辑
         valueField:'regionId',
         textField:'regionName'
         }); 


     get_province();


	 }
	 function get_province(){
		var id = $("#province").combobox("getValue");
			$.getJSON("province_and_city.action", {
				"id" : 1
			}, function(result) {
				var rs=result.msg;
					if (rs.success) {
						//有效
						var province_list = rs.o;
						$("#province").combobox("loadData", province_list);						 
						 $('#province').combobox('select',province_list[0].regionId);	
						

					} else {
						//$('#original_id_msg').text('');
						//alert("sdfsdfsdf");

					}

					

			});

		}
		function change_province() {

			var id = $("#province").combobox("getValue");
			

			$.getJSON("province_and_city.action", {
				"id" : id
			}, function(result) {

				var rs=result.msg;

					if (rs.success) {
						//有效
						var city_list = rs.o;
						$("#city").combobox("loadData", city_list);
						 
						 $('#city').combobox('select',city_list[0].regionId);


						
						

						change_city();

					} else {
						//$('#original_id_msg').text('');
						//alert("sdfsdfsdf");

					}

			

			});

		}

		function change_city() {

			var id = $("#city").combobox("getValue");
			
			$
					.getJSON(
							"province_and_city.action",
							{
								"id" : id
							},
							function(result) {

								var rs=result.msg;

													if (rs.success) {
														//有效
														var district_list = rs.o;
														$("#district").combobox("loadData", district_list);
														 
														 $('#district').combobox('select',district_list[0].regionId);

													} else {
														//$('#original_id_msg').text('');
														//alert("sdfsdfsdf");

													}

										

							});

		}


		
</script>

