<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<jsp:include page="WEB-INF/common/include_easyui1.jsp" flush="true" />
<script language='javascript' type='text/javascript'>
function qyd_pc(){
	window.location.href='portal/qyd';
}
function qyd_mobile(){
	window.location.href='web/login/qyd';
}


 

</script>

</head>
<body class="easyui-layout">
<div data-options="region:'center',title:''" style="padding:5px;" fit="true">
	<a style='width: 100%' href="#" class="easyui-linkbutton" iconCls="qyd"
			onclick="javascript:qyd_pc()">勤驿达MIS V3.0（PC）</a><a style='width: 100%' href="#" class="easyui-linkbutton" iconCls="qyd"
			onclick="javascript:qyd_mobile()">勤驿达MIS V3.0（Phone）</a>
	</div>
</body>
</html>
