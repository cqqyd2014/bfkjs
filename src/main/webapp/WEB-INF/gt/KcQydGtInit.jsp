<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>库存查询</title>
    
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
  
  <table>
  <tr>
  <td>序号</td>
  <td>
 B账册编码
  </td>
  <td>仓库管理方
  </td>
  <td>操作</td>
   
    <s:iterator value="hbs" id="id" status="st">
    <tr>
    <td><s:property value="#st.index+1"/></td>
    <td><s:property value="#id.zcbm"/></td>
    <td><s:property value="#id.ccmc"/></td>
    <td><a  href="SubSyncItemStockInfo.action?hgZc=<s:property value="#id.zcbm"/>">查询库存</a></td>
    </tr>
    </s:iterator>
    </table>
  </body>
</html>
