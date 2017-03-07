<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>adminUser</title>
    
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
<div class="col-md-offset-3 col-md-4">
<form class="form-horizontal" role="form" method="POST" action="/gd/station/add">

  <div class="form-group">
    <label for="staFullName" class="col-md-3 control-label">站点名称</label>
    <div class="col-md-9">
      <input type="text" class="form-control" id="staFullName" name="staFullName" placeholder="一号门">
    </div>
  </div>
    <div class="form-group">
    <label for="staAlias" class="col-md-3 control-label">站点代号</label>
    <div class="col-md-9">
      <input type="text" class="form-control" id="staAlias" name="staAlias" placeholder="G1">
    </div>
  </div>
  <div class="form-group">
    <label for="addr" class="col-md-3 control-label">站点地址</label>
    <div class="col-md-9">
      <input type="text" class="form-control" id="addr" name="addr" placeholder="北碚天生街道xx号西南大学一号门">
    </div>
  </div>
    <div class="form-group">
    <label for="brief" class="col-md-3 control-label">站点简介</label>
    <div class="col-md-9">
      <input type="text" class="form-control" id="brief" name="brief" placeholder="默认为站点名称">
    </div>
  </div>
  </div>

  <input type="hidden" class="form-control" id="imgPath" value="http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg" name="imgPath" placeholder="默认为站点名称">

  <input type="hidden" id="posx" name="posx" value="${posx}"/>
  <input type="hidden" id="posy" name="posy" value="${posy}"/>
  <div class="form-group" style="float:right">
    <div>
      <button type="submit" class="btn btn-default">添加</button>
    </div>
  </div>

</form>
</div>
  </body>
</html>


