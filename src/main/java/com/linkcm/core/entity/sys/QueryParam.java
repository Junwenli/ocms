package com.linkcm.core.entity.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.hibernate.ejb.criteria.ValueHandlerFactory;
import org.hibernate.ejb.criteria.expression.LiteralExpression;

import com.linkcm.core.entity.type.OperateType;
import com.linkcm.core.entity.type.PredictionType;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.CoreUtils;

public final class QueryParam {

	private String name;

	private OperateType operate;

	private PredictionType predictionType;

	private String value;

	private boolean group = false;

	private String[] names;

	private OperateType[] operates;

	public QueryParam(String name, String value) {
		this(name, OperateType.EQ.value, value);
	}

	public QueryParam(String name, OperateType operate, PredictionType predictionType, String value) {
		this.name = name;
		this.operate = operate;
		this.value = value;
		this.predictionType = predictionType;
	}

	public QueryParam(String[] groups, PredictionType predictionType, String value) {
		this.value = value;
		int length = groups.length / 2;
		names = new String[length];
		operates = new OperateType[length];
		for (int i = 0; i < length; i++) {
			names[i] = groups[2*i];
			operates[i] = getOperateType(groups[2 * i + 1]);
		}
		group = true;
		this.predictionType = predictionType;
	}

	public QueryParam(String name, String operate, String value) {
		this.name = name;
		if (operate.length() == 3) {
			operate = operate.substring(0, 2);
		} else if (operate.length() == 5) {
			operate = operate.substring(0, 4);
		}
		this.operate = getOperateType(operate);

		this.predictionType = PredictionType.AND;

		this.value = value;
	}

	private static OperateType getOperateType(String operate) {
		try {
			return Enum.valueOf(OperateType.class, operate);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("错误比较符:" + operate, e);
		}
	}

	public String getName() {
		return name;
	}

	public OperateType getOperate() {
		return operate;
	}

	public String getValue() {
		return value;
	}

	@SuppressWarnings({ "rawtypes" })
	public Predicate buildPredicate(CriteriaBuilder builder, Root root, Predicate predicates) {
		Predicate predicate = null;

		if (isGroup()) {
			for (int i = 0; i < names.length; i++) {
				if (predicate != null) {
					predicate = builder.or(predicate, buildSinglePredicate(builder, root, names[i], operates[i]));
				} else {
					predicate = buildSinglePredicate(builder, root, names[i], operates[i]);
				}
			}
		} else {
			predicate = buildSinglePredicate(builder, root, name, operate);
		}

		if (predicates != null) {
			if (PredictionType.OR.equals(predictionType)) {
				predicate = builder.or(predicates, predicate);
			} else {
				predicate = builder.and(predicates, predicate);
			}
		}

		return predicate;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Predicate buildSinglePredicate(CriteriaBuilder builder, Root root, String name, OperateType operate) {
		Predicate predicate = null;
		EntityType model = root.getModel();
		Class propertyClass = model.getAttribute(name).getJavaType();

		switch (operate) {
		case EQ:
			predicate = builder.equal(root.get(name), CoreUtils.convertStringToObject(value, propertyClass));
			break;
		case NE:
			predicate = builder.notEqual(root.get(name), CoreUtils.convertStringToObject(value, propertyClass));
			break;
		case LIKE:
			predicate = builder.like(getExpression(root, name, String.class), "%" + value + "%");
			break;
		case LE:
			predicate = builder.lessThanOrEqualTo(getExpression(root, name, propertyClass),
					getValueExpression((CriteriaBuilderImpl) builder, value, propertyClass));
			break;
		case LT:
			predicate = builder.lessThan(getExpression(root, name, propertyClass),
					getValueExpression((CriteriaBuilderImpl) builder, value, propertyClass));
			break;
		case GE:
			predicate = builder.greaterThanOrEqualTo(getExpression(root, name, propertyClass),
					getValueExpression((CriteriaBuilderImpl) builder, value, propertyClass));
			break;
		case GT:
			predicate = builder.greaterThan(getExpression(root, name, propertyClass),
					getValueExpression((CriteriaBuilderImpl) builder, value, propertyClass));
			break;
		case IN:
			List inList = new ArrayList();
			for (String subValue : value.split(",")) {
				if (ValueHandlerFactory.isNumeric(propertyClass)) {
					inList.add(ValueHandlerFactory.convert(subValue, (Class<Number>) propertyClass));
				} else {
					inList.add(CoreUtils.convertStringToObject(subValue.toString(), propertyClass));
				}
			}
			predicate = getExpression(root, name, propertyClass).in(inList);
			break;
		}
		return predicate;
	}

	public PredictionType getPredictionType() {
		return predictionType;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Expression getExpression(Root root, String propertyName, Class clazz) {
		EntityType model = root.getModel();
		return root.get(model.getSingularAttribute(propertyName, clazz));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Expression getValueExpression(CriteriaBuilderImpl criteriaBuilder, Object value, Class clazz) {
		if (ValueHandlerFactory.isNumeric(clazz)) {
			return new LiteralExpression(criteriaBuilder, ValueHandlerFactory.convert(value, (Class<Number>) clazz));
		} else {
			return new LiteralExpression(criteriaBuilder, CoreUtils.convertStringToObject(value.toString(), clazz));
		}
	}

	public static void plusOrParam(List<QueryParam> params, String paramName, OperateType operate, String value) {
		if (StringUtils.isNotEmpty(value)) {
			QueryParam orFilter = new QueryParam(paramName, operate, PredictionType.OR, value);
			params.add(orFilter);
		}
	}

	public static List<QueryParam> plusOrParams(List<QueryParam> params, String orParam, String[] paramArray) {
		if (paramArray == null || StringUtils.isEmpty(orParam)) {
			return params;
		}
		if (paramArray.length % 2 != 0) {
			throw new ServiceException("搜索数组必须是2的倍数");
		}
		QueryParam param = new QueryParam(paramArray, PredictionType.AND, orParam);
		params.add(param);
		return params;
	}

	public boolean isGroup() {
		return group;
	}

	public String[] getNames() {
		return names;
	}

	public OperateType[] getOperates() {
		return operates;
	}
	
	

}
