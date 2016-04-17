package com.linkcm.core.util;

import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public final class I18nUtils {
	
	public static final Logger LOGGER = LoggerUtils.getLogger(I18nUtils.class);

	private I18nUtils() {

	}

	public static String getMassage(String parameter) {
		if(parameter==null){
			return parameter;
		}
		String massage = null;
		try {
			massage = LocalizedTextUtil.findDefaultText(parameter, ActionContext.getContext().getLocale());
		} catch (Exception e) {
			LOGGER.debug("国际化异常!", e);
			return parameter;
		}
		if (massage == null) {
			return parameter;
		} else {
			return massage;
		}

	}

}
