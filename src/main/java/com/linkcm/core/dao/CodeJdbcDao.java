package com.linkcm.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linkcm.core.entity.sys.Column;
import com.linkcm.core.entity.sys.QueryParam;

public interface CodeJdbcDao {
	
	public Page<Map<String, Object>> findByPage(Pageable pageable, final List<QueryParam> filters) ;

	public List<Column> findColumn(String table) ;

	public List<Map<String, Object>> findIndex(String table);

}
