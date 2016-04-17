package com.linkcm.demo.repository.school.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.dao.BaseJdbcDao;
import com.linkcm.core.dao.BaseRepositoryImpl;
import com.linkcm.demo.entity.ClassInfo;
import com.linkcm.demo.repository.school.ClassInfoRepositoryCustom;

public class ClassInfoRepositoryImpl extends BaseRepositoryImpl<ClassInfo, Long> implements ClassInfoRepositoryCustom {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	@Override
	public List<?> findAllClassName() {
		return baseJdbcDao.findBySql("select classname as classname from classinfo");
	}

}
