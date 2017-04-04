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
    <title>login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link href="/gd/sources/css/login.css" rel="stylesheet">
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>

  </head>

	<body>
		<div class="container" style="margin-top:10%">
			<section id="content">
				<form method="POST" action="/gd/user/login">
					<h1>登录</h1>
					<div>
						<input name="userName" type="text" placeholder="用户名" required="" id="userName" />
					</div>
					<div>
						<input name="password" type="password" placeholder="密码" required="" id="password" />
					</div>
					<div>
						<input type="submit" value="登录" />
						<a href="#">忘记密码</a>
					</div>
				</form><!-- form -->
				<div class="button">
					<br>
				</div><!-- button -->
			</section><!-- content -->
		</div><!-- container -->
	</body>
</html>
