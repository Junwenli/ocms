sy.ns('sys.home');

$(function() {
	/* 添加tab方法 */
	sy.west.addTab = function(tabsId, title, href) {
		$('#index_div_pageLoading').show();
		var id = '#' + tabsId;
		if ($(id).tabs('exists', title)) {
			$(id).tabs('select', title);
		} else {
			if(href)
			{
				var iframid=tabsId+"_"+title;
				var content = '<iframe id="'+iframid+'" title="'+title+'"  frameborder="0"  src="'+ href + '" style="width:100%;height:100%;"></iframe>';
				$(id).tabs({onBeforeClose:function(title){
					try{
						var ifram=$("#"+tabsId+"_"+title).get(0);
						ifram.src='about:blank';
					}catch (e) {}
				}});
				$(id).tabs('add', {
					title : title,
					//href : href,
					closable : true,
					content : content
				});
			}
		}
		$('#index_div_pageLoading').hide();
	};
	
	$.ajax({
		cache : false,
		type : "POST",
		dataType : "JSON",
		url : basePath + "/icon/icon!findIconByUserId.action",
		success : function(data){
			var json=eval(data);
			var tBody = "<tr style='height:120px;'>";
			var count =0;
			$.each(json, function(index, item){
				count ++;
				tBody += "<td style='width:16.6%;'><a onclick='sy.west.addTab(\"center_tabs\",\"" + item.title + "\",\"" + basePath + item.url + "\");'>" +
						"<img src='" + basePath + item.iconUrl + "' width='60' height='60' />" +
						"<br/>" + item.title + "</a></td>";
				if(count% 6 == 0){
					tBody += "</tr><tr style='height:120px;'>";
				}
			});
			var num = Math.abs(6 - (count % 6));
			if(num>0&&num<6){
				for(var i = num; i >0; i --){
					tBody += "<td style='width:16.6%;'><div style=\"width:60px\"></div></td>";
				}	
			}
			tBody += "</tr>";
			if(count < 6)			{
				tBody += "<tr style='height:120px;'><td colspan='6'></td></tr>";
			}
			
			
			$('#menu_table').append(tBody);
		},
		error : function(data){
		}			
	});
});

sys.home.isExists = function(arr, value){
	var flag = false;
	for(var i = 0; i < arr.length; i ++) {
		if(arr[i] == value)
			flag = true;
	}
	return flag;
};