$(function() {
	if(hasPermission("SAVE_CLASSINFO")){
		$("#school_classinfo_input").show();
	}
	school.classinfo.add = function()
	{
		sy.trim('school_classinfo_form','classname','teacher');
		sy.ajaxSubmit("school_classinfo_form","school_classinfo_add","school_classinfo_datagrid");
	};
});
