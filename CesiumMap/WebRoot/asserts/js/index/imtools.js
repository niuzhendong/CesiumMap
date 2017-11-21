/**
 * 
 */
var imtools = {
		layim:null,
		init:imInit,
		imSocketInit:imSocketInit
}

function imInit(layim){
	  //基础配置
	
	  var socket = null;
	  if ('WebSocket' in window) {  
	        //创建一个WebSocket连接，URL：127.0.0.1:8080/realTimeWebSocket/webSocket  
	        //注：后端Server在模块realTimeWebSocket下，所以路径下多了一层realTimeWebSocket  
	        //IMwebsocket = new WebSocket("ws://localhost:8080/CesiumMap/rest/gpswebsocket");
		    socket = new WebSocket(path.socketPath+"rest/imwebsocket?user="+user);  
	    }
	    else {
	        alert('当前浏览器 不支持WebSocket')  
	    }
	    //连接发生错误的回调方法  
	    socket.onerror = function () {  
	    	console.log("连接发生错误");  
	    };  
	  
	    //连接成功建立的回调方法  
	    socket.onopen = function () {  
	    	console.log("连接成功");  
	    }  
	  
	    //接收到消息的回调方法，此处添加处理接收消息方法
	    socket.onmessage = function (data) {  
	    	console.log(data);
	    	var res = JSON.parse(data.data);
	    	if(data.type === 'message'){
	    	    layim.getMessage(res.data.mine);
	    	}
	    }  
	  
	    //连接关闭的回调方法  
	    socket.onclose = function () {  
	    	console.log("连接关闭,如需登录请刷新页面。");  
	    }  
	  
	    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
	    window.onbeforeunload = function () {  
	        closeWebSocket();  
	    }  
	  layim.config({

	    //初始化接口
	    init: {
	      //url: './asserts/json/getList2.json'
	      url: path.basePath+'rest/im/getFriends'
	      ,data: {username:user}
	    }
	    

	    //查看群员接口
	    ,members: {
	      url: path.basePath+'asserts/json/getMembers.json'
	      ,data: {}
	    }
	    
	    //上传图片接口
	    ,uploadImage: {
	      url: path.basePath+'upload/image' //（返回的数据格式见下文）
	      ,type: '' //默认post
	    } 
	    
	    //上传文件接口
	    ,uploadFile: {
	      url: path.basePath+'upload/file' //（返回的数据格式见下文）
	      ,type: '' //默认post
	    }
	    
	    //扩展工具栏
	    ,tool: [{
	      alias: 'code'
	      ,title: '代码'
	      ,icon: '&#xe64e;'
	    }]
	    
	    //,brief: true //是否简约模式（若开启则不显示主面板）
	    
	    ,title: 'WebIM' //自定义主面板最小化时的标题
	    //,right: '100px' //主面板相对浏览器右侧距离
	    //,minRight: '90px' //聊天面板最小化时相对浏览器右侧距离
	    ,initSkin: '5.jpg' //1-5 设置初始背景
	    //,skin: ['aaa.jpg'] //新增皮肤
	    //,isfriend: false //是否开启好友
	    //,isgroup: false //是否开启群组
	    ,min: true //是否始终最小化主面板，默认false
	    ,notice: true //是否开启桌面消息提醒，默认false
	    //,voice: false //声音提醒，默认开启，声音文件为：default.wav
	    
	    ,msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
	    ,find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
	    ,chatLog: layui.cache.dir + 'css/modules/layim/html/chatLog.html' //聊天记录页面地址，若不开启，剔除该项即可
	    
	  });
	  
	//监听在线状态的切换事件
	  layim.on('online', function(data){
	    console.log(data);
	  });
	  
	  //监听签名修改
	  layim.on('sign', function(value){
	    console.log(value);
	  });

	  //监听自定义工具栏点击，以添加代码为例
	  layim.on('tool(code)', function(insert){
	    layer.prompt({
	      title: '插入代码'
	      ,formType: 2
	      ,shade: 0
	    }, function(text, index){
	      layer.close(index);
	      insert('[pre class=layui-code]' + text + '[/pre]'); //将内容插入到编辑器
	    });
	  });
	  //监听layim建立就绪
	  layim.on('ready', function(res){

	    
	  });
	  //监听发送消息
	  layim.on('sendMessage', function(data){
	    var To = data.to;
	    socket.send(
	    		JSON.stringify({
	    			type: 'web' //随便定义，用于在服务端区分消息类型
	                ,data: data
	    }));
	  });
	  //监听查看群员
	  layim.on('members', function(data){
	    console.log(data);
	  });
	  //监听聊天窗口的切换
	  layim.on('chatChange', function(res){
	    
	  });
}

function imSocketInit(){
	
}