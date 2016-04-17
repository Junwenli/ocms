<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/permission/permission_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/permission/permission.js"></script>
<input id="permission_roleId" type="hidden" value="${roleId}"/>
<table id="permission_treegrid"></table>
