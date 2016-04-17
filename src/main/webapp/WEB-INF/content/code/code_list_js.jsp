<%@ taglib prefix="s" uri="/struts-tags" %>
sy.ns('${module}.${lowerCaseName}');
$(document).ready(function() {
	var buttons = ${module}.${lowerCaseName}.buildButtons();
	var operation = {title:'操作',width:'180',field:'opt',align:'center', 
			formatter:function(value,rec,index){
				var ops = [];
				if(hasPermission("BATCH_DELETE_${upperCaseName}")){
					var delOpt = '<a title="'+ sy_delete +'" onclick="${module}.${lowerCaseName}.del(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-del.gif) no-repeat">'+ sy_delete +'</a>';
					ops.push(delOpt);
				}
				if(hasPermission("UPDATE_${upperCaseName}")){
					var updateOpt = '<a title="'+ sy_update +'" onclick="${module}.${lowerCaseName}.postUpdate(\''+ rec.id + '\')" class="a-icon" style="background:url('+basePath+'/resources/images/icon-update.gif) no-repeat">'+ sy_update +'</a>';
					ops.push(updateOpt);
				}
				return ops.join('&nbsp;&nbsp;&nbsp;&nbsp;');
				}
			};
	var datagrid={
			url:basePath+'/${lowerCaseName}/${requestPath}!listAjax.action',
			title:"${desc}管理",
			toolbar:buttons,
			onDblClickRow:${module}.${lowerCaseName}.doubleClick
			};
	sy.datagrid("${module}_${lowerCaseName}_datagrid",datagrid,${module}.${lowerCaseName}.columns,true,operation);
});
<%@ page contentType="text/html;charset=UTF-8" %>
