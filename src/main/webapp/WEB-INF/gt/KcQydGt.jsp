<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>库存对照表</title>
    
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
  <p>账册编码：${hgZc}</p>
  <table>
  <tr>
  <td>序号</td>
  <td>
  货物编码
  </td>
  <td>仓库数量
  </td>
  <td>
  勤驿达系统数量
  </td>
  </tr>
  
  
  
    <s:iterator value="res" id="id" status="st">
    <tr>
    <td><s:property value="#st.index+1"/></td>
    <td><s:property value="#id.id.CId"/></td>
    <td><s:property value="#id.id.numInGt"/></td>
    <td><s:property value="#id.id.numInCqqyd"/></td>
    </tr>
    </s:iterator>
    </table>
  </body>
</html>
