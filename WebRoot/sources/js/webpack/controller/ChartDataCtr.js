

class ChartData{
	constructor(){
		
	}
	initEvent(){
		
	}
	
	_testAjax(dataArr,direction){
		dataArr = [0,0,0,0,0,0,0,0,0,0,0,0,0,5,3,8,2,12,9,5,4,9,6,9];
		direction = 'IN';
		$.ajax({
			type : "POST",
			url : "/gd/chartdata/update",
			traditional : true,
			//我们用text格式接收  
			//dataType: "text",   
			//json格式接收数据  
			dataType : "json",
			data : {"dataArr":dataArr,"direction":direction},
			success : function(jsonStr) {
			},
			error : function(xhr, ajaxOptions, thrownError) {

			}
		});
	}
}