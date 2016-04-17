package com.linkcm.core.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.linkcm.core.entity.sys.QueryParam;

public interface BaseRepositoryCustom<T, ID extends Serializable> {
	public Page<T> findAll(final Pageable pageable, List<QueryParam> filters);

	public List<T> findAll(List<QueryParam> filters);
	
	public List<T> findAll(List<QueryParam> filters, Sort sort);

	/**
	 * 判断实体是否被其它表使用
	 * 
	 * */
	public boolean isEndow(Class<?> entityClz, String property, Object value);

	public boolean isEntityUniqueById(ID idValue, Object... paramsArray);
}
