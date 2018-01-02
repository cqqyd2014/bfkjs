<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:property value="#request.com_name" /></title>
<jsp:include page="/WEB-INF/common/include_easyui2.jsp" flush="true" />
<script>
            $(document).ready(function(){
                $(".fakeloader").fakeLoader({
                    timeToHide:1200,
                    bgColor:"#9b59b6",
                    spinner:"spinner5"
                });
            });
        </script>
</head>
<body class="easyui-layout">
<div class="fakeloader"></div>
</body>
</html>