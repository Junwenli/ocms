package com.linkcm.core.interceptor;

import java.lang.reflect.Method;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.web.CoreAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionRequestInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Method method = invocation.getAction().getClass().getMethod(invocation.getProxy().getMethod());
		WebRequest webRequest = method.getAnnotation(WebRequest.class);
		if (webRequest != null && webRequest.value().equals(RequestType.AJAX)) {
			try {
				invocation.invoke();
			} catch (Exception e) {
				((CoreAction<?, ?>) invocation.getAction()).failure(e);
			}
			return null;
		} else {
			return invocation.invoke();
		}

	}

}
