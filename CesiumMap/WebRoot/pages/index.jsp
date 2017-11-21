<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user = request.getSession().getAttribute("user").toString();
%>
<!DOCTYPE html>
<html>
<head>
  <!-- Use correct character set. -->
  <meta charset="utf-8">
  <!-- Tell IE to use the latest, best version. -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Make the application on mobile take up the full browser screen and disable user scaling. -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
  <title>CesiumMap</title>
  <link type="text/css" rel="stylesheet" href="<%=basePath%>layui-2.1.5/css/layui.css">
  <link type="text/css" rel="stylesheet" href="<%=basePath%>Cesium-1.3.8/Widgets/widgets.css">
  <link type="text/css" rel="stylesheet" href="<%=basePath%>asserts/css/style.css">
  <script src="<%=basePath%>Cesium-1.3.8/Cesium.js"></script>
  <script src="<%=basePath%>layui-2.1.5/layui.js"></script>
  <script src="<%=basePath%>asserts/js/plugin/jquery.js"></script>
  <script>
  	var user = '<%=user%>';
  	var path = {};
  	path.basePath = '<%=basePath%>';
  	path.socketPath = '<%=socketPath%>';
  </script>
  <script src="<%=basePath%>asserts/js/index/measure.js"></script>
  <script src="<%=basePath%>asserts/js/index/tools.js"></script>
  <script src="<%=basePath%>asserts/js/index/imtools.js"></script>
  <script src="<%=basePath%>asserts/js/index/init.js"></script>
  <script src="<%=basePath%>asserts/js/index/webSocket.js"></script>
</head>
<body>
	<div id ="cesiumContainer">
		<div id="toolbar" class="toolbar-custom">
			<div class="layui-row">
				<div class="layui-col-xs6 layui-col-sm8 layui-col-md12">
					<button id = "chutiyan" class="layui-btn layui-btn-radius layui-btn-small layui-btn-normal">
	  					<i class="layui-icon">&#xe65f;</i> 工具栏
					</button>
			    </div>
			</div>
			<div id="toolspage" class="layui-row">
				<div class="layui-col-xs6 layui-col-sm8 layui-col-md12">
					<div class="layui-tab layui-tab-brief  layui-bg-black" lay-filter="TabBrief">
					    <ul class="layui-tab-title">
						    <li class="layui-this">地图演示</li>
						    <li>常用工具</li>
						    <li>我的地图</li>
					    </ul>
					    <div class="layui-tab-content">
					    	<div class="layui-tab-item layui-show">
					    		内容1
					    	</div>
						    <div class="layui-tab-item">内容2</div>
						    <div class="layui-tab-item">内容3</div>
					    </div>
					</div> 
			    </div>
			</div>
	    </div>
	    <div id="toolbar" class="lanlat-custom">
	    	<div class="lanlat">
	    		<div id = "lanlat"></div>
				<div id = "height"></div>
	    	</div>
	    </div>
   	</div>
</body>
</html>
