/**
 * 
 */

var tools = {
		viewer:null,
		Tiles:[],
		dataSources:[],
		toolbar:false,
		init:function(){
			layer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,time:1000000}) ;
			Cesium.BingMapsApi.defaultKey = 'Ap2z3Su_tKy7X9Zdy5EuFqzLmEa6bPKtLCS8_Gnsq-btg91SoZDGTto22NoEO4FB';
			/**
			var terrainProvider = new Cesium.CesiumTerrainProvider( {  
			    url : '//assets.agi.com/stk-terrain/world',
			    requestVertexNormals : true
			} );  
			*/
			var vrTheWorldProvider = new Cesium.VRTheWorldTerrainProvider({
				   url : 'http://www.vr-theworld.com/vr-theworld/tiles1.0.0/73/',
				   credit : 'Terrain data courtesy VT MÄK'
				});
			var imageryProviderimg_w= new Cesium.WebMapTileServiceImageryProvider({
		        url: "http://webst01.is.autonavi.com/appmaptile?style=6&x={TileCol}&y={TileRow}&z={TileMatrix}",
		        layer: "tdtBasicLayer",
		        style: "default",
		        format: "image/jpeg",
		        tileMatrixSetID: "GoogleMapsCompatible",
		        show: false
		    })
			var imageryProvidercia_w= new Cesium.WebMapTileServiceImageryProvider({
		        url: "http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles",
		        layer: "tdtBasicLayer",
		        style: "default",
		        format: "image/jpeg",
		        tileMatrixSetID: "GoogleMapsCompatible",
		        show: false
		    })
			tools.viewer = new Cesium.Viewer('cesiumContainer',{
				timeline:true,
				animation:true,
				infoBox:true
			});
			//tools.viewer.extend(Cesium.viewerCesiumNavigationMixin, {});
			tools.viewer.terrainProvider = vrTheWorldProvider;
			//tools.viewer.imageryLayers.addImageryProvider(imageryProviderimg_w);
			tools.viewer.imageryLayers.addImageryProvider(imageryProvidercia_w);
			tools.initEvent();
			measure.init(tools.viewer);
			var iframe = document.getElementsByClassName('cesium-infoBox-iframe')[0];
			iframe.setAttribute('sandbox', 'allow-same-origin allow-scripts allow-popups allow-forms'); 
			$("#toolspage").hide();
			layer.msg('加载完成！',{time: 1000});
		},
		initEvent:function(){
			//地图鼠标入口
			tools.getPosition();
			//其他鼠标入口
			layui.use(['util','element','layer'], function(){
				  var element = layui.element;
				  var layer = layui.layer;
				  var util = layui.util;
				  //监听导航点击
				  element.on('nav(demo)', function(elem){
					  debugger;
				    switch (elem[0].id){
				    	case 'newyork':
				    		tools.add3DTiles({
								url:'https://cesiumjs.org/NewYork/3DTilesGml'
							},elem[0].id);
				    		tools.moveTo(-73.9740,40.7725);
				    		break;
				    	case 'guibo':
				    		tools.add3DTiles({
				    		    url : 'https://beta.cesium.com/api/assets/1458?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxYmJiNTAxOC1lOTg5LTQzN2EtODg1OC0zMWJjM2IxNGNlYmMiLCJpZCI6NDQsImFzc2V0cyI6WzE0NThdLCJpYXQiOjE0OTkyNjM4MjB9.1WKijRa-ILkmG6utrhDWX6rDgasjD7dZv-G5ZyCmkKg'
				    		},elem[0].id);
				    		tools.moveTo(-75.597145,40.02868);
				    		break;
				    	case 'qingxie':
				    		tools.addGeojson('./asserts/json/CHN.json',elem[0].id);
				    		break;
				    	case 'bendi':
				    		tools.addModel(elem[0].id);
				    		break;
				    	case 'abuilding':
				    		tools.addModelgltf(elem[0].id);
				    		break;
				    	case 'sdeshape':
				    		tools.addSdeShape('./rest/gps/getGeoData?table=fq_zy_pg',elem[0].id);
				    		break;
				    }
				  });
				  element.on('nav(top)', function(elem){
					    switch (elem[0].id){
					    	case 'cl':
					    		measure.measurebase();
					    		break;
					    }
					  });
				  //执行
				  util.fixbar({
				    bar1: true,
				    bar2: true,
				    css: {bottom: 50},
				    click: function(type){
				      console.log(type);
				      if(type === 'bar1'){
				    	  layer.msg('只想弱弱提示');
				      }
				      if(type === 'bar1'){
				    	  layer.msg('只想弱弱提示');
				      }
				    }
				  });
				  $('#chutiyan').on('click', function(e){
					  if(!tools.toolbar){
						  $("#toolspage").show();
						  $(e.currentTarget).children(".layui-icon").html("&#xe671;");
						  tools.toolbar = true;
					  }else{
						  $("#toolspage").hide();
						  $(e.currentTarget).children(".layui-icon").html("&#xe65f;");
						  tools.toolbar = false;
					  }
				  });
				});
		},
		getPosition:function() {
			 //得到当前三维场景
			 var scene = tools.viewer.scene;
			 var ellipsoid = scene.globe.ellipsoid;
			 var longitudeString = null;
			 var latitudeString = null;
			 var height = null;
			 var carmeheight = null;
			 var cartesian = null;
			 // 定义当前场景的画布元素的事件处理
			 var handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
			 //设置鼠标移动事件的处理函数，这里负责监听x,y坐标值变化
			 handler.setInputAction(function(movement) {
			 //通过指定的椭球或者地图对应的坐标系，将鼠标的二维坐标转换为对应椭球体三维坐标
				 //cartesian = viewer.camera.pickEllipsoid(movement.endPosition, viewer.scene.globe.ellipsoid);
				 cartesian = tools.viewer.camera.pickEllipsoid(movement.endPosition, ellipsoid);
				 if (cartesian) {
					 //将笛卡尔坐标转换为地理坐标
					 var cartographic = ellipsoid.cartesianToCartographic(cartesian);
					 //将弧度转为度的十进制度表示
					 longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(8);
					 latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(8);
					 //height = Cesium.Math.toDegrees(cartographic.height).toFixed(8);
					 var height=scene.globe.getHeight(cartographic);
					 height = height.toFixed(2);
					 cartographic.height=height;
					 //获取相机高度
					 carmeheight = Math.ceil(tools.viewer.camera.positionCartographic.height).toFixed(2);
	
					 $('#lanlat').text("经度:"+longitudeString+";纬度:"+latitudeString);
					 $('#height').text("高程:"+height+";相机高度:"+carmeheight);
				 }else {
					 
				 }
			 }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);
		},
		moveTo:function(lon,lat){
	        tools.viewer.scene.camera.setView({
	            destination: Cesium.Cartesian3.fromDegrees(lon,lat-0.003,1500),
	            orientation:{
	                heading : 0.0,
	                pitch:-0.75,
	                rool:0.1
	            }
	        });
		},
		addModelgltf:function(id){
			var entity = tools.viewer.entities.add({
			    position : Cesium.Cartesian3.fromDegrees(-123.0744619, 44.0503706),
			    model : {
			        uri : './models/abuilding/abuilding.gltf'
			    }
			});
			//这个是镜头追踪，将镜头固定在小车上
			//tools.viewer.trackedEntity = entity;
		},
		addModel:function(id){
			layer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,time:100000}) ;
			//创建一个多边形实体对象
			var wyoming = tools.viewer.entities.add({
			  name : 'Wyoming',
			  polygon : {
			    //给出位置
			    hierarchy : Cesium.Cartesian3.fromDegreesArray([
			                              -109.080842,45.002073,
			                              -105.91517,45.002073,
			                              -104.058488,44.996596,
			                              -104.053011,43.002989,
			                              -104.053011,41.003906,
			                              -105.728954,40.998429,
			                              -107.919731,41.003906,
			                              -109.04798,40.998429,
			                              -111.047063,40.998429,
			                              -111.047063,42.000709,
			                              -111.047063,44.476286,
			                              -111.05254,45.002073]),
			    //离地高度
			    height : 0,
			    //材质（相当于填充色，但是可以填充的可不止颜色，可以看到这里是红色半透明）
			    material : Cesium.Color.RED.withAlpha(0.5),
			    //外边线
			    outline : true,
			    outlineColor : Cesium.Color.BLACK
			  }
			});
			//这是一个动画效果，进入后镜头就会自动转到这个实体处
			tools.dataSources.push({dataSource:wyoming,id:id});
			tools.viewer.zoomTo(wyoming);
			//离地高度
			wyoming.polygon.height = 300000;
			//两个高度差组成了它的体积
			wyoming.polygon.extrudedHeight = 250000;
			wyoming.description = '\
				<img\
				  width="50%"\
				  style="float:left; margin: 0 1em 1em 0;"\
				  src="//imgsrc.baidu.com/baike/abpic/item/d35a10f45f26d158dcc4740e.jpg"/>\
				<p>\
				  一大块果冻，想不想吃？\
				</p>\
				<p>\
				  介绍: \
				  <a style="color: WHITE"\
				    target="_blank"\
				    href="http://imgsrc.baidu.com/baike/abpic/item/d35a10f45f26d158dcc4740e.jpg">点击查看</a>\
				</p>';
			layer.msg('加载完成！',{time: 1000}); 
		},
		addGeojson:function(url,id){
			layer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,time:100000}) ;
			var flag = true;
			var ds = null;
			tools.dataSources.forEach(function(val){
				if(val.id==id){
					ds = val.dataSource;
					flag=false;
				}
			});
			if(flag){
				var dataSource = Cesium.GeoJsonDataSource.load(url);
				tools.dataSources.push({dataSource:dataSource,id:id});
				tools.viewer.dataSources.add(dataSource);
				
			}else{
				tools.viewer.flyTo(ds);
			}
			layer.msg('加载完成！',{time: 1000}); 
		},
		addSdeShape:function(url,id){
			layer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,time:100000}) ;
			var flag = true;
			var ds = null;
			tools.dataSources.forEach(function(val){
				if(val.id==id){
					ds = val.dataSource;
					flag=false;
				}
			});
			if(flag){
				var dataSource = Cesium.GeoJsonDataSource.load(url);
				tools.dataSources.push({dataSource:dataSource,id:id});
				tools.viewer.dataSources.add(dataSource);
				
			}else{
				tools.viewer.flyTo(ds);
			}
			layer.msg('加载完成！',{time: 1000}); 
		},
		add3DTiles:function(para,id){
			layer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,time:100000}) ; 
			var flag = true;
			tools.Tiles.forEach(function(val){
				if(val.id==id){
					flag=false;
				}
			});
			if(flag){
				var newelt = tools.viewer.scene.primitives.add(new Cesium.Cesium3DTileset(para));
				tools.Tiles.push({Tile:newelt,id:id})
			}
			layer.msg('加载完成！',{time: 1000}); 
		}
}

$(document).ready(function(){
	layui.use('layer', function(){
		tools.init();
	});
});