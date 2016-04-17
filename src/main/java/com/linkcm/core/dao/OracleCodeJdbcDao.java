package com.linkcm.core.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linkcm.core.entity.sys.Column;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.LoggerUtils;

public class OracleCodeJdbcDao extends BaseJdbcDao implements CodeJdbcDao {
	
	private Logger logger = LoggerUtils.getLogger(OracleCodeJdbcDao.class);

	public Page<Map<String, Object>> findByPage(Pageable pageable, final List<QueryParam> filters) {
		return findPageBySql(pageable, "select TABLE_NAME as \"tablename\" from user_tables", filters);
	}

	public List<Column> findColumn(String table) {
		try {
			List<Map<String, Object>> list = findBySql(
					"select c.*,t.*,(select 1 from user_cons_columns cc,user_constraints uc where cc.TABLE_NAME=t.TABLE_NAME and cc.CONSTRAINT_NAME = uc.CONSTRAINT_NAME and uc.CONSTRAINT_TYPE='P' and cc.COLUMN_NAME=c.COLUMN_NAME) as PRIMARY_KEY,(select 1 from user_cons_columns cc,user_constraints uc where cc.TABLE_NAME=t.TABLE_NAME and cc.CONSTRAINT_NAME = uc.CONSTRAINT_NAME and uc.CONSTRAINT_TYPE='U' and cc.COLUMN_NAME=c.COLUMN_NAME) as IS_UNIQUE from User_Tab_Cols t,User_Col_Comments c where t.TABLE_NAME=c.TABLE_NAME and t.COLUMN_NAME=c.COLUMN_NAME and t.TABLE_NAME=?",
					table);
			List<Column> result = new LinkedList<Column>();
			for (Map<String, Object> map : list) {
				Column column = new Column();
				column.setTable(map.get("TABLE_NAME").toString());
				String name = map.get("COLUMN_NAME").toString();
				column.setName(name);
				column.setFieldName(getFieldName(name));
				if (map.get("COMMENTS") != null) {
					column.setDesc(map.get("COMMENTS").toString());
				}
				String type = map.get("DATA_TYPE").toString();
				if (map.get("PRIMARY_KEY") != null) {
					column.setId(true);
				}
				if (map.get("IS_UNIQUE") != null) {
					column.setUnique(true);
				}
				column.setNullable("Y".equals(map.get("NULLABLE")));
				column.setLength(Integer.parseInt(map.get("CHAR_LENGTH") != null ? map.get("CHAR_LENGTH").toString()
						: map.get("DATA_LENGTH").toString()));
				if (type.indexOf("VARCHAR") != -1) {
					column.setType("String");
				} else if (type.indexOf("DATE") != -1 || type.indexOf("TIMESTAMP") != -1) {
					column.setType("Date");
				} else if (type.indexOf("NUMBER") != -1 || type.indexOf("LONG") != -1) {
					if (Integer.parseInt(map.get("DATA_SCALE").toString()) > 0) {
						column.setType("BigDecimal");
					} else {
						column.setType("Long");
					}
				}
				result.add(column);
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	private String getFieldName(String name) {
		String[] words = name.split("_");
		String fieldName = words[0].toLowerCase();
		for (int i = 1; i < words.length; i++) {
			fieldName += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
		}
		return fieldName;
	}

	public List<Map<String, Object>> findIndex(String table) {
		return findBySql(
				"select wm_concat(ind.COLUMN_NAME) as COLUMN_NAME, ind.INDEX_NAME as INDEX_NAME from user_ind_columns ind where ind.TABLE_NAME=? group by ind.INDEX_NAME",
				table);
	}

}
