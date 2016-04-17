package com.linkcm.core.dao.jdbc.dialect;

public class MySQLJdbcDialect implements JdbcDialect {

	/* (non-Javadoc)
	 * @see com.ericsson.core.jdbc.dialect.JdbcDialect#getPaginationCountSql(java.lang.String)
	 */
	@Override
	public String getPaginationCountSql(String sql) {
		return "select count(*) from (" + sql + ") t";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ericsson.core.jdbc.dialect.JdbcDialect#getPaginationQuerySql(java.lang.String, int, int)
	 */
	@Override
	public String getPaginationQuerySql(String sql, int startPos, int pageSize) {
		return "select * from (" + sql + ") t limit " + startPos + ", " + pageSize;
	}	

}
