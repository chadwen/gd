<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>hello</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
	<script>
	$(document).ready(function(){
		//$('.btn').click(function(){
			//alert('xx');
			//$('#nav').append('<li><a href="listMyGoods" >出售中</a></li>');	
			//$('#nav').append('<li><a href="listMyGoods" >出售中</a></li>');	
		//});	
	});
	</script>
  </head>
  
  <body>
  <jsp:include page="header.jsp"></jsp:include>
    This is my JSP page. <br>
    <button id="btn" class="btn btn-primary">btn</button>
  </body>
</html>

<script src="/gd/sources/js/myJs/test.js"></script>
