<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/icon/icon_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/icon/icon_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center"  align="center" style="padding-top:10px; color:#333;">
		<form id="sys_icon_form"  method="post" action="${ctx}/icon/icon!save.action">
			<table id="sys_icon_add" width="100%">
				<tr>
					<td align="right" width="30%"><s:text name="sys_icon_name"></s:text></td>
					<td align="left" width="70%">
						<input type='hidden' id='id' name='id' value='${id}'/>
						<input name="title" id="title" value="${title}" class="easyui-validatebox" required="true"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_icon_path"></s:text></td>
					<td align="left" width="70%">
						<textarea name="iconUrl" id="iconUrl" style="width:150px;resize:none;">${iconUrl}</textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_function_path"></s:text></td>
					<td align="left" width="70%">
						<textarea name="url" id="url" style="width:150px;resize:none;">${url}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_icon_input" class="easyui-linkbutton" style="display: block; width: 65px; float: right;" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_icon_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>