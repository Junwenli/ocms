$.parser.onComplete = function() {
		window.setTimeout(function() {
			$('#index_div_pageLoading').fadeOut(500);
		}, 100);
	};

$(function() {
	$.fn.panel.defaults.loadingMessage = sy_loadMsg;

	/**
	 * 为jQuery的ajax提供等待提示
	 */
	$(document).ajaxStart(sy.showLoadingDiv).ajaxStop(sy.hideLoadingDiv);
	
	$.ajaxSetup({
		statusCode : {
			401 : function() {
				alert('未授权!');
			},
			404 : function() {
				alert('没找到界面!');
			},
			408 : function() {
				alert('请求超时!');
			},
			500 : function() {
				alert('系统内部错误!');
			},
			503 : function() {
				alert('服务已停止!');
			},
			10000 : function() {
				alert('登录超时或未登录,请重新登录系统!');
			},
			10001 : function() {
				alert('您没有权限操作此功能!');
			}
		}
	});
	
	$(document).ajaxError(function(e,xhr,opt){
		if(($.browser.mozilla||(navigator.userAgent.indexOf("Chrome")!=-1))&&xhr.status=="0"&&xhr.statusText=="error"){
			window.location.href=basePath+"/j_spring_security_logout";//session过期 fire fox CHROME
		}else if($.browser.msie&&xhr.status=="200"){
			window.location.href=basePath+"/j_spring_security_logout";//session过期 IE
		}else if(xhr.status=="403"){
			$.messager.alert("提示","没有操作此功能的权限");
		}
	});
	
	/**
	 * IE6背景图片缓存
	 */
	try {
		document.execCommand("BackgroundImageCache", false, true);
	} catch (e) {
	};
	
	/**
	 * 对Date的扩展
	 * 
	 * 将 Date 转化为指定格式的String
	 * 
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
	 * 
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	 * 
	 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
	 * 
	 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
	 * 
	 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
	 * 
	 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二
	 * 08:09:04
	 * 
	 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	 */
	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, /* 月份 */
			"d+" : this.getDate(), /* 日 */
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, /* 小时 */
			"H+" : this.getHours(), /* 小时 */
			"m+" : this.getMinutes(), /* 分 */
			"s+" : this.getSeconds(), /* 秒 */
			"q+" : Math.floor((this.getMonth() + 3) / 3), /* 季度 */
			/* 毫秒 */
			"S" : this.getMilliseconds()
		};
		var week = {
			"0" : "\u65e5",
			"1" : "\u4e00",
			"2" : "\u4e8c",
			"3" : "\u4e09",
			"4" : "\u56db",
			"5" : "\u4e94",
			"6" : "\u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt
					.replace(
							RegExp.$1,
							((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
									: "\u5468")
									: "")
									+ week[this.getDay() + ""]);
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	};

});