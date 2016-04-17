$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : validate_password
	},
	passwordFormat:{
		validator : function(value, param) {
			return value.length>=6&&value.length<=16;
		},
		message : validate_passwordLength
	}
});

$.extend($.fn.datagrid.defaults.editors, {
	my97 : {
		init : function(container, options) {
			var input = $('<input class="Wdate" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',readOnly:true});"  />').appendTo(container);
			return input;
		},
		getValue : function(target) {
			return $(target).val();
		},
		setValue : function(target, value) {
			$(target).val(value);
		},
		resize : function(target, width) {
			var input = $(target);
			if ($.boxModel == true) {
				input.width(width - (input.outerWidth() - input.width()));
			} else {
				input.width(width);
			}
		}
	}
});

/**
 * 验证正整数、主键格式
 */
$.extend($.fn.validatebox.defaults.rules, {
	number: {
    	validator: function(value, param){
        	return  /^[1-9]\d{0,8}$/.test(value);
  		},
    	message: validate_positiveIntegerLength
    },
    isFit:{
    	validator:function(value,param){
    		return /^\w+$/.test(value);
    	},
    	message: validate_parameterValidate
    },
    maxLength:{
    	 validator: function(value, param){
             return value.length <= param[0];
         },
         // message: '请输入不大于30位的字符'
         message: validate_maxLength
    },
    categoryLength:{
   	 validator: function(value, param){
            return value.length <=8;
        },
        message: validate_categoryLength
   },
   msgLength:{
  	validator: function(value, param){
           return value.length <=20;
      },
      message: validate_msgLength
  }
});

/**
 * 验证整数(0-9)
 */
$.extend($.fn.validatebox.defaults.rules, {
	numbers: {
    	validator: function(value, param){
        	return  /^[0-9]\d{0,10}$/.test(value);
  		},
    	message: validate_positiveIntegerLength
    }
});
