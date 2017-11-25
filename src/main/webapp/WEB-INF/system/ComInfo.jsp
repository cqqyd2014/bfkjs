<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'systemInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="../css/bfkjs.css">
<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<script type="text/javascript" src="../js/jquery.metadata.js"></script>
<script type="text/javascript" src="../js/messages_zh.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#comInfoForm").validate({
			rules:{

				'ci.senderId':{
					required:true,
					maxlength:4,
					minlength:4
					
				},
				'ci.kjsPass': "required",
				'ci.eshopEntCode':{
					required:true,
					maxlength:10,
					minlength:10
				},
				'ci.stell':{
					required:true
				}

			},
			messages:{
				'ci.senderId':"请输入4位编码",
				'ci.kjsPass':"请输入跨境2.0平台密码",
				'ci.eshopEntCode':"请输入海关10位编码",
				'ci.stell':"请输入客服电话"
			}
		});

	});
</script>
</head>

<body>
	<s:form id="comInfoForm" action="comInfo.action" method="post"
		namespace="/system">
		<span class="STYLE4">公司信息</span>
		<table>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">公司名称：</span></td>
				<td bgcolor="#FFFFFF"><span class="STYLE1"><s:property
							value="#request.ci.CName" /></span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">系统内部编码：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.CId"
						readonly="true" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">2.0系统编码（一般与内部编码相同）：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield id="sender_id"
						name="ci.senderId" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">2.0系统密码：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield id="kjs_pass"
						name="ci.kjsPass" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">海关10位条码：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield id="hgbm"
						name="ci.eshopEntCode" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">客服电话：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield id="stell"
						name="ci.serviceTell" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">微信token：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.wxToken" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">微信AppID：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.wxAppid" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">微信AppSecret：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.wxSecret" size="40" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">微信服务器：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.wxServer" size="30" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">港腾AppKey：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.gtAppKey" size="30" /></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="STYLE3">港腾Secret：</span></td>
				<td bgcolor="#FFFFFF"><s:textfield name="ci.gtSecret" /></td>
			</tr>
		</table>
		<s:submit></s:submit>
	</s:form>


</body>
</html>
