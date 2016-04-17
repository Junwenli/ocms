$(function(){
	sy.ns('sys.give');
	$('#give_permission_datagrid').datagrid({
		title:sys_user_title,
		iconCls:'icon-save',
		height: 'auto',
		url : basePath+'/user/user!findByUserId.action',
		loadMsg : sys_user_loadMsg,
		frozenColumns:[[{field:'rowid',checkbox:true}]],
		clickSelected:true,
		columns:[[
            {width:'100',field:'id',align:'center',hidden:true},
			{title:sys_user_account,width:'100',field:'userCode',align:'center'},
			{title:sys_user_name,width:'100',field:'name',align:'center'},
			{title:sys_user_statusDesc,width:'100',field:'status',align:'center'},
			{title:sys_user_mobile,width:'150',field:'mobile',align:'center'},
			{title:sys_user_email,width:'200',field:'email',align:'center'},
			{title:sys_user_opt,width:'280',field:'opt',align:'center', 
				formatter:function(value,rec,index){
					var ops = [];
					var operation = "";
					
					/*if(hasPermission("Give_USER")){	*/
						var authOpt = '<a class="a-icon" title="'+sys_user_grant+'" onclick="sys.give.grant(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-grant.gif) no-repeat">'+sys_user_grant+'</a>';
						ops.push(authOpt);
					/*}*/
					
					for(var i=0;i<ops.length;i++){
						operation+=ops[i];
						if(i<ops.length-1){
							operation+='&nbsp;&nbsp;&nbsp;&nbsp;';
						}
					}
					
					return operation;
				}
			}
		]],
		toolbar:[
					{
						id:'btnRef',
						text:sys_user_load,
						iconCls:'pagination-load',
						handler:function(){
							$('#cookie_message_datagrid').datagrid("reload");
						}
					}
				],
		pagination : true,
		rownumbers : true,
		onLoadSuccess:function(data){

			dataGridHeadCheckBoxUnSelect($(this));
		}
	});
	
	//资源授权
	sys.give.grant = function (id){
		 $('#sysGive_permission').window({cache:false,title: sys_user_grant, href:basePath+"/givePermission/givepermission.action?userId="+id});
		 $('#sysGive_permission').window('open');
};
});