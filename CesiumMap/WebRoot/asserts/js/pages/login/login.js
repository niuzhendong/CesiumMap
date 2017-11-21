function login(){
	$.ajax({
		url:"./rest/im/login",
		dataType:"json",
		data:{username:$("#username").val(),password:$("#password").val()}
	})
}