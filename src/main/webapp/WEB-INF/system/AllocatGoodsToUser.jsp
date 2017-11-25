<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>进货到仓库</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

	<script type="text/javascript" src="../js/jquery.metadata.js"></script>
	<script type="text/javascript" src="../js/messages_zh.min.js"></script>
	<script type="text/javascript">
	


	
$(document).ready(function(){

	$("#allocate").validate({
		rules: {

			price: {
				required: true,
				number:true
			}

		},
		messages: {
			price: "请输入有效数字"
			
		}
	});


	
	
}); 
</script>
	<script language='javascript' type='text/javascript'>

	function	msg(){
		var msg='${requestScope.msg}';
		if (msg!=''){
			alert("${requestScope.msg}");
		}
	}
	msg();
	</script>
<style type="text/css">
<!--
.col1 {
	width: 150px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	font-size: 18px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}

a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.STYLE7 {
	font-size: 12
}
-->
</style>

  </head>
  
  <body bgcolor="#e5f6cf">
  <span class="STYLE4">当前登录用户：<s:property value="#request.user_name" /></span>
  <hr>
   <span class="STYLE4">将商品分配给销售员</span>
   <s:form id="allocate" action="allocatGoodsToUser.action" method="post" namespace="/system">
   <table width ="500">
	<tr>
   		<td width="100"><span class="STYLE1">商品：</span></td>
   		<td width="400"><s:select label="商品" name="goods" list="goodsList" value="0" listKey="key" listValue="value" cssStyle="height:18px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"/> </td>
   	</tr>
	<tr>
   		<td><span class="STYLE1">销售员：</span></td>
   		<td><s:select label="销售员" name="user" list="userList" value="0" listKey="key" listValue="value" cssStyle="height:18px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"/></td>
	</tr>
	
	<tr>
		<tr>
		<td><span class="STYLE1">价格：</span></td>
		<td><s:textfield name="price" label="价格" cssStyle="height:18px;  border:solid 1px #cadcb2; font-size:12px; color:#81b432;"/></td>
	</tr>

	
	
	<tr>
		<td>&nbsp;</td>
		<td><s:submit></s:submit></td>
	</tr>
   </tr></table>
   <span class="STYLE4"><s:fielderror/></span>
   </s:form>
  </body>
</html>
