sy.ns('school.classinfo');
school.classinfo.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_CLASSINFO','school_classinfo_add',{cache:false,title: "添加班级", href:basePath+'/classinfo/class-info!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_CLASSINFO','school_classinfo_datagrid',school.classinfo.del);
	sy.buildQueryButton(buttons,'QUERY_CLASSINFO','school_classinfo_search',{cache: false,title: "查询班级", width:"250"});
	return buttons;
};
//数据列
school.classinfo.columns = [
        	       		    {title : "班级编码",width : '100',field : 'id',align : 'center',hidden : true},
        	       		    {title : "班级名称",width : '100',field : 'classname',align : 'center'},
        	       		    {title : "班主任",width : '100',field : 'teacher',align : 'center'}	
        	    		];
//删除记录
school.classinfo.del = function(id)
{
	sy.postDel(id,basePath + "/classinfo/class-info!delete.action",'school_classinfo_datagrid');
};
//双击事件
school.classinfo.doubleClick = function(rowIndex, rowData)
{
	school.classinfo.postUpdate(rowData.id);
};
school.classinfo.postUpdate = function (id)
{
	$('#school_classinfo_add').window({cache:false,title: "修改", href:basePath+"/classinfo/class-info!input.action?operate=false&id="+id,width:'280'});
	$('#school_classinfo_add').window('open');
};
//清空查询条件
school.classinfo.clear = function(){
	$('#school_classinfo_search_form')[0].reset();
	$('#school_classinfo_datagrid').datagrid("load",{});
};
//查询
school.classinfo.search =function(){
	sy.search('school_classinfo_datagrid','school_classinfo_search_form');
};

school.classinfo.student=function (classId){
	var datagrid={
			height:280,
			url:basePath+'/student/student!listAjax.action?filter_EQS_class_Id='+classId,
			width:'auto',
			pageNumber : 1
		};
	sy.datagrid("school_classinfo_student_datagrid",datagrid,school.student.columns);
	$("#school_classinfo_student_data").window({cache:false,title: '班级学生', height:315});
	$("#school_classinfo_student_data").window("open");
};
