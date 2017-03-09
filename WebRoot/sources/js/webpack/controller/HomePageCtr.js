//HomePageCtr.js

import hpService from '../services/HomePageService';
import CommonService from '../services/CommonService';

class HomePage{
	constructor(){
		this.commonService = new CommonService();
		this.hpservice = new hpService();
		this.position = null;
		
		this.map = new BMap.Map("swuMap");
		this.initEvent();
	}
	initEvent(){
		let self = this;
		let map = this.map;
		self._initMap();

		//self.hpservice._testAjax([],1);
		//self.hpservice._testAjax([],2);

		self._initPoint();
		
		
	}
	
	_initMap(){
		let self = this;
		let map = self.map;
		let point = new BMap.Point(106.428907,29.826584);
		map.centerAndZoom(point, 16);
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.setCurrentCity("cq");          // 设置地图显示的城市 此项是必须设置的。没看出来
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		
		//坐标拾取
		map.addEventListener("click",function(e){
			$('#coordinate').html(e.point.lng + ' , ' + e.point.lat);
			
			//alert(e.point.lng + "," + e.point.lat);
		});
		map.addEventListener("rightclick",function(e){
			let id = 3;
			$('#coordinate').html(e.point.lng + ' , ' + e.point.lat);
			self.hpservice._addMapMenu(map,e.point.lng,e.point.lat,id);
			self.position = e.point;
			//alert(e.point.lng + "," + e.point.lat);
		});
	}
	
	//Ajax initialize the points in the map
	_initPoint(){
		let self = this;
		$.ajax({
			type : "POST",
			url : "/gd/map/initStation",
			traditional : true,
			dataType : "json",
			data : {},
			success : function(data) {
				console.log('data.length:'+data.length);
				self._setPoint(data,'G');
			},
			error : function(xhr, ajaxOptions, thrownError) {

			},
			complete:function(data){
				//console.log('data.length:'+data.length);
				//self._initMap();
			}
		});
		$.ajax({
			type : "POST",
			url : "/gd/map/initPark",
			traditional : true,
			dataType : "json",
			data : {},
			success : function(data) {
				console.log('data.length:'+data.length);
				self._setPoint(data,'P');
			},
			error : function(xhr, ajaxOptions, thrownError) {

			},
			complete:function(data){
				//console.log('data.length:'+data.length);
				//self._initMap();
			}
		});
	}
	
	//for _initAjax()
	_setPoint(data,type){
		let self = this;
		let map = this.map;
		$.each(data,function(index,obj){
			console.log(obj.id);
			
			let point1 = new BMap.Point(parseFloat(obj.posx) , parseFloat(obj.posy));
			let entrance1 = new BMap.Marker(point1);  // 创建标注
			map.addOverlay(entrance1);              // 将标注添加到地图中
			//entrance1.setAnimation(BMAP_ANIMATION_BOUNCE);
			//if(type=='G'){
				entrance1.addEventListener("click",function showWindow(){
					//let p = entrance1.getPosition();       //获取marker的位置
					//alert("centerLib的位置是" + p.lng + "," + p.lat);  
					let imgPath = obj.imgPath;//'http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg';//
					let addr = obj.addr;//'北碚天生街道xx号西南大学一号门';//
					let brief = obj.brief;//'西南大学一号门。';//obj.brief;
					let title = obj.fullName;//"一号门";//
					let alias = obj.alias;
					self.hpservice._setMsgWindow(map,imgPath,addr,brief,title,alias).open(point1); ;
				});//绑定监听器
				entrance1.addEventListener("rightclick",function showWindow(){
					let id = 2;
					self.hpservice._addPointMenu(entrance1,obj.id,type);
				});
			//}
		});
	}
	
	
	
	
	//useless
	_initMapOld(){
		let self = this;
		let map = self.map;
		
/*		let point = new BMap.Point(106.428907,29.826584);
		map.centerAndZoom(point, 16);
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.setCurrentCity("cq");          // 设置地图显示的城市 此项是必须设置的。没看出来
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		
		//坐标拾取
		map.addEventListener("click",function(e){
			$('#coordinate').html(e.point.lng + ' , ' + e.point.lat);
			
			//alert(e.point.lng + "," + e.point.lat);
		});
		map.addEventListener("rightclick",function(e){
			let id = 3;
			$('#coordinate').html(e.point.lng + ' , ' + e.point.lat);
			self.hpservice._addMapMenu(map,e.point.lng,e.point.lat,id);
			self.position = e.point;
			//alert(e.point.lng + "," + e.point.lat);
		});*/
		
		//一号门 _setMsgWindow
		let posx = 106.435303000;
		let point1 = new BMap.Point(parseFloat(posx) , 29.827845000);
		let entrance1 = new BMap.Marker(point1);  // 创建标注
		map.addOverlay(entrance1);              // 将标注添加到地图中
		entrance1.setAnimation(BMAP_ANIMATION_BOUNCE);
		entrance1.addEventListener("click",function showWindow(){
			//let p = entrance1.getPosition();       //获取marker的位置
			//alert("centerLib的位置是" + p.lng + "," + p.lat);  
			let img = '<img src="http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg" alt="" style="width:100%;height:100px;"/>';
			let msg = '地址：北碚天生街道xx号西南大学一号门<br/>简介：西南大学一号门。';
			let title = "一号门";
			self.hpservice._setMsgWindow(map,img,msg,title).open(point1); ;
		});//绑定监听器
		entrance1.addEventListener("rightclick",function showWindow(){
			let id = 2;
			self.hpservice._addPointMenu(entrance1,id);
		});
//		function showWindow(){
//			let p = entrance1.getPosition();       //获取marker的位置
//			//alert("centerLib的位置是" + p.lng + "," + p.lat);  
//			self.hpservice._setMsgWindow("","","","");
//		}
		//七号门
		let entrance7 = new BMap.Marker(new BMap.Point(106.437998 , 29.837017));  // 创建标注
		map.addOverlay(entrance7);// 将标注添加到地图中
		entrance7.setAnimation(BMAP_ANIMATION_BOUNCE);
		//五号门
		let entrance5 = new BMap.Marker(new BMap.Point(106.440639 , 29.835051));  // 创建标注
		map.addOverlay(entrance5);              // 将标注添加到地图中
		entrance5.setAnimation(BMAP_ANIMATION_BOUNCE);
		//三号门
		let entrance3 = new BMap.Marker(new BMap.Point(106.432177 , 29.824649));  // 创建标注
		map.addOverlay(entrance3);              // 将标注添加到地图中
		entrance3.setAnimation(BMAP_ANIMATION_BOUNCE);
		//二号门
		let entrance2 = new BMap.Marker(new BMap.Point(106.428143 , 29.819847));  // 创建标注
		map.addOverlay(entrance2);              // 将标注添加到地图中
		entrance2.setAnimation(BMAP_ANIMATION_BOUNCE);
		//六号门
		let entrance6 = new BMap.Marker(new BMap.Point(106.423315 , 29.817184));  // 创建标注
		map.addOverlay(entrance6);              // 将标注添加到地图中
		entrance6.setAnimation(BMAP_ANIMATION_BOUNCE);
		//八号门
		let entrance8 = new BMap.Marker(new BMap.Point(106.419385 , 29.817043));  // 创建标注
		map.addOverlay(entrance8);              // 将标注添加到地图中
		entrance8.setAnimation(BMAP_ANIMATION_BOUNCE);
		//中图
		let centerLib = new BMap.Marker(new BMap.Point(106.4314 , 29.82658));  // 创建标注
		map.addOverlay(centerLib);              // 将标注添加到地图中
		centerLib.addEventListener("click",getAttr);//绑定监听器
		function getAttr(){
			let p = centerLib.getPosition();       //获取marker的位置
			alert("centerLib的位置是" + p.lng + "," + p.lat);   
		}
		
		//八教
		let eight_building = new BMap.Marker(new BMap.Point(106.432752 , 29.828918));  // 创建标注
		map.addOverlay(eight_building);              // 将标注添加到地图中
			//八教
		let park1 = new BMap.Marker(new BMap.Point(106.427245 , 29.827375));  // 创建标注
		map.addOverlay(park1);              // 将标注添加到地图中
			//八教
		let park2 = new BMap.Marker(new BMap.Point(106.424811 , 29.822918));  // 创建标注
		map.addOverlay(park2);              // 将标注添加到地图中
			//八教
		let park3 = new BMap.Marker(new BMap.Point(106.432716 , 29.8326));  // 创建标注
		map.addOverlay(park3);              // 将标注添加到地图中
			//八教
		let park4 = new BMap.Marker(new BMap.Point(106.438213 , 29.83213));  // 创建标注
		map.addOverlay(park4);              // 将标注添加到地图中
	}

}
export default HomePage