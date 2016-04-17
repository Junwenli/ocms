package com.linkcm.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.hibernate.ejb.criteria.CriteriaQueryCompiler;
import org.hibernate.ejb.criteria.OrderImpl;
import org.hibernate.ejb.criteria.expression.function.AggregationFunction.COUNT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.util.CoreUtils;

public class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepositoryCustom<T, ID> {

	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findAll(Pageable pageable, List<QueryParam> filters) {
		filters = CoreUtils.orderFilters(filters);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getEntityClass());
		Root<T> root = query.from(getEntityClass());
		Predicate predicate = buildPredicateByQueryParam(builder, root, filters);
		if (predicate != null) {
			query.where(predicate);
		}

		if (pageable.getSort() != null) {
			setSort(pageable, root, query);
		}
		Query hqlQuery = em.createQuery(query);
		setResult(pageable, hqlQuery);
		long size = getSize(root, predicate);
		return new PageImpl<T>(hqlQuery.getResultList(), pageable, size);
	}

	private void setSort(Pageable pageable, Root<T> root, CriteriaQuery<T> query) {
		List<javax.persistence.criteria.Order> orders = new LinkedList<javax.persistence.criteria.Order>();
		for (Order order : pageable.getSort()) {
			orders.add(new OrderImpl(getExpression(root, order.getProperty()), order.isAscending()));
		}
		query.orderBy(orders);
	}

	private void setResult(Pageable pageable, Query hqlQuery) {
		hqlQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
		hqlQuery.setMaxResults(pageable.getPageSize());
	}

	private Predicate buildPredicateByQueryParam(CriteriaBuilder builder, Root<T> root, final List<QueryParam> filters) {
		Predicate predicate = null;
		for (QueryParam filter : filters) {
			predicate = filter.buildPredicate(builder, root, predicate);
		}
		return predicate;
	}

	private long getSize(Root<T> root, Predicate predicate) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		countQuery.from(getEntityClass());
		if (predicate != null) {
			countQuery.where(predicate);
		}

		countQuery.select(new CountAll((CriteriaBuilderImpl) builder, root, false));

		return em.createQuery(countQuery).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		return (Class<T>) getGenericClass(0);
	}

	@SuppressWarnings("unchecked")
	public Class<ID> getIdClass() {
		return (Class<ID>) getGenericClass(1);
	}

	private Class<?> getGenericClass(int i) {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<?>) params[i];
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public boolean isEndow(Class<?> entityClz, String property, Object value) {
		String entityName = entityClz.getSimpleName();
		String entityAlias = entityName.toLowerCase();
		StringBuilder hql = new StringBuilder(" from ").append(entityName).append(" ").append(entityAlias);
		hql.append(" where ").append(entityAlias).append(".").append(property).append("=?");

		Query query = em.createQuery(hql.toString());
		query.setParameter(1, value);
		List<?> result = query.getResultList();
		return !result.isEmpty();
	}

	@SuppressWarnings("serial")
	public static class CountAll extends COUNT {
		public CountAll(CriteriaBuilderImpl criteriaBuilder, Expression<?> expression, boolean distinct) {
			super(criteriaBuilder, expression, distinct);
		}

		public String render(CriteriaQueryCompiler.RenderingContext renderingContext) {
			StringBuilder buffer = new StringBuilder();
			buffer.append(getFunctionName()).append("(*)");
			return buffer.toString();
		}
	}

	/**
	 * 检查是否唯一
	 * 
	 */
	private boolean isEntityUnique(String idName, Object idValue, Object... paramsArray) {
		StringBuilder hql = new StringBuilder("from ").append(getEntityClass().getSimpleName()).append(" where ");
		int length = paramsArray.length / 2;
		Object[] param = new Object[idValue == null ? length : length + 1];
		for (int i = 0; i < length; i++) {
			hql.append(paramsArray[i * 2]).append("=?");
			param[i] = paramsArray[i * 2 + 1];
			if (i < length - 1) {
				hql.append(" and ");
			}
		}
		if (idValue != null) {
			hql.append(" and ").append(idName).append("!=?");
			param[param.length - 1] = idValue;
		}
		T entity = findUnique(hql.toString(), param);
		if (entity != null) {
			return true;
		}
		return false;
	}

	public boolean isEntityUniqueById(ID idValue, Object... paramsArray) {
		return isEntityUnique(getIdName(), idValue, paramsArray);
	}

	public String getIdName() {
		for (EntityType<?> type : em.getMetamodel().getEntities()) {
			if (type.getJavaType().equals(getEntityClass())) {
				return type.getDeclaredId(getIdClass()).getName();
			}
		}
		return null;
	}

	private Expression<?> getExpression(Root<T> root, String propertyName) {
		EntityType<T> model = root.getModel();
		Class<?> clazz = null;
		for (EntityType<?> type : em.getMetamodel().getEntities()) {
			if (type.getJavaType().equals(getEntityClass())) {
				type.getDeclaredAttribute(propertyName).getJavaType();
				break;
			}
		}
		return root.get(model.getSingularAttribute(propertyName, clazz));
	}

	@SuppressWarnings({ "unchecked" })
	private T findUnique(String hql, Object... params) {
		Query query = em.createQuery(hql, getEntityClass());
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		List<T> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<T> findAll(List<QueryParam> filters) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE);
		return findAll(pageable, filters).getContent();
	}
	
	@Override
	public List<T> findAll(List<QueryParam> filters, Sort sort) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,sort);
		return findAll(pageable, filters).getContent();
	}

}
