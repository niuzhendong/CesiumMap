/**
 * 
 */
var websocket = null;  
$(document).ready(function(){
	  
    //判断当前浏览器是否支持WebSocket  
    if ('WebSocket' in window) {  
        //创建一个WebSocket连接，URL：127.0.0.1:8080/realTimeWebSocket/webSocket  
        //注：后端Server在模块realTimeWebSocket下，所以路径下多了一层realTimeWebSocket  
        //websocket = new WebSocket("ws://18.221.33.144:8080/CesiumMap/rest/gpswebsocket");  
        websocket = new WebSocket("ws://localhost:8080/CesiumMap/rest/gpswebsocket");  
    }  
    else {  
        alert('当前浏览器 不支持WebSocket')  
    }  
  
    //连接发生错误的回调方法  
    websocket.onerror = function () {  
    	alert('连接发生错误');  
    };  
  
    //连接成功建立的回调方法  
    websocket.onopen = function () {  
    	//alert("连接成功");  
    }  
  
    //接收到消息的回调方法，此处添加处理接收消息方法，当前是将接收到的信息显示在网页上  
    websocket.onmessage = function (event) {
    	//alert(event.data);
    	var data = JSON.parse(event.data);
    	measure.dorwPoint(Cesium.Cartesian3.fromDegrees(data.Longitude,data.Latitude),data.DeviceId);
    }  
  
    //连接关闭的回调方法  
    websocket.onclose = function () {  
    	alert("连接关闭,如需登录请刷新页面。");  
    }  
  
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
    window.onbeforeunload = function () {  
        closeWebSocket();  
    }  
});