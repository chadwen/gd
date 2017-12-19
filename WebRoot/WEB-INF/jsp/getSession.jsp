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
	
	<link href="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=basePath%>/sources/js/jquery-3.1.1.min.js"></script>
	<script src="<%=basePath%>/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="/gd/sources/css/font/font.css" rel="stylesheet">
	<link href="/gd/sources/css/forpic.css" rel="stylesheet">
	<!-- <link rel="stylesheet" href="/gd/sources/css/font/font.css" type="text/css"/>
	<style type="text/css">
					body{
				font-family: '14_minutessharp';
				background-color: #eee;
							}
		</style> -->
	<!-- <style type="text/css">
	@media screen and (max-width: 980px) {
		body {
        	
        	font-size:450%;
    	}
    	h3{
    		font-size:150%;
    	}
    	img{
    		width:100%;
    	}
	}
	</style> -->
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
<div class="col-md-10 col-md-offset-2">
<br/>
<br/>
<p>Proxy IP: ${proxyIP}</p>
<p>Real IP: ${realIP}</p>

<hr>
</div>
<div class="col-md-10 col-md-offset-2">


<h3>get by jsp</h3>
<p>
pre+proj name is: <%=basePath %>
</p>
<hr>
<h3>get by js</h3>

<p id="pp"></p>
<p id="pp1"></p>
<p id="pp2"></p>
<p id="pp3"></p>
<p id="pp4"></p>
</div>
<br/>
<hr>
<div  class="col-md-10 col-md-offset-2" id="screenD"></div>

<!-- <img src="/gd/sources/images/default_swu.jpg"/> -->


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


