function setClickEvent(){
	$(".givepermission_checkbox").click(function(){
		var id = $(this).val();
		var checked = $(this).attr("checked");
		var unchecked=$(this).attr("unchecked");
		if(unchecked==="true"){
			$(this).attr("checked",true);
			$.messager.leftshow({				
				msg : sys_checkMsg,
				height : 20
			});
			return;
		}
		if(checked){
			selectParent(id);
		}else{
			unselectChildren(id);
		}
	});
}

$(document).ready(function() {
	sy.ns('sys.givePermission.treegrid');
	$('#givepermission_treegrid').treegrid({
		title:'',
		iconCls:'icon-save',
		height: 500,
		url : basePath+'/givepermission/givepermission!findAll.action?userId='+$("#givepermission_userId").val(),
		loadMsg : '数据装载中......',
		idField : "permissionId",
		treeField: "title",
		columns:[[
		    {title: sys_resource_chose, width:'50',field:'1',align:'center',
		    	formatter:function(value,rec,index){
		    		var value;
		    		if(rec.checked||rec.unchecked){
		    			value = '<input class="givepermission_checkbox" checked="checked" unchecked="'+rec.unchecked+'" id="'+rec.permissionId+'" type="checkbox" value="'+rec.permissionId+'"/>';
		    		}else{
		    			value = '<input class="givepermission_checkbox" type="checkbox" unchecked="'+rec.unchecked+'" id="'+rec.permissionId+'" value="'+rec.permissionId+'"/>';
		    		}
		    		return value;
		    	}
		    },
			{title:'资源编码',width:'150',field:'permissionId',align:'left',hidden:true},
			{title: sys_resource_name, width:'250',field:'title',align:'left'},
			{title: sys_resource_type, width:'100',field:'resourceType',align:'center'},
			{title: sys_resource_url, width:'400',field:'url',align:'left'},
			{title:'父资源编码',width:'300',field:'_parentId',align:'left', hidden:true},
			{width:'300',field:'unchecked',align:'left', hidden:true}
		]],
		toolbar:[
			{
				id:'btnAdd',
				text: sys_resource_confirm,
				iconCls:'icon-add',
				handler:function(){
					var ids = [];
					$.messager.confirm("请确认","您确认选择当前权限吗？",function(r){
						if(r){
							$(".givepermission_checkbox").each(function(){
								if($(this).attr("checked")&&$(this).attr("unchecked")==="false"){
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
	var parentNode = $("#givepermission_treegrid").treegrid("getParent",id);
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
	var children = $("#givepermission_treegrid").treegrid("getChildren",id);
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
	$.ajax({
		url : basePath+'/givepermission/givepermission!save.action',
		data : {
			'userId' : $("#givepermission_userId").val(),
			'resourceIds' : ids
		},
		cache : false,
		dataType : "json",
		type : "POST",
		success : function(response) {
			if(response.success==true)
			{
				$('#sysGive_permission').window('close');
				$('#give_permission_datagrid').datagrid('reload');
			}
			$.messager.leftshow({				
				msg : response.msg,
				height : 20
			});
		}
	});
}

