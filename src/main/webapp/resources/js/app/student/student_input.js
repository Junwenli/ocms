$(function() {
	if(hasPermission("SAVE_STUDENT")){
		$("#school_student_input").show();
	}
	school.student.add = function()
	{
		sy.trim('school_student_form','classname','teacher');
		sy.ajaxSubmit("school_student_form","school_student_add","school_student_datagrid");
	};
});
/*
$.extend($.fn.validatebox.defaults.rules, {
    diferrentPwd: {
        validator: function(value, param){
			return value==$(param[0]).val()?false:true;
        },
        message: "新旧密码不能相同"
    }
});
*/
