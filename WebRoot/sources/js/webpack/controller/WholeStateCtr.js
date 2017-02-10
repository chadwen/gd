//WholeStateCtr.js
import jq from '../unit/JQueryVendor';
import echarts from '../unit/ECharts/echarts';

class WholeState{
	constructor(){
		//this.echarts = require('../unit/ECharts/echarts');
		this.myChart;
		this.option;
		
		
		this.initEvent();
	}
	initEvent(){
		let self = this;
		
		self._initChart();
		
		self._setInterval();
		
		//the same as ++1 at y-axis
		$('#trig').on('click',function(){
			var data = self.option.series[0].data;
			++data[data.length-1];
			self.myChart.setOption(self.option);
		});
	}
//---------------------------------------------------------------------------
	
	//_someFunc(){};
	_setInterval(){
		let self = this;
		let option = self.option;
		setInterval(function(){
			var data0 = option.series[0].data;
		    var data1 = option.series[1].data;
		    var data2 = option.series[2].data;
			if(data0.length >= 10){
				data0.shift();//delete first number
				}
			if(data1.length >= 10){
				data1.shift();
				}
			if(data2.length >= 10){
				data2.shift();
				}
			data0.push(Math.round((Math.random() * 10 + 5)));
			data1.push(Math.round((Math.random() * 10 + 5)));
			data2.push(Math.round((Math.random() * 10 + 5)));
			
			if(option.xAxis[0].data.length >= 10){
				option.xAxis[0].data.shift(); 	
			}
			//if(option.xAxis[1].data.length >= 10){
			//	option.xAxis[1].data.shift();
			//}
			option.xAxis[0].data.push(self._getNowFormatDate());
			
			//option.xAxis[1].data.push(count++);
			self.myChart.setOption(option);
		},10000);
		
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
	    let cur = (date.getHours()>=0&&date.getHours()<=9)?"0"+date.getHours():date.getHours();
	    return cur;
	}

	_initChart(){
		let self = this;
		let domm = $('#main');//why can't use '$' to get the dom?
		self.myChart = echarts.init(document.getElementById('main'));
		self._initChartOption();
		self.myChart.setOption(self.option);
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
				legend: {
					data:['车流量1', '车流量2', '车流量3']
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
						}
					},
				],
				series: [
					{
						name:'车流量1',
						type:'line',
						data:[0]
					},
					{
						name:'车流量2',
						type:'line',
						data:[0]
					},
					{
						name:'车流量3',
						type:'line',
						data:[0]
					},
					]
			};
	}
	
}
export default WholeState