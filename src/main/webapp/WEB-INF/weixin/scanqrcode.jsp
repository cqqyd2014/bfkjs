<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML>
<html>
<head>


<title>欢迎使用勤驿达公司产品条码微信查询系统</title>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css"	href="../js/jquery.mobile-1.4.5.min.css" />
<script src="../js/jquery-2.1.4.min.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>
<script src="../js/qyd.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
function ip_search(ip){
	//alert(ip);
	$.getJSON("../system/get_ip_region.action", {
		ip:ip
		
	}, function(result) {
		$.each(result, function(i, field) {

			if (field.success) {
				var obj = JSON.parse(field.body);
				
				
				message_dialog("IP地址归属地",obj.country+' '+obj.province+' '+obj.city+' '+obj.district,"info");
				
				

			} else {
				message_dialog("错误",field.body,"alert");

			}

		});
	});
	
}
function initSearch(){


	$.getJSON("get_barcode.action", {
		barcode:'<s:property value="#request.barcode"/>',
		com_id:'<s:property value="#request.com_id"/>'
		
	}, function(result) {


		var field=result.msg;

			if (field.success) {
				var goods=field.o;
				
				$('#barcode').val(goods.barcode);
				$('#goods_name').val(goods.goods_name);
				
				var img_html='<img  style=\'width:100%;\' src=\'../img/product/'+goods.com_id+'/'+goods.goods_id+'.jpg\'>';
				
				$('#goods_img').html(img_html);
				var db=field.o5;
				$('#original_id').val(db.original_id);
				
				$('#receiver').val(db.receiver+" "+db.receiver_full_addr);
				
				var logs=field.o4;
				var logs_html="";
				$.each(logs, function(j, log) {
					var qr_dat=timeStamp2String(log.scan_dat);
					//alert(j);
					logs_html=logs_html+"<li><div >查询时间："+log.scan_dat+"</div><div >查询IP："+log.ip+"</div><div ><a id='search"+j+"' href='javascript:ip_search(\""+log.ip+"\")' data-role='button' data-icon='search'>IP归属地查询</a></div></li>";

				});
				$('#logs').html(logs_html);
				$('#logs').listview('refresh');
				$.each(logs, function(j, log) {
					
					$('#search'+j).button();
					

				});
				
				
				

			} else {
				message_dialog("错误",field.body,"alert");

			}

		
		
	});


	
}



	$(document).ready(function() {
		
		
		initSearch();
	});
</script>

</head>

<body>

<div data-role="page" id="pageone">
	<div data-role="header">
		
		<h1>欢迎使用勤驿达公司产品条码微信查询系统</h1>
	</div>
	<div data-role="content">
		<div data-role="fieldcontain">
			
				<lable for="barcode">您查询的条码为:</lable>
				<input type="text" name='barcode' id="barcode" readonly="readonly" value=""/>
			
		</div>
		<div data-role="fieldcontain">
				<lable for="goods_name">商品为:</lable>
				<input type="text" name='goods_name' id="goods_name" readonly="readonly" value=""/>
			
		</div>
			<div id="goods_img">
				<img align='center' src='../img/qyd-300-300.jpg' "/>
			</div>
		<div data-role="fieldcontain">
				<lable for="original_id">订单编号:</lable>
				<input type="text" id="original_id" name='original_id' readonly="readonly" value=""/>
			
		</div>
		<div data-role="fieldcontain">
				<lable for="receiver">订购人:</lable>
				<input type="text" id="receiver" name='receiver' readonly="readonly" value=""/>
				
			
		</div>
			
		<div>条码查询记录:</div>
			
		<ol data-role="listview" id="logs">
				
		
		</ol>

	</div>
	<div data-role="footer">
	2017.11 V3.0
	</div>

</div>

<jsp:include page="../web/dialog.jsp" flush="true" />
</body>
</html>
