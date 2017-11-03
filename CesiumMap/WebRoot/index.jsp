<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <link rel="stylesheet" href="./layui-2.1.5/css/layui.css">
  <link type="text/css" rel="stylesheet" href="./Cesium-1.3.8/Widgets/widgets.css">
  <link type="text/css" rel="stylesheet" href="./asserts/css/style.css">
  <script src="./Cesium-1.3.8/Cesium.js"></script>
  <script src="./layui-2.1.5/layui.js"></script>
  <script src="./asserts/js/jquery.js"></script>
  <script src="./asserts/js/measure.js"></script>
  <script src="./asserts/js/tools.js"></script>
  <script src="./asserts/js/webSocket.js"></script>
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
			<div id="toolspage" class="layui-row layui-anim layui-anim-fadeout" >
				<div class="layui-col-xs6 layui-col-sm8 layui-col-md12">
					<div class="layui-tab layui-tab-brief  layui-bg-black" lay-filter="TabBrief">
					    <ul class="layui-tab-title">
						    <li class="layui-this">地图演示</li>
						    <li>常用工具</li>
						    <li>我的地图</li>
					    </ul>
					    <div class="layui-tab-content">
					    	<div class="layui-tab-item layui-show">
					    		<div class="layui-side-scroll">
									<ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="demo">
									  <li class="layui-nav-item layui-nav-itemed">
									    <a>组件示例</a>
									    <dl class="layui-nav-child">
									      <dd id="newyork">
									        <a href="#">
									          <i class="layui-icon" style="top: 3px;">&#xe638;</i>
									    <cite>纽约白模3DTiel标准</cite>
									  </a>
									</dd>
									<dd id="guibo">
									  <a href="#">
									    <i class="layui-icon" style="position: relative; top: 3px;">&#xe63a;</i>
									    <cite>倾斜摄影3DTiel标准</cite>
									  </a>
									</dd>
									<dd id="qingxie">
									  <a href="#">
									    <i class="layui-icon" style="top: 1px;">&#xe637;</i>
									        <cite>中国geojson格式</cite>
									      </a>
									    </dd>
									    <dd id="bendi">
									      <a href="#">
									        <i class="layui-icon">&#xe633;</i>
									        <cite>本地三维模型</cite>
									      </a>
									    </dd>
									    <dd id="abuilding">
									      <a href="#">
									        <i class="layui-icon">&#xe633;</i>
									        <cite>gltf三维模型</cite>
									      </a>
									    </dd>
									     <dd id="sdeshape">
									      <a href="#">
									        <i class="layui-icon">&#xe633;</i>
									        <cite>sdeshape格式</cite>
									      </a>
									    </dd>
									  </dl>
									</li>
									<li class="layui-nav-item" style="height: 30px; text-align: center"></li>
									  </ul>
									</div>
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
