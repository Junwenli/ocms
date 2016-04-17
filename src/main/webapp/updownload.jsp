<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文件上传与下载</title>
	<script type="text/javascript">
		function deleteConfirm(url,filename) {
			//alert(url);
			if(confirm("确定要删除'"+filename+"'吗？")) {
				window.location.href = url; 
			}
		}
		function upValidate() {
			if(document.upform.file.value=="") {
				alert("请选择上传文件");
				return false;
			}
			if(document.upform.pathDir.value==""){
				alert("请输入上传路径");
				return false;
			}
		}
		function downValidate() {
			if(document.downform.directory.value=="") {
				alert("请输入要查看的目录");
				return false;
			}
		}
		function newValidate() {
			if(document.newDirectoryForm.newDirectory.value=="") {
				alert("请输入子目录名称");
				return false;
			}
		}
	</script>
</head>
<body>
	<form name="upform" action="${ctx}/up/up!uploadFile.action" method="post" enctype="multipart/form-data" onsubmit="return upValidate();">
		<table>
			<tr><th colspan="2">-----------文件上传-----------</th></tr>
			<tr>
				<td>选择文件：</td>
				<td><input type="file" id="file" name="file" style="width:215px;height:23px" size="30"/></td>
			</tr>
			<tr>
				<td>输入路径：</td>
				<td><input type="text" id="pathDir"  name="pathDir" size="30"/></td>
			</tr>
			<tr>
				<td></td>
				<td><span style="color: red">(输入格式：C:/upload)</span></td>
			</tr>
			<tr>
				<td><input type="submit" value="上传"/></td><td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>
	<table>
		<tr><td style="color:red">${message}</td></tr>
		<tr><td style="color:red">${realPath}</td></tr>
	</table>
	<form name="downform" action="${ctx}/down/down!getAllFiles.action"  onsubmit="return downValidate();">
			<table>
				<tr><th colspan="2">-----------文件下载与删除-----------</th></tr>
				<tr>
					<td>请输入目录：</td><td><input type="text" id="directory" name="directory" size="30" /></td><td><input type="submit" value="查看" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><span style="color: red">(输入格式：C:/upload)</span></td>
				</tr>
			</table>
		</form>
		<table>
			<tr><td>当前查看目录为：<b>${directory}</b></td></tr>
			<c:if test="${not empty directory}">
				<tr><td>新建${directory}的子目录：
				
					<form name="newDirectoryForm" action="${ctx}/down/down!newDirectory.action"  onsubmit="return newValidate();">
						<table>
							<tr>
								<td>请输入目录名称：${directory}</td><td><input type="text" id="subDirectory" name="subDirectory"/>
									<input type="hidden" id="dir" name="directory" value="${directory}"/></td>
								<td><input type="submit" value="新建" /></td>
							</tr>
							<tr><td colspan="2"><span style="color: red">${message2}</span></td></tr>
						</table>
					</form>
				
				</td></tr>
			</c:if>
			<tr><td colspan="2"><b>文件夹:</b></td></tr>
			<c:forEach var="dirInfo" items="${dirList}">
				<tr>
					<td>${dirInfo.name}</td>
					<c:if test="${dirInfo.status == 0}">
						<%-- <td>(空)<a href="${ctx}/down/down!deleteFile.action?directory=${directory}&wholePath=${dirInfo.name}">删除</a></td> --%>
						<td>(空)<a href="javascript:void(0)" onclick="deleteConfirm('${ctx}/down/down!deleteFile.action?directory=${directory}&wholePath=${dirInfo.name}','${dirInfo.name}');">删除</a></td>
					</c:if>
				</tr>
			</c:forEach>
			<tr><td colspan="2"><b>文件:</b></td></tr>
			<c:forEach var="fileInfo" items="${fileList}">
				<tr>
					<td><a href="${ctx}/down/down.action?directory=${directory}&fileName=${fileInfo.fileName}" >${fileInfo.wholePath}</a></td>
					<%-- <td><a href="${ctx}/down/down!deleteFile.action?directory=${directory}&wholePath=${fileInfo.wholePath}">删除</a></td> --%>
					<td><a href="javascript:void(0)" onclick="deleteConfirm('${ctx}/down/down!deleteFile.action?directory=${directory}&wholePath=${fileInfo.wholePath}','${fileInfo.fileName}');">删除</a></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>