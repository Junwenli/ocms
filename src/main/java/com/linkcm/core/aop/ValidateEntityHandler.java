package com.linkcm.core.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.BeforeAdvice;

import com.linkcm.core.annotation.Validate;
import com.linkcm.core.util.CoreUtils;

public class ValidateEntityHandler implements BeforeAdvice {

	public void before(JoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Annotation[][] annotations = method.getParameterAnnotations();
		for(int i=0;i<annotations.length;i++){
			for(Annotation annotation:annotations[i]){
				if(annotation instanceof Validate){
					CoreUtils.validateEntity(joinPoint.getArgs()[i]);
				}
			}
		}
	}



}
