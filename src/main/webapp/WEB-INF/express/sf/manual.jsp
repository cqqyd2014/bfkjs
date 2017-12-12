<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>顺丰手动推送数据</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../../js/qyd.js">
	
</script>

<link rel="stylesheet" type="text/css"
	href="../../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../../js/themes/icon.css" />
<script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../js/qyd.css">
<script type="text/javascript">
	function receive_message() {
		//alert($("#server_addr"));
		$.post($("#server_addr").val(), {
			xml : encodeURI($("#xml_text").val()),
			

		}, function(result) {
			result ='<pre>' + result + '</pre>';
			$("#xml_response_text").html(result);
		});
	}
	function order_write() {
		$.getJSON("manual_post.action", {
			post_text : encodeURI($("#post_text").val()),
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val(),
			method : "BspHttpClientOrder"

		}, function(result) {
			var field = result.msg;

			$("#response_text").val(field.body);

		});
	}
	function  route_write() {
		$.getJSON("manual_post.action", {
			post_text : encodeURI($("#post_text").val()),
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val(),
			method : "BspHttpClientRoute"

		}, function(result) {
			var field = result.msg;

			$("#response_text").val(field.body);

		});
	}

	
	function confirm_write() {
		$.getJSON("manual_post.action", {
			post_text : encodeURI($("#post_text").val()),
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val(),
			method : "BspHttpClientOrderConfirm"

		}, function(result) {
			var field = result.msg;

			$("#response_text").val(field.body);

		});
	}
	function order_search_write() {
		$.getJSON("manual_post.action", {
			post_text : encodeURI($("#post_text").val()),
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val(),
			method : "BspHttpClientOrderSearch"

		}, function(result) {
			var field = result.msg;

			$("#response_text").val(field.body);

		});
	}
	function order() {
		$.getJSON("manual_order.action", {
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val()

		}, function(result) {
			var field = result.msg;

			$("#post_text").val(field.body);

		});
	}
	function route() {
		$.getJSON("manual_route.action", {
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val()

		}, function(result) {
			var field = result.msg;

			$("#post_text").val(field.body);

		});
	}
	function order_search() {
		$.getJSON("manual_ordersearch.action", {
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val()

		}, function(result) {
			var field = result.msg;

			$("#post_text").val(field.body);

		});
	}
	function post() {
		$.getJSON("manual_post.action", {
			post_text : encodeURI($("#post_text").val()),
			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val()

		}, function(result) {

			var field = result.msg;

			//if (field.success) {

			$("#response_text").val(field.body);

			//}
			//{
			//	alert(field.body);				//	}

		});
	}
	function confirm() {
		$.getJSON("manual_confirm.action", {

			com_id : $("#com_id").val(),
			order_no : $("#order_no").val(),
			seq : $("#seq").val()

		}, function(result) {

			var field = result.msg;

			//if (field.success) {

			$("#post_text").val(field.body);

			//}
			//{
			//	alert(field.body);				//	}

		});
	}
</script>
</head>
<body>

	<table width='100%'>
		<th>一、请求数据</th>
		<tr>
			<td colspan='2'>1、基本参数</td>
		</tr>
		<tr>
			<td width="30%">公司</td>
			<td width="70%"><s:select name="com_id" id="com_id"
					list="coms_map" listKey="key" listValue="value" /></td>
		</tr>
		<tr>
			<td>订单编号</td>
			<td><input type="text" id="order_no"></td>
		</tr>
		<tr>
			<td>发货单序号</td>
			<td><input type="text" id="seq"></td>
		</tr>
		<tr>
			<td colspan='2'><hr></td>
		</tr>
		<tr>
			<td colspan='2'>2、功能参数</td>
		</tr>
		<tr>
			<td colspan='2'>2.1、推送订单</td>
		</tr>
		<tr>
			<td>参数：无</td>
			<td><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:order()">推送订单</a><a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="javascript:order_write()">推送订单数据写入数据库</a></td>
		</tr>
		<tr>
			<td colspan='2'>2.2、确认订单</td>
		</tr>
		<tr>
			<td>参数：无</td>
			<td><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:confirm()">确认订单</a><a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="javascript:confirm_write()">推送订单数据写入数据库</a></td>
		</tr>
		<tr>
			<td colspan='2'>2.3、查询订单</td>
		</tr>
		<tr>
			<td>参数：无</td>
			<td><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:order_search()">查询订单</a><a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:order_search_write()">查询订单数据写入数据库</a></td>
		</tr>
		<tr>
			<td colspan='2'>2.4、查询路由</td>
		</tr>
		<tr>
			<td>参数：无</td>
			<td><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:route()">查询路由</a><a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="javascript:route_write()">查询路由数据写入数据库</a></td>
		</tr>
		<tr>
			<td colspan='2'>2.5、推送路由</td>
		</tr>
		<tr>
			<td>参数：无</td>
			<td><input type="button" value="推送路由"></td>
		</tr>
		<tr>
			<td colspan='2'><hr></td>
		</tr>
		<tr>
			<td colspan='2'>3、顺丰系统推送数据</td>
		</tr>
		<tr>
			<td colspan='2'><textarea rows="3" cols="20" style="width: 100%"
					id="post_text">
在w3school，你可以找到你所需要的所有的网站建设教程。
</textarea></td>
		</tr>

		<tr>
			<td colspan='2'><hr></td>
		</tr>
		<tr>
			<td colspan='2'>4、顺丰系统返回结果</td>
		</tr>
		<tr>
			<td colspan='2'><textarea rows="3" cols="20" style="width: 100%"
					id="response_text">
在w3school，你可以找到你所需要的所有的网站建设教程。
</textarea></td>
		</tr>
		<tr>
			<td colspan='2'><hr></td>
		</tr>
	</table>
	<hr>

	<table width='100%'>
		<th>二、接收数据</th>
		<tr>
			<td width="30%">数据发送地址：</td>
			<td width="70%"><input style="width: 100%" type="text"
				id="server_addr"
				value="http://localhost:8080/Bfkjs/express/sf/receive_message.action"></td>
		</tr>
		<tr>
			<td colspan='2'>发送的数据</td>
		</tr>
		<td colspan='2'><textarea rows="3" cols="20" style="width: 100%"
				id="xml_text">
在w3school，你可以找到你所需要的所有的网站建设教程。
</textarea>
		<tr>
			<td colspan='2'><a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="javascript:receive_message()">发送</a></td>
		</tr>
		<tr>
		<td colspan='2'>返回</td>
		</tr>
		<tr>
		<td colspan='2'><span rows="3" cols="20" style="width: 100%"
				id="xml_response_text">
在w3school，你可以找到你所需要的所有的网站建设教程。
</textarea></td>
		</tr>
	</table>
</body>
</html>