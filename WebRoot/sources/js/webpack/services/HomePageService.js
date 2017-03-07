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
		           				//alert(self.position.lng + "add entrance" + self.position.lat);
		           				//self._preAddStation(self.position.lng+"",self.position.lat+"");
		           				//location.href = "/gd/station/preAdd?posx="+posx+"&posy=" + posy;//
		           				/*layer.msg("this is the message!!!!!!!");
		           				layer.open({
		           				  type: 2,
		           				  title: 'iframe父子操作',
		           				  maxmin: true,
		           				  shadeClose: true, //点击遮罩关闭层
		           				  area : ['800px' , '520px'],
		           				  content: 'http://layer.layui.com/mobile/' //iframe的url
		           				  });*/
		           				//self.commonService.AlertMessage("未选择任何文件");//弹窗提示
		           				self.commonService.options = {
		           			            header: '添加文件头 文件尾',
		           			            content: '<form role="form" id="ExcelForm" action="/Admin_Areas/Document/getDesc">' +
		           			                    ' <div class="form-group">' +
		           			                    ' <label for="inputfile">添加</label></br>' + 
		           			                    ' <input type="file" id="inputfile" >' +
		           			                    '<div id="DivDescrip" style="display:none"><label for="description">描述 </label>' +
		           			                    '<input type="text" class="form-control" id="description" name="description" placeholder=默认为>' +
		           			                    '<label style="display:none" for="endPart" >尾端</label>' +
		           			                    '<input style="display:none" type="text" class="form-control" id="endPart" name="endPart" ></div>' +
		           			                    '<input type="hidden" id="FileID" name="FileID" />' +
		           			                    '<input name="IsHead" id="IsHead" value="" type="hidden"/>'+
		           			                    ' </form>',
		           			            footer: '<div> <input type="hidden" id="url" />' +
		           			                    '<button type="button" class="btn btn-default btncancel">取消</button>' +
		           			                    '<a class="btn btn-primary btnsubmit" data-dismiss="modal">' +
		           			                    '<span class="glyphicon glyphicon-cloud-upload" id="uploadtitle">提交</span> </a>' +
		           			                    '</div>',
		           			        };
		           			        self.commonService.OpenParentModalLayer();
		           			}
		           		},
		           		{
		           			text:'添加停车场',
		           			callback:function(){
		           				alert("not implement yet");
		           				//self._preAddStation(self.position.lng,self.position.lat);
		           				location.href = "/gd/station/preAdd?posx="+posx+"&posy=" + posy;//
		           			}
		           		},
		           	];
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		map.addContextMenu(menu);
	}
	
	_addPointMenu(point,id){
		let self = this;
		let menu = new BMap.ContextMenu();
		let txtMenuItem = [
		           		{
		           			text:'修改站点',
		           			callback:function(){
		           				//alert(self.position.lng + "add entrance" + self.position.lat);
		           				location.href = "/gd/station/update?id="+id//
		           				alert("not implement yet!id:"+id);
		           				
		           				//Ajax here
		           			}
		           		},
		           		{
		           			text:'删除站点',
		           			callback:function(){
		           				location.href = "/gd/station/delete?id="+id//
		           				alert("not implement yet!id:"+id);
		           				//Ajax here
		           			}
		           		},
		           	];
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		point.addContextMenu(menu);
	}
	

	
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
	
	_setMsgWindow(map,imgPath,addr,brief,title){
	    let content = '<div style="margin:0;line-height:20px;padding:2px;width:380px;">' 
	    	+'<img src="'
	    	+imgPath
	    	+'" alt="" style="width:100%;height:100px;"/>'
	    	+'地址：'+addr
	    	+'<br/>简介：'+brief
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