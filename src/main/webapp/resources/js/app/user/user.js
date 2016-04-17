sy.ns('sys.user');

$(document).ready(function() {
	
	var buttons = sys.user.buildButtons();
	var operation = {title: sy_operation, width:'380',field:'opt',align:'center',
			formatter:function(value,rec,index){
				var ops = [];
				
				if(hasPermission("BATCH_DELETE_USER")){
					var delOpt = '<a class="a-icon" title="'+ sy_delete +'" onclick="sys.user.del(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ sy_delete +'</a>';
					ops.push(delOpt);
				}
				if(hasPermission("UPDATE_USER")){
					var updateOpt = '<a class="a-icon" title="'+ sy_update +'" onclick="sys.user.postUpdate(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ sy_update +'</a>';
					ops.push(updateOpt);
				}
				if(hasPermission("UPDATEPASSWORD_USER")){
					var pwdOpt = '<a class="a-icon" title="'+ sys_user_updatePwd +'" onclick="sys.user.updatePwd(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-modify-pwd.gif) no-repeat">'+ sys_user_updatePwd +'</a>';
					ops.push(pwdOpt);
				}
				if(hasPermission("GRANT_USER")){	
					var authOpt = '<a class="a-icon" title="'+ sys_user_grant +'" onclick="sys.user.authUser(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-grant.gif) no-repeat">'+ sys_user_grant +'</a>';
					ops.push(authOpt);
				}
				
				return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
			}
		};
	var datagrid={
			url:basePath+'/user/user!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.user.doubleClick
			};
	sy.datagrid("sys_user_datagrid",datagrid,sys.user.columns,true,operation);
});
