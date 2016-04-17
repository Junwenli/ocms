<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/common/modifyPassword.js"></script>
<div align="center" style="padding-top:10px; color:#333;">
	<form id="modifyPassword_form">
		<table>
			<tr>
				<td><s:text name='top_oldPassword'></s:text></td>
				<td><input id="modifyPassword_input_oldPassword" class="easyui-validatebox" required="true" type="password" missingMessage="<s:text name='sys_changeOldPasswordRequired'></s:text>" validType="passwordFormat"/></td>
			</tr>
			<tr>
				<td><s:text name='top_newPassword'></s:text></td>
				<td><input id="modifyPassword_input_password" class="easyui-validatebox" required="true" type="password" missingMessage="<s:text name='sys_changeNewPasswordRequired'></s:text>" validType="passwordFormat"/></td>
			</tr>
			<tr>
				<td><s:text name='top_confirmPassword'></s:text></td>
				<td><input id="modifyPassword_input_rePassword" class="easyui-validatebox" required="true" validType="passwordFormat&eqPassword['#modifyPassword_input_password']" type="password" missingMessage="<s:text name='sys_changeConfirmPasswordRequired'></s:text>"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><a class="easyui-linkbutton" href="javascript:void(0);" onclick="sy.modifyPassword.modify();"><s:text name='top_update'></s:text></a></td>
			</tr>
		</table>
	</form>
</div>