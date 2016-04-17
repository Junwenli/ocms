sy.ns('sys.icon');

//按钮
sys.icon.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_ICON','sys_icon_add',{cache:false,title: sys_icon_addtitle, href:basePath+'/icon/icon!input.action?test='+encodeURI(encodeURI('你好')),width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_ICON','sys_icon_datagrid',sys.icon.del);
	sy.buildQueryButton(buttons,'QUERY_ICON','sys_icon_search',{cache: false,title: sys_icon_query, width:"250"});
	return buttons;
};

//数据列表
sys.icon.columns = [
	              {title: sys_icon_name, width:'120',field:'title',align:'left',sortable:true},
	              {title: sys_icon_path, width:'270',field:'iconUrl',align:'left',sortable:true},
	              {title: sys_function_path, width:'270',field:'url',align:'left',sortable:true}
	    		];
//删除记录
sys.icon.del = function(id)
{
	sy.postDel(id,basePath + "/icon/icon!delete.action",'sys_icon_datagrid');
};

//修改记录
sys.icon.postUpdate = function (id)
{
	$('#sys_icon_add').window({cache:false,title: sys_icon_addtitle, href:basePath+"/icon/icon!input.action?operate=false&id="+id,width:'280'});
	$('#sys_icon_add').window('open');
};

//双击事件
sys.icon.doubleClick = function(rowIndex, rowData)
{
	sys.icon.postUpdate(rowData.id);
};

sys.icon.grant = function (id){
	$('#sysIcon_perm').window({cache:false,title: sys_icon_authorizedTitle, href:basePath+"/icon/icon!listRole.action?id="+id,width:850,height:'auto',left:270,top:180});
	$('#sysIcon_perm').window('open');
};
	
//清空查询条件
sys.icon.clear = function(){
	$('#sys_icon_search_form')[0].reset();
	$('#sys_icon_datagrid').datagrid("load",{});
};
//查询
sys.icon.search =function(){
	sy.search('sys_icon_datagrid','sys_icon_search_form');
};
