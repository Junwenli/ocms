package com.linkcm.core.util;

import java.util.HashMap;
import java.util.Map;

import com.linkcm.core.service.CommonService;
import com.linkcm.core.service.ServiceException;

public final class JspUtils {

	private JspUtils() {

	}


	public static Map<Object, Object> getChooseMap(Class<?> clz, String key, String value, String headerValue) {
		return CoreUtils.context.getBean(CommonService.class).getChooseMap(clz, key, value, headerValue);
	}

	public static Map<Object, Object> getTableMap(Class<?> clz, String key, String value) {
		return CoreUtils.context.getBean(CommonService.class).getTableMap(clz, key, value);
	}

	@SuppressWarnings("rawtypes")
	public static Map<Object, Object> getChooseMap(Enum<?>[] enums, String headerValue) {
		try {
			Map<Object, Object> result = new HashMap<Object, Object>();

			for (Enum type : enums) {
				Object key = type.getClass().getDeclaredField("value").get(type);
				Object value = type.getClass().getDeclaredMethod("getDesc").invoke(type);
				result.put(key, value);
			}
			result.put("", I18nUtils.getMassage(headerValue));
			return result;
		} catch (Exception e) {
			throw new ServiceException("获取Choose map失败", e);
		}
	}
}
