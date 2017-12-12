<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- 信息对话框 -->
<div data-role="dialog" id="page_message">
  <div data-role="header">
    <h1 id="message_head"></h1>
  </div>

  <div data-role="content">
  	<div>
  		<img width='50px' id="icon">
    	<span id="message_content" style="vertical-align:top;"></span>
    </div>
    <input type="button" value="确定" onclick="javascript:back()">
    
  </div>

  <div data-role="footer">
  <h1>V2017 V3.0</h1>
  </div>
</div>

<script type="text/javascript">
function message_dialog(head,content,type){
	//type只能为info,alert
	$('#message_head').text(head);
	$('#message_content').text(content);
	$("#icon").attr('src',"../js/images/icons-svg/"+type+"-black.svg"); 
	
	$.mobile.changePage('#page_message');
	
}

function back(){
	
					window.location.href='#pageone';

}


	
</script>