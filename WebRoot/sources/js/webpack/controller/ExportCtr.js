//ExportCtr.js

import jq from '../unit/JQueryVendor';
import echarts from '../unit/ECharts/echarts';
import CommonService from '../services/CommonService';

class Export{
	constructor(){
		this.commonService = new CommonService();
//		this.myChart;
//		this.option;
//		this.goEasy = new GoEasy({
//            appkey: 'bf8b21fc-dbde-4d1f-9fee-bd1f39641b73'
//        });
		
		
		this.priv='NULLPRIV';
		this.userInfo = null;
		this.active = ['','','','class="active"'];
		
		this.initEvent();
	}
	initEvent(){
		let self = this;

		//initialize chart
		self._initPriv();
		
//		self.goEasy.subscribe({
//	        channel: 'demo_channel',
//	        onMessage: function(message){
//	          alert('收到：'+message.content);
//	          console.log(message.content);
//	        }
//		});
	}
	//****************************************************************************************
	
	_initPriv(){
		let self = this;
		$.ajax({
			type:"POST",
			url:"/gd/user/getUserInfo",
			//dataType:"json",
			data:{},
			success:function(userInfo){
				console.log(userInfo);
				self.priv=userInfo.priv;
				self.userInfo = userInfo;
				self.commonService._generateNavi(userInfo,self.active);		
				
			},
			error:function(){
				console.log("something wrong!!!!!!!!!!");				
			},
		});
	}
}export default Export;