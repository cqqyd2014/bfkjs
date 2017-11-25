<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>合同列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">
<script type="text/javascript">
function del_contract(contract_id){
	$.getJSON("del_contract.action", {
		contract_id:contract_id
			
			
		}, function(result) {

			var field=result.msg;

				if (field.success) {
					window.location.href="contract_list_init.action";

				}else
				{
					alert(field.body)
;					}

			

		});
}

function all_arrival(contract_id){
	$.getJSON("set_all_arrival.action", {
		contract_id:contract_id
			
			
		}, function(result) {

			var field=result.msg;

				if (field.success) {
					window.location.href="contract_list_init.action";

				}

			

		});
		
	
}

function print_barcodes(contract_id){
$.getJSON("print_contract_barcode.action", {
	contract_id:contract_id
		
		
	}, function(result) {

		var field=result.msg;

			if (field.success) {
				window.location.href="../wh/make_new_barcode_init.action";

			}

		

	});
	
	
}

function new_contract(){
	$('#contract_id').removeAttr("readonly");
	$('#contract_id').val("");
	$('#supply_map').val("");
	$('#paper_dat').val("");
	//清空session的条目
	$.getJSON("clean_detail_in_session.action", {
		
		
	}, function(result) {

		$.each(result, function(i, field) {

			if (field.success) {
				var o = field.o;
				var n = o.length;
				$("#contract_detail_table").datagrid('loadData', {
					total : n,
					rows : o
				});
				$('#contract_detail').dialog('open');

			}

		});

	});
	
	
}

function save_contract_detail(){
	

	
	$.getJSON("save_contract_detail_in_db.action", {
		contract_no : $("#contract_id").val(),supply_id:$('#supply_map').val(),paper_dat:$('#paper_dat').datebox('getValue')
		
	}, function(result) {

		var field=result.msg;

			if (field.success) {
				alert("更新成功");
				$('#contract_detail').dialog('close');
				window.location.href="contract_list_init.action";

			}

		});

	
	

	
}


	function add_contract_detail() {
		$.getJSON("add_contract_detail_in_session.action", {
			goods_id : $("#goods_id").val(),
			price : $('#price').numberbox('getValue'),
			num : $('#num').numberbox('getValue')
		}, function(result) {

			var field=result.msg;

				if (field.success) {
					var o = field.o;
					var n = o.length;
					$("#contract_detail_table").datagrid('loadData', {
						total : n,
						rows : o
					});

				}
				else{
					alert(field.body);
					}

			

		});

	}

	function show_contract_detail_table_from_session() {
		$.getJSON("get_contract_detail_from_session.action", {}, function(result) {

			var field=result.msg;

				if (field.success) {
					var o = field.o;
					var n = o.length;
					$("#contract_detail_table").datagrid('loadData', {
						total : n,
						rows : o
					});

				}

			

		});

	}
	function edit_detail(goods_id,num,price,num_in){

		/*
		$("#goods_id").val(goods_id);
		$("#goods_id").attr("disabled","disabled");//提交的时候设置为可用，$("#goods_id").removeAttr("disabled");
		*/
		alert(goods_id);
		alert(num);
		alert(price);
		alert(num_in);
		
		}
	function del_detail(goods_id){
		$.getJSON("del_goods_id_in_session.action", {goods_id:goods_id}, function(result) {
			var field=result.msg;
		

			if (field.success) {
				var o = field.o;
				var n = o.length;
				$("#contract_detail_table").datagrid('loadData', {
					total : n,
					rows : o
				});

			}
			else{
alert("没有找到要删除的商品");
				}

	

	});
		
		}

	function show_contract_table() {

		$.getJSON("get_contract_list.action", {}, function(result) {
				var field=result.msg;
			

				if (field.success) {
					var o = field.o;
					var n = o.length;
					$("#contract_table").datagrid('loadData', {
						total : n,
						rows : o
					});

				}

		

		});

	}

	$(document).ready(function() {
		//对话框初始化
		
		$('#contract_detail').window('resize',{width:dialog_width,height:dialog_height});
		

		
		$('#contract_table').datagrid({  
		    //border:false,  
		    fitColumns:true,  
		    singleSelect: true,  
		    title:'合同列表',
		    rownumbers:true,
		    columns:[[  
		        {field:'contract_no',title:'合同编号'},  
		        {field:'supply_name',title:'供应商'},
		        {field:'amount',title:'商品金额'},
		        {field:'arrival',title:'是否到货完毕'},
		        {field:'print_count',title:'批量打印次数'},
		        {field:'last_print_dat',title:'最后一次打印时间'},
		        


		        
		        {field:'opt',title:'操作',width:'220px',align:'center',  
		            formatter:function(value,rec){  
		                var btn = '<a class="print_barcodes" onclick="print_barcodes(\''+rec.contract_no+'\')" href="javascript:void(0)">打印条码</a><a class="all_arrival" onclick="all_arrival(\''+rec.contract_no+'\')" href="javascript:void(0)">设置/取消-入库完毕</a>';  
		                return btn;  
		            }  
		        }  
		    ]],  
		    onLoadSuccess:function(data){  
		        $('.print_barcodes').linkbutton({text:'打印条码',plain:true,iconCls:'icon-print'});
		        $('.all_arrival').linkbutton({text:'设置/取消-入库完毕',plain:true,iconCls:'icon-tip'});  
		        
		    }  
		});  
		

		$('#contract_detail_table').datagrid({  
		    //border:false,  
		    fitColumns:true,  
		    singleSelect: true,  
		    title:'合同清单',
		    rownumbers:true,
		    columns:[[  
		        {field:'goods_id',title:'商品编码'},  
		        {field:'goods_name',title:'商品名称'},
		        {field:'price',title:'单价'},
		        {field:'c_unit',title:'单位'},
		        {field:'num',title:'数量'},
		        {field:'amount',title:'金额'},
		        {field:'num_in',title:'入库数量'},
		        {field:'num_out',title:'销售数量'},


		        
		        {field:'opt',title:'操作',width:'120px',align:'center',  
		            formatter:function(value,rec){  
		                var btn = '<a class="delcls" onclick="del_detail(\''+rec.goods_id+'\')" href="javascript:void(0)">删除</a><a class="editcls" onclick="edit_detail(\''+rec.goods_id+'\','+rec.num+','+rec.price+','+rec.num_in+')" href="javascript:void(0)"修改</a>';  
		                return btn;  
		            }  
		        }  
		    ]],  
		    onLoadSuccess:function(data){  
		        $('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-cancel'});  
		        $('.editcls').linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});  
		    }  
		});  



		
		$('#paper_dat').datebox({
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

				return y + '-' + m + '-' + d;
			},
			parser : function(s) {
				var t = Date.parse(s);
				if (!isNaN(t)) {
					return new Date(t);
				} else {
					return new Date();
				}
			}
			,
		    
		        required:true
		    ,
		    okText:"确定",
		    closeText:"关闭",
		    currentText:"今日"
		});

		

		$('#contract_table').datagrid({

			onDblClickRow : function(rowIndex, rowData) {

				//赋值
				
				
				$("#amount").text(rowData.amount);


				//合同基本信息
				$('#contract_id').val(rowData.contract_no);
				$("#contract_id").attr("readonly","readonly");
				

				$.ajax({
					type : "post",
					url : "get_contract.action",
					data : {
						contract_no : rowData.contract_no
					},
					async : false,
					success : function(result) {

						var field=result.msg;

							if (field.success) {

								var o=field.o;
								
								$('#supply_map').val(o.supply);
								var d=o.paperDat;
								var t=timeStamp2String(d.time);
								$('#paper_dat').datebox('setValue',t);
								

							}

						

					}
				});


				
				//$("#supply_id").text(rowData.supply_name);
				
				//合同明细
				$.ajax({
					type : "post",
					url : "get_contract_detail.action",
					data : {
						contract_no : rowData.contract_no
					},
					async : false,
					success : function(result) {

						var field=result.msg;

							if (field.success) {

								show_contract_detail_table_from_session();

							}

						

					}
				});

				$('#contract_detail').dialog('open');
				//alert(rowData.contract_no);
			},
			pagination : true,
			//rownumbers: true, 
			fitColumns : false
		});
		$('#contract_detail').dialog('close');
		show_contract_table();
	});
</script>
</head>
<body style="width: 98%; height: 98%;">
<h2>合同</h2>
<div align="right"><a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:new_contract()">新建</a></div>


	<table id="contract_table" title="合同列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				<th field="contract_no">合同编号</th>
				<th field="supply_name">供应商</th>
				<th field="amount">商品金额</th>
				<th field="print_count">批量打印次数</th>
				<th field="last_print_dat">最后一次打印时间</th>
			</tr>
		</thead>

	</table>



	<div id="contract_detail" class="easyui-dialog" title="合同清单"
		style="width: 600px; height: 400px; padding: 10px"
		data-options="
				iconCls: 'qyd',
				buttons: '#contract_detail_buttons'
			">
		<div>
			<div>
				<span>合同编号:</span><input type="text" id="contract_id" name="contract_id"/></span>
			
				<span>供应商：</span><s:select name="supply_map"  id="supply_map" list="supply_map" listKey="key" listValue="value" />
				<span>合同时间：</span><s:textfield name="paper_dat" id="paper_dat"
						class="easyui-datebox" required="required" /></span>
			</div>
			<table id="contract_detail_table" title="合同清单列表"
				class="easyui-datagrid" style="width: 100%;" pagination="true"
				iconCls="qyd">

				<thead>
					<tr>


						<th field="goods_id">商品编码</th>
						<th field="goods_name">商品名称</th>
						<th field="price">单价</th>
						<th field="c_unit">单位</th>
						<th field="num">数量</th>
						<th field="amount">金额</th>

					</tr>
				</thead>

			</table>

			<div>
				<div>新增合同清单</div>
				<table>
					<tr>
						<td>商品：</td>
						<td><s:select name="goods_id" id="goods_id"
								list="goods_id_map" listKey="key" listValue="value" /></td>
						<td>单价：</td>
						<td><input type="text" id="price" name="price"
							class="easyui-numberbox" precision="2"/></td>
					</tr>
					<tr>
						<td>数量：</td>
						<td><input type="text" id="num" name="num"
							class="easyui-numberbox" /></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="javascript:add_contract_detail()" iconCls="qyd">新增</a></td>
					</tr>

				</table>

			</div>
		</div>


	</div>
	<div id="contract_detail_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:save_contract_detail()">保存</a> 
			<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#contract_detail').dialog('close')">取消</a>
	</div>
</body>
</html>