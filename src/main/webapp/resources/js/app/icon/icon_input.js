$(document).ready(function() {
	sy.ns('sys_icon_input');
	if(hasPermission("SAVE_ICON")){
		$("#sys_icon_input").show();
	}
	sys_icon_input.add = function() {
		sy.ajaxSubmit("sys_icon_form","sys_icon_add","sys_icon_datagrid");
	};

});
