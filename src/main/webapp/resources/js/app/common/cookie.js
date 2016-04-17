
/**
 * cookie是定义成以下的格式进行缓存
 * 
 * document.cookie=xxdfsfsf=dsfds;dsfdsfd=dsfdsfs;dsfds=;userId=date1,model1,msg1|date2,model2,msg2|date3,model3,msg3
 */
var cookieUtil = new CookieUtil();	
function addPromptMsgToCookie(module,msg){
	$.post(basePath + "/common/common!addMsgToCoreSession.action", {'module' : module,'msg' : msg}, function(response) {});
	/*var value = cookieUtil.get(cookieUserId);
	if(value==null || value ==""){
		value = new Date().format("hh:mm:ss yyyy-MM-dd")+","+model+","+msg;
	}else{
		value = value+"|"+new Date().format("hh:mm:ss yyyy-MM-dd")+","+model+","+msg;
	}
	
	 var promptMsg = cookieUtil.remove(value) ;
	 cookieUtil.set(cookieUserId,promptMsg );*/
	 
}

function CookieUtil(){  
    this.get = function(key) {  
        var cookie = document.cookie;  
        var cookieArray = cookie.split(';');  
        var val = "";  
        for (var i = 0; i < cookieArray.length; i++) {  
            if (cookieArray[i].replace(/(^\s*)/g, "").substr(0, key.length) == key) {  
                val = cookieArray[i].replace(/(^\s*)/g, "").substr(key.length + 1);  
                break;  
            }  
        }
        return unescape(val);  
    }; 
    this.set = function(key, value) {  
        var cookie = "";  
        if (!!key && !!value)  
            cookie += key + "=" + escape(value) + ";";  
        if (!!arguments[2])  
            cookie += "expires=" + arguments[2].toGMTString() + ";";  
        if (!!arguments[3])  
            cookie += "domain=" + arguments[3] + ";";  
        if (!!arguments[4])  
            cookie += "path=" + arguments[4] + ";";  
        document.cookie = cookie;  
    };  
    this.remove = function(value) {  
    	var val = "" ;
    	 if(value != null && value != ""){  
    		 	var msgList = value.split("|");
    			if( msgList.length>25){  		
    				msgList.splice(0,msgList.length-25)	 ; 
    				for(var i=0;i<msgList.length;i++){  					
    				        val  =val + msgList[i] + "|" ; 								        
    				}			
    			} else{
    				val = value ;
		 	} 
    	 }     
    	 if(val==value){
    		 return unescape(val);
    	 }else{
    		 return unescape(val.substring(0,val.length   -   1) ) ;
    	 }
    };
}
/**
 * 用于格式化时间
 * */
Date.prototype.format = function(format){
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
	if(typeof(format)=="undefined")
		format="dd/MM/yyyy";
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};