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
		this.stations = null;
		
		
		this.initEvent();
	}
	initEvent(){
		let self = this;
		
		$('#singleData').on("click",function(){
			self.commonService.Loading();
	  		$('#iframeContent').attr("src","/gd/sources/htmls/sonpages/download_single.html");
	  		
	  	});
		$('#calculateData').on("click",function(){
			self.commonService.Loading();
	  		$('#iframeContent').attr("src","/gd/sources/htmls/sonpages/download_cal.html");
	  	});
		$('#clearStatus').on('click',function(){
    		self._clearStatue();
		});
		$('#resetpwd').on('click',function(){
			self._resetpwd();
		});
		/*$('#staInfo').on("click",function(){
			location.href = "/gd/data/getAllStations";
		});*/
		let iframe = $('#iframeContent');
		if (iframe.attachEvent){
		    iframe.attachEvent("onload", function(){
		        console.log("loading done");
		        self.commonService.CloseLoading();
		    });
		} else {
		    iframe.on("load",function(){
		    	console.log("loading done");
		        self.commonService.CloseLoading();
		    });
		}
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
            beforeSend:function(){
            	//self.commonService.LoadingOpaque();
            },			
			success:function(userInfo){
				console.log(userInfo);
				self.priv=userInfo.priv;
				self.userInfo = userInfo;
				self.commonService._generateNavi(userInfo,self.active);		
				
			},
			error:function(){
				console.log("something wrong!!!!!!!!!!");				
			},
            complete:function(){
		        //self.commonService.CloseLoading();
		    },
		});
	}

	
	_clearStatue(){//
		let self = this;
		$.ajax({
            type: "POST",
            cache: false,
	   		//traditional:true,//传数组时应设置为true
            url: "/gd/data/getLoginedStation",
            dataType: 'json',
            data: {},
            beforeSend:function(){
            	self.commonService.Loading();
            },
            success: function (data) {
            	self._generateLayout(data);
            },
            complete:function(){
		        self.commonService.CloseLoading();
		    },
		});
	}
	
	_generateLayout(data){
		let self = this;
		let options = '';
		$.each(data,function(){
			options = options + '<option value="'+this.userId+'">'+this.brief+'</option>'
		});
		self.commonService.options={
				header: '清除登录状态',
				content:'<label for="name">请选择需要清除登录状态的站点</label>'+
					'<select id="clearSta" class="form-control">'+
						options+
					'</select>',
				footer: '<button type="button" class="btn btn-default clearCancel">取消</button>' +
	                    '<button type="button" class="btn btn-primary clearSubmit">清除</button>'                 
	        };
	     self.commonService.OpenParentModalLayer();
	     $('.clearSubmit').click(function(){
	    	 //$('#addStation').submit();
	    	 let userId = $('#clearSta option:selected').val();
	    	 $.post("/gd/user/clearStatus/"+userId,{},function(data,status){
	    		 console.log('success');
	    		 console.log(data);
	    	 });
			 self.commonService.CloseLayer();
			 alert("清除成功!");
		});
	     $('.clearCancel').click(function(){
	    	self.commonService.CloseLayer();
		});
	}
	
	
	_resetpwd(){
		let self = this;
		$.ajax({
            type: "POST",
            cache: false,
	   		//traditional:true,//传数组时应设置为true
            url: "/gd/station/getall",
            dataType: 'json',
            data: {},
            success: function (data) {
            	self._resetpwdSub(data);
            },
		});
	}
	_resetpwdSub(data){
		let self = this;
		let options = '';
		$.each(data,function(){
			options = options + '<option value="'+this.userId+'">'+this.brief+'</option>'
		});
		self.commonService.options={
				header: '重置账户密码',
				content:'<label for="name">请选择需要重置账户密码的站点</label>'+
					'<select id="resetId" class="form-control">'+
						options+
					'</select>',
				footer: '<button type="button" class="btn btn-default resetCancel">取消</button>' +
	                    '<button type="button" class="btn btn-primary resetSubmit">重置</button>'                 
	        };
	     self.commonService.OpenParentModalLayer();
	     $('.resetSubmit').click(function(){
	    	 //$('#addStation').submit();
	    	 let staId = $('#resetId option:selected').val();
	    	 $.post("/gd/user/resetpwd/"+userId,{},function(data,status){
	    		 console.log('success');
	    		 console.log(data);
	    	 });
			 self.commonService.CloseLayer();
			 alert("重置成功!");
		});
	     $('.resetCancel').click(function(){
	    	self.commonService.CloseLayer();
		});
		
	}
	
}export default Export;