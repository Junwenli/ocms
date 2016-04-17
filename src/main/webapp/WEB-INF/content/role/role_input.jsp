<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/role/role_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/role/role_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center"  align="center" style="padding-top:10px; color:#333;">
		<form id="sys_role_form" method="post" action="${ctx}/role/role!saveByOperate.action">
			<table id="sys_role_add" width="100%">
				<tr>
					<td align="right" width="30%"><s:text name="sys_role_code"></s:text></td>
					<td align="left" width="70%">
						<input type="hidden" name="operate" id="roleOperate" value="${operate}"/>
						<input name="id" id="roleId" value="${id}"  type="hidden"/>
						<s:if test="operate==null||operate">
							<input name="roleCode" id="roleCode" class="easyui-validatebox" required="true" validType="isFit&maxLength[30]"/>
						</s:if>
						<s:else>
							<font class="edit-font">${roleCode}</font><input name="roleCode" id="roleCode" value="${roleCode}"  type="hidden"></input>
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_role_name"></s:text></td>
					<td align="left" width="70%">
						<input name="title" id="title" value="${title}" class="easyui-validatebox" required="true" missingMessage="<s:text name='sys_roletitlelRequired'></s:text>" validType="length[2,20]" invalidMessage="<s:text name='sys_roletitleInvalid'></s:text>"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_role_description"></s:text></td>
					<td align="left" width="70%">
						<textarea name="description" id="description" style="width:150px;resize:none;">${description}</textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_role_state"></s:text></td>
					<td  align="left" width="70%">
						<s:radio name="status" list="@com.linkcm.core.entity.type.RoleStatus@values()" listKey="value" listValue="desc" theme="simple"></s:radio>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_role_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_role_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>