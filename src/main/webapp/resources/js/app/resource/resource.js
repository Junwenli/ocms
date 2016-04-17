$(document).ready(function() {
	sy.ns('sys.resource');
	
	var buttons = buildButtons();
	function buildButtons() {
		var buttons = [];
		var operation;
		operation = hasPermission('ADD_RESOURCE');
		if (operation) {
			buttons.push({
				id : 'btnAdd',
				text : operation.title,
				iconCls : 'icon-add',
				handler : function() {
					var node = $('#sys_resource_treegrid').treegrid('getSelected');
					if (node) {
							$('#resource_add').window({cache : false,title : sys_resource_addtitle,href : basePath+ "/resource/resource!input.action?_parentId="+ node.id+ "&operate=true&_="+ Math.random(),width : 380});
							$('#resource_add').window('open');
							} else {
							$('#resource_add').window({chache : false,title : sys_resource_addtitle,href : basePath+ "/resource/resource!input.action?operate=true&_="+ Math.random(),width : 380});
							$('#resource_add').window('open');
					}
				}
			});
		}
		return buttons;
	}
	// 删除记录
	sys.resource.del = function(id) {
		$.messager.confirm(sys_resource_confirm, sys_resource_confirmInf, function(r) {
			if (r) {
				$.post(basePath + "/resource/resource!delete.action", {'id' : id}, function(response) {
					if (response.success == true) {
						$('#sys_resource_treegrid').treegrid('reload');
					}
					$.messager.leftshow({
						msg : response.msg,
						height : 20
					});
				});
			}
		});
	};

	// 修改记录
	sys.resource.modify = function(id) {
		if (id) {
			$('#resource_add').window({title : sys_resource_updatetitle,href : basePath+ "/resource/resource!input.action?operate=false&id="+ id + "&_=" + Math.random()});
			$('#resource_add').window('open');
			$('#resource_add').window("refresh");
		} else {
			$.messager.leftshow({
				msg : '请选择要修改的记录',
				height : 20
			});
		}
	};
	
	
	var winHeight = $("#centerDiv").height() - 90;
	$('#sys_resource_treegrid').treegrid({
		iconCls : 'icon-save',
		height : winHeight,
		url : basePath+ '/resource/resource!findAllDataGrid.action',
		loadMsg : sys_resource_loadMsg,
		idField : "id",
		treeField : "title",
		columns : [ [
		    {width : '0',field : 'id',align : 'left',hidden : true},
			{title : '资源编码',width : '0',field : 'permissionCode',align : 'left',hidden : true},
			{title : sys_resource_name,width : '250',field : 'title',align : 'left'},
			{title : '资源类型码',width : '0',field : 'permissionType',align : 'left',hidden : true},
			{title : sys_resource_type,width : '140',field : 'resourceType',align : 'left'},
			{title : sys_resource_url,width : '350',field : 'url',align : 'left'},
			{title : '父资源编码',width : '0',field : '_parentId',align : 'left',hidden : true},
			{title : sys_resource_operation,width : '200',field : 'model',align : 'center',
				formatter : function(value, row, index){
					var ops = [];
					var operation = "";
					if(hasPermission("DELETE_RESOURCE")){
						var delOpt = '<a class="a-icon" title="'+ sys_resource_delete +'" onclick="sys.resource.del(\''+ row.id + '\')" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ sys_resource_delete +'</a>';
						ops.push(delOpt);
					}
					if(hasPermission("UPDATE_RESOURCE")){
						var modOpt = '<a class="a-icon" title="'+ sys_resource_update +'" onclick="sys.resource.modify(\''+ row.id + '\')" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ sys_resource_update +'</a>';
						ops.push(modOpt);
					}
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
		toolbar : buttons
	});
});
