<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/language/membership/membership_${language}.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/membership/membership.js"></script>

<!-- 列表 -->
<table id="membership_datagrid"></table>
<input type='hidden' id='membership_userId' name='membership_userId' value='${id}'/>
