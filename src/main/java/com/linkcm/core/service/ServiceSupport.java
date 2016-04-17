package com.linkcm.core.service;

import org.slf4j.Logger;

import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.LoggerUtils;

public abstract class ServiceSupport {
	
	protected ServiceException handleException(String message, Exception e) {
		return CoreUtils.handleException(message, e, getLogger());
	}

	protected Logger getLogger() {
		return LoggerUtils.getLogger(this.getClass());
	}

}
