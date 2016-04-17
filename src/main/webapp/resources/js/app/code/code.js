sy.ns('sys.code');
sys.code.columns=[
      			{title:'表名',width:'150',field:'tablename',align:'left'},
    			{title:'操作',width:'180',field:'opt',align:'center', 
    				formatter:function(value,rec,index){
    					var addOpt = '<img class="li-op" src="'+basePath+'/resources/images/icon-grant.gif" title="生成后台代码" onclick="sys.code.openWindow(\''+rec.tablename+'\')"></img>';
    					return addOpt;
    				}
    			}
    		];
$(document).ready(function() {
	var buttons = [
	   			{
		        	 id:'btnAdd',
						text:'查询',
						iconCls:'icon-search',
						handler:function(){
							$('#sys_code_search').css('display','block');
							$('#sys_code_search').window({cache: false,title:'数据表查询', width:"250",heigth:'100'});
							$('#sys_code_search').window('open');
						} 
		         }
			];
	
	var datagrid={
			url:basePath+'/code/code!listAjax.action',
			toolbar:buttons
			};
	sy.datagrid("sys_code_datagrid",datagrid,sys.code.columns);
});

sys.code.openWindow = function(table){
	$('#sys_code_add').window({cache: false,title:'代码生成'});
	$('#sys_code_add').window('open');
	$("#code_table").html(table);
};
sys.code.addCode = function()
{
	var table = $("#code_table").html();
	var name = $("#code_entity").val();
	var packageName = $("#code_package").val();
	var module = $("#code_module").val();;
	var desc = $("#code_desc").val();
//	$("#sys_code_add").window("close");
//	$("#code_entity").val("");
//	$("#code_package").val("");
//	$("#code_module").val("");;
//	$("#code_desc").val("");
	$.post(basePath+"/code/code!dao.action?_="+Math.random(),{'name':name,'packageName':packageName,'table':table,'module':module},function(data){
		$.post(basePath+"/code/code!generate.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'Repository.java','folder':'repository'});
	});
	$.post(basePath+"/code/code!entity.action?_="+Math.random(),{'name':name,'packageName':packageName,'table':table,'module':module,'table':table},function(data){
		$.post(basePath+"/code/code!generateEntity.action",{'name':name,'packageName':packageName,'module':module,'table':table,'data':data,'file':'.java','folder':'entity'});
	});
	$.post(basePath+"/code/code!findEnums.action?_="+Math.random(),{'table':table},function(enumNames){
		for(var i=0;i<enumNames.length;i++){
			var enumName = enumNames[i];
			$.ajax({url:basePath+"/code/code!type.action?_="+Math.random(),
				async:false,
				data:{'name':name,'packageName':packageName,'module':module,'table':table,'enumName':enumName},
				success:function(data){
				if(!data.success){
					$.post(basePath+"/code/code!generateEnum.action",{'name':name,'packageName':packageName,'module':module,'table':table,'enumName':enumName,'data':data,'file':'.java','folder':'entity/type'});
				}
			}});
		}
	});
//	$.post(basePath+"/code/code!test.action",{'name':name,'packageName':packageName,'module':module,'desc':desc,'table':table},function(data){
//		$.post(basePath+"/code/code!generate.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'ServiceTest.java','folder':'service'});
//	});
	$.post(basePath+"/code/code!service.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'desc':desc,'table':table},function(data){
		$.post(basePath+"/code/code!generate.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'Service.java','folder':'service'});
	});
	$.post(basePath+"/code/code!action.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'desc':desc,'table':table},function(data){
		$.post(basePath+"/code/code!generate.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'Action.java','folder':'web'});
	});
	$.post(basePath+"/code/code!daoImpl.action?_="+Math.random(),{'name':name,'packageName':packageName,'table':table,'module':module},function(data){
		$.post(basePath+"/code/code!generateDaoImpl.action",{'name':name,'packageName':packageName,'table':table,'module':module,'data':data,'file':'RepositoryImpl.java','folder':'repository'});
	});
	$.post(basePath+"/code/code!datagridJs.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'desc':desc,'table':table},function(data){
		$.post(basePath+"/code/code!generateJs.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'_datagrid.js','folder':name});
	});
	$.post(basePath+"/code/code!listJs.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'desc':desc,'table':table},function(data){
		$.post(basePath+"/code/code!generateJs.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'.js','folder':name});
	});
	$.post(basePath+"/code/code!listJsp.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'table':table},function(data){
		$.post(basePath+"/code/code!generateJsp.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'.jsp','folder':name});
	});
	$.post(basePath+"/code/code!inputJsp.action?_="+Math.random(),{'name':name,'packageName':packageName,'module':module,'table':table},function(data){
		$.post(basePath+"/code/code!generateJsp.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'_input.jsp','folder':name});
	});
	$.post(basePath+"/code/code!inputJs.action",{'name':name,'packageName':packageName,'module':module,'desc':desc},function(data){
		$.post(basePath+"/code/code!generateJs.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'_input.js','folder':name});
	});
	$.post(basePath+"/code/code!sql.action",{'name':name,'packageName':packageName,'module':module,'desc':desc},function(data){
		$.post(basePath+"/code/code!generateSql.action",{'name':name,'packageName':packageName,'module':module,'data':data,'file':'.sql','folder':name});
	});
};
sys.code.clear = function(){
	$('#sys_code_search_form')[0].reset();
	$('#sys_code_datagrid').datagrid("load",{});
};
//查询
sys.code.search =function(){
	$('#sys_code_datagrid').datagrid('load',{
		filter_LIKES_tablename : $('#sys_code_tablename').val()
	});
};
