package com.linkcm.core.service.sys;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.CodeJdbcDao;
import com.linkcm.core.entity.sys.Column;
import com.linkcm.core.entity.sys.Index;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;

@Component
@Transactional
public class CodeService {

	@Autowired
	private CodeJdbcDao codeJdbcDao;

	/**
	 * 分页查询多个角色
	 * 
	 */
	public EasyUiDataGrid searchForAjax(final Pageable pageable, final List<QueryParam> filters) {
		return EasyUiUtils.getEasyUiDataGrid(codeJdbcDao.findByPage(pageable, filters));
	}

	public List<Column> findColumn(String table) {
		return codeJdbcDao.findColumn(table);
	}

	public void setCodeJdbcDao(CodeJdbcDao codeJdbcDao) {
		this.codeJdbcDao = codeJdbcDao;
	}

	public List<Index> findIndex(String table, List<Column> columnList) {
		List<Map<String, Object>> list = codeJdbcDao.findIndex(table);
		List<Index> result = new LinkedList<Index>();
		for (Map<String, Object> map : list) {
			Index index = new Index();
			index.setIndexName(map.get("INDEX_NAME").toString());
			String[] columns = map.get("COLUMN_NAME").toString().split(",");
			for (String column : columns) {
				for (Column columnData : columnList) {
					if (column.equals(columnData.getName())) {
						index.getColumns().add(columnData);
					}
				}
			}
			result.add(index);
		}
		return result;
	}

	public CodeJdbcDao getCodeJdbcDao() {
		return codeJdbcDao;
	}

}
