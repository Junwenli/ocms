package com.linkcm.core.service;

import com.linkcm.core.util.I18nUtils;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author pengyi
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(I18nUtils.getMassage(message));
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(I18nUtils.getMassage(message), cause);
	}
}
