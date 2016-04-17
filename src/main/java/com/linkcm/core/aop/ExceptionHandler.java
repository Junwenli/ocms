package com.linkcm.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.ThrowsAdvice;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.LoggerUtils;

public class ExceptionHandler implements ThrowsAdvice {

	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		ErrorMessage errorMessage = method.getAnnotation(ErrorMessage.class);
		if (!(ex instanceof ServiceException)) {
			String message = errorMessage == null ? ex.getMessage() : errorMessage.value();
			LoggerUtils.getLogger(joinPoint.getTarget().getClass()).error(message, ex);
			throw new ServiceException(message, ex);
		}

	}

}
