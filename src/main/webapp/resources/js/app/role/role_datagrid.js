sy.ns('sys.role');

//按钮
sys.role.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_ROLE','sys_role_add',{cache:false,title: sys_role_addtitle, href:basePath+'/role/role!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_ROLE','sys_role_datagrid',sys.role.del);
	sy.buildQueryButton(buttons,'QUERY_ROLE','sys_role_search',{cache: false,title: sys_role_searchtitle, width:"250"});
	return buttons;
};
//数据列
sys.role.columns = [
	       		    {title: sys_role_code, width:'150',field:'id',align:'left',hidden:true},
	    			{title: sys_role_code, width:'100',field:'roleCode',align:'center',sortable:true},
	    			{title: sys_role_name, width:'150',field:'title',align:'left',sortable:true},
	    			{title: sys_role_state, width:'50',field:'statusDesc',orderField:'status',align:'center',sortable:true},
	    			{title: sys_role_description, width:'350',field:'description',align:'left',sortable:true}
	    		];

//删除记录
sys.role.del = function(id)
{
	sy.postDel(id,basePath + "/role/role!delete.action",'sys_role_datagrid');
};

//修改记录
sys.role.postUpdate = function (id)
{
	$('#sys_role_add').window({cache:false,title: sys_role_updatetitle, href:basePath+"/role/role!input.action?operate=false&id="+id,width:'280'});
	$('#sys_role_add').window('open');
};

//双击事件
sys.role.doubleClick = function(rowIndex, rowData)
{
	sys.role.postUpdate(rowData.id);
};

//资源授权
sys.role.grant = function (id){
	 $('#sys_role_permission').window({cache:false,title: sys_role_authorizedtitle, href:basePath+"/permission/permission.action?roleId="+id});
	 $('#sys_role_permission').window('open');
};

//清空查询条件
sys.role.clear = function(){
	$('#sys_role_search_form')[0].reset();
	$('#sys_role_datagrid').datagrid("load",{});
};
//查询
sys.role.search =function(){
	sy.search('sys_role_datagrid','sys_role_search_form');
};
