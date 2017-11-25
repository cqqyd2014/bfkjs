<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'month.jsp' starting page</title>

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

<s:iterator value="mls" id="ml" status="st">

	<span class="STYLE4">月度统计报表--<s:property value="#ml.userName" /></span>
	<hr>
	<table width="100%">
		<tr>
			<td width="50%" ><span class="STYLE3">上月期间：<fmt:formatDate value="#ml.d_start1"
					pattern="yyyy-MM-dd HH:mm;ss" /> <s:date
					name="#ml.d_start1" format="yyyy-MM-dd" />至<s:date
					name="#ml.d_end1" format="yyyy-MM-dd" />总计：<s:property value="#ml.total1" /></span>
			</td>
			<td width="50%" ><span class="STYLE3">本月期间：<s:date name="#ml.d_start2" format="yyyy-MM-dd" />至<s:date
					name="#ml.d_end2" format="yyyy-MM-dd" />总计：<s:property value="#ml.total2" /></span>
			</td>
		</tr>
		<tr>
			<td width="50%" valign="top" scope="row">
				<table>

					<tr>

						<td><span class="STYLE3">序号</span></td>
						<td><span class="STYLE3">订单编号</span></td>
						<td><span class="STYLE3">时间</span></td>
						<td><span class="STYLE3">订单明细</span></td>
						<td><span class="STYLE3">商品数量</span></td>
						<td><span class="STYLE3">商品金额</span></td>
						<td><span class="STYLE3">折后金额</span></td>
					</tr>
					<s:iterator value="#ml.monthList1" id="id" status="st">
						<tr>
							<td><span class="STYLE1"><s:property value="#st.index+1" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.orderNo" /></span></td>
							<td><span class="STYLE1"><s:date name="#id.time" format="yyyy-MM-dd" /></span></td>
							<td><span class="STYLE1"><div title="<s:property value="#id.orderMemo" />" style="width:100px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis">鼠标悬浮</div></span></td>
							<td><span class="STYLE1"><s:property value="#id.qty" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.amount" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.total" /></span></td>
						</tr>
					</s:iterator>
				</table>
			</td>
			<td width="50%" valign="top" scope="row">
				<table>

					<tr>

						<td><span class="STYLE3">序号</span></td>
						<td><span class="STYLE3">订单编号</span></td>
						<td><span class="STYLE3">时间</span></td>
						<td><span class="STYLE3">订单明细</span></td>
						<td><span class="STYLE3">商品数量</span></td>
						<td><span class="STYLE3">商品金额</span></td>
						<td><span class="STYLE3">折后金额</span></td>
					</tr>
					<s:iterator value="#ml.monthList2" id="id" status="st">
						<tr>
							<td><span class="STYLE1"><s:property value="#st.index+1" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.orderNo" /></span></td>
							<td><span class="STYLE1"><s:date name="#id.time" format="yyyy-MM-dd" /></span></td>
							<td><span class="STYLE1"><div title="<s:property value="#id.orderMemo" />" style="width:100px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis">鼠标悬浮</div></span></td>
							<td><span class="STYLE1"><s:property value="#id.qty" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.amount" /></span></td>
							<td><span class="STYLE1"><s:property value="#id.total" /></span></td>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
	</table>
	</s:iterator>
</body>
</html>
