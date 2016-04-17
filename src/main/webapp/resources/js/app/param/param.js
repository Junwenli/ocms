sy.ns('sys.param');

$(document).ready(function() {
	
	var buttons = sys.param.buildButtons();
	var operation = {title: sy_operation, width:'330',field:'opt',align:'center', 
			formatter:function(value,rec,index){
				var ops = [];
				if(hasPermission("BATCH_DELETE_PARAM")){
						var delOpt = '<a title="'+sy_delete+'" onclick="sys.param.del(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+sy_delete+'</a>';
						ops.push(delOpt);
				}
				if(hasPermission("UPDATE_PARAM")){
						var updateOpt = '<a title="'+sy_update+'" onclick="sys.param.postUpdate(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+sy_update+'</a>';
						ops.push(updateOpt) ;
				}
				return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
			}
		};
	var datagrid={
			url:basePath+'/param/param!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.param.doubleClick
			};
	sy.datagrid("sys_param_datagrid",datagrid,sys.param.columns,true,operation);
});

