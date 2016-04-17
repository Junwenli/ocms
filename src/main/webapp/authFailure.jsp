<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	var urlPath='${ctx}/j_spring_security_logout';
	topPage(urlPath);
	
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