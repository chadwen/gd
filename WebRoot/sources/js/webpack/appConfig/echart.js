
var echarts = require('../unit/ECharts/echarts');

var option = {
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
				data:[getNowFormatDate()],
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

var domm = $('#main');
var chart1 = echarts.init(document.getElementById('main'));

chart1.setOption(option);
