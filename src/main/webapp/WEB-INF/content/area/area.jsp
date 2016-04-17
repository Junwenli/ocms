<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/area/area_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/area/area_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/area/area.js">
	
</script>
</head>

<body  class="easyui-layout" style="height: 100%" >
	<div region="north" style="overflow: hidden;">
		<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
			<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
			管理自营销系统的区域，查看区域信息；
		</div>
	</div>
	<div region="center" style="padding: 5px">
		<table id="sys_area_datagrid" fit="true"></table>
	</div>
<!-- 添加修改窗口 -->
<div id="sys_area_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 390px; height: 188px;"></div>
<!-- 查询窗口 -->
<div id="sys_area_search" align="center" style="display: none;padding-top:10px;color:#333;" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_area_search_form">
		<table>
			<tr>
				<td align="right"><s:text name="sys_area_code"></s:text></td>
				<td><input name="filter_LIKES_areaCode"></input></td>
			</tr>
			<tr>
				<td align="right"><s:text name="sys_area_name"></s:text></td>
				<td><input name="filter_LIKES_name"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="sys.area.clear();"><s:text name="global_clear"></s:text></a> 
					<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="sys.area.search();"><s:text name="global_query"></s:text></a>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>
