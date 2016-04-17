sy.ns('sys_department_user');
	$(function() {
		$("#userTree").tree({
			checkbox: true,
			url: basePath+'/user/user!userList.action'
		});
		/*
		sys_department_user_input.add = function() {
			$.ajax({
				url : basePath+'/department/department!save.action',
				data : {
					'deptCode' : $("#deptCode").val(),
					'title' : $("#title").val(),
					'description': $("#description").val(),
					'operate': $("#operate").val(),
					'resRate': $("#resRate").val()
				},
				cache : false,
				dataType : "json",
				type : "POST",
				success : function(response) {
					
					$('#sysDepartment_add').window('close');
					$('#sysDepartment_datagrid').treegrid('reload');
					$.messager.leftshow({
						
						msg : response.msg
					});
				}
			});
			
		};*/
	});