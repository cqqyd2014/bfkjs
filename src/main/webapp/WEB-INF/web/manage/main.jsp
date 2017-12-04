<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>


<title><s:property value="#request.com_name" />移动MIS</title>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="../../js/jquery.mobile-1.4.5.min.css" />
<script src="../../js/jquery-2.1.4.min.js"></script>
<script src="../../js/jquery.mobile-1.4.5.min.js"></script>
<script src="../../js/qyd.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">

function init(){

	$.getJSON("get_attention.action", {	}, function(result) {
		
		var field=result.msg;
			
			
				if (field.success){
					//成功登录
					var atts=field.o;
					atts.forEach(function(att,index,array){
						 
					　　　　$('#count_'+att.m_id).text(att.count);
					 
					　　});
				}
				else{
					
					message_dialog("系统错误",field.body,"alert");


					}
				
			
	
		
	});
	


	
}



	$(document).ready(function() {
		
		
		init();
	});
</script>

</head>

<body>

	<div data-role="page" id="pageone">
		<jsp:include page="common/head.jsp" flush="true" />

		<div data-role="content">
			<ul data-role="listview" data-inset="true">

				<s:iterator value="menu" >
					<li data-role="list-divider">${m_name }</li>




					<s:iterator value="menu_d" status="st">
						<li><a href="${web_url}">

								<h2>${m_d_name}</h2>
								<p>${web_attention_tips}</p>
								<s:if test="web_attention">
									<span class="ui-li-count" id="count_${m_id}${m_d_id}">0</span>
								</s:if>
						</a></li>




					</s:iterator>




				</s:iterator>






			</ul>
		</div>
		<jsp:include page="common/tail.jsp" flush="true" />

	</div>


	<jsp:include page="../dialog.jsp" flush="true" />
</body>
</html>
