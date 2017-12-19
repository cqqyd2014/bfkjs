<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<script language='javascript' type='text/javascript'>
var change_express_parent_init_method;
var change_express_order_no;
var change_express_seq;
	function change_express_ready(parent_init_method) {
		change_express_parent_init_method=parent_init_method;
		
		 dialog_init_mid('change_express_div');
		
		 

		 $('#change_express_logistics').combobox({    
             required:true,    
             multiple:false, //多选
             editable:false,  //是否可编辑
             valueField : 'logistics_code',
				textField : 'logistics_name'
             });  
		 $('#change_order_no').textbox('textbox').attr('readonly',true);
		 $('#change_seq').textbox('textbox').attr('readonly',true);
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
							$("#change_express_logistics").combobox("loadData",
									logistics_list);
							$('#change_express_logistics')
									.combobox('select',
											'<s:property value="#session.default_logistics_com" />');

						} else {
							//$('#original_id_msg').text('');
							//alert("sdfsdfsdf");

						}

					});
		 
	}


		<s:iterator value="experss_no_len_map" var="entry">

	var change_express_no_len_<s:property value="key"/> = <s:property value="value"/>;

	</s:iterator>
		
		
		
		function change_express_init(order_no,seq){
			
			change_express_order_no=order_no;
			change_express_seq=seq;
			$('#change_order_no').textbox('setValue',order_no);
			$('#change_express_no').textbox('setValue',"");
			$('#change_seq').textbox('setValue',seq);
			$('#change_express_logistics').combobox('select', '<s:property value="#session.default_logistics_com" />');
			dialog_init_mid('change_express_div');
			$('#change_express_div').dialog('open');
			
			}
		

		function change_express(){
			var logistics=$('#change_express_logistics').combobox('getValue');
			
			var len=eval('change_express_no_len_'+logistics);
			var new_express_no=$("#change_express_no").textbox('getValue');
			if (new_express_no.length == len) {

				//符合长度要求

				ajax_start();
				
				$.getJSON("change_express.action", {
					"order_no" : $("#change_order_no").textbox('getValue'),
					logistics:$('#change_express_logistics').combobox('getValue'),
					seq:$('#change_seq').textbox('getValue'),
					express_no:$('#change_express_no').textbox('getValue')
				}, function(result) {
					ajax_stop();
					var field=result.msg;
						if (field.success) {
							$.messager.alert("操作提示", "更新快递信息成功",
							"info");
							$('#change_express_div').dialog('close');
							change_express_parent_init_method(change_express_order_no,change_express_seq);
							

						} else {
							$('#change_express_message').html('<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>');
							$.messager.alert("操作提示", "更新快递信息出错！原因：" + field.body,
							"error");
						}

					
				});
				
			}
			else{
				$.messager.alert("操作提示", '物流单号长度不对，应该为'+len+'位',
				"error");
				 }


			


			}

	</script>

<div id="change_express_div" class="easyui-dialog" title="更新物流信息"
	style="width: 600px; height: 400px; padding: 10px"
	data-options="	iconCls: 'qyd',	buttons: '#change_express_buttons'">
	<table width='100%'>
		<tr>
			<td>订单编号：</td>

			<td><input class="easyui-textbox" id="change_order_no"
				style="width: 200px;" /></td>
		</tr>
		<tr>
			<td>包裹序号：</td>

			<td><input class="easyui-textbox" id="change_seq"
				style="width: 200px;" /></td>
		</tr>
		<tr>
			<td>物流企业：</td>

			<td><input  id="change_express_logistics" 
					style=" width: 200px;" /></td>
		</tr>
		<tr>
			<td>物流单号：</td>

			<td><input class="easyui-textbox" style="width: 200px;"  id="change_express_no"/></td>
		</tr>
	</table>



</div>
<div id="change_express_buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="javascript:change_express()">确定快递信息更新</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#change_express_div').dialog('close')">取消关闭</a>
</div>