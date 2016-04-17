sy.ns('school.classinfo');
$(document).ready(function() {
	var buttons = school.classinfo.buildButtons();
	var operation ={title: "操作", width:'340',field:'opt',align:'center', 
	formatter:function(value,rec,index){
		var ops = [];
		if(hasPermission("UPDATE_CLASSINFO")){
			var updateOpt = '<a class="a-icon" title="'+ "班级修 改" +'" onclick="school.classinfo.postUpdate(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ "修改" +'</a>';
			ops.push(updateOpt);
		}
		if(hasPermission("BATCH_DELETE_CLASSINFO")){
			var delOpt = '<a class="a-icon" title="'+ "删除" +'" onclick="school.classinfo.del(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ "删除" +'</a>';
			ops.push(delOpt);
		}
		if(hasPermission("QUERY_STUDENT")){
			var pwdOpt = '<a class="a-icon" title="班级学生" onclick="school.classinfo.student(\''+ rec.id + '\')" style="background:url('+basePath+'/resources/images/icon-dept-user.gif) no-repeat">班级学生</a>';
			ops.push(pwdOpt);
		}
		return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
	}
};
	var datagrid={
			url:basePath+'/classinfo/class-info!listAjax.action',
			toolbar:buttons,
			onDblClickRow:school.classinfo.doubleClick
			};
	sy.datagrid("school_classinfo_datagrid",datagrid,school.classinfo.columns,true,operation);
});
