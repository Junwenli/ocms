sy.ns('sys.param');

//按钮
sys.param.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_PARAM','sys_param_add',{cache:false,title: sys_param_addtitle, href:basePath+'/param/param!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_PARAM','sys_param_datagrid',sys.param.del);
	return buttons;
};

//数据列表
sys.param.columns =  [
	                {title: sys_param_name, width:'150',field:'id',align:'left',hidden:true},
	                {title: sys_param_name, width:'150',field:'paramName',align:'left',sortable:true},
	                {title: sys_param_value, width:'150',field:'paramValue',align:'left',sortable:true}
	    		];

//删除记录
sys.param.del = function(id)
{
	sy.postDel(id,basePath + "/param/param!delete.action",'sys_param_datagrid');
};

//修改记录
sys.param.postUpdate = function (id)
{
	$('#sys_param_add').window({cache:false,title: sys_param_updatetitle, href:basePath+"/param/param!input.action?operate=false&id="+id,width:'280'});
	$('#sys_param_add').window('open');
};

//双击事件
sys.param.doubleClick = function(rowIndex, rowData)
{
	sys.param.postUpdate(rowData.id);
};