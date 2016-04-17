sy.ns('sy.north');
var topNowUserId="<%=pageContext.getServletContext().getAttribute(SysConst.USER_ID).toString()%>";
sy.north.setRemindSelect= function (status){
	if(status == "0"){
		$("#sy_north_remind_disturd_show").css("display","block");
		$("#sy_north_remind_normal_show").css("display","none");
		$("#sy_north_remind_disturd").css("display","none");
		$("#sy_north_remind_normal").css("display","block");
	}else{
		$("#sy_north_remind_disturd_show").css("display","none");
		$("#sy_north_remind_normal_show").css("display","block");
		$("#sy_north_remind_disturd").css("display","block");
		$("#sy_north_remind_normal").css("display","none");
	}
};

function setLanguages(languages){
	if(languages == "zh"){
		$("#sy_north_remind_disturd_show1").css("display","block");
		$("#sy_north_remind_normal_show1").css("display","none");
		$("#sy_north_remind_disturd1").css("display","none");
		$("#sy_north_remind_normal1").css("display","block");
	}else{
		$("#sy_north_remind_disturd_show1").css("display","none");
		$("#sy_north_remind_normal_show1").css("display","block");
		$("#sy_north_remind_disturd1").css("display","block");
		$("#sy_north_remind_normal1").css("display","none");
	}
}
sy.north.hasSysPermission=function(operation){
	for(var i=0;i<sysoperations.length;i++){
		if(sysoperations[i].code==operation){
			return sysoperations[i];
		}
	}
	return null;
};

$(document).ready(
	function(){
		$.get(basePath+"/resource/resource!findSystemOperations.action",function(data){
			sysoperations = data;
			 if(sy.north.hasSysPermission("200")){
					$("#sysChange").css("display","block");
					$("#lineSys").css("display","block");
				}
				else{
					$("#sysChange").css("display","none");
					$("#lineSys").css("display","none");
				}
		});
		if(cookieUtil.get(topNowUserId)){
			input_top_remind_status=cookieUtil.get(topNowUserId);
		}else {
			 cookieUtil.set(topNowUserId,"0");
			 input_top_remind_status="0";
		}
		sy.north.setRemindSelect(input_top_remind_status);
	}
);
$(function() {
	if(theme=='gray'){
		$('#sy_north_theme_gray').hide();
		$('#sy_north_theme_blue_show').hide();
	}else{
		$('#sy_north_theme_gray_show').hide();
		$('#sy_north_theme_blue').hide();
	}
	sy.north.changeTheme = function(themeName) {/*更换主题*/
		sy.setCookie('linkcm_omc',themeName);
		if(themeName=="gray"){
			$('#sy_north_theme_gray_show').show();
			$('#sy_north_theme_gray').hide();
			$('#sy_north_theme_blue_show').hide();
			$('#sy_north_theme_blue').show();
		}else{
			$('#sy_north_theme_gray').show();
			$('#sy_north_theme_gray_show').hide();
			$('#sy_north_theme_blue').hide();
			$('#sy_north_theme_blue_show').show();
		}
		$.post(basePath + "/home/home!changeTheme.action",{'j_theme' : themeName},function(response){
			if(response.success==true){
				window.location=basePath +"/home/home!toMain.action";
			}
		});
		
	};
	sy.north.changeLanguage=function(languageName){/*更换语言*/
		language=languageName;
		$.post(basePath + "/home/home!changeLanguage.action",{
			'request_locale' : languageName
		},function(response) {
			if(response.success==true)
			{
				window.location.href=basePath+"/home/home!toMain.action";
			}
		});
	};
	sy.north.logout = function()
	{
		window.location.href=basePath+"/j_spring_security_logout";
	};
	sy.north.checkMessage = function()
	{
		sy.west.addTab('center_tabs', "信息提示列表", basePath+'/common/cookie_message.jsp') ;
	};
	sy.north.givePermission = function()
	{
		sy.west.addTab('center_tabs', "派发权限", basePath + "/home/home!toGiveUser.action") ;
	};
	sy.north.openModPwdWin = function(){
		$('#north_dialog_modifyPwd').window({cache:false, href:basePath + '/home/home!modifyPassword.action', width:300});
		$('#north_dialog_modifyPwd').window('open');		
	};
	sy.north.remindNormal=function (){
		input_top_remind_status="1";
		cookieUtil.set(topNowUserId,"1");
		sy.north.setRemindSelect(input_top_remind_status);
	};
	sy.north.remindDisturb=function (){
		input_top_remind_status="0";
		cookieUtil.set(topNowUserId,"0");
		sy.north.setRemindSelect(input_top_remind_status);
	};
	sy.north.estimate=function(){
		window.location.href='/estimate';
	};

});


