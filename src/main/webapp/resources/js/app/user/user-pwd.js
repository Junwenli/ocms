sy.ns('sys_user_pwd');
		sys_user_pwd.add = function() {
			if($("#pwdInfo").form("validate")){
				$.post(basePath + "/user/user!updatePwd.action", {'id' : $("#userId").val(),'authenticator' : $("#authenticator").val()},  function(response) {
					
					$('#sysUser_pwd_end').window('close');
					$('#sys_user_datagrid').datagrid('reload');
					
					$.messager.leftshow({						
						msg : response.msg,
						height : 20
					});
				});
			}
		};