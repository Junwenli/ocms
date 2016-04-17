/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linkcm.core.dao;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import com.linkcm.core.dao.jdbc.dialect.JdbcDialect;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.entity.type.OperateType;
import com.linkcm.core.util.CoreUtils;

/**
 * Convenient super class for JDBC-based data access objects.
 * 
 * <p>
 * Requires a {@link javax.sql.DataSource} to be set, providing a
 * {@link org.springframework.jdbc.core.JdbcTemplate} based on it to subclasses
 * through the {@link #getJdbcTemplate()} method.
 * 
 * <p>
 * This base class is mainly intended for JdbcTemplate usage but can also be
 * used when working with a Connection directly or when using
 * <code>org.springframework.jdbc.object</code> operation objects.
 * 
 * @author Juergen Hoeller
 * @since 28.07.2003
 * @see #setDataSource
 * @see #getJdbcTemplate
 * @see org.springframework.jdbc.core.JdbcTemplate
 */
public class BaseJdbcDao extends DaoSupport {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcDialect jdbcDialect;

	/**
	 * Set the JDBC DataSource to be used by this DAO.
	 */
	@Autowired
	public final void setDataSource(DataSource dataSource) {
		if (this.jdbcTemplate == null || dataSource != this.jdbcTemplate.getDataSource()) {
			this.jdbcTemplate = createJdbcTemplate(dataSource);
			initTemplateConfig();
		}
	}

	/**
	 * Create a JdbcTemplate for the given DataSource. Only invoked if
	 * populating the DAO with a DataSource reference!
	 * <p>
	 * Can be overridden in subclasses to provide a JdbcTemplate instance with
	 * different configuration, or a custom JdbcTemplate subclass.
	 * 
	 * @param dataSource
	 *            the JDBC DataSource to create a JdbcTemplate for
	 * @return the new JdbcTemplate instance
	 * @see #setDataSource
	 */
	protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Return the JDBC DataSource used by this DAO.
	 */
	private final DataSource getDataSource() {
		return (this.jdbcTemplate != null ? this.jdbcTemplate.getDataSource() : null);
	}

	/**
	 * Set the JdbcTemplate for this DAO explicitly, as an alternative to
	 * specifying a DataSource.
	 */
	public final void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		initTemplateConfig();
	}

	/**
	 * Return the JdbcTemplate for this DAO, pre-initialized with the DataSource
	 * or set explicitly.
	 */
	public final JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * Initialize the template-based configuration of this DAO. Called after a
	 * new JdbcTemplate has been set, either directly or through a DataSource.
	 * <p>
	 * This implementation is empty. Subclasses may override this to configure
	 * further objects based on the JdbcTemplate.
	 * 
	 * @see #getJdbcTemplate()
	 */
	protected void initTemplateConfig() {
	}

	@Override
	protected void checkDaoConfig() {
		if (this.jdbcTemplate == null) {
			throw new IllegalArgumentException("'dataSource' or 'jdbcTemplate' is required");
		}
	}

	/**
	 * Return the SQLExceptionTranslator of this DAO's JdbcTemplate, for
	 * translating SQLExceptions in custom JDBC access code.
	 * 
	 * @see org.springframework.jdbc.core.JdbcTemplate#getExceptionTranslator()
	 */
	protected final SQLExceptionTranslator getExceptionTranslator() {
		return getJdbcTemplate().getExceptionTranslator();
	}

	/**
	 * Get a JDBC Connection, either from the current transaction or a new one.
	 * 
	 * @return the JDBC Connection
	 * @throws CannotGetJdbcConnectionException
	 *             if the attempt to get a Connection failed
	 * @see org.springframework.jdbc.datasource.DataSourceUtils#getConnection(javax.sql.DataSource)
	 */
	protected final Connection getConnection() throws CannotGetJdbcConnectionException {
		return DataSourceUtils.getConnection(getDataSource());
	}

	/**
	 * Close the given JDBC Connection, created via this DAO's DataSource, if it
	 * isn't bound to the thread.
	 * 
	 * @param con
	 *            Connection to close
	 * @see org.springframework.jdbc.datasource.DataSourceUtils#releaseConnection
	 */
	protected final void releaseConnection(Connection con) {
		DataSourceUtils.releaseConnection(con, getDataSource());
	}

	/**
	 * 通过sql查询
	 * 
	 * */
	public List<Map<String, Object>> findBySql(String sql, final Object... values) {
		return getJdbcTemplate().queryForList(sql, values);
	}

	/**
	 * 通过sql查询
	 * 
	 * */
	public List<Map<String, Object>> findBySql(String sql, Sort sort, final Object... values) {
		String sortSql = getSortSql(sort, sql);
		return getJdbcTemplate().queryForList(sortSql, values);
	}

	/**
	 * 通过sql查询
	 * 
	 * */
	public List<Map<String, Object>> findBySql(String sql, List<QueryParam> filters, final Object... values) {
		filters = CoreUtils.orderFilters(filters);
		return getJdbcTemplate().queryForList(getFilterSql(sql, filters), getSqlParams(filters, values));
	}

	/**
	 * 通过sql查询
	 * 
	 * */
	public List<Map<String, Object>> findBySql(String sql, List<QueryParam> filters, Sort sort, final Object... values) {
		filters = CoreUtils.orderFilters(filters);
		String sortSql = getSortSql(sort, getFilterSql(sql, filters));
		return getJdbcTemplate().queryForList(sortSql, getSqlParams(filters, values));
	}

	/**
	 * 通过sql查询
	 * 
	 * */
	public Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, final Object... values) {
		String countSql = jdbcDialect.getPaginationCountSql(sql);
		long size = getJdbcTemplate().queryForLong(countSql, values);

		String sortSql = getSortSql(pageable.getSort(), sql);

		String querySql = jdbcDialect.getPaginationQuerySql(sortSql, pageable.getPageNumber() * pageable.getPageSize(),
				pageable.getPageSize());

		List<Map<String, Object>> result = getJdbcTemplate().queryForList(querySql, values);
		return new PageImpl<Map<String, Object>>(result, pageable, size);
	}

	private String getSortSql(Sort sort, String sql) {
		StringBuilder sb = new StringBuilder(sql);
		if (sort != null) {
			int i = 0;
			for (Order order : sort) {
				if (i == 0) {
					sb.append(" order by ");
					i++;
				} else {
					sb.append(",");
				}
				sb.append(order.getProperty()).append(" ").append(order.getDirection());

			}
		}
		return sb.toString();
	}

	public Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, List<QueryParam> filters,
			final Object... values) {
		filters = CoreUtils.orderFilters(filters);
		return findPageBySql(pageable, getFilterSql(sql, filters), getSqlParams(filters, values));
	}

	private String getFilterSql(String sql, List<QueryParam> filters) {
		StringBuilder sb = new StringBuilder("select * from (");
		sb.append(sql).append(") t");
		for (int i = 0; i < filters.size(); i++) {
			QueryParam queryParam = filters.get(i);
			if (i == 0) {
				sb.append(" where ");
			} else {
				sb.append(queryParam.getPredictionType().value).append(" ");
			}
			if (queryParam.isGroup()) {
				sb.append(" (");
				for (int j = 0; j < queryParam.getNames().length; j++) {
					appendSingleQueryParam(sb, queryParam.getNames()[j], queryParam.getValue(),
							queryParam.getOperates()[j]);
					if (j != queryParam.getNames().length - 1) {
						sb.append(" or ");
					}
				}
				sb.append(") ");

			} else {
				appendSingleQueryParam(sb, queryParam.getName(), queryParam.getValue(), queryParam.getOperate());
			}

		}
		return sb.toString();
	}

	private void appendSingleQueryParam(StringBuilder sb, String name, String value, OperateType operater) {
		sb.append(name).append(" ").append(operater.operater).append(" ");
		if (operater.equals(OperateType.IN)) {
			int length = value.split(",").length;
			sb.append("(");
			for (int j = 0; j < length; j++) {
				sb.append("?");
				if (j != length - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
		} else {
			sb.append("?");
		}
	}

	private Object[] getSqlParams(List<QueryParam> filters, final Object... values) {
		List<Object> params = new LinkedList<Object>();
		if (values != null) {
			for (Object value : values) {
				params.add(value);
			}
		}
		for (QueryParam param : filters) {
			if (param.isGroup()) {
				for (int j = 0; j < param.getNames().length; j++) {
					addSingleQueryParam(params, param.getValue(), param.getOperates()[j]);
				}
			} else {
				addSingleQueryParam(params, param.getValue(), param.getOperate());
			}
		}
		return params.toArray();
	}

	private void addSingleQueryParam(List<Object> params, String value, OperateType operate) {
		if (operate.equals(OperateType.LIKE)) {
			params.add("%" + value + "%");
		} else if (operate.equals(OperateType.IN)) {
			for (String subValue : value.split(",")) {
				params.add(subValue);
			}
		} else {
			params.add(value);
		}

	}

}
