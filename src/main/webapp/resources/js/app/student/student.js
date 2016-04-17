sy.ns('school.student');
$(document).ready(function() {
	var buttons = school.student.buildButtons();
	var operation ={title: "操作", width:'340',field:'opt',align:'center', 
	formatter:function(value,rec,index){
		var ops = [];
		if(hasPermission("UPDATE_STUDENT")){
			var updateOpt = '<a class="a-icon" title="'+ "学生修 改" +'" onclick="school.student.postUpdate(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ "修改" +'</a>';
			ops.push(updateOpt);
		}
		if(hasPermission("BATCH_DELETE_STUDENT")){
			var delOpt = '<a class="a-icon" title="'+ "删除" +'" onclick="school.student.del(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ "删除" +'</a>';
			ops.push(delOpt);
		}
		return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
	}
};
	var datagrid={
			url:basePath+'/student/student!listAjax.action',
			toolbar:buttons,
			onDblClickRow:school.student.doubleClick
			};
	sy.datagrid("school_student_datagrid",datagrid,school.student.columns,true,operation);
});