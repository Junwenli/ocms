<%@ taglib prefix="s" uri="/struts-tags" %>
sy.ns('${module}.${lowerCaseName}');
//按钮
${module}.${lowerCaseName}.buildButtons = function(){
	var buttons = [];
	sy.buildAddButton(buttons,'ADD_${upperCaseName}','${module}_${lowerCaseName}_add',{cache:false,title: "添加${desc}", href:basePath+'/${lowerCaseName}/${requestPath}!input.action',width:'280'});
	sy.buildDelButton(buttons,'BATCH_DELETE_${upperCaseName}','${module}_${lowerCaseName}_datagrid',${module}.${lowerCaseName}.del);
	sy.buildQueryButton(buttons,'QUERY_${upperCaseName}','${module}_${lowerCaseName}_search',{cache: false,title: "查询${desc}", width:"250"});
	return buttons;
};
//数据列表
${module}.${lowerCaseName}.columns = [<s:iterator value="entityCols" id="col" status="offset"><s:if test="#col.id!=true">
			{title:'${col.desc}',width:'150',field:'<s:if test="#col.enum">${fieldName}Desc</s:if><s:else>${fieldName}</s:else>',align:'left'}<s:if test="#offset.last==false">,</s:if></s:if></s:iterator>
	    		];
//删除记录
${module}.${lowerCaseName}.del = function(id)
{
	sy.postDel(id,basePath + "/${lowerCaseName}/${requestPath}!delete.action",'${module}_${lowerCaseName}_datagrid',"${desc}管理");
};
//修改记录
${module}.${lowerCaseName}.postUpdate = function (id)
{
	$('#${module}_${lowerCaseName}_add').window({cache:false,title:'修改${desc}',href:basePath+"/${lowerCaseName}/${requestPath}!input.action?operate=false&id="+id,width:'280'});
	$('#${module}_${lowerCaseName}_add').window('open');
};
//双击事件
${module}.${lowerCaseName}.doubleClick = function(rowIndex, rowData)
{
	${module}.${lowerCaseName}.postUpdate(rowData.id);
};
//清空查询条件
${module}.${lowerCaseName}.clear = function(){
	$('#${module}_${lowerCaseName}_search_form')[0].reset();
	$('#${module}_${lowerCaseName}_datagrid').datagrid("load",{});
};
//查询
${module}.${lowerCaseName}.search =function(){
	sy.search('${module}_${lowerCaseName}_datagrid','${module}_${lowerCaseName}_search_form');
};
<%@ page contentType="text/html;charset=UTF-8" %>