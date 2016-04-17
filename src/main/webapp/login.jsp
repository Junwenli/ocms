<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>开发平台</title>
<link href="${ctx}/resources/css/login-css.css" rel="stylesheet"
	type="text/css" />
</head>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript">
	//登录页面login.jsp 本注释不能删
	/**
	 * 获取cookie
	 */
	function getCookie(c_name){
		if (document.cookie.length>0){
			c_start=document.cookie.indexOf(c_name + "=");
			if (c_start!=-1){ 
				c_start=c_start + c_name.length+1;
				c_end=document.cookie.indexOf(";",c_start);
				if (c_end==-1) c_end=document.cookie.length;
				return unescape(document.cookie.substring(c_start,c_end));
			} 
		}
		return "";
	};
	function oncheckuser() {
		var username = document.securityForm.j_username.value;
		var password = document.securityForm.password.value;
		var captcha = document.securityForm.j_captcha_response.value;
		if (username == "") {
			document.getElementById('message').innerHTML = "<font color='red'>用户不能为空.</font>";
			return;
		} else if (password == "") {
			document.getElementById('message').innerHTML = "<font color='red'>密码不能为空.</font>";
			return;
		} else if (getPsdWrongNumber() === 3){
			document.getElementById('message').innerHTML = "<font color='red'>密码错误3次,已被锁定.</font>";
			return;
		}else {
		    $.ajax({
		    	type: 'POST',
//		    	date:"j_captcha_response="+captcha,
		    	url: basePath+"/jcaptcha!checkJcaptcha.action?j_captcha_response="+captcha,
		    	dataType: 'json',
//		    	enableLoading:true,
		    	success: function(data) {
		    		if(data.success == true){
						var userandpass = trim(username) + trim(password);
						document.securityForm.j_password.value = userandpass;
						document.securityForm.submit();
		    		}else if(data.success == false){
		    			$('#j_captcha_picture').click();
		    			document.getElementById('message').innerHTML = "<font color='red'>"+data.msg+"</font>";
		    			return;
		    		}
		    	},error:function() {		 
		    	}
		    });
		}
	}
	
	function getPsdWrongNumber(){
		return null;
	}

	//删除左右两端的空格
	function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	function keyEvenSubmit(e) {
		var key = window.event ? e.keyCode : e.which;
		if (key == 13) {
			oncheckuser();
		}
	}
	
	function keyEvenFocus(e) {
		var key = window.event ? e.keyCode : e.which;
		if (key == 13 && trim(document.securityForm.j_username.value)) {
			document.securityForm.password.focus();
		}
	}

	window.onload = function() {
		document.securityForm.action = "${ctx}/j_spring_security_check";
		var username = document.securityForm.j_username.value;
		if (username == "") {
			document.securityForm.j_username.focus();
		} else {
			document.securityForm.password.focus();
		}
		var theme = getCookie('linkcm_omc');
		document.securityForm.j_theme.value = theme;
		
	}
</script>
<body>
<div class="login-box">
<div class="login-left-box"><img
	src="resources/images/lg-logo.png" width="299" height="215"
	style="margin: 40px 0 0 30px;" /></div>
<div class="login-right-box">
<form id="securityForm" name="securityForm" action="#" method="post">
<table width="100%" height="120" style="margin-top: 80px;">
	<tr height="20">
		<td align="right" width="30%"><label>用户名:</label></td>
		 <input type="hidden" id="j_theme" name="j_theme"/>
		<td align="left"><input style="width: 60%" type='text'
			onkeypress="keyEvenFocus(event)" id='j_username' name='j_username'
			class="required"/></td>
	</tr>
	<tr height="20">
		<td align="right" width="30%"><label>密码:</label></td>
		<td align="left"><input style="width: 60%" onkeypress="keyEvenSubmit(event)" type='password'
			id='password' name='password' class="required" /> <input
			type="hidden" id="j_password" name="j_password" value="" /><br />
		</td>
	</tr height="20">
		<tr height="20">
		<td align="right" width="30%"><label>验证码:</label></td>
		<td><input id="j_captcha_response" type='text' name='j_captcha_response' value=''></td>
	</tr>
	<tr height="20">
		<td></td>
		<td>
			<img id="j_captcha_picture" height="40px" width="150px" src="jcaptcha" onclick="this.src=this.src+'?'" /><br />
		<div id="message"><c:if test="${not empty param.error}">
		<script type="text/javascript">
		</script>
			<font color="red"> <c:choose>
				<c:when
					test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">用户名或密码有错请重新输入。</c:when>
				<c:otherwise>
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"></c:out>
				</c:otherwise>
			</c:choose> </font>
		</c:if></div>
		</td>
	</tr>
	<tr height="20">
		<td colspan='2' align="center"><input type="button"
			onkeypress="keyEvenSubmit(this)" value="" class="login-button1"
			onclick="oncheckuser();" /> <!--<input type="submit" value="" class="login-button2"/>
					--></td>
	</tr>

</table>
</form>
</div>
</div>
</body>
</html>