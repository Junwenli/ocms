sy.ns('sys.department');

$(document).ready(function() {
	
	var buttons = sys.department.buildButtons();
	var operation ={title: sy_operation, width:'340',field:'opt',align:'center', 
		formatter:function(value,rec,index){
			var ops = [];
			if(hasPermission("BATCH_DELETE_DEPARTMENT")){
				var delOpt = '<a class="a-icon" title="'+ sy_delete +'" onclick="sys.department.del(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ sy_delete +'</a>';
				ops.push(delOpt);
			}
			if(hasPermission("UPDATE_DEPARTMENT")){
				var updateOpt = '<a class="a-icon" title="'+ sy_update +'" onclick="sys.department.postUpdate(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ sy_update +'</a>';
				ops.push(updateOpt);
			}
			if(hasPermission("LISTUSER_DEPARTMENT")){
				var pwdOpt = '<a class="a-icon" title="'+ sys_department_user_list +'" onclick="sys.department.userChecked(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-dept-user.gif) no-repeat">'+ sys_department_user_list +'</a>';
				ops.push(pwdOpt);
			}
			return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
		}
	};
	var datagrid={
			url:basePath+'/department/department!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.department.doubleClick
			};
	sy.datagrid("sys_department_datagrid",datagrid,sys.department.columns,true,operation);
});
