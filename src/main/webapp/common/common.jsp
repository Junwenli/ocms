<%@ include file="/common/taglibs.jsp"%>
<c:set var="language" value="${session.language}"/>
<link rel="stylesheet" id="index_link_easyuiThemes" type="text/css"
	href="${ctx}/resources/css/jquery-easyui-1.2.5/${theme}/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/jquery-easyui-1.2.5/icon.css"></link>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/jquery-easyui-portal/portal.css"></link>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/jquery-zTree-3.0/zTreeStyle/zTreeStyle.css"></link>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/pageLoadingDiv.css"></link>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/web-css.css"></link>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery.form.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-easyui-1.2.5/locale/easyui-lang-${language}.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-easyui-portal/jquery.portal.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-zTree-3.0/jquery.ztree.core-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-zTree-3.0/jquery.ztree.excheck-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/jquery-zTree-3.0/jquery.ztree.exedit-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${ctx}/resources/js/syUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/language/syConfig_${language}.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/syConfig.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/language/syValidateRule_${language}.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/syValidateRule.js"></script>

<script type="text/javascript">
var cookieUserId = sy.homeWin().cookieUserId;
var basePath = '${ctx}';
var operations=sy.homeWin().operations;
function hasPermission(operation){
	return sy.homeWin().hasPermission(operation);
}
</script>