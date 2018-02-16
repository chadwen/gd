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
    
    <title>record</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/gd/sources/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="/gd/sources/js/jquery-3.1.1.min.js"></script>
	<script src="/gd/sources/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="/gd/sources/css/forpic.css" rel="stylesheet">


  </head>
  
<body>


record record record record record record record record record record record record record record

<form method="POST" id="login_form" action="/gd/pic/rec">
	<div>
		<input name="str" type="text" placeholder="record" class="form-control required"  required="" id="str" />
	</div>
	<div>
		<input type="submit" id = "login_submit" class="btn" value="submit" />
	</div>
</form>

<div>
<c:forEach var="record" items="${records}" >
	<p>${record}</p>
</c:forEach>
</div>
<div>
<br/>

<form method="POST" id="upf" action="/gd/pic/upll" enctype="multipart/form-data">
	<div>
		<input name="file" type="file" class="form-control required"  id="upf" />
	</div>
	<div>
		<input type="submit" class="btn" value="upload" />
	</div>
</form>


<br/>
<hr/><hr/>
</div>
<div>
<br/>
<hr/>
<hr/>
<br/>

<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
当我登上那古老的城墙，当我抚摸着腐朽的柱梁，当我兴奋的倚栏远望，总会有一丝酸涩冲上喉头，总听到有一个声音大声的说：记得吗？
你的祖先名叫炎黄。 有人跟我说，曾经有一条大鱼，生活在北冥那个地方，它化作一只巨鸟，在天地之间翱翔。巨鸟有如垂天之云般的翅膀，虽九万里亦可扶摇直上。
圣贤赋予我们可以囊括天宇的胸襟，为我们塑造一个博大恢弘的殿堂。 
那时候，有个怪异的青年名叫嵇康，他临刑前，弹奏了一曲绝响，那宽袍博带在风中飞扬，他用了最优雅的姿态面对死亡。几千年过去，依旧有余音绕梁，只是他不知道，
真正断绝的不是曲谱，而是他的傲骨，乃至他身上的衣裳啊。我也曾梦回大唐，和一个叫李白的诗人云游四方，他用来下酒的是剑锋上的寒光，他的情人是空中的月亮。
</p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
我曾见他在月下徘徊、高歌吟唱，长风吹开他的发带，长袍飘逸宛如仙人模样。 可是后来换了帝王，他用一杯酒捧起了文人，摒弃了武将。他的子孙最终躲进了人间天堂，
把大片的土地拱手相让。然而在寒冷的北方，正有一支军队征战沙场，敌人都说，有岳家军在，我们打不了胜仗。可叹英雄遭忌，谗士高张，一缕忠魂终于消散在西湖之傍，
一个民族的精神就这么无可逆转的消亡。然而血色夕阳中，我依稀见到，有人把它插进土壤，那是将军用过的，一支宁折不弯的缨枪。 
</p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
时间的车轮悠悠荡荡，终于在甲申那里失了方向。于是瘦西湖畔，梅花岭上，为纪念这个悲剧建起一座祠堂。那个叫史可法的文弱书生，他不愿散开高束的发髻，
更不能脱去祖先留给他的衣裳，于是他决定与城共存共亡，丢了性命，护了信仰。残酷的杀戮，如山的尸骨，并不能把民族的精神埋葬，有人相信，千百年后，
它依然会在中华大地上熠熠发光。 就在千百年后的今天，我坐进麦当劳的厅堂，我穿起古奇牌的时装，我随口唱着my heart will go on，却莫名其妙的心伤，
因为我听到一个声音大声的说：忘了吗？你的祖先名叫炎黄。 我记得了，一群褐发篮眼的豺狼，带着尖船利炮，拆了我们的庙宇，毁了我们的殿堂。于是百年之后的今天—— 
我们懂得民主自由，却忘了伦理纲常，我们拥有音乐神童，却不识角徵宫商，我们能建起高楼大厦，却容不下一块公德牌坊，我们穿着西服革履，却没了自己的衣裳。
</p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
在哪里，那个礼仪之邦？在哪里，我的汉家儿郎？为什么我穿起最美丽的衣衫，你却说我行为异常？为什么我倍加珍惜的汉装，你竟说它属于扶桑！
</p>
<br/>
</div>

</body>
</html>

