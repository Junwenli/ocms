package com.linkcm.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.CommonRepositoryImpl;
import com.linkcm.core.util.I18nUtils;

@Component
@Transactional
public class CommonService extends ServiceSupport{

	@Autowired
	private CommonRepositoryImpl commonRepositoryImpl;

	/**
	 * 返回名值对MAP，一般用于构造动态下拉框
	 * 
	 * */
	public Map<Object, Object> getTableMap(Class<?> clz, String key, String value) {
		try {
			return commonRepositoryImpl.getTableMap(clz, key, value);
		} catch (Exception e) {
			throw handleException("获取table map失败", e);
		}

	}

	/**
	 * 返回名值对MAP，一般用于构造动态下拉框
	 * 
	 * */
	public Map<Object, Object> getChooseMap(Class<?> clz, String key, String value,String headerValue) {
		try {
			Map<Object, Object> result = commonRepositoryImpl.getTableMap(clz, key, value);
			result.put("", I18nUtils.getMassage(headerValue));
			return result;
		} catch (Exception e) {
			throw handleException("获取Choose map失败", e);
		}

	}

	/**
	 * 多表关联转义
	 * 
	 * */
	public Map<Object, Object> code2value(Object key, String entityName, String entityKey, String entityValue) {
		try {
			return commonRepositoryImpl.code2value(key, entityName, entityKey, entityValue);
		} catch (Exception e) {
			throw handleException("获取table map失败", e);
		}

	}

}
