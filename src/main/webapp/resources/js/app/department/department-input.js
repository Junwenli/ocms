sy.ns('sys_department_input');
$(function() {
	if(hasPermission("SAVE_DEPARMENT")){
		$("#sys_deparment_input").show();
	}
	sys_department_input.add = function()
	{
		sy.trim('sys_department_form','id','deptCode','title');
		sy.ajaxSubmit("sys_department_form","sys_department_add","sys_department_datagrid");
	};
});

$.extend($.fn.validatebox.defaults.rules, {
    mustInt: {
        validator: function(value, param){
			if(isNaN(value) || parseInt($("#resRate").val())>parseInt($("#useRate").val())+parseInt($("#oldUseRate").val()) ){
				return false;
			}
			if(value.indexOf(".")!=-1||parseInt(value)<0){
				return false;
			}
			return true;
        },
        message: sys_department_resRate
    }
});
