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
<script type="text/javascript" src="${ctx}/resources/js/language/department/department_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/department/department_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/user/user_datagrid.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/department/department.js">
	
</script>
</head>
<body  class="easyui-layout" style="height: 100%" >
	<div region="north" style="overflow: hidden;">
		<div style="width:95%; height:auto; line-height:30px; margin:8px 4px 8px 4px;">
			<span style="width:20px; height:30px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left; font-weight:bold;"></span>
			管理自营销系统的部门，查看部门下员工信息；
		</div>
	</div>
	<div region="center" style="padding: 5px">
		<table id="sys_department_datagrid" fit="true"></table>
	</div>
	
<div id="sys_department_user_data" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width:700px">
	<table id="sys_department_user_datagrid" height="100%"></table>
</div>

<input type="hidden" id="selectDeptCode" name="selectDeptCode" />
<div id="sys_department_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 280px; height: 217px;"></div>
</body>
</html>