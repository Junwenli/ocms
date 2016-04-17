package com.linkcm.core.dao.jdbc.dialect;

public interface JdbcDialect {

	/**
	 * get count SQL
	 * @param sql
	 * @return
	 */
	public String getPaginationCountSql(String sql);
	
	/**
	 * get query SQL
	 * @param sql
	 * @param startPos
	 * @param pageSize
	 * @return
	 */
	public String getPaginationQuerySql(String sql, int startPos, int pageSize);
	
}
