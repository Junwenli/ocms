<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/department/department_input_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/department/department-input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;">
		<form id="sys_department_form"  method="post" action="${ctx}/department/department!saveByOperate.action">
		<input type="hidden" name="deptOperate" id="deptOperate" value="${operate }"/>
		<input name="id" id="id" value="${id}"  type="hidden"/>
		<table id="sys_department_add" >
			<tr>
				<td align="right"><s:text name="sys_department_code"></s:text></td>
				<td align="left" width="70%">
					<s:if test="operate==null||operate">
					<!--  
						<input name="deptCode" id="deptCode" value="${deptCode}" class="easyui-validatebox"  
						required="true" missingMessage="部门编码必填" validType="isFit&maxLength[30]"/>
					-->
						<input name="deptCode" id="deptCode" value="${deptCode}" class="easyui-validatebox"  
						required="true" missingMessage="<s:text name='sys_department_codeRequired'></s:text>" validType="isFit&maxLength[30]"/>
					</s:if>
					<s:else>
						<font class="edit-font">${deptCode}</font><input name="deptCode" id="deptCode" value="${deptCode}"  type="hidden"></input>
					</s:else>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_department_name"></s:text></td>
				<td align="left" width="70%">
					<input name="title" id="title" value="${title}" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_department_nameRequired'></s:text>" validType="length[1,50]" invalidMessage="<s:text name='sys_department_nameLength'></s:text>"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_department_description"></s:text></td>
				<td align="left" width="70%"> 
					<textarea name="description" id="description" style="width:150px;resize:none;" class="easyui-validatebox">${description }</textarea>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_department_resRate"></s:text></td>
				<td align="left" width="70%">
					<input name="resRate" id="resRate" value="${resRate }" class="easyui-validatebox" 
					required="true" missingMessage="<s:text name='sys_department_resRateRequired'></s:text>" validType="mustInt" invalidMessage="<s:text name='sys_department_resRateSize'></s:text>" style="width:20px"/>
					<span style="position: relative;right: 2px;color: red;"><s:text name='sys_department_availableResRate'></s:text>${useRate+resRate}%</span>
					<input type="hidden" id="useRate" value="${useRate }"/>
					<input type="hidden" id="oldUseRate" value="${resRate }"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="sys_deparment_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys_department_input.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>