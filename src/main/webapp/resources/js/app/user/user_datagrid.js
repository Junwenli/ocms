sy.ns('sys.user');

//按钮
sys.user.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_USER','sys_user_add',{cache:false,title: sys_user_addtitle, href:basePath+'/user/user!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_USER','sys_user_datagrid',sys.user.del);
	sy.buildQueryButton(buttons,'QUERY_USER','sys_user_search',{cache: false,title: sys_user_searchtitle, width:"250"});
	return buttons;
};
//数据列
sys.user.columns = [
	       		    {title: sys_user_account, width:'100',field:'id',align:'center',hidden:true},
	       		    {title: sys_user_account, width:'100',field:'userCode',align:'center',sortable:true},
	       		    {title: sys_user_name, width:'100',field:'name',align:'center',sortable:true},
	       		    {title: sys_user_statusDesc, width:'100',field:'statusDesc',orderField:'status',align:'center',sortable:true},
	       		    {title: sys_user_mobile, width:'150',field:'mobile',align:'center',sortable:true},
	       		    {title: sys_user_email, width:'200',field:'email',align:'center',sortable:true}
	    		];
//删除记录
sys.user.del = function(id)
{
	sy.postDel(id,basePath + "/user/user!delete.action",'sys_user_datagrid');
};

//修改记录
sys.user.postUpdate = function (id)
{
	$('#sys_user_add').window({cache:false,title: sys_user_updatetitle, href:basePath+"/user/user!input.action?operate=false&id="+id,width:'280'});
	sy.windowCenter('sys_user_add');
	$('#sys_user_add').window('open');
};

//双击事件
sys.user.doubleClick = function(rowIndex, rowData)
{
	sys.user.postUpdate(rowData.id);
};

//给用户权限
sys.user.authUser = function (id){
	 $('#role_list_div').window({cache:false,title: sys_user_authtitle, href:basePath+"/membership/membership.action?id=" + id});
	 sy.windowCenter('role_list_div');
	 $('#role_list_div').window('open');
};
//修改密码
sys.user.updatePwd = function (id){
	 $('#sysUser_pwd_end').window({cache:false,title: sys_user_updatePwdtitle, height:167, width:310, href:basePath+"/user/user!pwd.action?id="+id+"&operate=false"});
	 sy.windowCenter('sysUser_pwd_end');
	 $('#sysUser_pwd_end').window('open');
};
//清空查询条件
sys.user.clear = function(){
	$('#sys_user_search_form')[0].reset();
	$('#sys_user_datagrid').datagrid("load",{});
};
//查询
sys.user.search =function(){
	sy.search('sys_user_datagrid','sys_user_search_form');
};