$(document).ready(function() {
	sy.ns('sys_param_input');
	if(hasPermission("SAVE_PARAM")){
		$("#sys_param_input").show();
	}
	sys_param_input.add = function() {
		sy.ajaxSubmit("sys_param_form","sys_param_add","sys_param_datagrid");
	};

});
