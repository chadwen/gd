function asd(){
	alert("test alert");
}

//判断输入域是否为空
function checkBlur(lab,id,disp){//lab,表示特定名称；id代表输入域的id；disp代表显示消息域的id
	var value = Trim($("#"+id).val());
	if(value==""){
		$("#"+disp).css({
			"color":"red"
		});
		$("#"+disp).html(lab+"不能为空");
		//alert(lab+"不能为空！");
		return false;
	}else{
		$("#"+disp).html("");
	}
}
//判断用户输入的是否为数字  可用
function checkNum(id,disp){//id代表输入域的id；disp代表显示消息域的id
	var value = Trim($("#"+id).val());
	if(isNaN(value)){
		$("#"+disp).css({
			"color":"red"
		});
		$("#"+disp).html("请输入一个数字！");
	}else{
		$("#"+disp).html("");
	}
}
//判断用户输入的是否为数字  可用 结合 IsNum(s) 函数
/*function checkNum(id,disp){
	var value = Trim($("#"+id).val());
	if(value==""||!IsNum(value)){
		$("#"+disp).css({
			"color":"red"
		});
		$("#"+disp).html("请输入一个数字！");
	}else{
		$("#"+disp).html("");
	}
}*/
//判断传入的字符串是否位数字
function IsNum(s)
{
	var r,re;
	re = /\d*/i; //\d表示数字,*表示匹配多个数字
	r = s.match(re);
	return (r==s)?true:false;    
	return false;
}

//删除前后空格
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


//jQuery格式：
$(document).ready(function(){
	$("#login").click(function(){
		var username = Trim($(":text").val());
		var pwd = Trim($(":password").val());
		if(username==""||pwd==""){
			return false;//不提交
		}
		
	});
	$("#comment").click(function(){
		var comm = Trim($("textarea").val());
		if(comm==""){
			alert("不能提交空评论！");
			return false;//不提交
		}
	});
	$("#send").click(function(){
		var comm = Trim($("textarea").val());
		if(comm==""){
			alert("不能发送空消息！");
			return false;//不提交
		}
	});
	
});

