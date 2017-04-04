<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="/gd/sources/images/icon_swu.jpg">
    <title>error</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>

  </head>
  
<body style="background-color:#eee">


	<div class="col-md-2"></div>
	<div class="col-md-8" style="height:100%">
	<h1>something wrong!!!</h1>
        
    
	</div>
	<div class="col-md-2"></div>
</body>
</html>

