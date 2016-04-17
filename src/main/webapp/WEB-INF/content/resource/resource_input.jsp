<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/resource/resource_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/resource/resource_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center"  align="center" style="padding-top:10px; color:#333;">
		<form id="sys_resource_form"  method="post" action="${ctx}/resource/resource!saveByOperate.action">
		<table id="sys_resource_add" width="100%">
			<tr>
				<td align="right" width="30%"><s:text name="sys_resource_parentName"></s:text></td>
				<td  align="left" width="70%">
					<span id="parentTitle" class="edit-font">${parentTitle}</span>
					<input type="hidden" name="_parentId" id="_parentId" value="${_parentId}" ></input>
				</td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="sys_resource_code"></s:text></td>
				<td align="left" width="70%">
				<input type="hidden" name="operate" id="operate" value="${operate}"/>
				<input name="id" id="permissionId" value="${id}"  type="hidden"/>
					<s:if test="operate == null || operate">
						<input name="permissionCode" id="permissionCode" class="easyui-validatebox" required="true" validType="isFit&maxLength[30]"/>
					</s:if>
					<s:else>
						<font style="font-size:13px">${permissionCode}</font><input name="permissionCode" id="permissionCode" value="${permissionCode}"  type="hidden"></input>
					</s:else>
				</td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="sys_resource_name"></s:text></td>
				<td align="left" width="70%">
					<input name="title" id="title" value="${title}" class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="sys_resource_url"></s:text></td>
				<td align="left" width="70%">
					<input name="url" id="url" value="${url}" class="easyui-validatebox"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="sys_resource_type"></s:text></td>
				<td align="left" width="70%">
					<s:select theme="simple" name="permissionType"
							id="permissionType" list="typeMap"></s:select>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_resource_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys.resource.input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>