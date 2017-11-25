<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'AccountCatalogAnalysisInit.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
    $( "#startDateS" ).datepicker({
		dateFormat:'yy-mm-dd',

					yearRange:'1990:2025',
					clearText:'清除',
					closeText:'关闭',
					prevText:'前一月',
					nextText:'后一月',
					currentText:'',
	monthNames:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']});
    
      $( "#endDateS" ).datepicker({
		dateFormat:'yy-mm-dd',

					yearRange:'1990:2025',
					clearText:'清除',
					closeText:'关闭',
					prevText:'前一月',
					nextText:'后一月',
					currentText:'',
	monthNames:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']});

  });
  </script>
</head>

<body>
	会计分型号汇总
	<s:form action="aca.action" method="get" namespace="/statis">
	开始时间
		<s:textfield id="startDateS" name="startDateS" />
		
		结束时间
		<s:textfield id="endDateS" name="endDateS" />
		<s:submit></s:submit>
	</s:form>

</body>
</html>
