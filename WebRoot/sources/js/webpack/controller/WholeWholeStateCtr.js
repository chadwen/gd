//WholeWholeStateCtr.js

import jq from '../unit/JQueryVendor';
import echarts from '../unit/ECharts/echarts';
import CommonService from '../services/CommonService';

class WholeWholeState{
	constructor(){
		this.commonService = new CommonService();
		this.myChart;
		this.option;
		this.goEasy = new GoEasy({
            appkey: 'bf8b21fc-dbde-4d1f-9fee-bd1f39641b73'
        });
		
		
		this.priv='NULLPRIV';
		this.userInfo = null;
		this.active = ['','','class="active"',''];
		
		this.initEvent();
	}
	initEvent(){
		let self = this;

		//initialize chart
		self._initPriv();
		
		self.goEasy.subscribe({
	        channel: 'demo_channel',
	        onMessage: function(message){
	          alert('收到：'+message.content);
	          console.log(message.content);
	        }
		});
		self._initChart();
		
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
	
	_initChart(){
		let self = this;
		let date = new Date();
		let clientHour = date.getHours();		
		let domm = $('#main');//why can't use '$' to get the dom?
		self.myChart = echarts.init(document.getElementById('main'));
		self._initChartOption();
		
		$.ajax({
			type:"POST",
			url:"/gd/chartdata/wholeWholeState",
			dataType:"json",
			data:{"clientHour":clientHour},
			success:function(data){
				if(data!=null){

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
				}
			},
			error:function(){
				console.log("something wrong!!!!!!!!");
				window.location.href = "/gd/user/login";
			}
		});
		
		
	}
	
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
export default WholeWholeState