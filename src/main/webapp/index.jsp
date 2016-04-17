<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath=request.getContextPath();%>
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript">
			//alert("index.jsp");
			var basePath="<%=basePath%>";
			var urlPath=basePath+"/login.jsp";
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
	</head>
	<body>
	</body>
</html>