package com.linkcm.demo.repository.school;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.demo.entity.ClassInfo;

public interface ClassInfoRepository extends BaseRepository<ClassInfo, Long>, ClassInfoRepositoryCustom {

	public ClassInfo findByClassname(String className);

	public List<ClassInfo> findByClassnameAndTeacher(String className, String teacher);

	@Query("select c.classname from ClassInfo c where c.classname=? and c.teacher=? ")
	public List<String> findClassInfo(String className, String teacher);

}
