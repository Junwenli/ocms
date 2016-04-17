<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/icon/icon_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/icon/icon_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/icon/icon.js"></script>
</head>

<body  class="easyui-layout" style="height: 100%" >
	<div region="north" style="overflow: hidden;">
		<!-- 功能介绍 -->
		<div class="introduct-div">
			<span class="introduct-icon"></span>
			管理自营销系统的首页图标，控制用户访问图标；
		</div>
	</div>
	<div region="center" style="padding: 5px">
		<table id="sys_icon_datagrid" fit="true"></table>
	</div>
<!-- 添加资源授权窗口 -->
<div id="sysIcon_perm" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true"></div>
<!-- 添加修改窗口 -->
<div id="sys_icon_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 215px;"></div>
<div id="sys_icon_search" align="center" style="display: none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_icon_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="sys_icon_name"></s:text></td>
				<td><input name="filter_LIKES_title"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_icon_path"></s:text></td>
				<td><input name="filter_LIKES_iconUrl"/></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_function_path"></s:text></td>
				<td><input name="filter_LIKES_url"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="sys.icon.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="sys.icon.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>