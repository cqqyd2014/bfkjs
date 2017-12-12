<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>中心主框架</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
</script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

<script language='javascript' type='text/javascript'>

/*
function goods_list(){
	frames["main"].location = "../goods/GoodsListInit.action";
	$('#content').attr('title','商品')
}
*/
<s:iterator value="javascrpits_method" status="st"> 

function ${m_d_js_method}(){
	frames["main"].location = '${m_d_js_url}';
	$('#content').attr('title','${m_d_name}')
}



</s:iterator> 

</script>

</head>

<body style="padding: 0px;margin:0px">


	<div class="easyui-layout" style="padding: 0px;width: 100%; height: 100%;margin-top:0px;margin-bottom:0px;">
		<div region="west" split="true" title="导航"
			style="padding: 0px;width: 150px;margin-top:0px;margin-bottom:0px;">
			<div class="easyui-accordion">
			<s:iterator value="menu" status="st"> 

				<div title="${m_name }" style="overflow: auto; padding: 10px;"
					selected="true">
					<h3 style="color: #0099FF;">${m_name }</h3>
					<p>${m_desc }</p>
					
					

					<ul>
					<s:iterator value="menu_d" >
					
					<li><a href="javascript:void(0)" onclick="javascript:${m_d_js_method}()">${ m_d_name}</a>
					</li>
					 
					</s:iterator>
					
					</ul>
				</div>

			</s:iterator> 
			
			
				
			</div>
		</div>
		<div id="content" region="center" title="首页"
			style="padding: 0px;margin-top:0px;margin-bottom:0px;">
			<iframe src="home_page_frame.action" height="100%" width="100%" border="0" 
				frameborder="0" id="main" name="main">
		</div>
	</div>




</body>
</html>
