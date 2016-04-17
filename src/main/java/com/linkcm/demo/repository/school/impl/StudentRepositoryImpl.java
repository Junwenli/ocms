package com.linkcm.demo.repository.school.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linkcm.core.dao.BaseJdbcDao;
import com.linkcm.core.dao.BaseRepositoryImpl;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.demo.entity.Student;
import com.linkcm.demo.entity.type.SexType;
import com.linkcm.demo.repository.school.StudentRepositoryCustom;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long> implements StudentRepositoryCustom {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	@Override
	public Page<?> findStudent(Pageable pageable, List<QueryParam> filters) {
		String sql = "select student_id as id,name as name,age as age,sex as sexDesc from student where sex=?";
		return baseJdbcDao.findPageBySql(pageable, sql, filters, SexType.male.value);
	}

}
