sy.ns('school.student');
school.student.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_STUDENT','school_student_add',{cache:false,title: "添加学生", href:basePath+'/student/student!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_STUDENT','school_student_datagrid',school.student.del);
	sy.buildQueryButton(buttons,'QUERY_STUDENT','school_student_search',{cache: false,title: "查询学生", width:"250"});
	sy.buildExportButton(buttons,'EXPORT_STUDENT','school_student_search_form',basePath+"/student/student!export.action");
	return buttons;
};
//数据列
school.student.columns = [
      	       		    {title : "名字",width : '100',field : 'name',align : 'center'},
    	       		    {title : "年龄",width : '100',field : 'age',align : 'center'}	,
    	       		    {title : "性别",width : '100',field : 'sexDesc',align : 'center'},
    	       		    {title : "班级",width : '100',field : 'classTitle',align : 'center'}
    	    		];
//删除记录
school.student.del = function(id)
{
	sy.postDel(id,basePath + "/student/student!delete.action",'school_student_datagrid');
};

school.student.postUpdate = function (id)
{
	$('#school_student_add').window({cache:false,title: "修改", href:basePath+"/student/student!input.action?operate=false&id="+id,width:'280'});
	$('#school_student_add').window('open');
};
//双击事件
school.student.doubleClick = function(rowIndex, rowData)
{
	school.student.postUpdate(rowData.id);
};
//清空查询条件
school.student.clear = function(){
	$('#school_student_search_form')[0].reset();
	$('#school_student_datagrid').datagrid("load",{});
};
//查询
school.student.search =function(){
	sy.search('school_student_datagrid','school_student_search_form');
};