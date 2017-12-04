<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>我的“跨境”订单</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>  
<!--  
input {  
    color: #00008B;  
    background-color: #ADD8E6;  
    border: 1px solid #00008B;  
}  

form{  
    margin:0px; padding:0px;  
    font:14px;  
}  
input{  
    font:14px Arial;  
}  
.txt{  
    border-bottom:1px solid #005aa7;    /* 下划线效果 */  
    color:#005aa7;  
    border-top:0px; border-left:0px;  
    border-right:0px;  
    background-color:transparent;       /* 背景色透明 */  
}  
.btn{  
    background-color:transparent;       /* 背景色透明 */  
    border:0px;                         /* 边框取消 */  
}  
.datalist{  
    border:1px solid #0058a3;   /* 表格边框 */  
    font-family:Arial;  
    border-collapse:collapse;   /* 边框重叠 */  
    background-color:#eaf5ff;   /* 表格背景色 */  
    font-size:14px;  
}  
.datalist caption{  
    padding-bottom:5px;  
    font:bold 1.4em;  
    text-align:left;  
}  
.datalist th{  
    border:1px solid #0058a3;   /* 行名称边框 */  
    background-color:#4bacff;   /* 行名称背景色 */  
    color:#FFFFFF;              /* 行名称颜色 */  
    font-weight:bold;  
    padding-top:4px; padding-bottom:4px;  
    padding-left:12px; padding-right:12px;  
    text-align:center;  
}  
.datalist td{  
    border:1px solid #0058a3;   /* 单元格边框 */  
    text-align:left;  
    padding-top:4px; padding-bottom:4px;  
    padding-left:10px; padding-right:10px;  
}  
.datalist tr.altrow{  
    background-color:#c7e5ff;   /* 隔行变色 */  
    
}  
.detail_head {
	border-bottom-style: dashed;
}
-->  
</style> 
  </head>
  
  <body>
      <img alt="重庆海关" style="display:block;max-width:100%;"src="../img/wx/<s:property value='#request.com_id'/>/weixin_head.jpg">
    <table class="datalist" summary="list of Orders" width="100%">  
    <caption>我的跨境订单</caption>
    
    <s:if test="v_coms.size()!=0">
    <s:iterator value="v_coms" id="id" status="st">
    <tr  class="altrow">
    <td>序号：<s:property value="#st.index+1"/></td>
    
    <td>申报状态：<s:property value="#id.CStatus"/><s:if test="#id.CStatus.equals(\'微信申报\')"><a href="delApply.action?orderNo=<s:property value="#id.COrderId"/>&userid=<s:property value="#request.user_id"/>&OpenID=<s:property value="#id.weixinFromuser"/>">撤销</a></s:if></td>
    
  </tr>
  <tr>
  <td>仓库状态：<s:property value="#id.gtStatus"/></td>
  <td>物流状态：<s:property value="#id.expressDetail"/></td>
    
  </tr>
  <tr><td colspan="3">订单时间：<s:property value="#id.weixinTime"/></td></tr>
  <tr >
    <td colspan="3">
    跨境订单海关条码：<s:property value="#id.barcode"/><br>
    收货人名称：<s:property value="#id.buyername"/><br>
    收货人地址：<s:property value="#id.addr"/><br>
    订单详情：<br/>
    <s:property value="#id.detailMemo" escapeHtml="false"/><br/>
    快递公司：<s:property value="#id.expressCom"/><br/>
    快递单号：<s:property value="#id.expressN0"/><a href="http://m.kuaidi100.com/index_all.html?type=<s:property value="#id.expressCom"/>&postid=<s:property value="#id.expressN0"/>&callbackurl=#" >采用“快递100”查询包裹到哪儿</a><br>

	<s:iterator value="expressList" id="expressList">
		<s:date name="#expressList.dat" format="yyyy-MM-dd HH:mm:ss.SSS"/>【<s:property value="#expressList.city"/>】<s:property value="#expressList.desc"/><br/>
	</s:iterator>
    </td>
  </tr>
    
    </s:iterator>
    </s:if>
	<s:else><tr><td colspan="3">您还未在我们的平台申报过跨境包裹哦。</td></tr></s:else> 
    
    
</table>  
  </body>
</html>
