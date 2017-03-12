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
		this.inId;
		this.outId;
		
		
		this.initEvent();
	}
	initEvent(){
		let self = this;
		
		//need to get user id and check privilege.as well as staId
		self.inId = 1;
		self.outId = 2;
		//initialize chart
		self._initChart();
		self._setTimeout();
		
		self.goEasy.subscribe({
	        channel: 'demo_channel',
	        onMessage: function(message){
	          alert('收到：'+message.content);
	          console.log(message.content);
	        }
		});
		
		//++1 at y-axis, when click
		
		$('#trig').on('click',function(){
			let dataOut = self.option.series[0].data;
			++dataOut[dataOut.length-1];
			console.log(dataOut);
			console.log(dataOut.toString());

			self.myChart.setOption(self.option);
			//self.commonService.AlertMessage("message");
		});
		$('#testbtn').on('click',function(){
			console.log(self._getSeconds());
			alert(self._getSeconds());
			location.href = "/gd/user/test";
		});
		$('#chartOut').on('click',function(){
			let dataOut = self.option.series[0].data;
			++dataOut[dataOut.length-1];
			console.log(dataOut);
			console.log(dataOut.toString());
			//let id = 2;
			self._pushData(dataOut.toString(),self.outId);
			self.myChart.setOption(self.option);
		});
		$('#chartIn').on('click',function(){
			let dataIn = self.option.series[1].data;
			++dataIn[dataIn.length-1];
			console.log(dataIn);
			console.log(dataIn.toString());
			//let id = 1;
			self._pushData(dataOut.toString(),self.inId);
			self.myChart.setOption(self.option);
		});
	}
//---------------------------------------------------------------------------
	//_someFunc(){};
	//when initialize the chart, the id must be the id of chartdata record, instead of station
	//so when initialize, check the direction is needed.
	_initChart(){
		let self = this;
		let domm = $('#main');//why can't use '$' to get the dom?
		self.myChart = echarts.init(document.getElementById('main'));
		self._initChartOption();
		//ajax here
//			self.option.series[0].data = dataOut;
//			self.option.series[1].data = dataIn;
//			self.option.xAxis[0].data = dataX;
//			self.myChart.setOption(self.option);
		//
	}
	// used by _initChart
	_initChartOption(){
		let self = this;
		self.option = {
				title: {
					text: '全局动态'
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
		let demoData = [5,3,8,10,5,3,8,18,54,26,45,38,58,67,66,70,80,26,90,43,78,99,100,35];
		let demoData2 = [];
		let demoDataX = [];
		for(let i = 0 ; i < 24 ; i++){
			demoDataX.push(self._getNowFormatDate());
			demoData2.push(Math.round((Math.random() * 10 + 5)));
		}
		self.option.series[0].data = demoData;
		self.option.series[1].data = demoData2;
		self.option.xAxis[0].data = demoDataX;
	}
	
	
	
	_setTimeout(){
		let self = this;
		let seconds = self._getSeconds();
		console.log(seconds);
		setTimeout(function(){
			self._setTimeoutSub();
			self._setInterval();
		},seconds*1000);
	}
	//used by _setTimeout
	_setInterval(){
		let self = this;
		setInterval(function(){
			self._setTimeoutSub();
		},1000*60*60);
	}
	// every hour. used by _setInterval and _setTimeout
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
		self.myChart.setOption(option);
		self._pushData(dataOut.toString(),self.outId);
		self._pushData(dataIn.toString(), self.inId);
	}
	//10 seconds delay used by _setTimeout
	_getSeconds(){
		let second = 0;
		let date = new Date();
		second = 3600-(date.getMinutes()*60+date.getSeconds());
		return second+10;
	}
	//push data to server.
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
	

	
	//return hour number, used by _setInterval and _initChartOption
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
	
}
export default WholeState