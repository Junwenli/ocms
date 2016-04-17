sy.ns('sys.icon');

$(document).ready(function() {
	
	var buttons = sys.icon.buildButtons();
	var operation ={title:sy_operation, width:'330',field:'opt',align:'center', 
		formatter:function(value,rec,index){
			var ops = [];
			if(hasPermission("BATCH_DELETE_ICON")){
			var delOpt = '<a title="'+sy_delete+'" onclick="sys.icon.del(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+sy_delete+'</a>';
			    ops.push(delOpt);
			}
			if(hasPermission("UPDATE_ICON")){
			var updateOpt = '<a title="'+sy_update+'" onclick="sys.icon.postUpdate(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+sy_update+'</a>';
				ops.push(updateOpt) ;
			}
			if(hasPermission("GRANT_ICON")){
			var grantOpt = '<a title="'+sys_icon_resourcesAuthorized+'" onclick="sys.icon.grant(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-grant.gif) no-repeat">'+sys_icon_resourcesAuthorized+'</a>';
				ops.push(grantOpt) ;
			}
			return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
		}
	};
	var datagrid={
			url:basePath+'/icon/icon!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.icon.doubleClick
			};
	sy.datagrid("sys_icon_datagrid",datagrid,sys.icon.columns,true,operation);
});
