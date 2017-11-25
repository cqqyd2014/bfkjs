<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>我的价目表</title>
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


function getUserPriceTable(){
	$.getJSON("../usergroup/get_user_price_table.action", {
		userid :$("#userid").val()
		

	}, function(result) {
		var field=result.msg;
		

		if (field.success) {
			var o = field.o;
			var n = o.length;
			$("#price_table").datagrid('loadData', {
				total : n,
				rows : o
			});

		}
		else{
			alert("得到用户协议价格出错");
			}

		
	});
}


$(document).ready(function() {


	$('#userid').val('<s:property value="#request.userid" />');


//	$('#order_dat').datetimebox('setValue', '${order_time}');


	


	

	$('#price_table').datagrid({  
	    //border:false,  
	    fitColumns:true,  
	    singleSelect: true,  
	    title:'价格列表',
	    rownumbers:true,
	    columns:[[  
	    	{field:'uuid',title:'流水号'},  
	        {field:'goods_id',title:'商品编码'},  
	        {field:'goods_name',title:'商品名称'},
	        {field:'start_time',title:'开始时间'},
	        {field:'end_time',title:'结束时间'},
	        {field:'price',title:'协议价格'},
	        {field:'effective_now',title:'当前有效'},
	        {field:'out_date',title:'过期'},
	        {field:'in_the_futurn',title:'未生效'}  
	    ]] 
	});  
	getUserPriceTable();

	

}




);



</script>
</head>
<body>

<input id="userid" type="hidden">
<h1>我的价格列表</h1>
<table id="price_table" title="价格列表" class="easyui-datagrid"
		style="width: 100%;" pagination="true" iconCls="qyd">

		<thead>
			<tr>


				
			</tr>
		</thead>

	</table>

</html>