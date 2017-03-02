//HomePageService.js

import jq from '../unit/JQueryVendor';
import msgWindow from '../unit/BMap/SearchInfoWindow';

class HomePageService{
	constructor(){
		
	}
	_setMsgWindow(map,img,msg,title){
	    let content = '<div style="margin:0;line-height:20px;padding:2px;width:380px;">' +
        '<img src="http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg" alt="" style="width:100%;height:100px;"/>' +
        '地址：北碚天生街道xx号西南大学一号门<br/>简介：西南大学一号门。' +
      '</div>';
	    var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
				title  : "一号门",      //标题
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