/**
 * 
 */
layui.use(['form', 'layedit'], function(){
  var form = layui.form,
  layer = layui.layer,
  layedit = layui.layedit;
 
  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
  //监听提交
  form.on('submit(demo1)', function(data){
	  	//loginTools.login(data);
	  	$.ajax({
			url:"http://localhost:8080/CesiumMap/rest/redis/getUser",
			dataType:"json",
			data:user,
			processData:false,
			contentType:"application/json",
			type:"POST",
			success:function(data){
				if(data){
			  		layer.alert(JSON.stringify(data), {
			  		  title: 'y最终的提交信息'
			  		})
			  	}
			},
			error:function(data){
				if(data){
			  		layer.alert(JSON.stringify(data), {
			  		  title: 'x最终的提交信息'
			  		})
			  	}
			}
		})
		return false;
  });
});


var loginTools = {
		user:{
			id:null,
			name:null,
			url:null
		},
		login:function(){
			//this.user.id=user.username;
			//this.user.name=user.username;
			var user = {'username':'123','password':'111111'};
			$.ajax({
				url:"http://localhost:8080/CesiumMap/rest/redis/getUser",
				dataType:"json",
				data:JSON.stringify(user),
				//processData:false,
				contentType:"application/json",
				type:"POST",
				success:function(data){
					if(data){
				  		layer.alert(JSON.stringify(data), {
				  		  title: 'y最终的提交信息'
				  		})
				  	}
				},
				error:function(data){
					if(data){
				  		layer.alert(JSON.stringify(data), {
				  		  title: 'x最终的提交信息'
				  		})
				  	}
				}
			})
		}
};