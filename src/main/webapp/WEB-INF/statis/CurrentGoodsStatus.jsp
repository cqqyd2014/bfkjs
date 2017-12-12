<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <style type="text/css">
.por_list dl dt {
	height: 120px;
	width: 120px;
	padding: 0px;
	margin: 0px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}

.por_list dl {
	float: left;
	border: 1px solid #CCC;
	margin: 0px;
	padding: 0px;
}

.por_list dl dd {
	height: 60px;
	width: 120px;
	margin: 0px;
	padding: 0px;
	font-style: normal;
	line-height: normal;
	font-variant: normal;
	border: thin none #CCC;
	text-align: center;
}
</style>
    <title>实时库存</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	font-size: 14px;
	color: #1F4A65;
}

.STYLE3 {
	color: #1F4A65;
	font-size: 14px;
	font-weight: bold;
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
  
  <body>
    库存情况表
    <table border="1px" width="100%"> 
<s:iterator value="hw_names" id="hw_name" status="status">
<tr >
<td><s:property value="#status.index+1" />、<s:property value="#hw_name" /></td>
</tr>
<tr>
<td>
<s:iterator value="vgss[#status.index]" id="vgs">
<div class="por_list">
			<dl>
				<dt>
					<img width="100px" height="100px"
						alt='<s:property value="#vgs.id.CId"/>'
						src='../img/product/<s:property value="#vgs.id.CId"/>.jpg'></img>

				</dt>
				<dd>
					<s:if test="#request.s_user_type=='0'.toString()">
						<s:property value="#vgs.id.CId" />-<s:property value="#vgs.id.yue" />
					</s:if>
					<s:if test="#request.s_user_type=='1'.toString()">
						<s:property value="#vgs.id.CId" />
					</s:if>
				</dd>

			</dl>

		</div>
</s:iterator>    
</td>

</tr>
</s:iterator>    
    </table>
  </body>
</html>
