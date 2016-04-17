<script type="text/javascript">var basePath='\${ctx}';</script>
<script type="text/javascript" src="\${ctx}/resources/js/app/${lowerCaseName}/${requestPath}_datagrid.js"></script>
<script type="text/javascript" src="\${ctx}/resources/js/app/${lowerCaseName}/${requestPath}.js"></script>
<%@ taglib prefix="s" uri="/struts-tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set var="entityName" value="${name}"/>
<!-- 功能介绍 -->
<div class="introduct-div">
	<span class="introduct-icon"></span>
	功能介绍；
</div>
<!-- 列表 -->
<div class="datagrid-wrapper">
<table id="${module}_${lowerCaseName}_datagrid"></table>
</div>
<!-- 添加修改窗口 -->
<div id="${module}_${lowerCaseName}_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 220px;"></div>
<div id="${module}_${lowerCaseName}_search" style="display:none" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="${module}_${lowerCaseName}_search_form">
		<table><s:iterator value="entityCols" id="col"><s:if test="#col.id!=true">
				<tr>
					<td align="right" width="30%">${col.desc}：</td>
					<td align="left" width="70%">
						<s:if test="#col.enum">\\<\\s:select  name="filter_EQ_${fieldName}" list="${fieldName}Map" theme="simple"/></s:if><s:else><input name="filter_EQ_${fieldName}"/></s:else>
					</td>
				</tr></s:if></s:iterator>
				<tr>
					<td colspan="2" align="right">
						<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="${module}.${lowerCaseName}.clear();">\\<\\s:text name="global_clear"><\\/s:text></a> 
						<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="${module}.${lowerCaseName}.search();">\\<\\s:text name="global_query"><\\/s:text></a>
					</td>
				</tr>
		</table>
	</form>
</div>
<%@ page contentType="text/html;charset=UTF-8" %>