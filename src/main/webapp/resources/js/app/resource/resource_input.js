$(document).ready(function() {
	sy.ns('sys.resource.input');
	if(hasPermission("SAVE_RESOURCE")){
		$("#sys_resource_input").show();
	}
	
	//设置显示父资源名
	if($('#parentTitle').html()==""){
		$('#parentTitle').html("系统资源");
	}
	
	sys.resource.input.add = function() {
		if($('#sys_resource_form').form('validate')){
			sy.trim('sys_resource_form','permissionId','permissionCode','title','url');
			$('#sys_resource_form').ajaxSubmit({success:function(response){
				if(response.success==true){
					$('#resource_add').window('close');
					$('#sys_resource_treegrid').treegrid('reload');
				}
				$.messager.leftshow({				
					msg : response.msg,
					height:20
				});
			
			}});
			
		}
	};

});
