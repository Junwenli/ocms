<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	var serverUrl='<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getParameter("port")%>/cas/logout?service=';
	serverUrl+='<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>${ctx}';
	topPage(serverUrl);
	
	function topPage(urlPath)
	{
		if(window.opener)
		{
			try{
				window.opener.top.location.href=urlPath;
			}catch(e)
			{
				window.top.location.href=urlPath;
			}
			window.close();
		}else if(window.dialogArguments)
		{
			window.dialogArguments.top.location.href=urlPath;
			window.close();
		}else
		{
			window.top.location.href=urlPath;
		}
	}
</script>
