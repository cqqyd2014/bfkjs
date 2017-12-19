<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>



<jsp:include page="../common/include_jqueryui2.jsp" flush="true" />


<title>My JSP 'down.jsp' starting page</title>
<script>

 
		function start(){
			var progressbar = $( "#progressbar" );
	        var progressbarValue = progressbar.find( ".ui-progressbar-value" );
			progressbarValue.css({
		          "background": '#' + Math.floor( Math.random() * 16777215 ).toString( 16 )
		        });
			progressbar.progressbar( "option", "value", false );
		}
	
		function stop(){
			var progressbar = $( "#progressbar" );
	       
			
			progressbar.progressbar( "option", {
		          value: 100
		        });
		}



		$(document)
		.ready(
				function() {
		        $( "#progressbar" ).progressbar({
		          value: true
		        });
		        var progressbar = $( "#progressbar" );
		        var progressbarValue = progressbar.find( ".ui-progressbar-value" );
				progressbarValue.css({
			          "background": '#' + Math.floor( Math.random() * 16777215 ).toString( 16 )
			        });
				});
		        
		    
	</script>
<style>
#progressbar .ui-progressbar-value {
	background-color: #ccc;
}
</style>
</head>

<body style="padding: 0px; margin: 0px">
	<div id="progressbar"></div>



</body>
</html>
