<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/common/home.js"></script>
<div style="width:96%; height:auto; line-height:24px; font-size:12px; 
	margin:10px 2% 20px 2%; padding-bottom:15px; border-bottom:1px  dashed #ccc; 
	color:#333; overflow:hidden; white-space:nowrap;">
	<p style="text-align:right; margin:0 10px 10px 0;">
	<a style="color:#FF0000; cursor:pointer;">
	<security:authentication property="principal.username"></security:authentication></a>！欢迎您进入自有媒介营销管理平台！</p>
	<p><span style="width:20px; height:24px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left;"></span>
	移动市场部针对发展需求和自身的特点，打造自营销管理平台。充分发挥广东移动自有媒介的营销传播价值，提升自有媒介营销价值；</p>
	<p><span style="width:20px; height:24px; background: url(${ctx}/resources/images/role_icon.png) no-repeat center center; float:left;">
	</span>自营销管理平台旨在有效整合自有媒介的营销传播资源，科学构建自有媒介传播管理体系；通过业务产品、自有媒介和客户的三方关联匹配，实现业务精准营销。</p></div>
<div id="menu_div" style="width:100%; height:auto;">
<table id="menu_table" border="0" cellpadding="0" cellspacing="0"  width="82%" height="240px" style="text-align:center; color:#333; font-weight:bold; line-height:18px; font-size:12px;" class="index-box-table">
<!-- 
<tr style="height:120px;">
<td><a><img src="${ctx}/resources/images/index_menu_icon1.png" width="60" height="60" /><br/>用户管理</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon2.png" width="60" height="60" /><br/>角色管理</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon3.png" width="60" height="60" /><br/>权限管理</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon4.png" width="60" height="60" /><br/>部门管理</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon5.png" width="60" height="60" /><br/>目标用户</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon6.png" width="60" height="60" /><br/>参数管理</a></td>
</tr>
<tr style="height:120px;">
<td><a><img src="${ctx}/resources/images/index_menu_icon7.png" width="60" height="60" /><br/>媒介类型</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon8.png" width="60" height="60" /><br/>媒介消息</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon9.png" width="60" height="60" /><br/>营销活动</a></td>
<td><a><img src="${ctx}/resources/images/index_menu_icon10.png" width="60" height="60" /><br/>营销审批</a></td>
<td><br/></td>
<td><br/></td>
</tr>
 -->
</table>
</div>