$(document).ready(function() {
	sy.ns('sys_role_input');
	if(hasPermission("SAVE_ROLE")){
		$("#sys_role_input").show();
	}
	sys_role_input.add = function() {
		sy.ajaxSubmit("sys_role_form","sys_role_add","sys_role_datagrid");
	};

});
