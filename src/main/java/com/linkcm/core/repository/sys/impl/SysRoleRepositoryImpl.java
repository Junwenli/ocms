package com.linkcm.core.repository.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.dao.BaseJdbcDao;
import com.linkcm.core.dao.BaseRepositoryImpl;
import com.linkcm.core.entity.sys.SysRole;
import com.linkcm.core.repository.sys.SysRoleRepositoryCustom;

public class SysRoleRepositoryImpl extends BaseRepositoryImpl<SysRole,Long>
		implements SysRoleRepositoryCustom {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	@Override
	public void abc() {
		baseJdbcDao.getJdbcTemplate();

	}
}
