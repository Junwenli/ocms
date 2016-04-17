sy.ns('sys.role');
$(document).ready(function() {
	var buttons = sys.role.buildButtons();
	var operation = {title: sy_operation, width:'330',field:'opt',align:'center', 
			formatter:function(value,rec,index){
				var ops = [];
				if(hasPermission("BATCH_DELETE_ROLE")){
					var delOpt = '<a title="'+ sy_delete +'" onclick="sys.role.del(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ sy_delete +'</a>';
					ops.push(delOpt);
				}
				if(hasPermission("UPDATE_ROLE")){
					var updateOpt = '<a title="'+ sy_update +'" onclick="sys.role.postUpdate(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ sy_update +'</a>';
					ops.push(updateOpt);
				}
				if(hasPermission("GRANT_ROLE")){
					var grantOpt = '<a title="'+ sys_role_resourcesAuthorized +'" onclick="sys.role.grant(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-grant.gif) no-repeat">'+ sys_role_resourcesAuthorized +'</a>';
					ops.push(grantOpt);
				}
				return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
			}
		};
	var datagrid={
			url:basePath+'/role/role!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.role.doubleClick
			};
	sy.datagrid("sys_role_datagrid",datagrid,sys.role.columns,true,operation);
});
