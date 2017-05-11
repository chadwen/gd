
//DemoCtr.js
import jq from '../unit/JQueryVendor';
import echarts from '../unit/ECharts/echarts';
//import CommonService from '../services/CommonService';

class Demo{
	constructor() {
        this.num = 5;
		//this.commonService = new CommonService();
		this.myChart;
		this.optionPIE;
		this.optionBAR;
        
        
        this.initEvent();
    }
	initEvent(){
		let self = this;
		self.myChart = echarts.init(document.getElementById('main'));
		
		self._initGate();
		
		
	}
//--------------------------------------------------------------------------------------------------
	
	
	_initGate(){
		let self = this;
		$.ajax({
            type: "POST",
            cache: false,
	   		//traditional:true,//传数组时应设置为true
            url: "/gd/station/getall",
            dataType: 'json',
            data: {},
            success: function (data) {
            	$("#staIds").html("");
            	$.each(data, function(){
				    console.log(this.id+","+this.brief);
				    $("#staIds").append('<option value="'+this.id+'">'+this.brief+'</option>');
					
				});
				 $("#viewChart").on("click",function(){
					self._generateChart();				
				}); 
            },
		});
	}
	
	_generateChart(){
		let self = this;
		let startdate = $("#startdate").val();
		let enddate = $("#enddate").val();
		let ids = self._getIds();
		let chartType = self._getChartType();
		let categories = self._getCategories();
		let direction = self._getDirection();
		let seriesName = "(出)";
		if(direction == "IN"){
			seriesName = "(入)";
		}
		if(startdate==""){
			console.log('startdate==""');
			alert('起始日期不能为空！');
			return;
		}
		else if(enddate==""){
			console.log('enddate==""');
			alert('终止日期不能为空！');
			return;
		}
		else if(ids==""){
			console.log('ids==""');
			alert('未选择站点！');
			return;
		}
		else if(startdate>enddate){
			console.log('startdate>enddate');
			alert('"起始日期"不能大于"终止日期"！');
			return;
		}
		
		$.ajax({
            type: "POST",
            cache: false,
	   		//traditional:true,//传数组时应设置为true
            url: "/gd/data/getChartData",
            dataType: 'json',
            data: {"ids":ids,"chartType":chartType,"startdate":startdate,"enddate":enddate,"direction":direction},
            success: function (data) {
            	// if success, the data will be a type of List<Integer>.
            	let category = categories;
            	let value = data;
            	if(chartType == "PIE"){
            		self._setPIEChart(category, value, seriesName);
            	}else if(chartType == "BAR"){
            		self._setBARChart(category, value, seriesName);
            	}
            },
		});
	}
	
	_setPIEChart(category,value,seriesName){
		let self = this;
		//self.optionPIE ={};
		self.optionPIE = {
				title : {
					text: '数据饼图',
				    subtext: 'SWU',
			        x:'center'
			    },
			    toolbox:{
								show: true,
								feature: {
							            dataView: {readOnly: true},
							            //restore: {},
							            saveAsImage: {},
							            //magicType: {type: ['line', 'bar']},
							        }
							},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: []
			    },
			    series : [
			        {
			            name: '车流量',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                  /*{value:335, name:'aa'},
			                  {value:310, name:'bb'},
			                  {value:234, name:'cc'},
			                  {value:135, name:'dd'},
			                  {value:0, name:'ee'}*/
			              ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		self.optionPIE.legend.data=category;
		let length = value.length;
		if(category.length > length){
			length=category.length;
		}
		for(let i = 0 ; i < length ; i++){
			let data = {value:value[i],name:category[i]};
			self.optionPIE.series[0].data.push(data);
		}
		self.optionPIE.series[0].name = '车流量'+seriesName;
		self.myChart.setOption(self.optionPIE,true);
		//self.myChart.clearOption();
	}
	_setBARChart(category,value,seriesName){
		let self = this;
		//self.option ={};
		self.optionBAR = {

				color: '#eeeeee',
				title : {
					text: '数据柱状图',
				    subtext: 'SWU',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    toolbox:{
								show: true,
								feature: {
							            dataView: {readOnly: true},
							            //restore: {},
							            saveAsImage: {},
							            //magicType: {type: ['line', 'bar']},
							        }
							},
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : [],
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'车流量',
			            type:'bar',
			            barWidth: '60%',
			            data:[]
			        }
			    ]
			};
		self.optionBAR.xAxis[0].data = category;
		self.optionBAR.series[0].data = value;
		self.optionBAR.series[0].name = '车流量'+seriesName;
		self.myChart.setOption(self.optionBAR,true);
	}
	
	
	_getIds(){
		let self = this;
		let selected = "";
		$('#staIds option:selected').each(function(){
			selected = selected + $(this).val() + ",";
		});
		//console.log(selected);
		//selected = selected.substring(0,selected.length-1);
		//console.log(selected);
		return selected.substring(0,selected.length-1);
	}
	_getCategories(){
		let self = this;
		let categories = [];
		$('#staIds option:selected').each(function(){
			categories.push($(this).text());
		});
		return categories;
	}
	_getChartType(){
		let self = this;
		let selected = "";
		$('#chartType option:selected').each(function(){
			selected = selected + $(this).val() + ",";
		});
		//selected = selected.substring(0, selected.length - 1);
		return selected.substring(0,selected.length-1);
	}
	_getDirection(){
		let self = this;
		let selected = "";
		$('#direction option:selected').each(function(){
			selected = selected + $(this).val() + ",";
		});
		return selected.substring(0,selected.length-1);
	}
	
	
	
	
	//useless
	_demo(){
		let self = this;//avoid unnecessary error from 'this' key word
		console.log('rmrmrm');
		console.log(self.num);
	}
	_put(){
		let self = this;
		let greet = document.createElement('div');
		greet.textContent = "aaaaaaaaaaaaaaaaaaaaaaa Hi there and greetings!";
		document.getElementById('root').appendChild(greet);
	}
	
}
export default Demo;