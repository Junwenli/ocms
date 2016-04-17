<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/student/student_input.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center" align="center" style="padding-top:10px; color:#333;">
		<form id="school_student_form"  method="post" action="${ctx}/student/student!saveByOperate.action">
		<input type="hidden" name="operate" value="${operate}"/>
		<input name="id" id="id" value="${id}"  type="hidden"/>
		<table  >
			<tr>
				<td align="right"><s:text name="姓名"></s:text></td>
				<td align="left" width="70%">
					<input name="name" value="${name}" class="easyui-validatebox" required="true" validType="length[2,50]"/>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="年龄"></s:text></td>
				<td align="left" width="70%">
					<input name="age" value="${age}" class="easyui-numberbox" required="true" min="6" max="20"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="性别"></s:text></td>
				<td  align="left" width="70%">
					<s:radio name="sex" list="@com.linkcm.demo.entity.type.SexType@values()" listKey="value" listValue="desc" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<td align="right"><s:text name="班级"></s:text></td>
				<td align="left">
				<s:select  name="classId" list="classMap" theme="simple"></s:select>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div region="south" border="false" style="text-align: right;">
		<a id="school_student_input" style="display:none; width: 65px; float: right;" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="school.student.add();"><s:text name="global_submit"></s:text></a>
	</div>
</div>