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
    <title>站点动态</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>
	
	<!-- 引入 echarts.js -->
    <!-- 
    <script src="/gd/sources/js/echarts/echarts.js"></script>
     -->
	<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>

  </head>
  
<body>

<div id="navi">

</div>	
	
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width:90%;height:85%" ></div>
    
    <div style="float:right;padding:0px 20px 0px 0px" id="btnContain">
	<!-- <button class="btn" id="trig" >trigger</button>
	<button class="btn btn-primary" id="testbtn">trigger</button> -->
<!-- 	<button class="btn" id="chartOut">OUT</button>
	<button class="btn" id="chartIn">IN</button> -->
	
	</div>
	<div id = "saveStaId" style="display:none">
	<c:choose>
    <c:when test="${!empty staId}">
      <c:out value="${staId}"/>
    </c:when>
   <c:otherwise>  
      <c:out value="NONE"/>
   </c:otherwise>
  </c:choose>
	</div>


</body>

</body>
</html>

<script src="/gd/sources/js/webpack/build/wholestate.bundle.js"></script>

