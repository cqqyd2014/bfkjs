<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript" src="js/jquery-2.1.4.min.js">
	
</script>
<script language='javascript' type='text/javascript'>
function qyd_pc(){
	window.location.href='portal/qyd';
}
</script>
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/qyd.css">
</head>
<body>
	<table style='width: 100%'>
		<tr>
		<td><a style='width: 100%' href="#" class="easyui-linkbutton" iconCls="qyd"
			onclick="javascript:qyd_pc()">勤驿达MIS V3.0（PC）</a></td>
		</tr>
		<tr>
		<td><a style='width: 100%' href="#" class="easyui-linkbutton" iconCls="qyd"
			onclick="javascript:qyd_mobile()">勤驿达MIS V3.0（Phone）</a></td>
		</tr>
	</table>
</body>
</html>
