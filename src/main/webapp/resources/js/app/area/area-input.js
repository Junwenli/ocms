sy.ns('sys_area_input');
$(function() {
	if(hasPermission("SAVE_AREA")){
		$("#sys_area_input").show();
	}
	sys_area_input.add = function()
	{
		sy.trim('sys_area_form','id','areaCode');
		sy.ajaxSubmit("sys_area_form","sys_area_add","sys_area_datagrid");
	};
});

