<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>预包装</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">

<script type="text/javascript">


function un_package(prepackage_barcode){

	$.getJSON("un_package.action", {
		prepackage_barcode : prepackage_barcode
		
	}, function(result) {
		
		var field=result.msg;
			
				if (field.success){
					//合格数据
					var html="<span><audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio><font color='bule'>"+field.body+"</font></span>"
					$("#message_sn").html(html);
					
					show_prepack_table();

					
					}
				else{
					 $.messager.alert("操作提示", "作废条码出错！原扫描条码"+$("#goods_barcode").val()+"，出错信息："+field.body,"error");  
					var html="<span><audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio><font color='red'>"+field.body+"</font></span>"
					$("#message_sn").html(html);


					}
				
				
			
		
		
	});
}

function add_goods_barcode_to_repackage(){

	$("#goods_barcode").attr("disabled","disabled");

	
	$("#message").empty();



	$.getJSON("add_goods_to_temp.action", {
		goods_barcode : $("#goods_barcode").val()
		,prepackage_barcode:$("#prepackage_barcode").val()
	}, function(result) {
		
		var field=result.msg;
			
				if (field.success){
					//合格数据
					var html="<span><audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio><font color='bule'>"+field.body+"</font></span>"
					$("#message_sn").html(html);
					
					show_goods_barcode_list_table(field.o);
					$('#weight').numberbox('setValue',field.o2); 
					if (field.o3=='数量已满'){
						save();
					}

					
					}
				else{
					 $.messager.alert("操作提示", "录入商品条码出错！原扫描条码"+$("#goods_barcode").val()+"，出错信息："+field.body,"error");  
					var html="<span><audio autoplay><source src=\'../sound/"+field.sound+".mp3\'><source src=\'../sound/"+field.sound+".ogg\'></audio><font color='red'>"+field.body+"</font></span>"
					$("#message_sn").html(html);


					}
				
				$("#goods_barcode").removeAttr("disabled");
				$("#goods_barcode").focus();

				$("#goods_barcode").val("");
			
		
		
	});
}






function check_prepackage_barcode(){

	
	$("#prepackage_barcode").attr("disabled","disabled");

	//检测合法性
	$("#message_pre").html("");


	
	$.getJSON("check_prepackage_barcode.action", {
		prepackage_barcode : $("#prepackage_barcode").val()
	}, function(result) {
		var field=result.msg;
			
				if (field.success){
					//合格数据
					var html_message ="<font color='blue'>"+$("#prepackage_barcode").val()+field.body+"</font>";

					html_message= html_message+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';
					
					$("#message_pre").html(html_message);
					$("#goods_barcode").val("");
					//$("#pre_sn").removeAttr("disabled");
					$("#goods_barcode").focus();
					$("#package_need").text($("#prepackage_barcode").val().substring(0,1));
					//check_if_goods_in_package_numbers(field.o);

					
					}
				else{
					var html_message ="<font color='red'>"+$("#prepackage_barcode").val()+field.body+"</font>";

					html_message= html_message+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';
					
					$("#message_pre").html(html_message);
					//$("#message_pre").html("<font color='red'>"+$("#pre_sn").val()+field.body+"</font>");
					$("#prepackage_barcode").removeAttr("disabled");
					$("#prepackage_barcode").val("");
					$("#prepackage_barcode").focus();

					}
				
			
		
		
	});

	
}


	
	
	




function save(){


		
		
		$.getJSON("save_prepackage.action", {
			prepackage_barcode : $("#prepackage_barcode").val(),
			wh_id:$("#wh_id").val()
			}, function(result) {

			var field=result.msg;
				$("#message_pre").empty();
				if (field.success){
		
					var html_message = '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';
					
					$("#message_pre").html(html_message);

				
				$.messager.alert("操作提示","商品共"+$("#package_need").text()+"件已经打包至包裹"+$("#prepackage_barcode").val()+",预包装成功!","info");  
				

				window.location.href='prepackage_init.action';
				}else{
					
					var html_message ="<font color='rad'>"+$("#prepackage_barcode").val()+field.body+"</font>";

					html_message= html_message+ '<audio autoplay><source src=\'../sound/'+field.sound+'.mp3\'><source src=\'../sound/'+field.sound+'.ogg\'></audio>';
					
					$("#message_pre").html(html_message);
					alert("预包装出错："+field.body)
					window.location.href='prepackage_init.action';

					}

			
		});

	
	
}
function show_prepack_table(){



	$.getJSON("get_prepackage.action", {}, function(result) {
		
		
		var field=result.msg;
			
			if (field.success){
				var n = field.o;

				$("#prepackage_table").datagrid('loadData',{ total: n.length, rows: n });

				
				}
			else{
				alert("错误"+field.body);
			}
			
		

			
		
	});


	
}


function show_goods_barcode_list_table(o){

	var n = o.length;

			$("#goods_barcode_list_table").datagrid('loadData',{ total: n, rows: o });

	}



	$(document).ready(function() {
		
		$('#wh_id').val('<s:property value="#request.wh_id"/>')
		$('#prepackage_table').datagrid({  
		    //border:false,  
		    fitColumns:true,  
		    singleSelect: true,  
		    title:'已预包装包裹',
		    rownumbers:true,
		    columns:[[  
		        {field:'prepackage_barcode',title:'预包装编码'},  
		        {field:'num',title:'内件数量'},
		        {field:'memo_names',title:'内件名称'},
		        {field:'memo_barcodes',title:'内件条码'},
		        {field:'prepackage_dat',title:'打包时间'},
		        {field:'time_last_chinese',title:'已经过去的时间'},


		        
		        {field:'opt',title:'操作',width:'100px',align:'center',  
		            formatter:function(value,rec){  
		                var btn = '<a class="editcls" onclick="un_package(\''+rec.prepackage_barcode+'\')" href="javascript:void(0)">解包</a>';  
		                return btn;  
		            }  
		        }  
		    ]],  
		    onLoadSuccess:function(data){  
		        $('.editcls').linkbutton({text:'解包',plain:true,iconCls:'icon-cancel'});  
		    }  
		});  



		
		$("#prepackage_barcode").focus();

		$("#prepackage_barcode").keydown(function() {

			
			
			

			if ($("#prepackage_barcode").val().length == 18) {
				check_prepackage_barcode();
				
			}

		});
		
		
	

		$('#prepackage_table').datagrid({

			onDblClickRow : function(rowIndex, rowData) {

				
			},
			pagination : true,
			//rownumbers: true, 
			fitColumns : false
		});


		
		 show_prepack_table();




		 
	});
	
</script>


</head>

<body style="width: 97%; height: 97%;">
	<h2>商品预包装</h2>

	<table width="100%" border="1px" class="box">
	<tr>
			<td>预包装仓库：</td>
			<td><s:select label="仓库" name="wh_id"
						id="wh_id" list="wh_map" listKey="key" listValue="value" /><a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:set_default('default_warehouse',$('#wh_id').val())"
					iconCls="qyd">设为默认值</a></td>
		</tr>
		<tr>
			<td>扫描预包装条码：</td>
			<td><input type="text" name="prepackage_barcode" id="prepackage_barcode"
				style="height: 40px; font-size: 30px; width: 300px; background-color: #ffffcc; border-color: red"
				size=30 /><span id="message_pre" style="font-size: 30px;"></span></td>
		</tr>
		<tr>

			<td>扫描商品条码：</td>
			<td><input type="text" name="goods_barcode" id="goods_barcode"
				style="height: 40px; font-size: 30px; width: 300px; background-color: #ffffcc; border-color: red"
				size=30 /><a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="javascript:add_goods_barcode_to_repackage()"
			iconCls="icon-ok">加入该商品</a><span id="message_sn" style="font-size: 30px;"></td>
		</tr>
		<tr>
			<td>包裹重量：</td>
			<td><input type="text" id="weight" class="easyui-numberbox"
				precision="2" />公斤</td>
		</tr>
		<tr>
			<td colspan="2">该包裹条码需要打包<span id="package_need">0</span>,已经录入<span
				id="input_goods_num">0</span>。
			</td>
			</td>
		</tr>

		<tr>
			<td colspan="2">

				<div>
					<table id="goods_barcode_list_table" style="width: 100%; overflow-y: auto"
						title='已经录入以下商品' class="easyui-datagrid" rownumbers="true"
						fitColumns="true" iconCls="qyd">
						<thead>
							<tr>


								<th field="goods_id">商品编码</th>
								<th field="goods_name">商品名称</th>
								<th field="barcode">Barcode</th>

							</tr>
						</thead>

					</table>

				</div>

			</td>
		</tr>
		<tr>
			<td colspan="2">

				<div>
					<table id="prepackage_table" style="width: 100%; overflow-y: auto"
						title='已预包装包裹' class="easyui-datagrid" rownumbers="true"
						iconCls="qyd">
						<thead>
							
						</thead>

					</table>

				</div>

			</td>
		</tr>

	</table>


</body>
</html>