function setClickEvent(){
	$(".permission_checkbox").click(function(){
		var id = $(this).val();
		var checked = $(this).attr("checked");
		if(checked){
			selectParent(id);
		}else{
			unselectChildren(id);
		}
	});
}

$(document).ready(function() {
	sy.ns('sys.permission.treegrid');
	
	$('#permission_treegrid').treegrid({
		title:'',
		iconCls:'icon-save',
		height: 500,
		url : basePath+'/permission/permission!findAll.action?roleId='+$("#permission_roleId").val(),
		loadMsg : '数据装载中......',
		idField : "permissionId",
		treeField: "title",
		columns:[[
		    {title: resource_chose, width:'50',field:'1',align:'center',
		    	formatter:function(value,rec,index){
		    		var value;
		    		if(rec.checked){
		    			value = '<input class="permission_checkbox" checked="checked" id="'+rec.permissionId+'" type="checkbox" value="'+rec.permissionId+'"/>';
		    		}else{
		    			value = '<input class="permission_checkbox" type="checkbox" id="'+rec.permissionId+'" value="'+rec.permissionId+'"/>';
		    		}
		    		return value;
		    	}
		    },
			{title:'资源编码',width:'150',field:'permissionId',align:'left',hidden:true},
			{title: resource_name, width:'250',field:'title',align:'left'},
			{title: resource_type, width:'100',field:'resourceType',align:'center'},
			{title: resource_url, width:'400',field:'url',align:'left'},
			{title:'父资源编码',width:'300',field:'_parentId',align:'left', hidden:true}
		]],
		toolbar:[
			{
				id:'btnAdd',
				text: resource_confirm,
				iconCls:'icon-add',
				handler:function(){
					var ids = [];
					$.messager.confirm(permission_confirm, permission_confirmInf, function(r){
						if(r){
							$(".permission_checkbox").each(function(){
								if($(this).attr("checked")){
									ids.push($(this).val());
								}
								
							});
							save(ids.join(','));
						}
					});
					
				}
			}
		],
		onLoadSuccess:function(){
			setClickEvent();
		}
	});
	//setTimeout(setClickEvent,100);
});

function selectParent(id){
	var parentNode = $("#permission_treegrid").treegrid("getParent",id);
	if(parentNode){
		var parentId = parentNode.permissionId;
		var parentCheckBox = $("#"+parentId);
		if(parentCheckBox.attr("checked"))
		{
			return;
		}
		parentCheckBox.attr("checked","checked");
		selectParent(parentId);
	}
}

function unselectChildren(id){
	var children = $("#permission_treegrid").treegrid("getChildren",id);
	if(children && children!=""){
		$("tr[node-id="+id+"]").next().find("input[type=checkbox]:checked").attr("checked",false);
	}
	/*
	var children = $("#permission_treegrid").treegrid("getChildren",id);
	if(children && children!=""){
		for(var i=0;i<children.length;i++){
			var childId = children[i].permissionId;
			var childCheckBox = $("#"+childId);
			childCheckBox.attr("checked",false);
		}
	}*/
	
}


function save(ids)
{
	$.post(basePath + "/permission/permission!save.action", {'roleId' : $("#permission_roleId").val(),'resourceIds' : ids},function(response) {
		if(response.success==true){
			$('#sysRole_permission').window('close');
			$('#sysRole_datagrid').datagrid('reload');
		}
		$.messager.leftshow({				
			msg : response.msg,
			height : 20
		});
	});
}

