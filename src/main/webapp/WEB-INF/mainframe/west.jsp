<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>中心主框架</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../common/include_easyui2.jsp" flush="true" />


</head>

<body style="padding: 0px;margin:0px">



<script language='javascript' type='text/javascript'>



function navi(url){
	

	$('#body').layout('panel','center').panel('refresh',url);
	//center_load_ready();
	

}





</script>



			<div class="easyui-accordion" data-options="border:false,fit:true">
			<s:iterator value="menu" status="st"> 

				<div title="${m_name }" style="overflow: auto; padding: 10px;"
					selected="true">
					<h3 style="color: #0099FF;">${m_name }</h3>
					<p>${m_desc }</p>
					
					

					<ul>
					<s:iterator value="mds" >
					
					
					<li><a href="javascript:void(0)" onclick="javascript:navi('${ menu_d_js_url}')">${ d_name}</a>
					</li>
					 
					</s:iterator>
					
					</ul>
				</div>

			</s:iterator> 
			
			
				
			</div>
		




</body>
</html>
