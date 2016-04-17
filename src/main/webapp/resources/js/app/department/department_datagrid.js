sy.ns('sys.department');

//按钮
sys.department.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_DEPARTMENT','sys_department_add',{cache:false,title: sys_department_addtitle, href:basePath+'/department/department!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_DEPARTMENT','sys_department_datagrid',sys.department.del);
	return buttons;
};

//数据列表
sys.department.columns = [
      	       		    {title: sys_department_code, width:'100',field:'id',align:'center',hidden:true},
    	       		    {title: sys_department_code, width:'100',field:'deptCode',align:'center',sortable:true},
    	       		    {title: sys_department_name, width:'100',field:'title',align:'center',sortable:true}
    	    		];

//删除记录
sys.department.del = function(id)
{
	sy.postDel(id,basePath + "/department/department!delete.action",'sys_department_datagrid');
};

//修改记录
sys.department.postUpdate = function (id)
{
	$('#sys_department_add').window({cache:false,title: sys_department_updatetitle, href:basePath+"/department/department!input.action?operate=false&id="+id,width:'280'});
	$('#sys_department_add').window('open');
};

//双击事件
sys.department.doubleClick = function(rowIndex, rowData)
{
	sys.department.postUpdate(rowData.id);
};

sys.department.userChecked=function (deptId)
{
	var datagrid={
			url:basePath+'/user/user!listAjax.action?filter_EQS_deptId='+deptId,
			onLoadSuccess:function(){
				$("#sys_department_user_data").window("open");
			}
		};
	sy.datagrid("sys_department_user_datagrid",datagrid,sys.user.columns);
	$("#sys_department_user_data").window({title: sys_department_userListtitle });
};