package com.linkcm.core.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseJdbcDao;

@Component
@Transactional
public class TestUtils {
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	@ErrorMessage("执行SQL出错")
	public void executeSql(String... sql) {
		baseJdbcDao.getJdbcTemplate().batchUpdate(sql);
	}
}
