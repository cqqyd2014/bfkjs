<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>淘宝订单导入</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="../js/jquery-2.1.4.min.js">
	
</script>
<script type="text/javascript" src="../js/qyd.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script type="text/javascript">
        function ajaxFileUpload()
        {
            var file = ['file1','file2'];  
              
            $.ajaxFileUpload
            (
                {
                    url:'upload.action',//用于文件上传的服务器端请求地址
                    data : {
                    	order_from : 'TB',
                        
                    },
                    secureuri:false,//一般设置为false
                    fileElementId: file,// 文件id数组
                    dataType: 'json',//返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                       alert("success");
                       alert(data.text());
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert("fail");
                    }
                }
            );
              
            return false;
  
        }
 
    </script>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css" />
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/qyd.css">



<script type="text/javascript" src="../js/datagrid-detailview.js"></script>








</head>

<body style="width: 95%; height: 95%;">
	 <input type="file"  id="file1" name="file" />
        <input type="file"  id="file2" name="file" />
        <br />
        <input type="button" value="上传" onclick="return ajaxFileUpload();">
</body>
</html>

