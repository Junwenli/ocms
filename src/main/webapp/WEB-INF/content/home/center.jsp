<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.linkcm.core.util.I18nUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<div id="center_tabs" class="easyui-tabs" fit="true" style="overflow: hidden;">
	<div title="<s:text name='global_Homepage'></s:text>" closable="false" href="${ctx}/home/home!toHome.action"></div>
</div>