sy.ns('sy.modifyPassword');
$(function() {
	sy.modifyPassword.modify = function() {/*修改密码*/
		if ($('#modifyPassword_form').form('validate')) {
			$.post(basePath + "/user/user!modifyPwd.action", {oldPwd : $('#modifyPassword_form #modifyPassword_input_oldPassword').val(),authenticator : $('#modifyPassword_form #modifyPassword_input_password').val()}, function(response) {
				if (response.success) {
					$('#north_dialog_modifyPwd').dialog('close');
					$.messager.leftshow({msg : response.msg,height : 20});
				} else {
					$.messager.leftshow({msg : response.msg,height : 20});
				}
			});
		}
	};
});