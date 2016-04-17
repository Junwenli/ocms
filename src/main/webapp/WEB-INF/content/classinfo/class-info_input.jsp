<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/classinfo/class-info_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;">
		<form id="school_classinfo_form"  method="post" action="${ctx}/classinfo/class-info!saveByOperate.action">
		<input type="hidden" name="operate" value="${operate}"/>
		<input name="id" id="id" value="${id}"  type="hidden"/>
		<table  >
			<tr>
				<td align="right"><s:text name="班级名称"></s:text></td>
				<td align="left" width="70%">
					<input name="classname" id="classname" value="${classname}" class="easyui-validatebox"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="班主任"></s:text></td>
				<td align="left" width="70%">
					<input name="teacher" id="teacher" value="${teacher}" class="easyui-validatebox"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="school_classinfo_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="school.classinfo.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>