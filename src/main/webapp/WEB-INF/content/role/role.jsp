<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/role/role_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/role/role_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/role/role.js"></script>

</head>
<body  class="easyui-layout" style="height: 100%" >
<!-- 功能介绍 -->
<!-- 列表 -->
<div region="north" style="overflow: hidden;">
	<div class="introduct-div">
	<span class="introduct-icon"></span>
	管理自营销系统的各种角色，控制用户访问菜单及权限；
	</div>
</div>
<div region="center" style="padding: 5px">
	<table id="sys_role_datagrid" fit="true"></table>
</div>

<!-- 添加修改窗口 -->
<div id="sys_role_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 215px;"></div>
<div id="sys_role_permission" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 850px; height:540px;"></div>
<div id="sys_role_search" align="center" style="display: none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_role_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="sys_role_code"></s:text></td>
				<td><input name="filter_LIKES_roleCode"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_role_name"></s:text></td>
				<td><input name="filter_LIKES_title"/></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_role_state"></s:text></td>
				<td  align="left"><s:select  name="filter_EQI_status" list="statusMap" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="sys.role.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="sys.role.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>