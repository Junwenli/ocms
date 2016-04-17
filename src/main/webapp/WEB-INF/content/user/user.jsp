<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/user/user_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/user/user_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/user/user.js"></script>
</head>
<body  class="easyui-layout" style="height: 100%" >
<div region="north" style="overflow: hidden;">
	<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
	<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
	管理自营销系统的用户基本信息，控制用户的角色；
	</div>
</div>
<div region="center" style="padding: 5px">
	<table id="sys_user_datagrid" fit="true"></table>
</div>

<div id="sysUser_pwd_end" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 250px; height:140px"></div>
<div id="sys_user_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 280px;height: 250px;"></div>
<!-- 角色列表弹出框 -->
<div id="role_list_div" iconCls="icon-add" closed="true" collapsible="true" minimizable="false" maximizable="false" 
	resizable="false"  modal="true" title="角色列表" style="width: 680px;"></div>
<div id="sys_user_search" align="center" style="display:none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_user_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="sys_user_account"></s:text></td>
				<td><input name="filter_LIKES_userCode"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_name"></s:text></td>
				<td><input name="filter_LIKES_name"/></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_user_statusDesc"></s:text></td>
				<td align="left">
					<%-- 
					<s:select id="user_search_status" list="#{'0':'冻结','1':'激活'}"  headerKey="" headerValue="所有" theme="simple"></s:select>
					--%>
					<s:select id="user_search_status" name="filter_LIKES_status" list="statusMap" theme="simple" ></s:select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="sys.user.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="sys.user.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>