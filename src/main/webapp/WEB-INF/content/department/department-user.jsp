<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/department/department_user_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/department/department-user.js">
</script>
<div class="easyui-layout" fit="true">
	<div region="center" align="left">
		<input type="hidden" name="operate" id="operate" value="${operate }"/>
		${id}<input type="hidden" name="deptId" id="deptId" value="${id}"/><br>
		<ul id="userTree"></ul>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_department_user_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>