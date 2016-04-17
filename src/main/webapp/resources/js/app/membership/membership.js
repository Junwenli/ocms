sy.ns('sys.membership');
$(function(){
	$('#membership_datagrid').datagrid({
		//title:'角色列表',
		iconCls:'icon-save',
		height: 'auto',
		url : basePath+'/membership/membership!findAll.action?userId=' + $('#membership_userId').val(),
		loadMsg : sys_membership_loadMsg,
		columns:[[
			{title: role_chose, width:'40',field:'1',align:'center',
		    	formatter:function(value,row,index){
		    		var value;
		    		if(row.checked){
		    			value = '<input class="membership_checkbox" checked="checked" type="checkbox" value="'+row.roleId+'"/>';
		    		}else{
		    			value = '<input class="membership_checkbox" type="checkbox" value="'+row.roleId+'"/>';
		    		}
		    		return value;
		    	}
		    },
		    {title:'角色代码',width:'100',field:'roleId',align:'left',hidden:true},
			{title: role_name, width:'120',field:'title',align:'left'},
			{title: role_state, width:'80',field:'status',align:'center'},
			{title: role_description, width:'300',field:'description',align:'left'}
		]],
		toolbar:[{
				id:'btnAdd',
				text: role_authorization,
				iconCls:'icon-edit',
				handler:function(){
					var ids = [];				
					$.messager.confirm(sys_membership_confirm, sys_membership_confirmInf, function(r) {
						if (r) {
							$('.membership_checkbox').each(function(){
								if($(this).attr('checked')){
									ids.push($(this).val());
								}
							});
							sys.membership.saveAuth(ids.join(','));
						}
					});
				}
			}
		],
		pagination : true,
		rownumbers : true

	});
});

//删除记录
sys.membership.saveAuth = function(ids)
{
	$.post(basePath + "/membership/membership!saveUserRoleShip.action",  {'roleId' : ids,'userId' : $('#membership_userId').val()},  function(response) {
		if(response.success==true){
			$('#role_list_div').window('close');
			$('#membership_datagrid').datagrid('reload');
		}
		$.messager.leftshow({				
			msg : response.msg,
			height : 20
		});
	});
};

