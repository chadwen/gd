//WholeStateCtr.js
import jq from '../unit/JQueryVendor';
import echarts from '../unit/ECharts/echarts';
import CommonService from '../services/CommonService';

class WholeState{
	constructor(){
		//this.echarts = require('../unit/ECharts/echarts');
		this.commonService = new CommonService();
		this.myChart;
		this.option;
		
		this.goEasy = new GoEasy({
            appkey: 'bf8b21fc-dbde-4d1f-9fee-bd1f39641b73'
        });
		this.channelIN='STATIONCHANNELIN';
		this.channelOUT='STATIONCHANNELOUT';
		this.timeChannel = 'TIMECHANNEL';
		this.inId;
		this.outId;
		this.staId;
		this.priv='NULLPRIV';
		this.userInfo = null;
		this.active = ['','class="active"','',''];
		
		
		this.initEvent();
	}
	initEvent(){
		let self = this;
		
		//need to get user id and check privilege.as well as staId
		self.inId = 1;
		self.outId = 2;
		self.staId = 0 ;
		
		self.staId = $("#saveStaId").text();
		//alert(self.staId);
		//initialize chart
		self._initPriv();
		
		
		
		self.goEasy.subscribe({
	        channel: 'demo_channel',
	        onMessage: function(message){
	          alert('收到：'+message.content);
	          console.log(message.content);
	        }
		});
		
		//++1 at y-axis, when click
		
		$('#trig').on('click',function(){
			
			self._tesajax();


			//self.commonService.AlertMessage("message");
		});
		$('#testbtn').on('click',function(){
			console.log(self._getSeconds());
			alert(self._getSeconds());
			location.href = "/gd/user/test";
		});

	}
//---------------------------------------------------------------------------
	//_someFunc(){};
	
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
				self._initChart();
				
			},
			error:function(){
				console.log("something wrong!!!!!!!!!!");				
			},
		});
	}
	
	//when initialize the chart, the id must be the id of chartdata record, instead of station
	//so when initialize, check the direction is needed.
	// called in _initPriv()
	_initChart(){
		let self = this;
		let date = new Date();
		let clientHour = date.getHours();
		
		let domm = $('#main');//why can't use '$' to get the dom?
		self.myChart = echarts.init(document.getElementById('main'));
		self._initChartOption();

		$.ajax({
			type:"POST",
			//async:false,
			url:"/gd/chartdata/wholeState",
			dataType:"json",
			data:{"clientHour":clientHour,"staId":self.staId},
			success:function(data){
				if(data!=null){
					console.log("success!");
					/*console.log(data[0]);
					console.log(data[1]);
					console.log(data[2]);*/
					self.option.title.text = self.option.title.text + "("+data.brief+")";
					self.option.series[0].data = data.outDataList;
					self.option.series[1].data = data.inDataList;
					let xdata = [];
					for(let i = 0 ; i<24;i++){
						
						if(data.hourList[i]<10){
							xdata.push("0"+data.hourList[i]);
						}else{
							xdata.push(""+data.hourList[i]);
						}
					}
					self.option.xAxis[0].data = xdata;
					self.myChart.setOption(self.option);
					self.outId = data.ids[0];
					self.inId = data.ids[1];
					//self.staId = data.staId;
					if(self.userInfo.staId != self.staId){
//						$('#chartOut').remove();
//						$('#chartIn').remove();
//						$('#chartOut').css("style","display:none");
//						$('#chartIn').css("style","display:none");
					}else{
						$("#btnContain").append('<button class="btn" id="chartOut">OUT</button>&nbsp;&nbsp;');
						$("#btnContain").append('<button class="btn" id="chartIn">IN</button>');
						$("#btnContain").append();
						self._initBtn();
						self._setTimeout();
					}
					self.channelIN = self.channelIN + self.staId;
					self.channelOUT = self.channelOUT + self.staId;
					self.timeChannel = self.timeChannel + self.staId;
					self._monitorChange();
				}else{
					location.href="/gd/user/login";
				}
			},
			error:function(){
				console.log("error!");
				location.href="/gd/user/login";
			},
			complete:function(){
				console.log("complete!");
			},
		
		});
	}
	// called in _initChart
	_initChartOption(){
		let self = this;
		self.option = {
				title: {
					text: '站点动态'
				},
				tooltip: {
					trigger: 'axis',
				},
				toolbox:{
					show: true,
					feature: {
				            dataView: {readOnly: true},
				            //restore: {},
				            saveAsImage: {},
				            magicType: {type: ['line', 'bar']},
				        }
				},
				legend: {
					data:['出车流', '入车流']
				},
				//this is x-axis, the array has only one object,that means one serious elements(on the button)
				xAxis: [
					{
						type: 'category',
						boundaryGap: true,
						data:[self._getNowFormatDate()],
						splitNumber:10,
						
					}
				],
				//this is y-axis, the same as x-axis
				yAxis: [
					{
						type: 'value',
						boundaryGap: [0, '100%'],
						splitLine: {
							show: false
						},
						axisLabel: {
				            formatter: '{value} 辆'
				        },

					},
				],
				series: [
					{
						name:'出车流',
						type:'line',
						data:[0],
						markPoint: {
			                data: [
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}

			                ]},
			                markLine: {
			                    data: [
			                        {type: 'average', name: '平均值'}
			                    ]
			                }

					},
					{
						name:'入车流',
						type:'line',
						data:[0],
						markPoint: {
			                data: [
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}

			                ]},
			                markLine: {
			                    data: [
			                        {type: 'average', name: '平均值'}
			                    ]
			                }
					},
					
					]
			};
		
		// will be useless
		/*let demoData = [5,3,8,10,5,3,8,18,54,26,45,38,58,67,66,70,80,26,90,43,78,99,100,35];
		let demoData2 = [];
		let demoDataX = [];
		for(let i = 0 ; i < 24 ; i++){
			demoDataX.push(self._getNowFormatDate());
			demoData2.push(Math.round((Math.random() * 10 + 5)));
		}
		self.option.series[0].data = demoData;
		self.option.series[1].data = demoData2;
		self.option.xAxis[0].data = demoDataX;
		self.myChart.setOption(self.option);*/
	}
	// called in _initChart
	_initBtn(){
		let self = this;
		$('#chartOut').bind('click',function(){
			let dataOut = self.option.series[0].data;
			++dataOut[dataOut.length-1];
			console.log(dataOut);
			console.log(dataOut.toString());
			//let id = 2;
			self._pushData(dataOut.toString(),self.outId);
			
			self._publishGoEasy(self.channelOUT,self._arrayToString(dataOut,","));
			//self.myChart.setOption(self.option);
		});
		$('#chartIn').bind('click',function(){
			let dataIn = self.option.series[1].data;
			++dataIn[dataIn.length-1];
			console.log(dataIn);
			console.log(dataIn.toString());
			//let id = 1;
			self._pushData(dataIn.toString(),self.inId);
			
			
			self._publishGoEasy(self.channelIN, self._arrayToString(dataIn,","));
			
			//self.myChart.setOption(self.option);
		});
	}
	//push data to server. called in _initBtn()
	_pushData(datas,id){
		let self = this;
		let date = new Date();
		let currHour = date.getHours()
		$.ajax({
			type:"POST",
			url:"/gd/chartdata/update/"+id,
			dataType:"json",
			data:{"datas":datas,"currHour":currHour},
			success:function(data){},
			error:function(){},
			complete:function(){},
		});
	}

	
	// use goeasy to publish change data,called in _setTimeoutSub() and _initBtn()
	_publishGoEasy(channel,data){
		let self = this;
		self.goEasy.publish({
            channel: channel,
            message: data
        });
	}
	// receive change from specific channel, called in _initChart()
	_monitorChange(){
		let self = this;
		self.goEasy.subscribe({
	        channel: self.channelOUT,
	        onMessage: function(message){
	          //alert('收到：'+message.content);
	          console.log(message.content);
	          let outData = self._stringToArray(message.content,",");
	          self.option.series[0].data = outData;
	          self.myChart.setOption(self.option);
	          
	        }
		});
		self.goEasy.subscribe({
	        channel: self.channelIN,
	        onMessage: function(message){
	          //alert('收到：'+message.content);
	          console.log(message.content);
	          let inData = self._stringToArray(message.content,",");
	          self.option.series[1].data = inData;
	          self.myChart.setOption(self.option);
	        }
		});
		//set xAxis data
		self.goEasy.subscribe({
	        channel: self.timeChannel,
	        onMessage: function(message){
	          //alert('收到：'+message.content);
	          console.log(message.content);
	          let timeData = self._stringToArray(message.content,",");
	          self.option.xAxis[0].data = timeData;
	          self.myChart.setOption(self.option);
	        }
		});
	}
	// transform string into array
	_stringToArray(str,separator){
		let arr = str.split(separator);
		return arr;
	}
	// transform array into string
	_arrayToString(arr,separator){
		let str = arr.join(separator);
		return str;
	}
	
	
	
	//called in _initChart
	_setTimeout(){
		let self = this;
		let seconds = self._getSeconds();
		console.log(seconds);
		setTimeout(function(){
			self._setTimeoutSub();
			self._setInterval();
		},seconds*1000);
	}
	//called in _setTimeout
	_setInterval(){
		let self = this;
		setInterval(function(){
			self._setTimeoutSub();
		},1000*60*60);
	}
	// every hour. called in _setInterval and _setTimeout
	_setTimeoutSub(){
		let self = this;
		let option = self.option;
		let num = 24;
		let dataOut = option.series[0].data;
	    let dataIn = option.series[1].data;
		if(dataOut.length >= num){
			dataOut.shift();//delete first number
			}
		if(dataIn.length >= num){
			dataIn.shift();
			}
		
		dataOut.push(Math.round((Math.random() * 10 + 5)));
		dataIn.push(Math.round((Math.random() * 10 + 5)));
		
		if(option.xAxis[0].data.length >= num){
			option.xAxis[0].data.shift(); 	
		}
		option.xAxis[0].data.push(self._getNowFormatDate());
		
		self._publishGoEasy(self.channelOUT, self._arrayToString(dataOut,","));
		self._publishGoEasy(self.channelIN,self._arrayToString(dataIn,","));
		self._publishGoEasy(self.timeChannel, self._arrayToString(option.xAxis[0].data,","));
		//self.myChart.setOption(option);
		
		
		self._pushData(dataOut.toString(),self.outId);
		self._pushData(dataIn.toString(), self.inId);
	}
	//10 seconds delay called in _setTimeout
	_getSeconds(){
		let second = 0;
		let date = new Date();
		second = 3600-(date.getMinutes()*60+date.getSeconds());
		return second+10;
	}
	//return hour number, called in _setInterval and _initChartOption
	_getNowFormatDate(){
		let date = new Date();
		let seperator1 = "-";
		let seperator2 = ":";
		let month = date.getMonth() + 1;
		let strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    let currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    let dateandtime = strDate+"-" +((date.getHours()>=0&&date.getHours()<=9)?"0"+date.getHours():date.getHours());
	    let times = ((date.getHours()>=0&&date.getHours()<=9)?"0"+date.getHours():date.getHours());
	    return times;
	    //return date.getMinutes();
	}

	
//-----------------uesless-------------------------------------------------------
	//test method useless now
	_tesajax(){
		let self = this;
		$.ajax({
			type:"POST",
			url:"/gd/chartdata/wholeState",
			dataType:"json",
			data:{},
			success:function(data){
				console.log("success!");
				console.log(data[0]);
				console.log(data[1]);
				console.log(data[2]);
				
			},
			error:function(){
				console.log("error!");
			},
			complete:function(){
				console.log("complete!");
			},
		
		});
	}
}
export default WholeState