package com.linkcm.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Component;

import com.linkcm.core.util.CoreUtils;

@Component
public class CommonRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	
	private static final String VALUE = "value";

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public Map<Object, Object> getTableMap(Class<?> clz, String key, String value) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		String entityName = clz.getSimpleName();
		StringBuilder hql = new StringBuilder("");
		hql.append("select new Map(").append(key).append(" as key,");
		hql.append(value).append(" as value) from ").append(entityName);
		List<Map<Object, Object>> list = em.createQuery(hql.toString()).getResultList();
		for (Map<Object, Object> m : list) {
			result.put(m.get("key"), m.get(VALUE));
		}
		return result;
	}

	/**
	 * 多表关联转义
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public Map<Object, Object> code2value(Object key, String entityName, String entityKey, String entityValue) {
		StringBuilder hql = new StringBuilder("");
		Map<Object, Object> map = new HashMap<Object, Object>();
		hql.append("select new Map(").append(entityValue).append(" as value) from ");
		hql.append(entityName).append(" where ");
		hql.append(entityKey).append("=?");

		Class<?> clazz = null;
		for (EntityType<?> type : em.getMetamodel().getEntities()) {
			if (type.getJavaType().getName().endsWith(entityName)) {
				clazz = type.getDeclaredAttribute(entityKey).getJavaType();
				break;
			}
		}
		Query query = em.createQuery(hql.toString());
		query.setParameter(1, CoreUtils.convertStringToObject("" + key, clazz));
		List<Map<String, Object>> result = query.getResultList();
		if (result.isEmpty()) {
			map.put(VALUE, "");
		} else {
			map.put(VALUE, result.get(0).get(VALUE));
		}
		return map;
	}
}
