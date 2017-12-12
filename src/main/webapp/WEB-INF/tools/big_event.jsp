<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>大事记</title>

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
<script type="text/javascript" src="../js/print_logistics_bill.js"></script>
<link rel="stylesheet" type="text/css" href="../js/print_logistics_bill.css">
<script type="text/javascript">
function del_big_event(){

	$.messager.confirm('提示','确认删除吗？',function(r){
	    if (r){
	    	$.getJSON("delete_big_event.action", {

	    		"uuid":$('#view_uuid').val()
	    		
	    	}, function(result) {
	    			
	    			
	    			
	    			$.each(result, function(i, field) {
	    				
	    				if (field.success){
	    					alert('删除成功');
	    					$('#view_detail_div').dialog('close');
	    					show_big_event_table();
	    					
	    					}
	    				else{
	    					alert("错误"+field.body);
	    				}
	    				
	    			
	    		});
	    				
	    			
	    		});
	    }
	});

	
	
}

function view_detail(uuid){
	//alert(uuid);

	

	$.getJSON("get_big_event_item.action", {

		"uuid":uuid
		
	}, function(result) {
			
			
			
			$.each(result, function(i, field) {
				
				if (field.success){
					var o=field.o;
					$('#view_b_date').text(o.b_date);
					$('#view_title').text(o.title);
					$('#view_content').text(o.content);
					$('#view_uuid').val(o.uuid);
					$('#view_detail_div').dialog('open');
					
					}
				else{
					alert("错误"+field.body);
				}
				
			
		});
				
			
		});

	
}

function add_big_event(){

	if ($('#title').val().length=0){
		alert("标题不能为空");
		return
		}
	if ($('#content').val().length=0){
		alert("事件内容不能为空");
		return
		}


$.getJSON("add_big_event.action", {

	title:$('#title').val(),
	content:$('#content').val(),
	b_date:$('#b_date').datebox('getValue')
}, function(result) {
		
		
		
		$.each(result, function(i, field) {
			
			if (field.success){
				alert('添加成功');
				show_big_event_table();

				
				}
			else{
				alert("错误"+field.body);
			}
			
		
	});
			
		
	});

	
}


function show_big_event_table(){
	$.getJSON("get_big_event_table.action", {}, function(result) {
		
		
		
		$.each(result, function(i, field) {
			
			if (field.success){
				var n = field.o;

				$("#big_event_table").datagrid('loadData',{ total: n.length, rows: n });

				
				}
			else{
				alert("错误"+field.body);
			}
			
		
	});
			
		
	});
}

		
	


	$(document).ready(function() {
		//$('#print_logistics_bill').dialog('close');
		$('#b_date').datebox({
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
		
		$('#b_date').datebox('setValue','${b_date}');
		
		$('#big_event_table').datagrid({  
		    //border:false,  
		    fitColumns:true,  
		    singleSelect: true,  
		    title:'大事记',
		    rownumbers:true,
		    columns:[[  
		        {field:'b_date',title:'日期'},  
		        {field:'title',title:'标题'},
		        


		        
		        {field:'opt',title:'操作',width:'100px',align:'center',  
		            formatter:function(value,rec){  
		                var btn = '<a class="editcls" onclick="view_detail(\''+rec.uuid+'\')" href="javascript:void(0)">详情</a>';  
		                return btn;  
		            }  
		        }  
		    ]],  
		    onLoadSuccess:function(data){  
		        $('.editcls').linkbutton({text:'详情',plain:true,iconCls:'icon-more'});  
		    }  
		}); 
		$('#view_detail_div').dialog('close');
		show_big_event_table();
		

	});
</script>




</head>

<body style="width: 95%; height: 95%;">
	<h2>公司大事记</h2>

		<table width="100%" border="1px" class="box">
			<tr>
				<td width="10%">事件时间：</td>
				<td width="15%"><s:textfield name="b_date" id="b_date"
						class="easyui-datebox" required="required" /></td>

				

				<td width="10%"><span>标题：</span></td>
				<td width="65%"><input type='text' id='title' style="width:100%"/></td>
			


			</tr>
			<tr>
				
				<td colspan='4'>事件内容</td>
			</tr>
			<tr>
				<td colspan='4'><textarea  rows="10" name="content" id="content" style="width:100%"></textarea></td>

				
				
			</tr>
			<tr>
				<td colspan='4' align='right'><a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="javascript:add_big_event()"
					iconCls="icon-ok">添加大事记</a></td>
			</tr>
			</table>



		<table id="big_event_table" style="width: 100%; overflow-y: auto"
			title='大事记' class="easyui-datagrid" idField="itemid"
			rownumbers="true" iconCls="qyd">
			

		</table>

<div id="view_detail_div" class="easyui-dialog"	title="大事记详情" style="width: 600px; height: 400px; padding: 10px"
		data-options="iconCls: 'qyd',buttons: '#view_detail_buttons'">
		<input type="hidden" id="view_uuid" />
		<table class='box' width='100%'>
			<tr>
				<td width='10%'>时间：</td><td width='15%'><span id='view_b_date'></span></td>
				<td width='10%'>标题：</td><td width='65%'><span id='view_title'></span></td>
				
			</tr>
			<tr>
				<td colspan='4'>事件内容：</td>
				
			</tr>
			<tr>
				<td colspan='4' ><span id='view_content'></span></td>
				
			</tr>
			
			
		</table>
		



</div>
<div id="view_detail_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#view_detail_div').dialog('close');" id="order_return_ok">确认退出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:del_big_event()">删除</a>
	</div>
</body>
</html>
