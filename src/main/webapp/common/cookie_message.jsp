<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/common/cookie_message.js"></script>

<!-- 功能介绍 -->
<div class="introduct-div">
	<span class="introduct-icon"></span>
	提示信息列表：记录提示信息<font style="color:red;">（能记录30条提示信息，超过30条信息系统自动删除列表后面的记录）</font>
</div>
<!-- 列表 -->
<table id="cookie_message_datagrid"></table>
