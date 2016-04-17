<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript">
	sy.ns('sy.west');
	$(function() {
		
		/**通过Ajax请求后台初始化功能树*/
		var treeNodes = "";
		var setting = 
		{
				isSimpleData : true,
				treeNodeKey : "id",
				treeNodeParentKey : "pId",
				nameCol : "title",//显示树的名称，默认是“name”
				showLine : true
		};
		
		$(function(){
			$.ajax({
				async : false,
				cache : false,
				type : "POST",
				dataType : "JSON",
				url : "${ctx}/resource/resource!findMenu.action",
				error : function(){
					$.message.leftshow({
						title : "提示",
						msg : "获得菜单失败"
					});
				},
				success : function(data){
					//加载菜单树
					$.fn.zTree.init($('#west_tree'), {
						data : {
							simpleData : {
								enable : true
							}
						},
						callback : {
							onClick : function(event, treeId, treeNode, clickFlag) {
								if(treeNode.page != null && treeNode.page != "" && treeNode.page != "/**") {
									sy.west.addTab('center_tabs', treeNode.name, basePath+treeNode.page);
								}
							}
						}
					},data);
				}
			});
		});
		
	});
</script>
<!-- <div class="panel-header accordion-header accordion-header-selected" style="height: 17px; width: 183px;">
	<div class="panel-title">
		
		<s:text name="global_Menulist"></s:text>
	</div>
</div> -->
<div title="菜单" iconCls="icon-reload" selected="true">
	<ul id="west_tree" class="ztree"></ul>
</div>