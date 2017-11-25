<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>


<title><s:property value="#request.com_name" />移动MIS</title>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="../../js/jquery.mobile-1.4.5.min.css" />
<script src="../../js/jquery-2.1.4.min.js"></script>
<script src="../../js/jquery.mobile-1.4.5.min.js"></script>
<script src="../../js/qyd.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">

function init(){

	$.getJSON("get_attention.action", {	}, function(result) {
		
		var field=result.msg;
			
			
				if (field.success){
					//成功登录
					var att=field.o;
					$('#unpaid_count').text(att.un_paid_count);
					$('#wait_assgin_package_count').text(att.wait_assign_package);
					$('#wait_package_count').text(att.wait_package);
					$('#wait_deliver_count').text(att.wait_deliver);
					//alert(att.un_paid_count);

					//$("span[data-role=content] ul li").listview("refresh");

				//window.location.href='../manage/main_init.action';
				}
				else{
					
					message_dialog("系统错误",field.body,"alert");


					}
				
			
	
		
	});
	


	
}



	$(document).ready(function() {
		
		
		init();
	});
</script>

</head>

<body>

	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1><s:property value="#request.com_name" />移动MIS</h1>
			<div data-role="navbar">
				<ul>
					<li><a href="#" data-icon="home">首页</a></li>
					<li><a href="#" data-icon="refresh">刷新</a></li>
					<li><a href="#" data-icon="search">退出</a></li>
				</ul>
			</div>
		</div>

		<div data-role="content">
			<ul data-role="listview" data-inset="true">
      <li data-role="list-divider">订单</li>
      <li>
					<a href="#">
					<img src="../../img/icon/icon_09.jpg">
					<h2>录入订单</h2>
					<p>录入订单详细信息</p>
					</a>
				</li>
				<li><a href="#">
					<img src="../../img/icon/icon_12.jpg">
					<h2>等待付款的订单</h2>
					<p>取现未付款订单</p>
					<span class="ui-li-count" id="unpaid_count">0</span>
					</a>
				</li>
      <li data-role="list-divider">库房</li>
      <li><a href="#">
					<img src="../../img/icon/icon_32.jpg">
					<h2>待分拣订单</h2>
					<p>等待分配拣货人的订单</p>
					<span class="ui-li-count" id="wait_assgin_package_count">0</span>
					</a>
				</li>
	<li><a href="#">
					<img src="../../img/icon/icon_84.jpg">
					<h2>待拣货订单</h2>
					<p>等拣货的订单</p>
					<span class="ui-li-count" id="wait_package_count">0</span>
					</a>
				</li>
      <li><a href="#">
					<img src="../../img/icon/icon_01.jpg">
					<h2>等待出库的包裹</h2>
					<p>已经拣货完成的包裹</p>
					<span class="ui-li-count" id="wait_deliver_count">0</span>
					</a>
				</li>
      
      <li data-role="list-divider">系统</li>
      
    </ul>
		</div>

		<div data-role="footer">
			<h1>V2017 V3.0</h1>
		</div>
	</div>


<jsp:include page="../dialog.jsp" flush="true" />
</body>
</html>
