<%@page import="com.linkcm.core.util.SysConst"%>
<%@page import="java.util.Locale"%>

<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String userId = pageContext.getServletContext().getAttribute(SysConst.USER_ID).toString();
	session.setAttribute(SysConst.USER_ID, userId);
	
	String language = (String)session.getAttribute("language");
	if(language == null){
		language = ActionContext.getContext().getLocale().getLanguage();
	}
	if(language!=null && language!="zh" && language!="en"){
		language="en";
	}
	session.removeAttribute("language");
	session.setAttribute("language",language);
%>
<c:set var="language" value="${session.language}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>开发平台</title>
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
	var cookieUserId = "<%=userId%>";
</script>
<script type="text/javascript">
	var basePath = '${ctx}';
</script>
<script type="text/javascript">
	var operations;
	$(function(){
		$('div.layout-panel-west')
		.find('div.panel-header')
		.find('div.panel-icon.icon-reload').css('cursor','pointer').bind('click',function(){
			$('body').layout('panel','west').panel('refresh',basePath+'/home/home!toMenu.action');
		});
		
        $(".panel-title").html(menuName); 
        $.get(basePath+"/resource/resource!findOperations.action",function(data){operations = data;});
	});
	function getMessager() {
		return $.messager;
	}
	function hasPermission(operation){
		for(var i=0;i<operations.length;i++){
			if(operations[i].code==operation){
				return operations[i];
			}
		}
		return null;
	}
</script>
</head>

<body id="index_layout" class="easyui-layout">

<div id="index_div_pageLoading" class="pageloadingdiv"><span
	class="pageloadingspan"><s:text name="global_pageLoading"></s:text></span></div>
<div id="index_div_dataLoading" class="dataloadingdiv"><span>
<font size="3"><s:text name="global_pageOperating"></s:text></font> <br />
<img src="${ctx}/resources/images/dataload.gif" border="0" /> </span></div>

<!-- 头部区域 
<div href="${ctx}/common/top.jsp" region="north"
	style="height: 90px; background: url(${ctx}/resources/images/top-bg.png) no-repeat center top;"></div>
-->
<div href="${ctx}/home/home!toTop.action" region="north"
	style="height: 90px; background: url(${ctx}/resources/images/top-bg.png) no-repeat center top;"></div>
	
<!-- 菜单区域 
<div href="${ctx}/common/menu.jsp" region="west" iconCls="icon-reload"
	split="true" style="width: 200px;"></div>
-->
<div href="${ctx}/home/home!toMenu.action" id="menuTitle" region="west" iconCls="icon-reload"
	split="true" style="width: 200px;" title="菜单列表"></div>
<!-- 主区域 
<div href="${ctx}/common/center.jsp" region="center" id="centerDiv"></div>
-->
<div href="${ctx}/home/home!toCenter.action" region="center" style="overflow: hidden;" id="centerDiv"></div>
</body>
</html>