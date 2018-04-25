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
    <title>EVEINF</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="eve,Game monster">
	<meta http-equiv="description" content="EVE INF">
	
	<link href="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=basePath%>/sources/js/jquery-3.1.1.min.js"></script>
	<script src="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="/gd/sources/css/font/font.css" rel="stylesheet">
	<link href="/gd/sources/css/forpic.css" rel="stylesheet">
	<link rel="stylesheet" href="/gd/sources/css/font/font.css" type="text/css"/>
  </head>
  
<body>


<div class="col-md-6 col-md-offset-2">
<table class="table">
  <caption><h3>较低的抗性(抗性由低到高一般为45%,55%,65%,75%)</h3></caption>
  <thead>
    <tr>
      <th><h2>舰船所属集团</h2></th>
      <th><h2>最低</h2></th>
      <th><h2>较低</h2></th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="item" items="${listM}">
		<tr>
	      <td><h2>${item.name}</h2></td>
	      <td><h2>${item.lowestResistance}</h2></td>
	      <td><h2>${item.lowerResistance}</h2></td>
	    </tr>
	</c:forEach>
  </tbody>
</table>
</div>

</body>
</html>
<script type="text/javascript">

function getFullPath(){
	return window.document.location.href;
}
function getPosName(){
	return window.document.location.pathname;
}
function getProjName(){
	return getPosName().substring(0,getPosName().substr(1).indexOf('/')+1);
}
function getPreName(){
	return getFullPath().substring(0,getFullPath().indexOf(getPosName()));
}
//alert(getFullPath());
$('#pp').html("current URL is: "+getFullPath());
$('#pp1').html("post name is: "+getPosName());
$('#pp2').html("project name is: "+getProjName());
$('#pp3').html("pre name is: "+getPreName());
$('#pp4').html("pre+proj name is: "+getPreName()+getProjName());
var html = '';
html+='浏览器当前窗口可视区域宽度:'+$(window).width()+'px<br/>'
+'浏览器当前窗口可视区域高度:'+$(window).height()+'px<br/>'
+'显示器分辨率宽度:'+screen.width+'<br/>'
+'显示器分辨率高度:'+screen.height+'<br/>'
$('#screenD').html(html);
</script>


