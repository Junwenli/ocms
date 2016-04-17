<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/user/user_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/user/user-input.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var userId = $("#userId").val();
	if(userId == ''){
		$("<option value='' selected='selected'><s:text name='sys_user_departmentMapDefaultValue'></s:text></option>").appendTo("#departmentId");
	}
});
</script>

<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;" >
		<form id="sys_user_form" method="post" action="${ctx}/user/user!saveByOperate.action">
		<input type="hidden" name="userOperate" id="userOperate" value="${operate }"/>
		<input name="id" id="userId" value="${id}" type="hidden"/>
		<table id="sys_user_add" >
			<tr>
				<td align="right"><s:text name="sys_user_account"></s:text></td>
				<td align="left" width="70%">
				<s:if test="operate==null||operate">
							<input name="userCode" id="userCode" class="easyui-validatebox" required="true" validType="isFit&maxLength[30]"/>
						</s:if>
						<s:else>
							<font class="edit-font">${userCode}</font><input name="userCode" id="userCode" value="${userCode}"  type="hidden"></input>
						</s:else>
				</td>
			</tr>
			<s:if test="operate==null||operate">
				<tr>
					<td align="right"><s:text name="sys_user_password"></s:text></td>
					<td align="left" width="70%">
						<input type="password" style="width: 149px" name="authenticator" id="authenticator" value="" 
						class="easyui-validatebox" required="true" missingMessage="<s:text name='sys_userPasswordRequired'></s:text>" validType="length[6,16]" invalidMessage="<s:text name='sys_user_passwordLength'></s:text>"/>
					</td>
				</tr>
			</s:if>
			<s:else>
					<input type="hidden" name="authenticator" id="authenticator" value="${authenticator}" class="easyui-validatebox" />
					
			</s:else>
			<tr>
				<td align="right"><s:text name="sys_user_name"></s:text></td>
				<td align="left" width="70%">
					<input name="name" width="200px" id="name" value="${name}" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_userNameRequired'></s:text>" validType="length[1,30]" invalidMessage="<s:text name='sys_user_NameLength'></s:text>"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_email"></s:text></td>
				<td align="left" width="70%">
					<input name="email" width="200px" id="email" value="${email}" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_userEmailRequired'></s:text>" validType="email" invalidMessage="<s:text name='sys_userEmailInvalid'></s:text>"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_mobile"></s:text></td>
				<td align="left" width="70%">
					<input name="mobile" width="200px" id="mobile" value="${mobile}" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_userMobileRequired'></s:text>" validType="length[11,11]" invalidMessage="<s:text name='sys_user_MobileLength'></s:text>"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_statusDesc"></s:text></td>
				<td align="left">
					<%--
					<s:radio name="status" list="#{'0':'冻结','1':'激活'}" theme="simple"></s:radio>
					--%>
					<s:radio name="status" list="@com.linkcm.core.entity.type.UserStatus@values()" listKey="value" listValue="desc" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_department_name"></s:text></td>
				<td align="left" id="user_input_deptCodeMsg">
				<%-- 
				<s:select id="departmentId" name="deptId" list="departmentMap" headerKey="" headerValue="请选择"
				theme="simple" onchange="UserDeptChange()"></s:select>
				--%>
				<s:select id="departmentId" name="deptId" list="departmentMap" theme="simple" onchange="UserDeptChange()"></s:select>
				</td>
			</tr>
			
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_user_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_user_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>