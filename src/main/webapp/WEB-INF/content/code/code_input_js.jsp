$(document).ready(function() {
	if(hasPermission("SAVE_${upperCaseName}")){
		$("#${module}_${lowerCaseName}_input").show();
	}
	${module}.${lowerCaseName}.add = function() {
		sy.trim('${module}_${lowerCaseName}_form');
		sy.ajaxSubmit("${module}_${lowerCaseName}_form","${module}_${lowerCaseName}_add","${module}_${lowerCaseName}_datagrid");
	};
});
<%@ page contentType="text/html;charset=UTF-8" %>