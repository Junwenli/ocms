<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.linkcm.core.util.SysConst"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript">var theme='${theme}';</script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/app/common/cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/app/common/top.js"></script>
<script language="javascript"> 
var languages  = "<%=ActionContext.getContext().getLocale().getLanguage()%>";
setLanguages(languages);

function correctPNG() // correctly handle PNG transparency in Win IE 5.5 & 6. 
{ 
    var arVersion = navigator.appVersion.split("MSIE") 
    var version = parseFloat(arVersion[1]) 
    if ((version >= 5.5) && (document.body.filters)) 
    { 
       for(var j=0; j<document.images.length; j++) 
       { 
          var img = document.images[j] 
          var imgName = img.src.toUpperCase() 
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG") 
          { 
             var imgID = (img.id) ? "id='" + img.id + "' " : "" 
             var imgClass = (img.className) ? "class='" + img.className + "' " : "" 
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' " 
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle 
             if (img.align == "right") imgStyle = "float:right;" + imgStyle 
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle 
             var strNewHTML = "<span " + imgID + imgClass + imgTitle 
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";" 
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader" 
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML 
             j = j-1 
          } 
       } 
    }     
} 
if (document.all){window.attachEvent('onload',correctPNG)}//IE
else{window.addEventListener('load',correctPNG,false);} //FireFox
</script>



<input type="hidden" id="input_top_remind_status" />
<div style="position:absolute;top:14px;left:30px;width:406px;height:62px;"><img src="${ctx}/resources/images/logo.png" alt=""/></div>

<div style="position: absolute; right: 10px; top: 5px;">
	<span id="north_span_welcome"></span>
</div>

<div style="position: absolute; right: 0px; bottom: 0px;">
	<ul style="margin:0; padding:0;"><li style=" height:26px; line-height:26px; list-style-type: none; float:left;"><s:text name="top_currentUser"></s:text>[<a style="color:#FF0000; cursor:pointer;"><security:authentication property="principal.username"></security:authentication></a>]</li>
	<li style=" height:26px; line-height:26px; list-style-type: none;  float:left;">
	<a href="javascript:void(0)" class="easyui-menubutton" menu="#north_menubutton_glxt" iconCls="">
		<span><span class="icon-edit" style="width:20px; float:left">&nbsp;</span><s:text name="top_controlPanel"></s:text></span></a>
	</li>
	<li style=" height:26px; line-height:26px; list-style-type: none;  float:left;">
		<a href="javascript:void(0)" class="easyui-menubutton" onclick="sy.north.logout();" iconCls="icon-tip"><s:text name="top_logoutOf"></s:text></a>
	</li>
	</ul>
</div>

<div id="north_menubutton_glxt" style="width: 100px;">
	<div>
		
		<span><s:text name="sys_change_theme"/></span>
		<div style="width: 100px;">
			<div id="sy_north_theme_gray_show" iconCls="icon-ok"><s:text name="sys_theme_gray"/></div>
			<div id="sy_north_theme_gray" onclick="sy.north.changeTheme('gray');"><s:text name="sys_theme_gray"/></div>
			<div id="sy_north_theme_blue_show" iconCls="icon-ok"><s:text name="sys_theme_blue"/></div>
			<div id="sy_north_theme_blue" onclick="sy.north.changeTheme('blue');"><s:text name="sys_theme_blue"/></div>
		</div>
	</div>
	<div>
		<span><s:text name="top_switchLanguage"></s:text></span>
		<div style="width: 100px;">
			 <%--
			<div onclick="sy.north.changeLanguage('zh_CN');"><s:text name="top_chinese"></s:text></div>
			<div onclick="sy.north.changeLanguage('en_US');"><s:text name="top_english"></s:text></div>
			--%>
			<div  id="sy_north_remind_disturd_show1" iconCls="icon-ok" style="none"><s:text name="top_chinese"></s:text></div>
			<div onclick="sy.north.changeLanguage('zh_CN');" id="sy_north_remind_disturd1" style="none"><s:text name="top_chinese"></s:text></div>
			<div  id="sy_north_remind_normal_show1" iconCls="icon-ok" style="none"><s:text name="top_english"></s:text></div>
			<div onclick="sy.north.changeLanguage('en_US');" id="sy_north_remind_normal1" style="none"><s:text name="top_english"></s:text></div>
		</div>
	</div>
	<div>
		<span><s:text name="top_remindPattern"></s:text></span>
		<div style="width: 100px;">
			<div  id="sy_north_remind_disturd_show" iconCls="icon-ok" style="none"><s:text name="top_remindDisturb"></s:text></div>
			<div onclick="sy.north.remindDisturb()" id="sy_north_remind_disturd" style="none"><s:text name="top_remindDisturb"></s:text></div>
			<div  id="sy_north_remind_normal_show" iconCls="icon-ok" style="none"><s:text name="top_remindNormal"></s:text></div>
			<div onclick="sy.north.remindNormal()" id="sy_north_remind_normal" style="none"><s:text name="top_remindNormal"></s:text></div>
		</div>
	</div>
	<div class="menu-sep"></div>
	<div id="sysChange">
		<span>切换系统</span>
		<div style="width: 117px;">
			<div  id="sy_north_media_show" iconCls="icon-ok" style="none">营销管理平台</div>
			<div onclick="sy.north.estimate()" id="sy_north_estimate" style="none">效果评估系统</div>
		</div>
	</div>
	<div class="menu-sep" id="lineSys"></div>
	<div onclick="sy.north.openModPwdWin();"><s:text name="top_changePassword"></s:text></div>
	<div onclick="sy.north.checkMessage();"><s:text name="top_remindInfo"></s:text></div>
	<div onclick="sy.north.givePermission();"><s:text name="top_distributePermission"></s:text></div>
</div>


<div id="north_dialog_modifyPwd" title="<s:text name='top_titleChangePassword'></s:text>" closed="true" collapsible="false" minimizable="false" maximizable="false" modal="true" style="width: 250px;"></div>


