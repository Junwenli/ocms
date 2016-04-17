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
import com.linkcm.core.util.PropertyUtils;

public class MysqlCodeJdbcDao extends BaseJdbcDao implements CodeJdbcDao {

	private static final String DATABASE;
	
	private Logger logger = LoggerUtils.getLogger(MysqlCodeJdbcDao.class);

	static {
		String value = PropertyUtils.get("jdbc.url");
		DATABASE = value.split("/")[3].split("\\?")[0];
	}
	
	@Override
	public Page<Map<String, Object>> findByPage(Pageable pageable, List<QueryParam> filters) {

		try {
			return findPageBySql(pageable, "SELECT TABLE_NAME as tablename FROM information_schema.TABLES WHERE table_schema=? union all SELECT TABLE_NAME FROM information_schema.VIEWS WHERE table_schema=?",
					filters, DATABASE,DATABASE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Column> findColumn(String table) {
		try {
			StringBuilder sb = new StringBuilder("");
			sb.append("SELECT table_name,column_name,numeric_scale,is_nullable,data_type, ");
			sb.append("character_maximum_length,column_key,column_comment ");
			sb.append("FROM information_schema.COLUMNS  WHERE table_schema=? AND table_name=?");
			List<Map<String, Object>> list = findBySql(sb.toString(), DATABASE, table);
			List<Column> result = new LinkedList<Column>();
			for (Map<String, Object> map : list) {
				Column column = new Column();
				column.setTable(map.get("table_name").toString());
				String name = map.get("column_name").toString();
				column.setName(name);
				column.setFieldName(getFieldName(name));
				if (map.get("column_comment") != null && !map.get("column_comment").toString().equals("")) {
					column.setDesc(map.get("column_comment").toString());
				}
				String type = map.get("data_type").toString();
				if (map.get("column_key") != null) {
					if (map.get("column_key").toString().equals("PRI")) {
						column.setId(true);
					}
					if (map.get("column_key").toString().equals("UNI")) {
						column.setUnique(true);
					}
				}

				column.setNullable("YES".equals(map.get("is_nullable")));
				if (map.get("character_maximum_length") != null && !map.get("character_maximum_length").toString().equals("")) {
					column.setLength(Long.parseLong(map.get("character_maximum_length").toString()));
				}
				if (type.indexOf("varchar") != -1) {
					column.setType("String");
				} else if (type.indexOf("decimal") != -1 || type.indexOf("float") != -1 || type.indexOf("double") != -1
						|| type.indexOf("numeric") != -1) {
					column.setType("BigDecimal");
				} else if (type.indexOf("date") != -1 || type.indexOf("time") != -1 || type.indexOf("year") != -1
						|| type.indexOf("timestamp") != -1 || type.indexOf("datetime") != -1) {
					column.setType("Date");
				} else {
					column.setType("Long");
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
		try {
			String[] words = name.split("_");
			String fieldName = words[0].toLowerCase();
			for (int i = 1; i < words.length; i++) {
				fieldName += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
			}
			return fieldName;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Map<String, Object>> findIndex(String table) {
		try {
			String sql = "select index_name as INDEX_NAME,group_concat(column_name) as COLUMN_NAME from information_schema.STATISTICS where table_schema=? AND table_name=? group by index_name";
			return findBySql(sql, DATABASE, table);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
