/**
 * js扩展工具类
 * 
 * 孙宇
 */

var sy = $.extend({}, sy);/* 全局对象 */


(function($) {

	/**
	 * 增加formatString功能
	 * 
	 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 */
	sy.fs = function(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	};

	/**
	 * 增加命名空间功能
	 * 
	 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
	 */
	sy.ns = function() {
		var o = {}, d;
		for ( var i = 0; i < arguments.length; i++) {
			d = arguments[i].split(".");
			o = window[d[0]] = window[d[0]] || {};
			for ( var k = 0; k < d.slice(1).length; k++) {
				o = o[d[k + 1]] = o[d[k + 1]] || {};
			}
		}
		return o;
	};

	/**
	 * 异步表单提交
	 * 
	 */
	sy.ajaxSubmit = function(form, window, datagrid) {
		
		if ($("#" + form).form("validate")) {
			$("#" + form).ajaxSubmit({
				
				success : function(response) {
					if(typeof(response.success)=="undefined"){
						$.messager.alert(sy_remind,"登录超时或未登录,请重新登录系统!");
						return;
					}
					if (response.success == true) {
						$('#' + window).window('close');
						$('#' + datagrid).datagrid('reload');
					}
					$.messager.leftshow({
						msg : response.msg,
						height : 20
					});

				}
			});
		}
	};
	
	/**
	 * 删除数据
	 * 
	 * */
	sy.postDel =  function(ids,url,datagrid){
		$.messager.confirm(sy_confirm, sy_confirmInf, function(r) {
			if (r) {
				var param;
				//增加对复合主键支持
				if(typeof(ids)=='object'){
					param = ids;
				}else{
					param = {'ids' : ids};
				}
				$.post(url, param,  function(response) {
					if(response.success==true){
						$('#'+datagrid).datagrid('reload');
					}
					$.messager.leftshow({				
						msg : response.msg,
						height : 20
					});
				});
			}
		});
	};
	
	/**
	 * 批量删除数据
	 * 
	 * */
	sy.batchDel = function(datagrid,delMethod,getIdsMethod){
		var rows = $('#'+datagrid).datagrid('getSelections');
		var ids = [];
		if (rows.length <= 0) {
			$.messager.alert( sy_remind,sy_deleteMsg);
		} else {
			//增加对复合主键支持
			if(getIdsMethod){
				delMethod(getIdsMethod(rows));
			}else{
				for ( var i = 0; i < rows.length; i++) {
					ids.push("" + rows[i].id + "");
				}
				delMethod(ids.join(','));
			}
		}
	};
	
	/**
	 * 搜索
	 * 
	 * */
	sy.search = function(datagridId,serarchFormId){
		var filters = {};
		var array = $('#'+serarchFormId).serialize().split('&');
		for(var i=0;i<array.length;i++){
			var entry = array[i].split('=');
			if(entry.length>1){
				filters[entry[0]] = decodeURIComponent(entry[1]);
			}
		}
		$('#'+datagridId).datagrid('load',filters);
	};
	/**
	 * 构造数据表格
	 * */
	sy.datagrid = function(id,datagrid,columns,isFrozen,operation){
		var colunmsTmp = [];
		$.extend(colunmsTmp,columns);
		if(operation){
			colunmsTmp.push(operation);
		}
		//外高
		var height;
		if(sy.getCookie('linkcm_omc')=='gray'){
			height = sy.fixHeight(1)-167;
		}else{
			height = sy.fixHeight(1)-180;
		}
		
		var width = sy.fixWidth(1)-220;//外宽
		
		var datagridDefault = {
				iconCls:'icon-save',
				height: height,
				width: width,
				columns:[colunmsTmp],
				loadMsg : sy_loadMsg,
				clickSelected:true,
				pagination : true,
				rownumbers : true,
				onLoadSuccess:function(data){
					sy.dataGridHeadCheckBoxUnSelect($(this));
				}
		};
		if(isFrozen){
			datagridDefault['frozenColumns'] = [[{field:'rowid',checkbox:true}]];
		}
		$.extend(datagridDefault,datagrid);
		$('#'+id).datagrid(datagridDefault);
	};
	
	sy.fixWidth = function(percent){
		return document.body.clientWidth * percent ;
	};
	sy.fixHeight = function(percent){
		return document.body.clientHeight * percent ;
	};
	

	/**
	 * 过滤空白字符串 用法：sy.trim('form_id','第一个表单元素','第二个表单元素')
	 */
	sy.trim = function() {
		if (arguments.length > 1) {
			for ( var i= 0; i < arguments.length-1; i++) {
				var element = $("#"+arguments[0]+" [name='"+arguments[i+1]+"']");
				element.val($.trim(element.val()));
			}
			
		} else if(arguments.length == 1){
			$('#' + arguments[0] + ' :input').each(function() {

				if (typeof (this.value) != "undefined") {
					this.value = $.trim(this.value);
				}

			});
		}

	};

	/**
	 * 获得项目根路径
	 * 
	 * 使用方法：sy.bp();
	 */
	sy.bp = function() {
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		var localhostPaht = curWwwPath.substring(0, pos);
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName);
	};
	
	/**
	 * dataGrid头部的checkBox不选中
	 * @param dataGrid
	 */
	 sy.dataGridHeadCheckBoxUnSelect = function(dataGrid){
		
		var checkObj = dataGrid.datagrid("getPanel").find("div .datagrid-header-check").children("input[type='checkbox']");
		if(checkObj.attr("checked")){
			checkObj.removeAttr("checked");
		}
	};


	/**
	 * 生成UUID
	 */
	sy.UUID = function() {
		var s = [], itoh = '0123456789ABCDEF';
		for ( var i = 0; i < 36; i++)
			s[i] = Math.floor(Math.random() * 0x10);
		s[14] = 4;
		s[19] = (s[19] & 0x3) | 0x8;
		for ( var i = 0; i < 36; i++)
			s[i] = itoh[s[i]];
		s[8] = s[13] = s[18] = s[23] = '-';
		return s.join('');
	};
	
	/**
	 * 生成增加按钮
	 */
	sy.buildAddButton = function(buttons,permission,win,winParam){
		var operation = hasPermission(permission);
		if(operation){
			buttons.push({
				id:'btnAdd',
				text:operation.title,
				iconCls:'icon-add',
				handler:function(){
					$('#'+win).window(winParam);
					sy.windowCenter(win);
					$('#'+win).window('open');
				}
			});
		}
	};
	
	sy.buildDelButton = function(buttons,permission,datagird,delFunction,getIdsMethod){
		var operation = hasPermission(permission);
		if(operation){
			buttons.push({
				id:'btnDel',
				text: operation.title,
				iconCls:'icon-remove',
				handler:function(){
					sy.batchDel(datagird,delFunction,getIdsMethod);
				}
			});
		
		}
	};
	sy.buildQueryButton = function(buttons,permission,win,winParam){
		var operation = hasPermission(permission);
		if(operation){
			buttons.push({
		        	 id:'btnAdd',
						text:operation.title,
						iconCls:'icon-search',
						handler:function(){
							$('#'+win).show();
							$('#'+win).window(winParam);
							$('#'+win).window('open');
						} 
		         });
		}
	};
	sy.buildExportButton = function(buttons,permission,searchForm,url){
		operation = hasPermission(permission);
		if(operation){
			buttons.push({
		        	 id:'btnPort',
						text:operation.title,
						iconCls:'icon-print',
						handler:function(){
							url = sy.getFilterUrl(searchForm,url);
							location.href = url;
						} 
		         });
		}
	};
	
	/**
	 * 获取查询框的过滤条件并构造超链接地址
	 * */
	sy.getFilterUrl = function(searchForm,url){
		var array = $('#'+searchForm).serialize().split('&');
		var flag = true;
		for(var i=0;i<array.length;i++){
			var entry = array[i].split('=');
			if(entry.length>1&&entry[1]){
				if(flag){
					url+='?';
					flag = false;
				}else{
					url+='&';
				}
				url+=entry[0]+'='+decodeURIComponent(entry[1]);
			}
		}
		return url;
	};
	
	/**
	 * 设置cookie，默认有效期为10年
	 */
	sy.setCookie = function(c_name,value,expiredays){
		document.cookie=c_name+ "=" +value+";path=/";
	};
	
	/**
	 * 获取cookie
	 */
	sy.getCookie = function(c_name){
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

	
	
	/**
	 * 显示AJAX开始时的提示信息
	 */
	sy.showLoadingDiv = function() {
		var loadingDiv = $('#_AJAXLOADINGDIV_');
		if (loadingDiv.length < 1) {
			$("body")
					.append(
							'<div id="_AJAXLOADINGDIV_" style="z-index: 9999999; position: absolute; top: 0px; right: 0px; background-color:#FFFEE6; color:#8F5700; padding:5px;font-size: 13px;"><div>数据处理中，请稍候。。。。</div></div>');
		} else {
			loadingDiv.show();
		}
	};
	/**
	 * AJAX结束时隐藏提示信息
	 */
	sy.hideLoadingDiv = function() {
		$('#_AJAXLOADINGDIV_').fadeOut(500);
	};
	
	sy.homeWin=function(){
		var pwin=this;
		while(window.parent!=pwin){
			pwin=window.parent;
		}
		return pwin;
	};
	
	sy.getLeft=function(width){
		var left=(sy.fixWidth(1)/2-width/2)+'px';
		return left;
	};
	
	sy.getTop=function(height){
		var top=(sy.fixHeight(1)/2-height/2)+'px';
		return top;
	};
	
	//窗口居中
	sy.windowCenter=function(winId,width,height){
		if(!width){
			width=$('#'+winId).data('window').options.width;
			width=(width==null?0:width);
		}
		if(!height){
			height=$('#'+winId).data('window').options.height;
			height=(height==null?0:height);
		}
		$('#'+winId).window({
			left:sy.getLeft(width),
			top:sy.getTop(height)
		});
	};
	
	

})(jQuery);