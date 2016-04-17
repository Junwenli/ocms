sy.ns('sys.area');

//按钮
sys.area.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_AREA','sys_area_add',{cache:false,title: sys_area_addtitle, href:basePath+'/area/area!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_AREA','sys_area_datagrid',sys.area.del);
	sy.buildQueryButton(buttons,'LIST_AREA','sys_area_search',{cache: false,title: sys_area_title, width:"250"});
	return buttons;
};

//数据列
sys.area.columns = [
	       		    {title : sys_area_code,width : '100',field : 'id',align : 'center',hidden : true},
	       		    {title : sys_area_code,width : '100',field : 'areaCode',align : 'center',sortable:true},
	       		    {title : sys_area_name,width : '100',field : 'areaName',align : 'center',sortable:true},
	       		    {title : sys_area_description,width : '100',field : 'description',align : 'center',sortable:true}
	    		];

//删除记录
sys.area.del = function(id) {
	sy.postDel(id,basePath + "/area/area!delete.action",'sys_area_datagrid');
};
//修改记录
sys.area.postUpdate = function (id)
{
	$('#sys_area_add').window({cache:false,title: sys_area_updatetitle, href:basePath+"/area/area!input.action?operate=false&id=" + id,width:'280'});
	$('#sys_area_add').window('open');
};
//双击事件
sys.area.doubleClick = function(rowIndex, rowData)
{
	sys.area.postUpdate(rowData.id);
};
//清空查询条件
sys.area.clear = function(){
	$('#sys_area_search_form')[0].reset();
	$('#sys_area_datagrid').datagrid("load",{});
};
//查询
sys.area.search =function(){
	sy.search('sys_area_datagrid','sys_area_search_form');
};