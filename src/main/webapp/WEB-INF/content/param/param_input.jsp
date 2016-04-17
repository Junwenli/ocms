<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/param/param_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/param/param_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center"  align="center" style="padding-top:10px; color:#333;">
		<form id="sys_param_form" method="post" action="${ctx}/param/param!saveByOperate.action">
			<table id="sys_param_add" width="100%">
				<tr>
					<td align="right" width="30%"><s:text name="sys_param_name"></s:text></td>
					<td align="left" width="70%">
						<input type="hidden" name="paramOperate" id="paramOperate" value="${operate}"/>
						<input name="id" id="paramId" value="${id}"  type="hidden"/>
						<s:if test="operate==null||operate">
							<input name="paramName" id="paramName" class="easyui-validatebox" required="true" validType="isFit"/>
						</s:if>
						<s:else>
							<font class="edit-font">${paramName}</font><input name="paramName" id="paramName" value="${paramName}"  type="hidden"></input>
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" width="30%"><s:text name="sys_param_value"></s:text></td>
					<td align="left" width="70%">
						<input name="paramValue" id="paramValue" value="${paramValue}" class="easyui-validatebox" required="true"/>
					</td>
				</tr>
			
			</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_param_input" style="display:none; width: 65px; float: right;"  class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_param_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>