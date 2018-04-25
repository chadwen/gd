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
    
    <title>index</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="eve">
	<meta http-equiv="description" content="eve">
	
	<link href="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=basePath%>/sources/js/jquery-3.1.1.min.js"></script>
	<script src="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="/gd/sources/css/font/font.css" rel="stylesheet">
	<link href="/gd/sources/css/forpic.css" rel="stylesheet">
	<link rel="stylesheet" href="/gd/sources/css/font/font.css" type="text/css"/>

<script>
	
	function urlOnclick(obj){
		var ids = $(obj).attr("id");
		var clas_s = "."+ids;
		var targetArea = $(clas_s);
		if(targetArea.css("display")=="none"){
			targetArea.css("display","block");
		}else{
			targetArea.css("display","none");
		}
	}
</script>
  </head>
  
  <body>
  <div class="col-md-6 col-md-offset-2">
  <table class="table">
  	<caption><h1>我们的征途是星辰大海</h1></caption>
  	<thead>
    <tr>
      <th><h1>Hyperlink Here</h1></th>
    </tr>
  </thead>
  	<tbody>
  		<tr>
	      <td><h1><a href="/gd/eve/inf">EVE INF</a></h1></td>
	    </tr>
  		<tr>
	      <td><h1><a href="https://www.eve-ship.com/browse/new">eve-ship</a></h1></td>
	    </tr>
  	</tbody>
  </table>
  </div>
    <br>
    <br>
    <a href="/gd/eve">EVE Online</a>
    <br>
    <p>abcdefghijklmnopqrstuvwxyz</p>
    <p>ABCDEFGHIJKLMNOPQRSTUVWXYZ</p>
	
	<div style="display:none">
	<a href="javascript:void(0);" id="one2ni" onclick="urlOnclick(this);" style="font-weight:bold;">pic_col1-pic_col9</a><br><br>
	<div class="one2ni" style="display:none">
	<a href="/gd/eve">eve1</a><br><br>
	<a href="/gd/eve">eve2</a><br><br>
	</div>
	
	<a href="javascript:void(0);" id="misc" onclick="urlOnclick(this);" style="font-weight:bold;">pic1-pic5 - misc</a><br><br>
	<div class="misc" style="display:none">
	<a href="/gd/eve">eve3</a><br><br>
	<a href="/gd/eve">eve4</a><br><br>
	</div>
	<br><br>
	
	</div>
  </body>

  
</html>

