<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">var basePath='${ctx}';</script>
<script type="text/javascript" src="${ctx}/resources/js/app/code/code.js"></script>

<!-- 功能介绍 -->
<div class="introduct-div">
	<span class="introduct-icon"></span>
	根据数据表生成后台和页面代码.
</div>
<!-- 列表 -->
<div class="datagrid-wrapper">
<table id="sys_code_datagrid"></table>
</div>
<!-- 添加资源授权窗口 -->
<div id="sysIcon_perm" class="easyui-window" iconCls="icon-perm" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 220px;"></div>
<!-- 添加修改窗口 -->
<div id="sysIcon_add" class="easyui-window" iconCls="icon-add" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true" style="width: 350px; height: 220px;"></div>
<div id="sys_code_add" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_code_form">
		<table>
			<tr>
				<td align="right">表名：</td>
				<td><span id="code_table"></span></td>
			</tr>
			<tr>
				<td align="right">包路径：</td>
				<td><input id="code_package"/></td>
			</tr>
			<tr>
				<td align="right">模块：</td>
				<td><input id="code_module"/></td>
			</tr>
			<tr>
				<td align="right">实体名称：</td>
				<td><input id="code_entity"/></td>
			</tr>
			<tr>
				<td align="right">实体描述：</td>
				<td><input id="code_desc"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="sys.code.addCode();">确定</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="sys_code_search" style="display:none" class="easyui-window" iconCls="icon-search" closed="true" collapsible="false" minimizable="false" maximizable="false" resizable="false" modal="true">
	<form id="sys_code_search_form">
		<table>
				<tr>
					<td align="right" width="30%">表名：</td>
					<td align="left" width="70%">
						<input id="sys_code_tablename" name="tablename"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<a class="easyui-linkbutton" iconCls="icon-undo" href="javascript:void(0)" onclick="sys.code.clear();">清空</a> 
						<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onclick="sys.code.search();">查询</a>
					</td>
				</tr>
		</table>
	</form>
</div>