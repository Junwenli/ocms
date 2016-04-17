/**
 * 
 */
package com.linkcm.core.dao.jdbc.dialect;

public class OracleJdbcDialect implements JdbcDialect {

	public String getPaginationCountSql(String sql) {
		return "select count(*) from (" + sql + ") t";
	}

	public String getPaginationQuerySql(String sql, int startPos, int pageSize) {

		StringBuilder newSql = new StringBuilder();
		newSql.append("select * from ");
		newSql.append("(select t.*,rownum rn from ");
		newSql.append("(");
		newSql.append(sql);
		newSql.append(")");
		newSql.append("t where rownum<=");
		newSql.append(startPos + pageSize);
		newSql.append(") ");
		newSql.append("where rn> ");
		newSql.append(startPos);

		return newSql.toString();
	}

}
