$(document).ready(function() {
	
	sy.ns('sys.icon');
	$('#icon_role_datagrid').datagrid({
		title:'',
		iconCls:'icon-save',
		height: 'auto',
		url : basePath+'/icon/icon!findRoleByIconId.action?id='+$("#icon_role_id").val(),
		loadMsg : sys_icon_list_loadMsg,
		clickSelected:true,
		columns:[[
		          {title: sys_role_chose, width:'50',field:'1',align:'center',
				    	formatter:function(value,rec,index){
				    		var value;
				    		if(rec.checked){
				    			value = '<input class="icon_role_checkbox" checked="checked" type="checkbox" value="'+rec.roleId+'"/>';
				    		}else{
				    			value = '<input class="icon_role_checkbox" type="checkbox" value="'+rec.roleId+'"/>';
				    		}
				    		return value;
				    	}
				    },
		    {title: sys_role_code, width:'150',field:'roleId',align:'left'},
			{title: sys_role_name, width:'150',field:'title',align:'left'},
			{title: sys_role_state, width:'100',field:'status',align:'center'},
			{title: sys_role_description, width:'300',field:'description',align:'left'}
		]],
		toolbar:[
			{
				id:'btnAdd',
				text: sys_role_confirm,
				iconCls:'icon-add',
				handler:function(){
					var ids = [];
					$.messager.confirm(sys_icon_list_confirm, sys_icon_list_confirmInf, function(r){
						if(r){
							$(".icon_role_checkbox").each(function(){
								if($(this).attr("checked")){
									ids.push($(this).val());
								}
								
							});
							grant(ids.join(','));
						}
					});
					
				}
			}
		],
		pagination : true,
		rownumbers : true

	});
});

function grant(ids)
{
	$.post(basePath + "/icon/icon!grant.action", {
		'id' : $("#icon_role_id").val(),
		'roleIds' : ids
	},function(response) {
		if(response.success==true)
		{
			$('#sysIcon_perm').window('close');
			$('#icon_role_datagrid').datagrid('reload');
		}
		$.messager.leftshow({				
			msg : response.msg,
			height : 20
		});
	});
}