$(document).ready(function() {
	
	sy.ns('sys.cookie_message');
	$('#cookie_message_datagrid').datagrid({
		title:'信息提示列表',
		iconCls:'icon-save',
		height: 'auto',
		url : basePath+'/common/common!getLog.action',
		loadMsg : '数据装载中......',
		columns:[[
		    {title:'时间',width:'150',field:'time',align:'left'},
			{title:'模块名称',width:'150',field:'module',align:'left'},	
			{title:'提示信息',width:'200',field:'msg',align:'center'}		
		]],
		rownumbers : true,
		toolbar:[
			{
				id:'btnRef',
				text:'刷新',
				iconCls:'pagination-load',
				handler:function(){
					$('#cookie_message_datagrid').datagrid("reload");
				}
			}
		]

	});
});

