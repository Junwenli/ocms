<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/student/student_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/student/student.js">
	
</script>
<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
	<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
	班级学生管理系统；查看学生信息
</div>
<div class="datagrid-wrapper">
<table id="school_student_datagrid"></table>
</div>
<div id="school_student_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 280px; height: 217px;"></div>
<div id="school_student_search" align="center" style="display: none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
<form id="school_student_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="姓名"></s:text></td>
				<td><input name="filter_LIKE_name"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="年龄"></s:text></td>
				<td><input name="filter_EQ_age"/></td>
			</tr>
			<tr>
				<td align="right" width="30%"><s:text name="性别"></s:text></td>
				<td  align="left" width="70%">
					<s:radio name="filter_EQ_sexDesc" list="@com.linkcm.demo.entity.type.SexType@values()" listKey="value" listValue="desc" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="school.student.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="school.student.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>
