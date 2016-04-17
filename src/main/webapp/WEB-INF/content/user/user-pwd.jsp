<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
	String userId=request.getAttribute("id").toString();
	boolean operate=(Boolean)request.getAttribute("operate");
%>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/user/user_pwd_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/user/user-pwd.js">
	
</script>
<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;">
	
		<input type="hidden" name="operate" id="operate" value="${operate }"/>
		<input type="hidden" name="userId" id="userId" value="${id}"/>
		<form id="pwdInfo">
		<table id="sys_user_pwd">
		
			<tr>
				<td align="right"><s:text name="sys_user_password"></s:text></td>
				<td>
					<input type="password" name="authenticator" id="authenticator" value="" 
					class="easyui-validatebox" required="true" missingMessage="<s:text name='sys_userPasswordRequired'></s:text>" validType="passwordFormat" />
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_confirmPassword"></s:text></td>
				<td>
					<input type="password" name="confirmAuthenticator" id="confirmAuthenticator" value="" 
					class="easyui-validatebox" required="true" missingMessage="<s:text name='sys_userConfirmPasswordRequired'></s:text>" validType="passwordFormat&eqPassword['#authenticator']"/>
				</td>
			</tr>
		</table>
		</form>
		
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_user_pwd.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>