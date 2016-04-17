<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/common/giveUser_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/common/giveUser.js"></script>

<!-- 功能介绍 -->
<div class="introduct-div">
	<span class="introduct-icon"></span>
	<s:text name="sys_giveUser_pageContent1"></s:text><font style="color:red;"><s:text name="sys_giveUser_pageContent2"></s:text></font>
</div>
<!-- 列表 -->
<div id="sysGive_permission" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 850px; height:540px;"></div>
<table id="give_permission_datagrid"></table>
