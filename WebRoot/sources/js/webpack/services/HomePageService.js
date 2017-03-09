//HomePageService.js

import jquery from '../unit/JQueryVendor';
//import msgWindow from '../unit/BMap/SearchInfoWindow';
import CommonService from '../services/CommonService';
//import openlayer from "../unit/JqLayer/layer/layer";

class HomePageService{
	constructor(){
		this.commonService = new CommonService();		
	}
	
	_addMapMenu(map,posx,posy,id){
		let self = this;
		let menu = new BMap.ContextMenu();
		let txtMenuItem = [
		           		{
		           			text:'添加站点',
		           			callback:function(){
		           				self.commonService.options = {
		           			            header: '添加站点',
		           			            content:'<form role="form" id="addStation" method="POST" action="/gd/station/add">' +
		           			                    '<div class="form-group">' +

		           			                    '<label for="name">站点名称</label>' +
		           			                    '<input type="text" class="form-control" id="fullName" name="fullName" placeholder="一号门">' +
		           			                    '<label for="name">站点代号</label>' +
		           			                    '<input type="text" class="form-control" id="alias" name="alias" placeholder="G1">' +
		           			                    '<label for="name">站点地址</label>' +
		           			                    '<input type="text" class="form-control" id="addr" name="addr" placeholder="北碚天生街道xx号西南大学一号门">' +
		           			                    '<label for="name">站点简介</label>' +
		           			                    '<input type="text" class="form-control" id="brief" name="brief" placeholder="默认为站点名称">' +
		           			                    
			           			                '<input type="hidden" class="form-control" id="imgPath" value="http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg" name="imgPath">' +
			           			                '<input type="hidden" id="posx" name="posx" value="'+posx+'"/>' +
			           			                '<input type="hidden" id="posy" name="posy" value="'+posy+'"/>' +
		           			                    '</div>'+
		           			                    ' </form>',
		           			            footer: '<button type="button" class="btn btn-default stacancel">取消</button>' +
		           			                    '<button type="button" class="btn btn-primary stasubmit"><span class="glyphicon glyphicon-cloud-upload" id="uploadtitle">添加</span></button>' 
		           			                    
		           			        };
		           			        self.commonService.OpenParentModalLayer();
		           			     $('.stasubmit').click(function(){
		           			    	 $('#addStation').submit();
		           					console.log("submit now");
		           					self.commonService.CloseLayer();
		           				});
		           			     $('.stacancel').click(function(){
		           				  self.commonService.CloseLayer();
		           				});
		           			}
		           		},
		           		{
		           			text:'添加停车点',
		           			callback:function(){
		           				self.commonService.options = {
		           			            header: '添加停车点',
		           			            content: '<form role="form" id="addPark" method="POST" action="/gd/park/add">' +
		           			                    '<div class="form-group">' +

		           			                    '<label for="name">停车点名称</label>' +
		           			                    '<input type="text" class="form-control" id="fullName" name="fullName" placeholder="一号停车点">' +
		           			                    '<label for="name">停车点代号</label>' +
		           			                    '<input type="text" class="form-control" id="alias" name="alias" placeholder="P1">' +
		           			                    '<label for="name">停车点地址</label>' +
		           			                    '<input type="text" class="form-control" id="addr" name="addr" placeholder="北碚天生街道xx号西南大学一号停车点">' +
		           			                    '<label for="name">停车点简介</label>' +
		           			                    '<input type="text" class="form-control" id="brief" name="brief" placeholder="默认为停车点名称">' +
		           			                    '<label for="name">停车点车位总量</label>' +
		           			                    '<input type="text" class="form-control" id="totalLot" name="totalLot" placeholder="停车点车位总量">' +

			           			                '<input type="hidden" class="form-control" id="imgPath" value="http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg" name="imgPath">' +
			           			                '<input type="hidden" id="posx" name="posx" value="'+posx+'"/>' +
			           			                '<input type="hidden" id="posy" name="posy" value="'+posy+'"/>' +
		           			                    '</div>'+
		           			                    ' </form>',
		           			            footer: '<button type="button" class="btn btn-default parkcancel">取消</button>' +
		           			                    '<button type="button" class="btn btn-primary parksubmit"><span class="glyphicon glyphicon-cloud-upload" id="uploadtitle">添加</span></button>' 
		           			                    
		           			        };
		           			        self.commonService.OpenParentModalLayer();
		           			     $('.parksubmit').click(function(){
		           			    	 $('#addPark').submit();
		           					console.log("submit now");
		           					self.commonService.CloseLayer();
		           				});
		           			     $('.parkcancel').click(function(){
		           				  self.commonService.CloseLayer();
		           				});
		           			}
		           		},
		           	];
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		map.addContextMenu(menu);
	}
	
	_addPointMenu(point,id,type){
		let self = this;
		let menu = new BMap.ContextMenu();
		let txtMenuItem=[];
		if(type=='G'){
			txtMenuItem = [
			           		{
			           			text:'修改站点',
			           			callback:function(){
			           				//alert(self.position.lng + "add entrance" + self.position.lat);
			           				//location.href = "/gd/station/update/"+id//
			           				//alert("not implement yet!id:"+id);
			           				//let data = self._getPoint(type,id);
			           				$.ajax({
			           					type : "POST",
			           					url : "/gd/station/get/"+id,
			           					traditional : true,
			           					//我们用text格式接收  
			           					//dataType: "text",   
			           					//json格式接收数据  
			           					dataType : "json",
			           					data : {},
			           					success : function(data) {
			           						self.commonService.options = {
					           			            header: '修改站点',
					           			            content: '<form role="form" id="updateStation" method="POST" action="/gd/station/update">' +
					           			                    '<div class="form-group">' +
					           			                    
					           			                    '<input type="hidden" id="id" name="id" value="'+data.id+'"/>' +
					           			                    '<label for="name">站点名称</label>' +
					           			                    '<input type="text" class="form-control" id="fullName" name="fullName" value="'+ data.fullName +'">' +
					           			                    '<label for="name">站点代号</label>' +
					           			                    '<input type="text" class="form-control" id="alias" name="alias" value="'+ data.alias +'">' +
					           			                    '<label for="name">站点地址</label>' +
					           			                    '<input type="text" class="form-control" id="addr" name="addr" value="'+ data.addr +'">' +
					           			                    '<label for="name">站点简介</label>' +
					           			                    '<input type="text" class="form-control" id="brief" name="brief" value="'+ data.brief +'">' +

					           			                    '</div>'+
					           			                    ' </form>',
					           			            footer: '<button type="button" class="btn btn-default stacancel">取消</button>' +
					           			                    '<button type="button" class="btn btn-primary stasubmit"><span class="glyphicon glyphicon-cloud-upload" id="uploadtitle">添加</span></button>' 
					           			                    
					           			        };
					           			        self.commonService.OpenParentModalLayer();
					           			     $('.stasubmit').click(function(){
					           			    	 $('#updateStation').submit();
					           					console.log("submit now");
					           					self.commonService.CloseLayer();
					           				});
					           			     $('.stacancel').click(function(){
					           				  self.commonService.CloseLayer();
					           				});
			           					},
			           					error : function(xhr, ajaxOptions, thrownError) {

			           					}
			           				});
			           			}
			           		},
			           		{
			           			text:'删除站点',
			           			callback:function(){
			           				location.href = "/gd/station/delete"+id//
			           				console.log('delete station id:'+id);
			           				//Ajax here
			           			}
			           		},
			           	];
		}
		if(type=='P'){
			txtMenuItem = [
        		{
        			text:'修改停车点',
        			callback:function(){
        				//let data = self._getPoint(type,id);
        				$.ajax({
           					type : "POST",
           					url : "/gd/park/get/"+id,
           					traditional : true,
           					//我们用text格式接收  
           					//dataType: "text",   
           					//json格式接收数据  
           					dataType : "json",
           					data : {},
           					success : function(data) {
		           				self.commonService.options = {
		           			            header: '修改停车点',
		           			            content: '<form role="form" id="updatePark" method="POST" action="/gd/park/update">' +
		           			                    '<div class="form-group">' +
		           			                    
		           			                    '<input type="hidden" id="id" name="id" value="'+data.id+'"/>' +
		           			                    '<label for="name">停车点名称</label>' +
		           			                    '<input type="text" class="form-control" id="fullName" name="fullName" value="'+ data.fullName +'">' +
		           			                    '<label for="name">停车点代号</label>' +
		           			                    '<input type="text" class="form-control" id="alias" name="alias" value="'+ data.alias +'">' +
		           			                    '<label for="name">停车点地址</label>' +
		           			                    '<input type="text" class="form-control" id="addr" name="addr" value="'+ data.addr +'">' +
		           			                    '<label for="name">停车点简介</label>' +
		           			                    '<input type="text" class="form-control" id="brief" name="brief" value="'+ data.brief +'">' +
		           			                    '<label for="name">停车点车位总量</label>' +
		           			                    '<input type="text" class="form-control" id="totalLot" name="totalLot" value="'+ data.totalLot +'">' +
		           			               
		           			                    '</div>'+
		           			                    ' </form>',
		           			            footer: '<button type="button" class="btn btn-default parkcancel">取消</button>' +
		           			                    '<button type="button" class="btn btn-primary parksubmit"><span class="glyphicon glyphicon-cloud-upload" id="uploadtitle">添加</span></button>' 
		           			                    
		           			        };
		           			        self.commonService.OpenParentModalLayer();
		           			     $('.parksubmit').click(function(){
		           			    	 $('#updatePark').submit();
		           					console.log("submit now");
		           					self.commonService.CloseLayer();
		           				});
		           			     $('.parkcancel').click(function(){
		           				  self.commonService.CloseLayer();
		           				});
           					},
           					error : function(xhr, ajaxOptions, thrownError) {

		   					}
		   				});
        			}
        		},
        		{
        			text:'删除停车点',
        			callback:function(){
        				location.href = "/gd/park/delete/"+id;
           				console.log('delete park id:'+id);
        				//Ajax here
        			}
        		},
        	];
		}
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		point.addContextMenu(menu);
	}

	_getPoint(pointType,id){
		let pointData;
		let url = "/gd/"+pointType=="P"?"park":"station"+"/get/"+id;
		url = "/gd/park/get/1";
		$.ajax({
			type : "POST",
			url:url,
			//url : "/gd/"+pointType=="P"?"park":"station"+"/get/"+id,
			traditional : true,
			//我们用text格式接收  
			//dataType: "text",   
			//json格式接收数据  
			dataType : "json",
			data : {},
			success : function(data) {
				//return data;
				pointData = data;
			},
			error : function(xhr, ajaxOptions, thrownError) {

			}
		});
		return pointData;
	}

	//useless
	_testAjax(dataArr,id){
		for(let i = 0 ; i < 24 ; i++){
			dataArr.push(Math.round((Math.random() * 10 + 5)));
		}
		//dataArr = [0,0,0,0,0,0,0,0,0,0,0,0,0,5,3,8,2,12,9,5,4,9,6,9];
		//direction = 'IN';
		$.ajax({
			type : "POST",
			url : "/gd/chartdata/update",
			traditional : true,
			//我们用text格式接收  
			//dataType: "text",   
			//json格式接收数据  
			dataType : "json",
			data : {"dataArr":dataArr,"id":id},
			success : function(data) {
			},
			error : function(xhr, ajaxOptions, thrownError) {

			}
		});
	}
	
	//when click the point then show the message about the point
	_setMsgWindow(map,imgPath,addr,brief,title,alias){
	    let content = '<div style="margin:0;line-height:20px;padding:2px;width:380px;">' 
	    	+'<img src="'
	    	+imgPath
	    	+'" alt="" style="width:100%;height:100px;"/>'
	    	+'地址：'+addr
	    	+'<br/>简介：'+brief
	    	+'<br/>代号：'+alias
	    	+'</div>';
	    let searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
				title  : title,      //标题
				//width  : 290,             //宽度
				//height : 105,              //高度
				panel  : "panel",         //检索结果面板
				enableAutoPan : true,     //自动平移
				searchTypes   :[
					BMAPLIB_TAB_SEARCH,   //周边检索
					BMAPLIB_TAB_TO_HERE,  //到这里去
					BMAPLIB_TAB_FROM_HERE //从这里出发
				]
			});
		return searchInfoWindow;
	}
}
export default HomePageService;