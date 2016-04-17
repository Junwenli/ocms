<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/param/param_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/param/param_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/param/param.js"></script>
</head>
<body  class="easyui-layout" style="height: 100%" >
	<div region="north" style="overflow: hidden;">
		<!-- 功能介绍 -->
		<div class="introduct-div">
			<span class="introduct-icon"></span>
			系统参数表维护；
		</div>
	</div>
	<div region="center" style="padding: 5px">
		<table id="sys_param_datagrid" fit="true"></table>
	</div>
<!-- 添加修改窗口 -->
<div id="sys_param_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 390px; height: 147px;"></div>
<div id="param_update" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 850px;"></div>
</body>
</html>