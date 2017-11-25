<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>按品种销售统计表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    按品种销售统计表,开始时间<s:property value="#request.startDateS"/>——结束时间
    <s:property value="#request.endDateS" />
	
    <table border="1px">
    <tr><td>序号</td><td>产品编码</td><td>产品名称</td><td>单价</td><td>数量</td><td>金额</td></tr>>
    <s:iterator value="ppcs" id="id" status="st">
    <tr>
    <td><s:property	value="#st.index+1"/></td>
    <td><s:property value="#id.c_id"/></td>
    <td><s:property value="#id.c_name"/></td>
    <td><s:property value="#id.price"/></td>
    <td><s:property value="#id.count"/></td>
    <td><s:property value="#id.sum"/></td>
    </tr>
    </s:iterator>
    <tr><td colspan="6">小计：<s:property	value="#request.all"/>元<a href="saleToOdoo?startDate=<s:property value="#request.startDateS"/>&endDate=<s:property value="#request.endDateS" />">导入ODOO系统</a></td></tr>
    </table>
  </body>
</html>
