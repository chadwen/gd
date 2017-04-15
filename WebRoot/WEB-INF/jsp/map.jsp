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
    <title>map</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>
	
	<!--BMap-->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dofCSbYC4fKt2RXGpfgayjy55NlcfQRc"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	<style type="text/css">
		body, html,#swuMap {
			width: 100%;
			height: 100%;
			overflow: hidden;
			margin:0;
			font-family:"微软雅黑";
		}
	</style>
  </head>
  
<body>

<div id="navi">

</div>	

	<!--坐标
	<div>
		<div style="float:left;width:5%;text-align:right"><span>坐标：</span></div>
		<div id="coordinate" style="width:100%px;height:30px;background-color:#fff;margin-left:5%"></div>

	</div>
	-->
	<!--地图-->
	<div id="swuMap"></div>

</body>
</html>

<script src="/gd/sources/js/webpack/build/homepage.bundle.js"></script>
