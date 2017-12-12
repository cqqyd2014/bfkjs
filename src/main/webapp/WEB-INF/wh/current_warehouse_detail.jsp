<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>查看当前库存</title>

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
<script type="text/javascript" src="../js/qyd.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">

<script type="text/javascript">
function download_goods_in_storage(wh_id,storage_id,goods_id){
	alert(wh_id);
	alert(storage_id);
	alert(goods_id);
}



function show_wh_by_goods_id_div(){

	$.getJSON("GetWhByGoodsId.action", {
		
	}, function(result) {
		
		$('#wh_by_goods_id').empty();
		var html='';
		html=html+'<div class=\'wh_list\'>';
		$.each(result, function(i, item) {

			html=html+'<span><dl><dt><img width=\'100px\' src=\'../img/product/<s:property value="#request.com_id"/>/'+item.goods_id+'.jpg\'/></dt>';

		html=html+'<dd>';
	html=html+'<div>'+item.goods_name+'</div>';
	html=html+'<div>'+item.yue+'</div>';
			
	html=html+'</dd>';
			
			

	html=html+'</dl></span>';

	});

	html=html+'</div>';
	$('#wh_by_goods_id').html(html);
	
});
	
}
	
	function show_wh_by_wh_div(){
		

		
		$.getJSON("get_warehouse_detail_by_warehouse.action", {
			
		}, function(result) {
			$('#wh_by_wh').empty();
			var html='';
			
			var field=result.msg;
			ajax_authority(field);
				if (field.success){
					html=html+'<table width=\'100%\'>';
					var data=field.o;
					//拆分仓库
					$.each(data, function(i_wh, field_wh) {

					html=html+'<tr><td><div class=\'wh_list\'>';
					html=html+'<h3>'+field_wh.wh_name+'</h3>';
					//拆分库位
					var storage=field_wh.ss
					$.each(storage, function(i_s, field_s) {
						html=html+'<div class=\'storage_list\'>';
						html=html+'<h4>'+field_s.s_name+'</h4>';
						var wh_storage=field_s.wh_id+'_'+field_s.s_id;
						var wh_storage_value=field_s.wh_id+'_'+field_s.s_id+'_value';
						html=html+'<div>该库位共有商品<input  id=\''+wh_storage+'\' />件,价值<input  id=\''+wh_storage_value+'\' />万元</div>';
						html=html+'<div>';
						//拆分商品
						var detail=field_s.sds;
						
						$.each(detail, function(i_d, field_d) {
							
							if (field_d.num>0){
							html=html+'<span style=\'float:left;\'><dl><dt><img width=\'110px\' src=\'../img/product/<s:property value="#request.com_id"/>/'+field_d.goods_id+'.jpg\'/></dt>';

							html=html+'<dd>';
							html=html+field_d.goods_name+'<br>';
							html=html+'A:<font color=\'red\'>'+field_d.num+'</font>P:<font color=\'red\'>'+field_d.avg+'</font><a href=\'javascript:void(0)\' id="download_detail" class=\"easyui-linkbutton\" onclick=\'javascript:download_goods_in_storage(\''+field_s.wh_id+'\',\''+field_s.s_id+'\',\''+field_d.goods_id+'\')></a>';
							
							html=html+'</dd>';
								
								

							html=html+'</dl></span>';
							}
							

							
						});
						//alert(wh_storage);
						//$("#custom_defaul").val('erwerwe');
						
						//alert(html);

						
						html=html+'<div class=\'clear\'></div>';
						html=html+'</div>';
						html=html+'<div class=\'clear\'></div>';

						html=html+'</div>';
						
					});
					html=html+'<div class=\'clear\'></div>';


					html=html+'</div></td></tr>';

						});

					

					html=html+'</table>';
					}
				else{
					alert("错误"+field.body);
				}

				

		
			
			$('#wh_by_wh').html(html);
			$("a#download_detail").linkbutton({  iconCls:'icon-sum' });
			$.each(result, function(i, field) {
				
					html=html+'<table width=\'100%\'>';
					var data=field.o;
					//拆分仓库
					$.each(data, function(i_wh, field_wh) {

					
					//拆分库位
					var storage=field_wh.ss
					$.each(storage, function(i_s, field_s) {
						
						var wh_storage=field_s.wh_id+'_'+field_s.s_id;
						var wh_storage_value=field_s.wh_id+'_'+field_s.s_id+'_value';
						var count=0;
						var goods_value=0;
						var detail=field_s.sds;
						$.each(detail, function(i_d, field_d) {
							
							count=count+field_d.num;
							goods_value=goods_value+field_d.value;
							

							
						});
						
							$("#"+wh_storage).val(count);
							$("#"+wh_storage_value).val((goods_value/10000).toFixed(2));
							
						
				
				

						});

					
					});
				
				});
				

		
			
			
		});

		
		

		}

	



	$(document).ready(function() {
		
		show_wh_by_wh_div();
		//show_wh_by_goods_id_div();
	});
	
</script>


</head>

<body style="width:98%;height:98%;">

	<h2>库存情况</h2>
	<div class="easyui-tabs" style="width: 100%;">
		<div id="wh_by_wh" title="按仓库" style="padding: 10px; width: 98%;">

		</div>
		<div id="wh_by_goods_id" title="按品种"
			style="padding: 10px; width: 100%;"></div>

	</div>



</body>
</html>
