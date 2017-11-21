/**
 * 
 */

$(document).ready(function(){
	layui.use(['layer','layim'], function(){
		tools.init();
		imtools.init(layui.layim);
	});
});