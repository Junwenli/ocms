package com.linkcm.demo.service.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.demo.entity.ClassInfo;
import com.linkcm.demo.entity.Student;
import com.linkcm.demo.repository.school.ClassInfoRepository;

@Component
@Transactional
public class ClassInfoService extends CoreService<ClassInfo, Long> {

	@Autowired
	private ClassInfoRepository classInfoRepository;

	protected void beforeSave(ClassInfo entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "className", entity.getClassname())) {
			throw new ServiceException("班级名称已存在");
		}
	}

	protected void beforeDelete(ClassInfo entity) {
		if (isEndow(Student.class, "classId", entity.getId())) {
			throw new ServiceException("班级下已有学生，不能删除");
		}
	}

	@Override
	protected BaseRepository<ClassInfo, Long> getDao() {
		return classInfoRepository;
	}

	@Override
	public String getModule() {
		return "班级管理";
	}
	
}
