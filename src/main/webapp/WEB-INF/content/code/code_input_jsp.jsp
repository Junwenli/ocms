<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set var="entityName" value="${name}"/>
<script type="text/javascript">var basePath='\${ctx}';</script>
<script type="text/javascript" src="\${ctx}/resources/js/app/${lowerCaseName}/${requestPath}_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center"  align="center" style="padding-top:10px; color:#333;">
		<form id="${module}_${lowerCaseName}_form" method="post" action="\${ctx}/${lowerCaseName}/${requestPath}!saveByOperate.action">
			<table id="${module}_${lowerCaseName}_add" width="100%">
				<input type="hidden" name="id" value="\$\{id}"/><s:iterator value="entityCols" id="col"><s:if test="#col.id!=true">
				<tr>
					<td align="right" width="30%">${col.desc}：</td>
					<td align="left" width="70%">
					<s:if test="#col.enum"><<s:if test="#col.radio">s:radio</s:if><s:else>s:select</s:else> name="${fieldName}" list="@${packageName}.entity.type.${entityName}${enumFieldName}@values()" listKey="value" listValue="desc" theme="simple"/></s:if><s:else><input name="${fieldName}" value="\$\{${fieldName}}" <s:property escape='false' value="#col.validate"/>/></s:else>
					</td>
				</tr></s:if></s:iterator>
			</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="${module}_${lowerCaseName}_input" style="display:none;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="${module}.${lowerCaseName}.add();">\\<\\s:text name="global_submit"><\\/s:text></a>
	</div>
</div>
