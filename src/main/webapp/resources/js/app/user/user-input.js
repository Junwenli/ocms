sy.ns('sys_user_input');
$(function() {
	if (hasPermission("SAVE_USER")) {
		$("#sys_user_input").show();
	}
	sys_user_input.add = function() {
		if ($("#departmentId_msg")) {
			$("#departmentId_msg").remove();
		}
		if ($("#departmentId").val() == "") {
			$("#user_input_deptCodeMsg").append(
					"<span id='departmentId_msg' style='color: red'>"
							+ user_select + "</span>");
			return;
		}
		sy.trim('sys_user_form','userId','userCode');
		sy.ajaxSubmit("sys_user_form","sys_user_add","sys_user_datagrid");
	};
});
UserDeptChange = function() {
	if ($("#departmentId").val() == "") {
		$("#user_input_deptCodeMsg").append(
				"<span id='departmentId_msg' style='color: red'>" + user_select
						+ "</span>");
		return;
	} else {
		$("#departmentId_msg").remove();
	}
};
