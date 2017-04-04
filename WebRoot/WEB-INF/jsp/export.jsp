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
    <title>数据导出</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>
	


  </head>
  
<body>

	<!--nav-->
	<nav class="navbar navbar-default" role="navigation">
		<div class = "container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="homepage.html">车流监控系统</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="/gd">地图</a></li>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						动态
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="/gd/chartdata/wholeState">全局动态</a></li>
						<li><a href="/gd/chartdata/sectionState">本站点动态</a></li>
					</ul>
				</li>
				<li><a class="active" href="/gd/user/export">数据导出</a></li>
			</ul>
		</div>
		</div>
	</nav><!--nav-->



</body>

</body>
</html>



