<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/area/area_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/area/area-input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;">
		<form id="sys_area_form"  method="post" action="${ctx}/area/area!saveByOperate.action">
		<input type="hidden" name="deptOperate" id="deptOperate" value="${operate }"/>
		<input name="id" id="id" value="${id}"  type="hidden"/>
		<table id="sys_area_add">
			<tr>
				<td align="right"><s:text name="sys_area_code"></s:text></td>
				<td align="left" width="70%">
					<s:if test="operate==null||operate">
					<!--  
						<input name="deptCode" id="deptCode" value="${deptCode}" class="easyui-validatebox"  
						required="true" missingMessage="部门编码必填" validType="isFit&maxLength[30]"/>
					-->
						<input name="areaCode" id="areaCode" value="${areaCode}" class="easyui-validatebox"  
						required="true" missingMessage="<s:text name='sys_area_codeRequired'></s:text>" validType="isFit&maxLength[30]"/>
					</s:if>
					<s:else>
						<font class="edit-font">${areaCode}</font><input name="areaCode" id="areaCode" value="${areaCode}"  type="hidden"></input>
					</s:else>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_area_name"></s:text></td>
				<td align="left" width="70%">
					<input name="areaName" id="areaName" value="${areaName}" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_area_nameRequired'></s:text>" validType="length[1,50]" invalidMessage="账号1-50位组成"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_area_description"></s:text></td>
				<td align="left" width="70%"> 
					<textarea name="description" id="description" style="width:150px;resize:none;" class="easyui-validatebox">${description }</textarea>
				</td>
			</tr>
			
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_area_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_area_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>