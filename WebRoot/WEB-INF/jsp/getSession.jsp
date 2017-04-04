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
    <title>get session</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

  </head>
  
<body>
<div class="col-md-6 col-md-offset-2">
<table class="table">
  <caption>here is the user list</caption>
  <thead>
    <tr>
      <th>userId</th>
      <th>userName</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="item" items="${userMap.keySet()}">
		<tr>
	      <td>${item}</td>
	      <td>${userMap.get(item)}</td>
	    </tr>
	</c:forEach>
  </tbody>
</table>
</div>

</body>

</body>
</html>


