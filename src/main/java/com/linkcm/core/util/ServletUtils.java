package com.linkcm.core.util;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.entity.type.OperateType;
import com.linkcm.core.entity.type.PredictionType;

public final class ServletUtils {

	public static final String PREFIX = "filter";

	private ServletUtils() {

	}

	/**
	 * 获取filter开头的所有查询条件,以"_"作为分隔符，例如filter_LIKE_name,filter_EQ_code,
	 * 中间是操作符，最后的是字段名称，操作符种类参考OperateType枚举类.
	 * 
	 * @return List<QueryParam>
	 */
	@SuppressWarnings("unchecked")
	public static List<QueryParam> getParameters() {
		Enumeration<String> paramNames = ServletActionContext.getRequest().getParameterNames();
		List<QueryParam> result = new LinkedList<QueryParam>();

		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if (paramName.startsWith(PREFIX)) {
				String[] tmp = paramName.split("_");
				if (!StringUtils.isEmpty(ServletActionContext.getRequest().getParameterValues(paramName)[0].toString())) {
					if (tmp.length == 3) {
						result.add(new QueryParam(tmp[2], tmp[1], ServletActionContext.getRequest().getParameterValues(
								paramName)[0]));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 获取filter开头的所有查询条件,额外当前用户ID的查询条件
	 * 
	 * @param filed
	 *            实体里与当前用户ID相比的属性名
	 * @param operate
	 * @return List<QueryParam>
	 */
	public static List<QueryParam> getParametersPlusUserId(String filed, OperateType operate) {
		List<QueryParam> result = getParameters();
		result.add(new QueryParam(filed, operate, PredictionType.AND, SecurityUtils.getUserId().toString()));
		return result;
	}

	/**
	 * 获取filter开头的所有查询条件,额外当前用户名的查询条件
	 * 
	 * @param filed
	 *            实体里与当前用户username相比的属性名
	 * @param operate
	 * @return List<QueryParam>
	 */
	public static List<QueryParam> getParametersPlusUsername(String filed, OperateType operate) {
		List<QueryParam> result = getParameters();
		result.add(new QueryParam(filed, operate, PredictionType.AND, SecurityUtils.getUsername()));
		return result;
	}
}
