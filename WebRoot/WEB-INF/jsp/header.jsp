<%@ page language="java" 
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 导航栏 start -->
<script src="/gd/sources/js/jQuery-plus/MyJS/myScript.js"></script>
<link href="/gd/sources/js/jQuery-plus/MyJS/asd.css" rel="stylesheet" type="text/css" />
<link href="/gd/sources/js/jQuery-plus/MyJS/sd.css" rel="stylesheet" type="text/css" />
<script>
$(function () {
    $(window).scroll(function () {
        //导航
        if ($(window).scrollTop() > 50) {
            $('.header').removeClass('large').addClass('small');
            //$('.header').attr('class','header small');
        } else {
            $('.header').removeClass('small').addClass('large');
        }
    });
});
</script>
<!-- 导航栏 end -->

<nav class="header large" style="background-color:#555">
<div class="inner">
	<h1 style="color:#f7f7f7"><a href="addU">monitor sys</a></h1>
	<!--
	<span class="search">
		<input id="searchBox" name="q" type="text" placeholder="输入关键字搜索" class="form-control" value="">
	</span>
	-->
	<ul class="nav" id="nav">
		<li><a class="cur" href="addU" title="首页">清理闲置</a></li>
		<li><a href="addU" >出售中</a></li>
		<li>
			<a href="addU">消息
			<c:if test="${!empty sessionScope.usern}">
			
			
			<span class="am-badge am-badge-danger am-round"
			style="margin-left: 5px;">new</span>
			</c:if>
			</a>
		</li>
		<c:if test="${!empty sessionScope.usern}">
			<li><a href="logout" >退出登录</a></li>
		</c:if>
		<!--<li><a href="/gii.html" target="_blank">GII</a></li>-->
	</ul>
</div>
</nav>
	