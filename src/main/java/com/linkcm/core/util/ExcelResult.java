package com.linkcm.core.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

public class ExcelResult extends StrutsResultSupport {

	private static final long serialVersionUID = 1L;

	private String contentType = "application/vnd.ms-excel";

	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);
		ServletContext servletContext = (ServletContext) invocation.getInvocationContext().get(SERVLET_CONTEXT);

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + ".xls\"");
		InputStream in = null;
		try {
			ValueStack stack = invocation.getStack();

			in = servletContext.getResourceAsStream(finalLocation);

			Map<String, Object> map = new HashMap<String, Object>();
			for (Method method : invocation.getAction().getClass().getDeclaredMethods()) {
				String methodName = method.getName();
				if (methodName.startsWith("get")) {
					setOgnlVlaue(methodName.substring(3), stack, map);
					continue;
				}
				if (methodName.startsWith("is")) {
					setOgnlVlaue(methodName.substring(2), stack, map);
				}
			}

			OutputStream out = response.getOutputStream();

			XLSTransformer transformer = new XLSTransformer();
			Workbook workbook = transformer.transformXLS(in, map);
			workbook.write(out);

			out.flush();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private void setOgnlVlaue(String name, ValueStack stack, Map<String, Object> map) {
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		map.put(name, stack.findValue(name));
	}

}
