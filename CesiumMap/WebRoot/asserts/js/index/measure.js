/**
 * 
 */
var measure = {
		viewer:null,
		handler:null,
		ismeasure:false,
		mCartesian:[],
		mPoints:[],
		mLines:[],
		mPolygons:[],
		startP:null,
		endP:null,
		init:function(viewer){
			this.viewer=viewer;
			measure.handler = new Cesium.ScreenSpaceEventHandler(measure.viewer.scene.canvas);
		},
		measurebase:function(){

			if (!measure.ismeasure) {
				measure.viewer.scene.canvas.style.cursor='crosshair'; 
				measure.handler.setInputAction(function (movement) {
					var cartesian = measure.viewer.camera.pickEllipsoid(movement.position, measure.viewer.scene.globe.ellipsoid);
					var rayl = null;
					var ray = measure.viewer.camera.getPickRay(movement.position,rayl);
					
					 if (cartesian) {
					 	
						 measure.mCartesian.push(cartesian);
						 
						 measure.endP=measure.startP;
						 measure.startP=cartesian;
						 
						 if(measure.mCartesian.length>1){
							measure.dorwPoint(cartesian)
							measure.drowLine(measure.startP,measure.endP)
						 }else{
							 if(measure.mCartesian.length<1){
								 return;
							 }else{
								 measure.dorwPoint(cartesian)
							 }
						 }
					 }
		            
		        }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
				
				measure.handler.setInputAction(function (doublerightclick) {
		        	for(var i = 0;i<measure.mPoints.length;i++){
		        		if(i==measure.mPoints.length){
		        			measure.viewer.entities.remove(measure.mPoints[i]);
		        		}else{
		        			measure.viewer.entities.remove(measure.mPoints[i]);
		        			measure.viewer.entities.remove(measure.mLines[i]);
		        		}
		        	}
		        	measure.mPoints=[];
		        	measure.mLines=[];
		        	measure.mCartesian=[];
		        	measure.endP=null;
		        	measure.startP=null;
		        }, Cesium.ScreenSpaceEventType.RIGHT_CLICK);
		    }
		    else {
		    	measure.viewer.scene.canvas.style.cursor='auto';
		    	for(var i = 0;i<measure.mPoints.length;i++){
	        		if(i==measure.mPoints.length){
	        			measure.viewer.entities.remove(measure.mPoints[i]);
	        		}else{
	        			measure.viewer.entities.remove(measure.mPoints[i]);
	        			measure.viewer.entities.remove(measure.mLines[i]);
	        		}
	        	}
	        	measure.mPoints=[];
	        	measure.mLines=[];
	        	measure.mCartesian=[];
	        	measure.endP=null;
	        	measure.startP=null;
	        	measure.handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);
	        	measure.handler.removeInputAction(Cesium.ScreenSpaceEventType.RIGHT_CLICK);
		    }
		    measure.ismeasure = !measure.ismeasure;
		},
		dorwPoint:function(cartesian,uuid){
			var measurepoint = measure.viewer.entities.add({
				  name : 'measurepoint',
				  description: '<iframe width="400" height="300" src="'+path.basePath+'page/video/flvPlayDemo.html?uuid='+uuid+'" frameborder="0" allowfullscreen></iframe>',
				  position : cartesian,
				  point : {
				    pixelSize : 5,
				    color : Cesium.Color.RED,
				    outlineColor : Cesium.Color.WHITE,
				    outlineWidth : 2
				  }
				});
			measure.mPoints.push(measurepoint);
		},
		drowLine:function(pointStart,pointEnd){
			var entity = measure.viewer.entities.add({
				  name : 'measureLine',
				  position : measure.getMiddlePoint(pointStart,pointEnd),
				  polyline : {
				    positions : new Array(pointStart,pointEnd),
				    width : 5,
				    material : new Cesium.PolylineGlowMaterialProperty({
				         glowPower : 0.3,
				         color : Cesium.Color.BLUE
				    })
				  },
				  label : {
					    text : measure.getLength(pointStart,pointEnd).toString()+'米',
					    font : '14pt monospace',
					    style: Cesium.LabelStyle.FILL_AND_OUTLINE,
					    outlineWidth : 2,
					    verticalOrigin : Cesium.VerticalOrigin.BUTTON,
					    pixelOffset : new Cesium.Cartesian2(0, 20)
				  }
				});
			measure.mLines.push(entity);
		},
		drowPolygon:function(points){
			var cartesian = measure.viewer.camera.getPickRay();
		},
		getMiddlePoint:function(pointStart,pointEnd){
			var x = (pointStart.x + pointEnd.x)/2;
			var y = (pointStart.y + pointEnd.y)/2;
			var z = (pointStart.z + pointEnd.z)/2;
			var middle = new Cesium.Cartesian3(x, y, z);
			return middle
		},
		getLength:function(pointStart,pointEnd){
			var x1 = pointStart.x;
			var y1 = pointStart.y;
			var z1 = pointStart.z;
			var x2 = pointEnd.x;
			var y2 = pointEnd.y;
			var z2 = pointEnd.z;
			var xdiff = x2 - x1;
			var ydiff = y2 - y1;
			var zdiff = z2 - z1;
			var length = Math.pow((xdiff * xdiff + ydiff * ydiff + zdiff * zdiff), 0.5); 
			return length.toFixed(2);
		}
}