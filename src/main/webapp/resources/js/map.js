Array.prototype.remove = function(s) {   
    for (var i = 0; i < this.length; i++) {   
        if (s == this[i])   
            this.splice(i, 1);   
    }   
};   
  
/**  
 * Simple Map  
 *   
 *   
 * var m = new Map();  
 * m.put('key','value');  
 * ...  
 * var s = "";  
 * m.each(function(key,value,index){  
 *      s += index+":"+ key+"="+value+"\n";  
 * });  
 * alert(s);  
 *   
 * @author dewitt  
 * @date 2008-05-24  
 */  
function Map() {   
	
	this.pageSize=10;
	this.pageNumber=1;
	
    /** 存放键的数组(遍历用到) */  
    this.keys = new Array();   
    /** 存放数据 */  
    this.data = new Object();   
       
    /**  
     * 放入一个键值对  
     * @param {String} key  
     * @param {Object} value  
     */  
    this.put = function(key, value) {   
        if(this.data[key] == null){   
            this.keys.push(key);   
        }   
        this.data[key] = value;   
    };   
       
    /**  
     * 获取某键对应的值  
     * @param {String} key  
     * @return {Object} value  
     */  
    this.get = function(key) {   
        return this.data[key];   
    };   
       
    /**  
     * 删除一个键值对  
     * @param {String} key  
     */  
    this.remove = function(key) {   
        this.keys.remove(key);   
        this.data[key] = null;   
    };   
       
    /**  
     * 遍历Map,执行处理函数  
     *   
     * @param {Function} 回调函数 function(key,value,index){..}  
     */  
    this.eachDesc = function(fn){   
        if(typeof fn != 'function'){   
            return;   
        }   
        var len = this.keys.length;   
        for(var i=len-1;i>=0;i--){   
            var k = this.keys[i];   
            fn(k,this.data[k],i);   
        }   
    };   
    
    /**  
     * 遍历Map,执行处理函数  
     *   
     * @param {Function} 回调函数 function(key,value,index){..}  
     */  
    this.each = function(fn){   
        if(typeof fn != 'function'){   
            return;   
        }   
        var len = this.keys.length;   
        for(var i=0;i<len;i++){   
            var k = this.keys[i];   
            fn(k,this.data[k],i);   
        }   
    };   
       
    /**  
     * 获取键值数组(类似Java的entrySet())  
     * @return 键值对象{key,value}的数组  
     */  
    this.entrys = function() {   
        var len = this.keys.length;   
        var entrys = new Array(len);   
        for (var i = 0; i < len; i++) {   
            entrys[i] = {   
                key : this.keys[i],   
                value : this.data[i]   
            };   
        }   
        return entrys;   
    };   
       
    /**  
     * 判断Map是否为空  
     */  
    this.isEmpty = function() {   
        return this.keys.length == 0;   
    };   
       
    /**  
     * 获取键值对数量  
     */  
    this.size = function(){   
        return this.keys.length;   
    };   
       
    /**  
     * 重写toString   
     */  
    this.toString = function(){   
        var s = "{";   
        for(var i=0;i<this.keys.length;i++,s+=','){   
            var k = this.keys[i];   
            s += k+"="+this.data[k];   
        }   
        s+="}";   
        return s;   
    };   
    
    
    
	/*-------------------------------------------------------扩展map到项目应用----------------------------------------------------------*/
    /**
     * 根据pageSize对数据进行分页 
     */
    this.rowsData=function(pageNumber){
    	if(pageNumber>0){
    		this.pageNumber = pageNumber;
    	}
    	
    	if((pageNumber*this.pageSize)>this.size()){
    		this.pages = parseInt((this.size()-1)/this.pageSize)+1;
    		this.pageNumber = this.pages;
    	}
    	
    	var pageBeginRowNum = (this.pageNumber*this.pageSize)-this.pageSize+1;
    	var pageEndRowNum = pageBeginRowNum+this.pageSize-1;
    	if((pageBeginRowNum+this.pageSize-1)>this.size()){
    		pageEndRowNum = this.size();
    	}
    	
    	var data=[]; 
    	var i=1;
   
    	this.eachDesc(function(key,value,index){
    		if(i>=pageBeginRowNum && i<=pageEndRowNum){
    			value.num=i;
    			data.push(value);
    		}
			i++;
    	});
    	return data;
    };
    
    /**
     * 排序
     */
    this.sortable=function(order){
    	
    	var pv0=[],pv1=[],pv2=[];
    	this.each(function(key,value,index){
    		if(value.priorityVal==0){
    			pv0.push(value);
    		}else if(value.priorityVal==1){
    			pv1.push(value);
    		}else{
    			pv2.push(value);
    		}
    	});
    	
    	var tmpMap = new Map();
    	if(order=="asc"){
        	
        	for ( var i = 0; i < pv0.length; i++) {
        		tmpMap.put(pv0[i].messageCode,pv0[i]);
			}
        	for ( var i = 0; i < pv1.length; i++) {
        		tmpMap.put(pv1[i].messageCode,pv1[i]);
			}
        	for ( var i = 0; i < pv2.length; i++) {
        		tmpMap.put(pv2[i].messageCode,pv2[i]);
			}
    	}else{
    		
        	for ( var i = 0; i < pv2.length; i++) {
        		tmpMap.put(pv2[i].messageCode,pv2[i]);
			}
        	for ( var i = 0; i < pv1.length; i++) {
        		tmpMap.put(pv1[i].messageCode,pv1[i]);
			}
        	for ( var i = 0; i < pv0.length; i++) {
        		tmpMap.put(pv0[i].messageCode,pv0[i]);
			}
    	}
    	return tmpMap;
    };
    /*--------------------------------------------------------------------------------------------------------------------------*/
}