<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/resource/resource_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/resource/resource.js"></script>
<!-- 功能介绍 -->
</head>
<body  class="easyui-layout" style="height: 100%" >
<div region="north" style="overflow: hidden;">
	<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
	<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
	自有媒介营销管理的权限管理；针对系统的菜单、功能划分不同的权限；
	</div>
</div>
<div region="center" style="padding: 5px">
	<table id="sys_resource_treegrid" fit="true"></table>
</div>
<div id="resource_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 221px;"></div>
</body>
</html>