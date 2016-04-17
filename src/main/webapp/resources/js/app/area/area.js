sy.ns('sys.area');
$(document).ready(function() {
	var buttons = sys.area.buildButtons();
	var operation ={title: sy_operation,width : '180',field : 'opt',align : 'center', 
		formatter:function(value,rec, index){
			var ops = [];
            if (hasPermission("BATCH_DELETE_AREA")) {
			    var delOpt = '<a title="'+ sy_delete+ '" onclick="sys.area.del(\''+ rec.id+ '\')" class="a-icon" style="background:url('+ basePath+ '/resources/images/icon-del.gif) no-repeat">'+ sy_delete+ '</a>';
				ops.push(delOpt);
			}
			if (hasPermission("UPDATE_AREA")) {
				var updateOpt = '<a title="'+ sy_update+ '" onclick="sys.area.postUpdate(\''+ rec.id+ '\')" class="a-icon" style="background:url('+ basePath+ '/resources/images/icon-update.gif) no-repeat">'+ sy_update+ '</a>';
				ops.push(updateOpt);
			}
			return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
		}
	};
	var datagrid={
			url:basePath+'/area/area!listAjax.action',
			toolbar:buttons,
			onDblClickRow:sys.area.doubleClick
			};
	sy.datagrid("sys_area_datagrid",datagrid,sys.area.columns,true,operation);
});
