<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'wxList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language='javascript' type='text/javascript'>

	function	msg(){
		var msg='${requestScope.msg}';
		if (msg!=''){
			alert("${requestScope.msg}");
		}
	}
	msg();
	function	declear(orderId){
		if (confirm("你确定申报这个订单"+orderId+"吗？")) {
			window.location.href='declearOrder.action?orderNo='+orderId;
		}
	}
	function  del( orderId,userid){
		if (confirm("你确定删除这个订单"+orderId+"吗？")) {
			window.location.href='delOrder.action?orderNo='+orderId+'&userid='+userid;
		}
	}
	function search(){
		document.form_search.submit();
	}
	function first(){
	window.document.location.href = "wxList.action?fromUserName=<s:property value="#request.fromUserName"/>";

	}
	function back(i){
		window.document.location.href = "wxList.action?currentPage="+i+"&fromUserName=<s:property value="#request.fromUserName"/>";
	}
	function next(i){
		window.document.location.href = "wxList.action?currentPage="+i+"&fromUserName=<s:property value="#request.fromUserName"/>";
	}
	function end(){
		window.document.location.href = "wxList.action?currentPage="+<s:property value="#request.totalPage"/>+"&fromUserName=<s:property value="#request.fromUserName"/>";
	}
    
        function validate()
        
        {
        
            var page = document.getElementsByName("currentPage")[0].value;
            if (page==""){
            alert("请输入需要跳转的页面！");
                
                
                
                return false;
            }
                
            if(page > <s:property value="#request.totalPage"/>)
            {
                alert("你输入的页数大于最大页数，页面将跳转到首页！");
                
                window.document.location.href = "wxList.action?fromUserName=<s:property value="#request.fromUserName"/>";
                
                return false;
            }
            
            document.form_forward.submit();
        }
    


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
.STYLE2 {
	font-size: 12px;
	color:red;
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
<p><span class="STYLE4">微信中心</span></p>
<p><span class="STYLE1">1、微信关注号共有<s:property value="#request.wxNo" />个，<a href="SyncWX.action">立即同步</a>，<a href="ShowWXUser.action">立即查看</a></span></p>
<p><span class="STYLE1">2、当前查看：
<s:if test="#request.fromUserName==null">所有信息</s:if>
<s:if test="#request.fromUserName!=null"><s:property value="#request.nikeName" />的信息,返回<a href="wxList.action">所有信息</a></span></s:if>
    <table width="100%" border="1" cellspacing="0"
					cellpadding="0" bgcolor="#c0de98" style="border-collapse: collapse">
    <tr>
 <td width="5%" height="30" background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif">序号</td>
        <td width="25%" background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif">时间</td>
        <td width="15%" background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif">客户</td>
        <td width="55%" background="http://img.alicdn.com/imgextra/i1/60893736/TB2uvdUdFXXXXc6XXXXXXXXXXXX_!!60893736.gif">信息</td>
</tr>
    <s:iterator value="wList" id="w" status="st">
    <tr>
    	<td bgcolor="#FFFFFF"><span class="STYLE1"><s:property	value="#st.index+1+(#request.currentPage-1)*#request.pageSize" /></span></td>
    	<td bgcolor="#FFFFFF"><s:date name="#w.input_time" format="yyyy-MM-dd HH:mm:ss" /></td>
    	<td bgcolor="#FFFFFF">
    	<s:if test="#w.msg_from=='R'.toString()">
    	<span class="STYLE1"><a href="wxList.action?fromUserName=<s:property value="#w.from_username" />"><s:property value="#w.nikeName" /></a></span>
    	</s:if>
    	<s:if test="#w.msg_from=='S'.toString()">
    	<span class="STYLE2">已回复信息：<s:property value="#w.nikeName" /></span>
    	</s:if></td>
    	<td bgcolor="#FFFFFF">
    	<s:if test='#w.msg_type=="TXT"'>
    	<span class="STYLE1">
    	
    	<s:if test="#w.msg_from=='R'.toString()">
    	<a href="replyWXInit.action?nikeName=<s:property value="#w.nikeName" />&fromUserName=<s:property value="#w.from_username" />&com_id=<s:property value="#request.com_id" />&msg=<s:property value="#w.txt" />&msg_type=<s:property value="#w.msg_type" />">回复</a>
    	</s:if>
    	
    	<s:property value="#w.txt" /></span>
    	</s:if>
    	<s:if test='#w.msg_type=="PIC"'>
    	<span class="STYLE1">
    		<s:if test="#w.msg_from=='R'.toString()">
    	<a href="replyWXInit.action?nikeName=<s:property value="#w.nikeName" />&fromUserName=<s:property value="#w.from_username" />&com_id=<s:property value="#request.com_id" />&msg=<s:property value="#w.img_media_id" />&msg_type=<s:property value="#w.msg_type" />">回复</a>
    	</s:if>
    	
    	<a target="_blank" href='GetPic.action?com_id=<s:property value="#request.com_id" />&mediaid=<s:property value="#w.img_media_id" />'>点击图片链接</a>
    	</span>
    	</s:if>
    	</td>
    </tr>
    </s:iterator>
    </table><br>
    <table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="25%" height="29" nowrap="nowrap"><span
										class="STYLE1">共<s:property value="#request.allRows" />条纪录，当前第<s:property
												value="#request.currentPage" />/<s:property
												value="#request.totalPage" />页，每页<s:property
												value="#request.pageSize" />条纪录
									</span></td>
									<td width="75%" valign="top" class="STYLE1"><div
											align="right">
											<table width="352" height="20" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="62" height="22" valign="middle"><div
															align="right">
															<img
																src="http://img.alicdn.com/imgextra/i1/60893736/TB2yKN0dFXXXXbKXXXXXXXXXXXX_!!60893736.gif"
																width="37" height="15" onclick="first();" />
														</div></td>
													<td width="50" height="22" valign="middle"><div
															align="right">
															<img
																src="http://img.alicdn.com/imgextra/i3/60893736/TB2TTR.dFXXXXXcXXXXXXXXXXXX_!!60893736.gif"
																width="43" height="15"
																onclick="back(<s:if test="#request.currentPage == 1">1</s:if><s:else><s:property value="#request.currentPage - 1"/></s:else>);" />
														</div></td>
													<td width="54" height="22" valign="middle"><div
															align="right">
															<img
																src="http://img.alicdn.com/imgextra/i4/60893736/TB24WdZdFXXXXb4XXXXXXXXXXXX_!!60893736.gif"
																width="43" height="15"
																onclick="next(<s:if test="#request.currentPage != #request.totalPage"><s:property value="#request.currentPage + 1"/></s:if><s:else><s:property value="#request.totalPage"/></s:else>);" />
														</div></td>
													<td width="49" height="22" valign="middle"><div
															align="right">
															<img
																src="http://img.alicdn.com/imgextra/i3/60893736/TB2LQVHdFXXXXbWXpXXXXXXXXXX_!!60893736.gif"
																width="37" height="15" onclick="end();" />
														</div></td>
													<td width="59" height="22" valign="middle"><div
															align="right">转到第</div></td>
													<s:form id="form_forward" action="WXList.action" method="post"
														namespace="/">
														<s:hidden name="fromUserName" value="%{fromUserName}"></s:hidden>
														<td width="25" height="22" valign="middle"><span
															class="STYLE7"> <input name="currentPage"
																type="text" class="STYLE1"
																style="height:20px; width:25px;" size="5" />
														</span></td>
														<td width="23" height="22" valign="middle">页</td>
														<td width="30" height="22" valign="middle"><img
															src="http://img.alicdn.com/imgextra/i1/60893736/TB2ua46dFXXXXXFXXXXXXXXXXXX_!!60893736.gif"
															width="37" height="15" onclick="validate();" /></td>
													</s:form>
												</tr>
											</table>
										</div></td>
								</tr>
							</table>
  </body>
</html>
