<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/student/student_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/classinfo/classinfo_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/classinfo/classinfo.js">
	
</script>
<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
	<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
	班级学生管理系统；查看班级信息
</div>
<div class="datagrid-wrapper">
<table id="school_classinfo_datagrid"></table>
</div>

<div id="school_classinfo_student_data" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width:700px;height:321px;">
	<table id="school_classinfo_student_datagrid" height="100%"></table>
</div>

<div id="school_classinfo_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 280px; height: 217px;"></div>
<div id="school_classinfo_search" align="center" style="display: none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
<form id="school_classinfo_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="班级"></s:text></td>
				<td><input name="filter_LIKE_classname"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="班主任"></s:text></td>
				<td><input name="filter_LIKE_teacher"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="school.classinfo.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="school.classinfo.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>
