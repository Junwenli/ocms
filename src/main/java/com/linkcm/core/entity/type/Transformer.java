package com.linkcm.core.entity.type;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface Transformer {
	@SuppressWarnings("rawtypes")
	public void transfer(String key, Map map);

	/**
	 * 对sql查询结果列表进行枚举黑底
	 * 
	 * @param list
	 *            通过SQL查出的结果集
	 * @param key
	 *            要用枚举的属性
	 * @param transformer
	 *            枚举类的transformer属性。
	 * */
	public void transfer(String key, List<?> list);

	/**
	 * 对sql查询结果列表进行枚举黑底
	 * 
	 * @param page
	 *            通过SQL查出的结果集
	 * @param key
	 *            要用枚举的属性
	 * @param transformer
	 *            枚举类的transformer属性。
	 * */
	public void transfer(String key, Page<?> page);
}
