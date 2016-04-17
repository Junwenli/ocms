package com.linkcm.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.linkcm.core.annotation.Validation;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.entity.type.PredictionType;
import com.linkcm.core.service.CommonService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.service.sys.TestUtils;

public final class CoreUtils {

	private static Logger logger = LoggerUtils.getLogger(CoreUtils.class);

	static {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
	}

	public static final WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

	private CoreUtils() {

	}

	/**
	 * 处理SQL语句规范。
	 * 
	 * @param sqlPath
	 *            要处理的SQL
	 * @param context
	 *            　
	 */
	public static void executeSql(String sqlPath, ApplicationContext context) {
		TestUtils baseJdbcDao = context.getBean(TestUtils.class);
		List<String> sqls = new LinkedList<String>();
		RandomAccessFile file = null;
		try {
			String s = null;
			file = new RandomAccessFile(context.getResource(sqlPath).getFile(), "r");
			while ((s = file.readLine()) != null) {
				if (!s.trim().equals("") && !s.startsWith("//")) {
					sqls.add(s);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		if (!sqls.isEmpty()) {
			baseJdbcDao.executeSql(sqls.toArray(new String[0]));
		}

	}

	/**
	 * 多表关联转义
	 * 
	 * @param key
	 *            与外部实体联系的属性
	 * @param entity
	 *            关联的实体类
	 * @param entityKey
	 *            关联的属性与key相关联的
	 * @param entityValue
	 *            要查询显示的关联实体类的属性
	 * 
	 * */
	public static String code2value(Object key, Class<?> entity, String entityKey, String entityValue) {
		try {
			CommonService commonService = context.getBean(CommonService.class);
			return commonService.code2value(key, entity.getSimpleName(), entityKey, entityValue).get("value")
					.toString();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 把object转换为整形值，object必须为BigDecimal,Long,Integer及其原始类型
	 * 
	 * @param o
	 *            要转换的Object。
	 * @return
	 * */
	public static Integer object2int(Object o) {
		int value;
		if (o == null) {
			return null;
		}
		if (o.getClass() == BigDecimal.class) {
			value = ((BigDecimal) o).intValue();
		} else if (o.getClass() == Integer.class) {
			value = (Integer) o;
		} else {
			value = ((Long) o).intValue();
		}
		return value;
	}

	/**
	 * 把String类型转换成其他类型。
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param toType
	 *            转换成的类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <Y> Y convertStringToObject(String value, Class<Y> toType) {
		try {
			return (Y) org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw new ServiceException("类型转换失败", e);
		}
	}

	/**
	 * 校验实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public static void validateEntity(Object entity) {
		Validation validation = null;
		try {
			for (Field field : entity.getClass().getDeclaredFields()) {
				validation = field.getAnnotation(Validation.class);
				if (validation != null) {
					for (Method m : entity.getClass().getDeclaredMethods()) {
						if (m.getName().toUpperCase().equals("GET" + field.getName().toUpperCase())) {
							Object fieldValue = m.invoke(entity);
							if (fieldValue != null) {
								if (field.getType() == String.class) {
									String value = fieldValue.toString();
									if (!validation.nullable() && "".equals(value)) {
										throw new ServiceException(validation.name() + "不能为空");
									}
									if (value.length() < validation.min()) {
										throw new ServiceException(validation.name() + "长度不能少于" + validation.min());
									}
									if (value.length() > validation.max()) {
										throw new ServiceException(validation.name() + "长度不能超过" + validation.max());
									}
									if (validation.email()) {
										String check = "\\w+@(\\w+.)+[a-z]{2,3}";
										Pattern regex = Pattern.compile(check);
										Matcher matcher = regex.matcher(value);
										boolean isMatched = matcher.matches();
										if (!isMatched) {
											throw new ServiceException(validation.name() + "不是正常的email格式");
										}
									}

								} else {
									Long value = Long.parseLong(fieldValue.toString());
									if (value < validation.min()) {
										throw new ServiceException(validation.name() + "不能少于" + validation.min());
									}
									if (value > validation.max()) {
										throw new ServiceException(validation.name() + "不能超过" + validation.max());
									}
								}
								if (validation.enumClass() != Enum.class) {
									boolean pass = false;
									Enum<?>[] enums = validation.enumClass().getEnumConstants();
									for (Enum<?> type : enums) {
										if (type.getClass().getField("value").get(type).equals(fieldValue)) {
											pass = true;
											break;
										}
									}
									if (!pass) {
										StringBuilder sb = new StringBuilder(validation.name());
										sb.append("只能为{");
										for (int i = 0; i < enums.length; i++) {
											sb.append(validation.enumClass().getField("value").get(enums[i]));
											if (i < enums.length - 1) {
												sb.append(",");
											}
										}
										sb.append("}其中之一");
										throw new ServiceException(sb.toString());
									}
								}
								if (!StringUtils.isEmpty(validation.regex())) {
									String[] regex = validation.regex().split("::");
									if (!Pattern.matches(regex[0], fieldValue.toString())) {
										throw new ServiceException(regex[1]);
									}
								}
							} else {
								if (!validation.nullable()) {
									throw new ServiceException(validation.name() + "不能为空");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw handleException("校验" + validation.name() + "失败", e, logger);
		}
	}

	public static ServiceException handleException(String message, Exception e, Logger logger) {
		if (e instanceof ServiceException) {
			return (ServiceException) e;
		} else {
			logger.error(e.getMessage(), e);
			return new ServiceException(message, e);
		}

	}

	/**
	 * 排序filters
	 * 
	 * */
	public static List<QueryParam> orderFilters(List<QueryParam> filters) {
		List<QueryParam> result = new LinkedList<QueryParam>(filters);
		// 把and的filter排前面，or的排后面
		for (int i = 0, j = 0, k = 0; i < filters.size(); i++) {
			if (filters.get(i).getPredictionType().equals(PredictionType.AND)) {
				result.set(j++, filters.get(i));
			} else {
				++k;
				result.set(filters.size() - k, filters.get(i));
			}
		}
		return result;
	}

}
