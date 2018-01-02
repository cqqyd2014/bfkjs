<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>




<script language='javascript' type='text/javascript'>
	function exit() {
		window.parent.location = "../system/exit.action";
	}
	function homepage() {
		self.parent.frames["mainFrame"].location = "center.action";
	}
	function pwdInit() {
		self.parent.frames["mainFrame"].frames["main"].document.location = "../system/pwdInit.action";
	}
	function go() {
		self.parent.frames["mainFrame"].frames["main"].history.go(-1);
	}
	function back() {
		self.parent.frames["mainFrame"].frames["main"].history.go(1);
	}
	function reload() {
		self.parent.frames["mainFrame"].frames["main"].location.reload();
	}
</script>



<script type="text/javascript">



	/**
	 * jquery 定时刷新
	 * edit 
	 */
	setInterval("Push()", <s:property value="#application.interval_time" /> * 1000);
	function Push() {
		
		$.ajax({
			type : "POST",
			url : "../system/set_user_online.action",
			data: {user_id:"<s:property value='#request.user_id' />", com_id:"<s:property value='#request.com_id'/>"},
            dataType: "json",
			success : function(data) {
				var field=data.msg;
				ajax_authority(field);
					
					
						$('#current_time').text(field.o);
					
					
				
			
			}
		});
	}
</script>

	<div
		style="display: table; background-color: #996699; color: white; padding: 0px; border: 0px; width: 100%; height: 60px;">

		<div
			style="float: left; display: table-cell; padding: 5px;  height: 50px;border: 0px;">

			<img alt="" src="../img/logo-800-400.jpg"
				style="height: 40px; vertical-align: middle; margin: 0px;line-height:50px;border: 0px;" />



			<p
				style="border: 0px;font-weight:bold;line-height:50px;font-size: 20px; vertical-align: middle; display: inline-block; margin: 0px;"><s:property value="#application.app_name"/>
				</p>

</div>

		<div
			style="display: table-cell; float: right; padding: 5px; height: 50px;border: 0px;">


			
		
			<p style="border: 0px;line-height:50px;vertical-align: middle; padding: 0px;display: inline-block;margin: 0px ;">
				最后刷新时间：
				<s:property value="#request.chinese_date" />
				登录用户：
				<s:property value="#request.user_name" />
			</p>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="border: 0px;line-height:50px;vertical-align: middle; padding: 0px;margin: 0px ; "
				onclick="javascript:exit()">退出</a>
		</div>






	</div>
</div>