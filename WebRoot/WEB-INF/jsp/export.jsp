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
	
	<script src="/gd/sources/js/myJs/pageLoading.js"></script>
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="/gd/sources/js/plugins/leftNavi/css/jquery-accordion-menu.css" rel="stylesheet">
	<link href="/gd/sources/js/plugins/leftNavi/css/font-awesome.css" rel="stylesheet">
	
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap-select.min.js"></script>
	<script src="/gd/sources/js/webpack/unit/JqLayer/layer.js"></script>
	<!-- <script src="/gd/sources/js/plugins/jedate/jedate.js"></script> -->
	<script id="leftNaviJS" src="/gd/sources/js/plugins/leftNavi/js/jquery-accordion-menu.js"></script>

<script type="text/javascript">

$(function(){	
	//顶部导航切换
	$("#leftNavi-list li").click(function(){
		$("#leftNavi-list li.active").removeClass("active")
		$(this).addClass("active");
	})	
	
	$("#leftNavi-list li ul li").click(function(){
		$("#leftNavi-list li ul li.active").removeClass("active")
		$(this).addClass("active");
	})
})	
</script>
  </head>
  
<body>

<div id="navi"></div>
<br>
<div id="leftNavi" class="col-md-3">
	<div id="jquery-accordion-menu" class="jquery-accordion-menu white">
		<ul id="leftNavi-list">
		   <li><a href="#"><i class="fa fa-home"></i>Home </a></li>
		   <li class="active"><a href="/gd/data/export#"><i class="fa fa-info"></i>本站信息</a></li>
			<li><a href="/gd/data/export#"><i class="fa fa-bar-chart-o"></i>查看数据图表</a>
				<!-- <ul class="submenu">
					<li><a href="/gd/data/export#">饼图</a></li>
					<li><a href="/gd/data/export#">柱状图</a></li>
					<li><a href="/gd/data/export#">折线图</a></li>
				</ul> -->
			</li>
			<li><a href="/gd/data/export#"><i class="fa fa-th-list"></i>查看站点动态</a>
				<ul id="allStation" class="submenu">
					<li><a href="/gd/data/export#">一号门</a></li>
					<li><a href="/gd/data/export#">二号门</a></li>
					<li><a href="/gd/data/export#">三号门</a></li>
					<li><a href="/gd/data/export#">五号门</a></li>
					<li><a href="/gd/data/export#">六号门</a></li>
					<li><a href="/gd/data/export#">七号门</a></li>
					<li><a href="/gd/data/export#">八号门</a></li>
				</ul>
			</li>
			<li><a href="/gd/data/export#"><i class="fa fa-download"></i>数据下载 </a>
				<ul class="submenu">
					<li><a id="singleData" href="/gd/data/export#">站点车流数据</a></li>
					<li><a id="calculateData" href="/gd/data/export#">统计车流数据</a></li>
					<li><a id="staInfo" href="/gd/data/getAllStations">站点信息数据</a></li>					
				</ul>
			</li>
			<li><a href="/gd/data/export#"><i class="fa fa-user"></i>管理操作员 </a>
				<ul class="submenu">
					<li><a id="resetpwd" href="/gd/data/export#">重置账号密码</a></li>
					<li><a id="clearStatus" href="/gd/data/export#">清除登录状态</a></li>
					<li><a href="/gd/data/export#">查看账号信息</a></li>
					
				</ul>
			</li>
			<li><a href="/gd/data/export#"><i class="fa fa-steam"></i>About </a></li>
		   
		</ul>
		<!-- <div class="jquery-accordion-menu-footer">
			Footer
		</div> -->
	</div>
</div>


<div id="manageContent" style="padding-top:30px" class="col-md-8">

<iframe id="iframeContent" frameborder=0 scrolling=no width=100% height="80%" src=""></iframe>


  
  
</div>

<div class="col-md-1">
</div>

<script type="text/javascript">

	$("#jquery-accordion-menu").jqueryAccordionMenu();
	
</script>
</body>


</html>

<!-- <script type="text/javascript">
    jeDate.skin('gray');

    	$('#btn').click(function(){
    		var sjdi = $('#startdate').val();
    		var jsi = $('#startdate');
    		console.log(sjdi);
    		console.log(jsi);
    		//alert($('startdate').val());
    	});

	jeDate({
		dateCell:"#single_startdate",//isinitVal:true,
		format:"YYYY-MM-DD",
		isTime:false, //isClear:false,
		choosefun:function(elem, val) {
			//$('#startdate').val(val);
		},
		//minDate:"2015-10-19 00:00:00",
		//maxDate:"2016-11-8 00:00:00"
	})
	jeDate({
		dateCell:"#single_enddate",//isinitVal:true,
		format:"YYYY-MM-DD",
		isTime:false, //isClear:false,
		choosefun:function(elem, val) {
			//$('#enddate').val(elem);
		},
		//minDate:"2015-10-19 00:00:00",
		//maxDate:"2016-11-8 00:00:00"
	})

	jeDate({
		dateCell:"#cal_startdate",//isinitVal:true,
		format:"YYYY-MM-DD",
		isTime:false, //isClear:false,
		choosefun:function(elem, val) {
			//$('#startdate').val(val);
		},
		//minDate:"2015-10-19 00:00:00",
		//maxDate:"2016-11-8 00:00:00"
	})
	jeDate({
		dateCell:"#cal_enddate",//isinitVal:true,
		format:"YYYY-MM-DD",
		isTime:false, //isClear:false,
		choosefun:function(elem, val) {
			//$('#enddate').val(elem);
		},
		//minDate:"2015-10-19 00:00:00",
		//maxDate:"2016-11-8 00:00:00"
	})
</script> -->

<script src="/gd/sources/js/webpack/build/exports.bundle.js"></script>
<script>
$(document).ready(function(){


	$.ajax({
            type: "POST",
            cache: false,
	   		//traditional:true,//传数组时应设置为true
            url: "/gd/station/getall",
            dataType: 'json',
            data: {},
            success: function (data) {
            	$("#allStation").html("");
            	$("#allStation").attr("class","submenu");
            	$.each(data, function(){
				    console.log(this.id+","+this.brief);
				    $("#allStation").append('<li><a href="/gd/chartdata/get/'+ this.id +'" target="_Blank" onclick=openNewTab(this)>'+this.brief+'</a></li>');
				    //$("#allStation").append('<li><a href="javascript:void(0);" onclick="openNewTab(this)">'+this.brief+'</a></li>');
				    
				    
				    
				});
				//$("#jquery-accordion-menu").jqueryAccordionMenu();
				
				
				var head = $("head").remove('#leftNaviJS')
				var file = "/gd/sources/js/plugins/leftNavi/js/jquery-accordion-menu.js";
				head.append('<scr'+'ipt id="leftNaviJS" src="'+file+'" type="text/javascript">'+'</scr'+'ipt>');
				//$("<scri"+"pt>"+"</scr"+"ipt>").attr({src:file,type:'text/javascript',id:'leftNaviJS'}).appendto(head);
				$("#jquery-accordion-menu").jqueryAccordionMenu(); 
            },
		}); 
		
		

});
/* function generateDownloadHtml(title){
	var htmls = '<div class="col-md-8">'+
  	title+
	'<form class="form-horizontal" role="form">'+

	'<hr>'+
	  '<div class="form-group col-md-12">'+
	    '<label>起始日期</label>'+
	    '<input class="form-control" id="startdate" type="text" placeholder="请选择起始日期" value=""  readonly>'+
	  '</div>'+
	  '<div class="form-group col-md-12">'+
	    '<label>终止日期</label>'+
	    '<input class="form-control" id="enddate" type="text" placeholder="请选择终止日期" value=""  readonly>'+
	  '</div>'+
	
	  '<div class="form-group col-md-12">'+
	    '<label>站点</label>'+

	      '<select id="usertype" name="usertype" class="selectpicker show-tick form-control" multiple data-live-search="false">'+
			'<option value="0">苹果</option>'+
			'<option value="1">菠萝</option>'+
			'<option value="2">香蕉</option>'+
		  '</select>'+

	  '</div>'+
	  '<div class="col-md-12">'+
		  '<div style="float:right">'+
		  	'<button type="button" class="btn btn-default">&nbsp下载&nbsp</button>'+
		  '</div>'+
	  '</div>'+
	'</form>'+
  '</div>';
  return htmls;

} */

</script>

